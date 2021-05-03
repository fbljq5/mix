import { Request } from '@/utils/request'

export function login(parameter: any) {
    return Request.axiosInstance({
        url: 'http://127.0.0.1:8502/mix-auth/auth/login',
        method: 'post',
        data: parameter
    })
}