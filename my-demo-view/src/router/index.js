import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', component: () => import('@/views/login')},
    {path: '/index', component: () => import('@/views/index')},
    {path: '/member', component: () => import('@/views/member/member')},
    {path: '/menu', component: () => import('@/views/menu/menu')}
  ]
})
