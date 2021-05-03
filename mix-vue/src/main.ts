import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import { Request } from '@/utils/request'
import VueAxios from 'vue-axios'

createApp(App)
    .use(store)
    .use(router)
    .use(Antd)
    .use(VueAxios, Request.init())
    .mount('#app')
