/* eslint-disable no-tabs */
/* eslint-disable no-tabs */
<template>
    <div>
      <div v-if="loading"></div>
      <div v-else class="podcastPlayer">
        <div>
          <h3>{{this.title}}</h3>
          <span class="level">{{this.level}}</span>
        </div>
        <div class="buttons">
            <v-icon x-large class="button" @click.native="startFromBeginning">skip_previous</v-icon>
            <v-icon x-large class="button" @click.native="skipSeconds(-10)">replay_10</v-icon>
            <a class="button play" v-on:click="playAudio()" title="Play/Pause Song">
                <transition name="slide-fade" mode="out-in">
                    <v-icon class="button" v-if="paused || firstPlay" size="55" key="play">play_circle_filled</v-icon>
                    <v-icon class="button" v-else size="55" key="pause">pause_circle_filled</v-icon>
                </transition>
            </a>
            <v-icon x-large class="button" @click.native="skipSeconds(10)">forward_10</v-icon>
            <v-icon x-large class="button">skip_next</v-icon>
        </div>
        <div class="currentTimeContainer">
          <span class="currentTime">{{audio.currentTime | formatTime}}</span>
          <span class="totalTime">{{audio.duration | formatTime}}</span>
        </div>
        <v-slider
          track-color="white"
          v-model="sliderValue"
          @click="setTrackTime(sliderValue)"
          :max="trackDuration">
          </v-slider>
        </div>
    </div>
</template>

<script>
const timeOffset = 100000;

export default {
  name: 'podcast-player',
  props: {
    file: {
      type: String,
      default: null
    },
    title: {
      type: String,
      default: null
    },
    level: {
      type: String,
      default: null
    },
    thumbnail: {
      type: String,
      default: null
    }
  },
  data() {
    return {
      firstPlay: true,
      loading: true,
      playing: false,
      paused: false,
      audio: undefined,
      sliderValue: 0,
      trackDuration: 0
    };
  },
  methods: {
    startFromBeginning() {
      this.audio.currentTime = 0;
    },
    setTrackTime(val) {
      this.audio.currentTime = val / timeOffset;
    },
    skipSeconds(seconds) {
      this.audio.currentTime = (this.audio.currentTime * timeOffset + (seconds * timeOffset)) / timeOffset;
    },
    playAudio() {
      this.firstPlay = false;
      if (this.playing) {
        this.pauseAudio();
      } else {
        this.paused = false;
        this.audio.play();
        this.playing = true;
      }
    },
    pauseAudio() {
      this.paused = true;
      this.playing = false;
      this.audio.pause();
    },
    mute() {
      this.isMuted = !this.isMuted;
      this.audio.muted = this.isMuted;
      this.volumeValue = this.isMuted ? 0 : 75;
    },
    loadData() {
      this.trackDuration = this.audio.duration * timeOffset;
      this.loading = false;
    },
    updateSlider() {
      this.sliderValue = this.audio.currentTime * timeOffset;
      if (this.audio.ended) {
        this.audio.currentTime = 0;
        this.sliderValue = 0;
        this.audio.pause();
        this.paused = true;
        this.playing = false;
      }
    },
    init() {
      this.audio.addEventListener('loadeddata', this.loadData);
      this.audio.addEventListener('timeupdate', this.updateSlider);
    }
  },
  mounted() {
    this.audio = new Audio(this.file);
    this.init();
  },
  filters: {
    formatTime(s) {
      return (s - (s %= 60)) / 60 + (s > 9 ? ':' : ':0') + parseInt(s);
    }
  },
  beforeDestroy() {
    this.audio.pause();
    this.audio.removeEventListener('timeupdate', this.loadData);
    this.audio.removeEventListener('loadeddata', this.updateSlider);
  }
};

</script>

<style scoped>

.podcastPlayer{
    background-color: rgb(243, 242, 189);
    background-image: linear-gradient(to right, #9ca5f5, #7ff5ae);
    border-bottom-left-radius: 8px;
    border-bottom-right-radius: 8px;
    box-shadow: 5px 5px 10px -4px #63645e;
    padding: 20px;
    margin: -12px -12px 0px -12px;
    overflow: hidden;
}

.podcastPlayer .buttons{
    text-align: center;
    margin: 20px auto;
}

.playerButtons {
position: relative;
margin: 0 auto;
margin-bottom: 1.5rem;
text-align: center;
}

.button {
    color: rgba(0, 0, 0, 0.75);
    border-radius: 50%;
    margin-top: 10px;
    outline: 0;
    text-decoration: none;
    cursor: pointer;
    transition: 0.5s;
}

.button.play{
    margin: 0 1.5rem;
}

.button:active{
    opacity: 0.75;
    transform: scale(0.75);
}

.level{
  color: white;
}

.currentTimeContainer{
  width: 100%;
  height: 1rem;
  display: flex;
  justify-content: space-between;

}

.currentTimeContainer .totalTime{
  font-size: 1rem;
  font-family: monospace;
}

.currentTimeContainer .currentTime{
  font-size: 1rem;
  font-family: monospace;
}

</style>
