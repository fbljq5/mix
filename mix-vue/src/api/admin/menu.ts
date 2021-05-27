import {Request} from '@/utils/request'

/**
 * 获得菜单列表
 * @param userId
 */
export function listMenu(roleId: any) {
    return Request.axiosInstance({
        url: '/mix-admin/menu/list/' + roleId,
        method: 'post'
    })
}
