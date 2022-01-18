<template>
    <div>
        <p>{{lesson.discussion}}</p>
    </div>
</template>

<script>

export default {
  props: ['lesson'],
  data() {
    return {
      currentLesson: null
    };
  }
};
</script>
