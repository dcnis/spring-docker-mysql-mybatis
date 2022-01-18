<template>
    <div>
        <div v-if="loading">Loading..</div>
        <div v-else>
          <v-simple-table :light="false">
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-left">
                <span class="nobreak">
                  <input type="checkbox" id="checkboxPinyin" v-model="hideVocabularyChinese">
                  <label for="checkboxPinyin"> hide chinese</label>
                </span>
              </th>
              <th>
                <span class="nobreak">
                  <input type="checkbox" id="checkboxPinyin" v-model="hideVocabularyPinyin">
                  <label for="checkboxPinyin"> hide pinyin</label>
                </span>
              </th>
              <th class="text-left">
                <span class="nobreak">
                  <input type="checkbox" id="checkboxTranslation" v-model="hideVocabularyTranslation">
                  <label for="checkboxTranslation"> hide translation</label>
                </span>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in vocabulary" :key="item.vocabularyId">
              <td><span v-if="!hideVocabularyChinese" class="chineseFont nobreak">{{item.chinese}}</span></td>
              <td><span v-if="!hideVocabularyPinyin">{{item.pinyin}}</span></td>
              <td><span v-if="!hideVocabularyTranslation">{{item.english}}</span></td>
            </tr>
          </tbody>
        </template>
        </v-simple-table>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      currentLesson: null,
      loading: true,
      vocabulary: [],
      hideVocabularyPinyin: false,
      hideVocabularyTranslation: false,
      hideVocabularyChinese: false
    };
  },
  watch: {
    hideVocabularyPinyin(value) {
      localStorage.hideVocabularyPinyin = value;
      this.hideVocabularyPinyin = value;
    },
    hideVocabularyTranslation(value) {
      localStorage.hideVocabularyTranslation = value;
      this.hideVocabularyTranslation = value;
    },
    hideVocabularyChinese(value) {
      localStorage.hideVocabularyChinese = value;
      this.hideVocabularyChinese = value;
    }
  },
  created() {
    axios.get('https://heroku-popup-chinese-backend.herokuapp.com/getVocabularyByLessonId/' +
          this.$route.params.id)
      .then((response) => {
        this.vocabulary = response.data;
      })
      .finally(() => (this.loading = false));
  },
  mounted() {
    if (localStorage.hideVocabularyPinyin === 'true') {
      this.hideVocabularyPinyin = localStorage.hideVocabularyPinyin;
    }
    if (localStorage.hideVocabularyTranslation === 'true') {
      this.hideVocabularyTranslation = localStorage.hideVocabularyTranslation;
    }
    if (localStorage.hideVocabularyChinese === 'true') {
      this.hideVocabularyChinese = localStorage.hideVocabularyChinese;
    }
  }
};
</script>

<style scoped>
table{
    font-size: 20px;
}
th {
  background-color: #F5F5F5;;
  color: rgba(255,255,255,0.66);
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

td {
  vertical-align: top;
  line-height: 30px;
}

.english {
    color: #757575;
    font-size: 18px;
}
.speaker{
    size: 20%
}

.chineseFont{
    font-family: Tahoma, Arial, Helvetica, "Microsoft YaHei New", "Microsoft Yahei", "微软雅黑", 宋体, SimSun, STXihei, "华文细黑", sans-serif;
    font-size: 1.8em;
}

table tr:hover td {
   background-color: white;
   opacity: 1.0;
}

.nobreak{
    white-space:  nowrap;
}

</style>
