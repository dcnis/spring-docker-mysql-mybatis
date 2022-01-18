import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import userdata from './modules/userdata';
import constants from '../config/constants';

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    userdata
  },
  state: {
    lessonsByDifficulty: [],
    latestLessons: [],
    authenticated: false,
    user: null,
    likedUserLessons: []
  },
  getters: {
    getAuthenticationState: state => state.isAuthenticated
  },
  mutations: {
    updateTimestamp(state, lessonId) {
      state.lessons
        .filter(item => item.id === lessonId)
        .map(lesson => (lesson.lastTimeWatched = new Date().toISOString()));
    },
    deleteUserData(state) {
      state.user = {};
      state.latestLessons = [];
      state.latestLessons = [];
    },
    setLatestLessonsOfUser(state, latestLessons) {
      state.latestLessons = latestLessons;
    },
    addNewLessonToLatestLessonsOfUser(state, lessonId) {

    },
    setAuthentication(state, authStatus) {
      state.authenticated = authStatus;
    },
    setUserData(state, user) {
      state.user = user;
    },
    setLessonsByDiffculty(state, lessons) {
      state.lessonsByDifficulty = lessons;
    },
    setLessonTimestamp(state, lessonId, newTimestamp) {
      if (state.latestLessons) {
        state.latestLessons
          .filter(lesson => lesson.id === lessonId)
          .map(lesson => (lesson.lastSeen = newTimestamp));
      }
    },
    setLikedUserLessons(state, likedUserLessons) {
      state.likedUserLessons = likedUserLessons;
    }
  },
  actions: {
    async getLatestLessonsOfUser(context) {
      const accessToken = await Vue.prototype.$auth.getAccessToken();
      var requestBody = { email: this.state.user.email, limit: 5 };
      axios.defaults.headers.common.Authorization = `Bearer ${accessToken}`;
      var response = await axios.post(
        constants.url.GET_USER_LESSONS,
        requestBody
      );
      context.commit('setLatestLessonsOfUser', response.data.content);
    },
    addLatestLessonsOfUser(context, lessonId) {
      // make POST request to DB
      var newDate = new Date().toISOString();

      var url = constants.url.ADD_LATEST_LESSON_OF_USER +
        '?email=' + this.state.user.email +
        '&lessonId=' + lessonId +
        '&lastSeen=' + newDate;

      axios.post(url);

      // add lesson to state (I think I dont need it because it will get fetched anyways)
    },
    updateLessonTimestamp(context, lessonId) {
      // set new Date
      var newTimestamp = new Date().toISOString();

      // make post request to DB
      var bodyWithTimestamp = {
        lessonId: lessonId,
        lastSeen: newTimestamp,
        email: this.state.user.email
      };

      return new Promise((resolve, reject) => {
        axios.post(constants.url.UPDATE_USER_LESSON, bodyWithTimestamp)
          .then(response => {
            // update state if there was a DB UPDATE row
            if (response === 1) {
              context.commit('setLessonTimestamp', lessonId, newTimestamp);
            }
            resolve(response);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    deleteUserData(context) {
      context.commit('deleteUserData');
    },
    fetchAuthentication(context, authStatus) {
      context.commit('setAuthentication', authStatus);
      if (authStatus && !this.state.user) {
        store.dispatch('getUserdata');
      }
    },
    async getUserdata(context) {
      var response = await Vue.prototype.$auth.getUser();
      context.commit('setUserData', response);
      store.dispatch('getLatestLessonsOfUser');
    },
    async fetchLessonsByDiffculty(context, level) {
      axios.defaults.headers.common.Authorization = `Bearer ${await Vue.prototype.$auth.getAccessToken()}`;
      const searchedLevel = {
        difficulty: level
      };
      axios.post(constants.url.FIND_LESSONS_BY_DIFFICULTY, searchedLevel)
        .then(res => {
          context.commit('setLessonsByDiffculty', res.data);
        });
    },
    async getLikedUserLessons(context) {
      var user = await Vue.prototype.$auth.getUser();

      var body = {
        email: user.email,
        liked: true
      };

      axios.post(constants.url.GET_USER_LESSONS, body)
        .then(response => {
          context.commit('setLikedUserLessons', response.data.content);
        });
    }
  }
});

export default store;
