import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
  routes: [
    // meta中添加不需要登录验证的标识
    {path: '/login', meta: {noRequireAuth: true}, name: '登录', component: () => import('@/views/login')},
    {
      // 嵌套路由,配合标签页使用
      path: '/',
      name: '标签页',
      component: () => import('@/components/table'),
      children: [
        {path: 'index', name: '首页', component: () => import('@/views/index')},
        {path: 'menuView', name: '菜单管理', component: () => import('@/views/menu/menu')},
        {path: 'user', name: '用户管理', component: () => import('@/views/user/user')}
      ]
    }
  ]
})

// 解决路由重复跳转报错问题
const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch((err) => err)
}

export default router
// 登录拦截
router.beforeEach((to, from, next) => {
  // 判断该路由是否需要登录权限
  if (to.meta.noRequireAuth) {
    next()
  } else {
    // 判断本地是否存在token
    if (sessionStorage.getItem('token')) {
      next()
    } else {
      // 未登录,跳转到登陆页面
      next('/login')
    }
  }
})
