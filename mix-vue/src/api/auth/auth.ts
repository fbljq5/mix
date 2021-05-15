import { Request } from '@/utils/request'

export function login(parameter: any) {
    return Request.axiosInstance({
        url: '/mix-auth/auth/login',
        method: 'post',
        data: parameter
    })
}
