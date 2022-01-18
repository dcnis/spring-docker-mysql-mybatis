<template>
  <div class="login">
    <div id="okta-signin-container"></div>
  </div>
</template>

<script>
import OktaSignIn from '@okta/okta-signin-widget';
import '@okta/okta-signin-widget/dist/css/okta-sign-in.min.css';
import config from '../config/config';

export default {
  name: 'Login',
  mounted() {
    this.$nextTick(function() {
      this.widget = new OktaSignIn({
        /**
         * Note: when using the Sign-In Widget for an OIDC flow, it still
         * needs to be configured with the base URL for your Okta Org. Here
         * we derive it from the given issuer for convenience.
         */
        baseUrl: config.oidc.issuer.split('/oauth2')[0],
        clientId: config.oidc.clientId,
        redirectUri: config.oidc.redirectUri,
        logo: require('@/assets/logo.png'),
        i18n: {
          en: {
            'primaryauth.title': 'Sign in to Popup Chinese'
          }
        },
        authParams: {
          pkce: true,
          issuer: config.oidc.issuer,
          display: 'page',
          scopes: config.oidc.scopes
        },
        features: {
          registration: false
        }
      });
      this.widget.renderEl(
        { el: '#okta-signin-container' },
        () => {
          /**
           * In this flow, the success handler will not be called because we redirect
           * to the Okta org for the authentication workflow.
           */
        },
        (err) => {
          throw err;
        }
      );
    });
  },
  destroyed() {
    // Remove the widget from the DOM on path change
    this.widget.remove();
  }
};
</script>
