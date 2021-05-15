import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios';
import { message, notification } from 'ant-design-vue';
export class Request {

    public static axiosInstance: AxiosInstance;

    public static init() {
        this.axiosInstance = axios.create({
            baseURL: process.env.VUE_APP_SERVER,
            timeout: 60000
        });
        this.initInterceptors();
        return axios;

    }

    public static initInterceptors() {
        // 设置Post请求头
        this.axiosInstance.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
        /**
        * 请求拦截器
        * 每次请求前，如果存在token则在请求头中携带token
        */
        this.axiosInstance.interceptors.request.use(
            (config: AxiosRequestConfig) => {
                // 登录流程控制中，根据本地是否存在token判断用户的登录情况
                // 但是即使token存在，也有可能token是过期的，所以在每次的请求头中携带token
                // 后台根据携带的token判断用户的登录情况，并返回给我们对应的状态码
                const token = localStorage.getItem('MIX_TOKEN');
                if (token) {
                    config.headers['mix-token'] = token;
                }
                return config
            },
            (error: any) => {
                console.log(error)
            }
        );

        // 响应拦截器
        this.axiosInstance.interceptors.response.use(
            (response: AxiosResponse) => {
                if (response.status == 200) {
                    return response;
                } else {
                    Request.errorHandler(response);
                    return response;
                }
            },
            (error: any) => {
                console.log(error);
                const { response } = error;
                if (response) {
                    // 请求已发出，但是不在2xx的范围
                    Request.errorHandler(response);
                    return Promise.reject(response.data);
                } else {
                    // 处理断网的情况
                    // eg:请求超时或断网时，更新state的network状态
                    // network状态在app.vue中控制着一个全局的断网提示组件的显示隐藏
                    // 关于断网组件中的刷新重新获取数据，会在断网组件中说明
                    message.warn('网络连接异常,请稍后再试!');
                }
            }
        );
    }

    private static errorHandler(res: any) {
        console.log(res)
        switch (res.status) {
            case 401:
                break;
            case 403:
                break;
            case 404:
                message.warn("请求资源不存在");
                break;
            default:
                message.warn('连接错误');
        }
    }

}