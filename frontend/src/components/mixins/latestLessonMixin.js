import store from '../../store/index';

export const latestLessonMixin = {
  methods: {
    updateAll(lessonId) {
      if (store.state.authenticated) {
        store.dispatch('updateLessonTimestamp', lessonId)
          .then(response => {
            if (response.data === 0) {
              // there was no matching lesson in latestLessonsOfUser
              // therefore add lesson to latestLessonsOfUser
              store.dispatch('addLatestLessonsOfUser', lessonId);
            }
          });
      }
    }
  }
};
