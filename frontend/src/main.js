import Vue from 'vue';
import vuetify from './plugins/vuetify';
import App from './App.vue';
import router from './router';
import store from './store/index';
import authentication from "@/plugins/keycloak-plugin"

Vue.config.productionTip = false;

Vue.use(authentication);

Vue.$keycloak
  .init({ checkLoginIframe: false })
  .then(() => {
    new Vue({
      router,
      store,
      vuetify,
      render: h => h(App)
    }).$mount('#app');
  });




