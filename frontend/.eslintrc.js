module.exports = {
  root: false,
  env: {
    node: true
  },
  extends: [
    'plugin:vue/essential',
    '@vue/standard'
  ],
  rules: {
    semi: [2, 'always'],
    'space-before-function-paren': ['error', 'never'],
    'linebreak-style': 'off'
  },
  // parserOptions: {
  //   parser: 'babel-eslint'
  // }
};
