// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import http from './api/http.js'
Vue.prototype.$http = http

import moment from 'moment'
Vue.prototype.$moment = moment;

import math from 'mathjs'
Vue.prototype.$math = math;

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
