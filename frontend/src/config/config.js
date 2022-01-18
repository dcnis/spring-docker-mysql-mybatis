const { VUE_APP_OKTA_CLIENT_ID, VUE_APP_OKTA_ISSUER, VUE_APP_OKTA_REDIRECT_URI } = process.env;

export default {
  oidc: {
    clientId: VUE_APP_OKTA_CLIENT_ID,
    issuer: VUE_APP_OKTA_ISSUER,
    redirectUri: VUE_APP_OKTA_REDIRECT_URI,
    scopes: ['openid', 'profile', 'email'],
    pkce: true
  }
};
