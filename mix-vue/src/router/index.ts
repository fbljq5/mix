import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [

  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/Login.vue'),
  },

  {
    path: '/',
    component: () => import('@/components/layout/layout.vue'),
    redirect: '/dashboard',
    name: '首页',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/Dashboard.vue'),
        meta: { title: '主页' }
      },
      {
        path: 'welcome',
        component: () => import('@/views/Welcome.vue'),
        meta: { title: '欢迎' }
      }]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

//全局路由守卫登录跳转
router.beforeEach((to, from, next) => {
  document.title = `${to.meta.title}`
  const token = localStorage.getItem('MIX_TOKEN')
  if (!token && to.path !== '/login') {
    next('/login')
  } else {
    next()
  }
})

export default router
