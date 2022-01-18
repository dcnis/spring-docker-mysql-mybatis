<template>
    <div>
        <p>Advanced</p>
        <lesson-list v-bind:level="level"></lesson-list>
    </div>
</template>

<script>
import LessonList from '../components/LessonList.vue';

export default {
  components: {
    LessonList
  },
  data() {
    return {
      level: 4
    };
  }
};
</script>
