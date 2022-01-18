import Vue from 'vue';
import Router from 'vue-router';
import Home from '../views/Home.vue';
import Wordlist from '../views/Wordlist.vue';
import AbsoluteBeginners from '../views/AbsoluteBeginners';
import Elementary from '../views/Elementary.vue';
import Intermediate from '../views/Intermediate';
import Advanced from '../views/Advanced';
import LessonDetails from '../components/LessonDetails.vue';
import Discussion from '../components/Discussion.vue';
import Transcript from '../components/Transcript.vue';
import Vocabulary from '../components/Vocabulary.vue';
import Favorites from '../views/Favorites.vue';
import Login from '../views/Login.vue';
import Auth from '@okta/okta-vue';
import config from '../config/config';

Vue.use(Router);
Vue.use(Auth, config.oidc);

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      component: Home
    },
    { path: '/implicit/callback', component: Auth.handleCallback() },
    {
      path: '/favorites',
      component: Favorites,
      meta: {
        requiresAuth: true
      }
    },
    { path: '/wordlist', component: Wordlist },
    { path: '/lessons-absolute-beginners', component: AbsoluteBeginners },
    { path: '/lessons-elementary', component: Elementary },
    { path: '/lessons-intermediate', component: Intermediate },
    { path: '/lessons-advanced', component: Advanced },
    { path: '/login', name: 'login', component: Login },
    {
      path: '/lesson/:id',
      component: LessonDetails,
      props: true,
      children: [
        { path: '', redirect: 'discussion' },
        { path: 'discussion', component: Discussion, props: true },
        { path: 'transcript', component: Transcript },
        { path: 'vocabulary', component: Vocabulary }
      ]
    }
  ]
});

router.beforeEach(async(to, from, next) => {
  if (to.matched.some(route => route.meta.requiresAuth) && !(await Vue.prototype.$auth.isAuthenticated())) {
    // Navigate to custom login page
    next({ path: '/login' });
  } else {
    next();
  }
});

export default router;
