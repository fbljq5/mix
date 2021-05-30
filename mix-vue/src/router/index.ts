import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import {checkToken} from '@/api/admin/user'
import Login from '@/views/login/index.vue'
import Dashboard from '@/views/dashboard/index.vue'
import Layout from '@/components/layout/layout.vue'
import User from '@/views/admin/user.vue'
import Role from '@/views/admin/role.vue'
import Menu from '@/views/admin/menu.vue'

const routes: Array<RouteRecordRaw> = [

    {
        path: '/login',
        name: 'login',
        component: Login,
        meta: {title: '登录'}
    },

    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        name: '首页',
        children: [
            {
                path: 'dashboard',
                component: Dashboard,
                meta: {title: '主页'}
            }]
    },

    {
        path: '/admin',
        component: Layout,
        redirect: '/auth/user',
        name: '权限管理',
        meta: {title: '权限管理'},
        children: [
            {
                path: 'user',
                name: '用户管理',
                component: User,
                meta: {title: '用户管理'}
            },
            {
                path: 'role',
                name: '角色管理',
                component: Role,
                meta: {title: '角色管理'}
            },
            {
                path: 'menu',
                name: '菜单管理',
                component: Menu,
                meta: {title: '菜单管理'}
            }]
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

//全局路由守卫登录跳转
router.beforeEach(async (to, from, next) => {
    document.title = `${to.meta.title}`
    const token = localStorage.getItem('MIX_TOKEN')
    let checkFlag = false;
    // 校验token 是否有效
    await checkToken().then((response) => {
        const data = response.data
        if (data.code != 200) {
            checkFlag = true
        }
    });
    console.log(checkFlag)
    if ((!token || checkFlag) && to.path !== '/login') {
        next('/login')
    } else {
        next()
    }
})

export default router
