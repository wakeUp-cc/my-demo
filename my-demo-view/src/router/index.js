import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/login', component: () => import('@/views/login')},
    {
      // 嵌套路由,配合标签页使用
      path: '/',
      component: () => import('@/components/table'),
      children: [
        {path: 'index', component: () => import('@/views/index')},
        {path: 'member', component: () => import('@/views/member/member')},
        {path: 'menuView', component: () => import('@/views/menu/menu')},
        {path: 'user', component: () => import('@/views/user/user')}
      ]
    }
  ]
})
