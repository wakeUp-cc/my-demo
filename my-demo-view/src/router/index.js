import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/login', name: '登录', component: () => import('@/views/login')},
    {
      // 嵌套路由,配合标签页使用
      path: '/',
      name: '标签页',
      component: () => import('@/components/table'),
      children: [
        {path: 'index', name: '首页', component: () => import('@/views/index')},
        {path: 'member', name: '会员管理', component: () => import('@/views/member/member')},
        {path: 'menuView', name: '菜单管理', component: () => import('@/views/menu/menu')},
        {path: 'user', name: '用户管理', component: () => import('@/views/user/user')}
      ]
    }
  ]
})
