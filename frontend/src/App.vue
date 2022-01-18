<template>
  <v-app light id="popup">
    <v-navigation-drawer
      v-model="drawer"
      app
      >
      <v-list dense nav>
        <v-list-item to="/">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-group prepend-icon="layers" value="true">
          <template v-slot:activator>
              <v-list-item-title >Chinese Lessons</v-list-item-title>
          </template>

          <v-list-item to="/lessons-absolute-beginners">
            <v-list-item-action></v-list-item-action>
            <v-list-item-title>Absolute Beginners</v-list-item-title>
            <v-icon>child_care</v-icon>
          </v-list-item>

          <v-list-item to="/lessons-elementary">
            <v-list-item-action></v-list-item-action>
            <v-list-item-title>Elementary</v-list-item-title>
            <v-icon>spa</v-icon>
          </v-list-item>

          <v-list-item to="/lessons-intermediate">
            <v-list-item-action></v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>Intermediate</v-list-item-title>
            </v-list-item-content>
            <v-icon>star</v-icon>
          </v-list-item>

          <v-list-item to="/lessons-advanced">
            <v-list-item-action></v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>Advanced</v-list-item-title>
            </v-list-item-content>
            <v-icon>school</v-icon>
          </v-list-item>
        </v-list-group>

        <v-list-item to="/favorites">
          <v-list-item-action>
            <v-icon>star</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Favorites</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="/wordlist">
          <v-list-item-action>
            <v-icon>g_translate</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Wordlist</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar color="#86E0C8" dark fixed app>
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
      <div class="search-wrapper">
        <input type="text" v-model="searchTerm" placeholder="Search lesson.." id="searchInput">
      </div>
      <v-btn outlined right fixed v-if="!isLoggedIn" to="/login" id="login-button"> Login </v-btn>
      <v-spacer></v-spacer>
          <v-menu v-if="isLoggedIn" fixed right>
            <template v-slot:activator="{ on }">
              <v-btn
                dark
                icon
                v-on="on"
              >
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </template>

            <v-list>
              <v-list-item
                v-for="(item, i) in items"
                :key="i"
                @click="logout"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
    </v-app-bar>
    <v-content>
      <v-container fluid>
        <router-view v-if="searchTerm.length == 0"></router-view>
        <div v-else>
          <v-list two-line>
                <template v-for="lesson in searchResult">
                  <v-list-item :key="lesson.id" :to="'/lesson/' + lesson.id" @click="performClick(lesson.id)">
                    <v-list-item-avatar>
                      <img :src="lesson.thumbnail">
                    </v-list-item-avatar>
                    <v-list-item-content>
                      <v-list-item-title v-html="lesson.title"></v-list-item-title>
                      <v-list-item-subtitle v-html="lesson.difficulty.description"></v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-list>
        </div>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import axios from 'axios';
import constants from './config/constants';
import { latestLessonMixin } from './components/mixins/latestLessonMixin';

const searchTimeout = 500;

export default {
  name: 'App',
  props: {
    source: String,
    lessons: []
  },
  mixins: [
    latestLessonMixin
  ],
  data() {
    return {
      drawer: null,
      authenticated: false,
      items: [
        { title: 'Logout' }
      ],
      searchTerm: '',
      timeout: null,
      searchResult: null
    };
  },
  created() {
    this.isAuthenticated();
  },
  methods: {
    async isAuthenticated() {
      this.$store.dispatch('fetchAuthentication', await this.$auth.isAuthenticated());
    },
    async logout() {
      await this.$auth.logout();
      await this.isAuthenticated();
      this.$router.push({ path: './' });
      this.$store.dispatch('deleteUserData');
    },
    initSearchBar() {
      const input = document.getElementById('searchInput');
      let timeout = null;
      input.addEventListener('keyup', () => {
        clearTimeout(timeout);
        if (this.searchTerm.length > 2) {
          timeout = setTimeout(() => {
            var body = {
              searchTerm: this.searchTerm
            };

            axios.post(constants.url.SEARCH_LESSON, body)
              .then(response => {
                this.searchResult = response.data;
              });
          }, searchTimeout);
        }
      });
    },
    performClick(lessonId) {
      this.searchTerm = '';
      this.searchResult = null;
      latestLessonMixin.methods.updateAll(lessonId);
    }
  },
  computed: {
    isLoggedIn() {
      return this.$store.state.authenticated;
    }
  },
  watch: {
    // Everytime the route changes, check for auth status
    $route: 'isAuthenticated'
  },
  mounted() {
    this.initSearchBar();
  }
};
</script>

<style>
.primary--text {
    color: #43E0B6 !important;
    caret-color: #43E0B6 !important;
}

.search-wrapper {
  color: white;
}

.search-wrapper input {
   padding: 4px;
}

.search-wrapper input:focus {
  outline: none;
  transform: scale(1.05);
}

::placeholder {
  color: white;
}

</style>
