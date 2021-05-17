import { Request } from '@/utils/request'

export function checkToken() {
    return Request.axiosInstance({
        url: '/mix-admin/user/checkToken',
        method: 'post'
    })
}

export function logout() {
    return Request.axiosInstance({
        url: '/mix-admin/user/logout',
        method: 'post'
    })
}

export function getUserInfo() {
    return Request.axiosInstance({
        url: '/mix-admin/user/info',
        method: 'get'
    })
}

export function getUserList(parameter: any) {
    return Request.axiosInstance({
        url: "/mix-admin/user/list",
        method: "post",
        data: parameter
    })
}

export function deleteUser(id: any) {
    return Request.axiosInstance({
        url: "/mix-admin/user/delete/" + id,
        method: "post"
    })
}