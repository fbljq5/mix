import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Login from '../views/Login.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

//全局路由守卫登录跳转
router.beforeEach((to, from, next) => {
  document.title = `${to.meta.title}`
  const role = localStorage.getItem('userName')
  if (!role && to.path !== '/login') {
    next('/login')
  } else {
    next()
  }
})

export default router
