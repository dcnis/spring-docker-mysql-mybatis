import axios from 'axios';
import constants from '../../config/constants';
import store from '../../store/index';

export default {
  methods: {
    async updateUserLessonInDatabase(lessonId, liked) {
      const bodyWithLike = {
        email: store.state.user.email,
        lessonId: lessonId,
        liked: liked
      };

      axios.post(constants.url.UPDATE_USER_LESSON, bodyWithLike)
        .then();
    }
  }
};
