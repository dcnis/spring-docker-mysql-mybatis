<template>
  <div>
    <v-list two-line>
      <template v-for="lesson in getLessons">
        <v-list-item :key="lesson.id" :to="'/lesson/' + lesson.id" v-on:click.native="updateLastSeen(lesson.id)">
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
</template>

<script>
import { latestLessonMixin } from './mixins/latestLessonMixin';

export default {
  mixins: [latestLessonMixin],
  props: {
    level: Number
  },
  data() {
    return {
      loading: true
    };
  },
  methods: {
    updateLastSeen(lessonId) {
      latestLessonMixin.methods.updateAll(lessonId);
    }
  },
  created() {
    this.$store.dispatch('fetchLessonsByDiffculty', this.level);
  },
  computed: {
    getLessons() {
      return this.$store.state.lessonsByDifficulty;
    }
  }
};
</script>
