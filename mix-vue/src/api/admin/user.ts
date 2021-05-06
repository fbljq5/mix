import { Request } from '@/utils/request'

export function logout() {
    return Request.axiosInstance({
        url: 'http://127.0.0.1:8502/mix-admin/user/logout',
        method: 'post'
    })
}