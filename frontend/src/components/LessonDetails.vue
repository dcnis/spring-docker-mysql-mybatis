<template>
  <div class="removePadding">
    <div v-if="loading">Lesson loading..</div>
    <div v-else>
      <div class="removePadding">
      <podcast-player
        :file="currentLesson.audio"
        :title="currentLesson.title"
        :level="currentLesson.difficulty.description"
        :thumbnail="currentLesson.thumbnail">
      </podcast-player>
      </div>
    <v-container>
    <v-layout justify-end>
        <v-btn text icon color="pink" v-on:click="toggleLike">
          <v-icon v-bind:disabled="!(userLesson.liked)">mdi-heart</v-icon>
        </v-btn>
    </v-layout>
    </v-container>
    <router-view :lesson="currentLesson"></router-view>
    <br><br>
    <v-bottom-navigation fixed :value="e31" grow color="teal">
        <v-btn to="discussion">
          <span>Discussion</span>
          <v-icon>feedback</v-icon>
        </v-btn>
      <v-btn to="transcript">
        <span>Transcript</span>
        <v-icon>clear_all</v-icon>
      </v-btn>
      <v-btn to="vocabulary">
        <span>Vocabulary</span>
        <v-icon>translate</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </div>
  </div>
</template>

<script>
import axios from 'axios';
import constants from '../config/constants';
import updateLikeMixin from './mixins/updateLikeMixin';
import PodcastPlayer from './PodcastPlayer';

export default {
  mixins: [updateLikeMixin],
  props: ['id'],
  data() {
    return {
      e3: 0,
      e31: true,
      file: '',
      currentLesson: {},
      userLesson: {},
      loading: true
    };
  },
  components: {
    PodcastPlayer
  },
  methods: {
    toggleLike() {
      if (this.$store.state.authenticated) {
        if (this.userLesson.liked) {
          this.unlikeLesson();
        } else {
          this.likeLesson();
        }
      } else {
        this.redirectLogin();
      }
    },
    likeLesson() {
      this.userLesson.liked = true;
      updateLikeMixin.methods.updateUserLessonInDatabase(this.id, true);
    },
    unlikeLesson() {
      this.userLesson.liked = false;
      updateLikeMixin.methods.updateUserLessonInDatabase(this.id, false);
    },
    async redirectLogin() {
      await this.$router.push({ path: '/login' });
    },
    audioFinish() {
    },
    getLesson() {
      axios.get(constants.url.GET_LESSON + this.id)
        .then(response => {
          // set current lesson
          this.currentLesson = response.data;
          if (this.$store.state.authenticated) {
            this.getMatchingUserLesson();
          }
        })
        .finally(() => (this.loading = false));
    },
    getMatchingUserLesson() {
      var body = {
        email: this.$store.state.user.email,
        lessonId: this.id
      };
      axios.post(constants.url.GET_SINGLE_USER_LESSON, body)
        .then(response => {
          // set userLesson
          this.userLesson = response.data;
        });
    }
  },
  created() {
    this.getLesson();
  }
};
</script>
