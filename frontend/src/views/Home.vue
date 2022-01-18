<template>
  <div>
    <h1>
      <div v-if="isLoggedIn" ><span class="level">Welcome </span> {{userdata && userdata.name}}</div>
      <div v-else ><span class="level">Welcome </span> to Popup Chinese</div>
    </h1>
    <p>Congratulations on finding the world's best community for learning Chinese. If you're just getting started, check out our collection of Chinese podcasts first. These are broken down by difficulty and can be covered in any order: important words and concepts will repeat. Once you understand the basics move on to our collection of Chinese videos, or test yourself with our collection of sample hsk tests. More advanced students are encouraged to check out our archive of manually annotated Chinese short stories.</p>

    <div v-if="isLoggedIn">
    <h2>Your last lessons</h2>

    <v-list two-line>
      <template v-for="entry in usersLatestLessons">
        <v-list-item :key="entry.id" :to="'/lesson/' + entry.lessonId.id" @click.native="updateLatestLessons(entry.lessonId.id)">
          <v-list-item-avatar>
            <img :src="entry.lessonId.thumbnail">
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title>{{entry.lessonId.title}}</v-list-item-title>
            <v-list-item-subtitle class="text--primary" v-text="entry.lessonId.difficulty.description"></v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
    </div>
  </div>
</template>

<script>
import { latestLessonMixin } from '../components/mixins/latestLessonMixin';

export default {
  mixins: [latestLessonMixin],
  data() {
    return {
      latestLessons: {}
    };
  },
  created() {
    if (this.$store.state.authenticated) {
      this.$store.dispatch('getUserdata');
    }
  },
  methods: {
    updateLatestLessons(lessonId) {
      latestLessonMixin.methods.updateAll(lessonId);
    }
  },
  computed: {
    isLoggedIn() {
      return this.$store.state.authenticated;
    },
    userdata() {
      return this.$store.state.user;
    },
    usersLatestLessons() {
      return this.$store.state.latestLessons;
    }
  }
};
</script>

<style scoped>
.level {
  color: #75baa7;
}
</style>
