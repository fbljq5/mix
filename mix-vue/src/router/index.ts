import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'

const routes: Array<RouteRecordRaw> = [

  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/Login.vue')
  },

  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/Home.vue')
  },

  
  {
    path: '/welcome',
    name: 'welcome',
    component: () =>
    import(
      /* webpackChunkName: "dashboard-welcome" */ '@/components/layout/layout.vue'
    )
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
  console.log(token)
  if (!token && to.path !== '/login') {
    next('/login')
  } else {
    next()
  }
})

export default router
