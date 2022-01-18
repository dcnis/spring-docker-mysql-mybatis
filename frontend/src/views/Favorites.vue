<template>
  <div>
    <p>Your liked lessons</p>
    <v-list two-line>
      <template v-for="favourite in likedUserLessons">
        <v-list-item :key="favourite.id" thumbnail :to="'/lesson/' + favourite.lessonId.id">
          <v-list-item-avatar>
            <v-img v-bind:src="favourite.lessonId.thumbnail"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-html="favourite.lessonId.title"></v-list-item-title>
            <v-list-item-subtitle itle v-html="favourite.lessonId.difficulty.description"></v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
  </div>
</template>

<script>

export default {
  created() {
    this.$store.dispatch('getLikedUserLessons');
  },
  computed: {
    likedUserLessons() {
      return this.$store.state.likedUserLessons;
    }
  }
};
</script>
