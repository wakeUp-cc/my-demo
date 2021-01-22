import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/login', component: () => import('@/views/login')},
    {
      path: '/',
      component: () => import('@/components/table'),
      children: [
        {path: 'index', component: () => import('@/views/index')},
        {path: 'member', component: () => import('@/views/member/member')},
        {path: 'menu', component: () => import('@/views/menu/menu')}
      ]
    }
  ]
})
