import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/Home',
    name: 'Home',
    component: Home
  }
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
