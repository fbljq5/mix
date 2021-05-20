import {Request} from '@/utils/request'

export function listRole(userId: any) {
    return Request.axiosInstance({
        url: '/mix-admin/role/list',
        method: 'post',
        params: userId
    })
}

