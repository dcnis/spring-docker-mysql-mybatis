<template>
    <div>
        <p>Elementary</p>
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
      level: 2
    };
  }
};
</script>
