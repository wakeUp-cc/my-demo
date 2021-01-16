// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import http from './api/http.js'
import moment from 'moment'
import * as math from 'mathjs'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import md5 from 'js-md5'
import '@/styles/style.css'

Vue.use(ElementUI)
Vue.prototype.$http = http
Vue.prototype.$moment = moment
Vue.prototype.$math = math
Vue.prototype.$md5 = md5

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
