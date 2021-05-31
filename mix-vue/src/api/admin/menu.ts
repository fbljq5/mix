import {Request} from '@/utils/request'

/**
 * 获得菜单列表
 * @param userId
 */
export function listMenu(menuId: any) {
    return Request.axiosInstance({
        url: '/mix-admin/menu/list/' + menuId,
        method: 'post'
    })
}

/**
 * 分页查询菜单
 * @param menuSearchDTO
 */
export function pageMenu(menuSearchDTO: any) {
    return Request.axiosInstance({
        url: '/mix-admin/menu/page',
        method: 'post',
        data: menuSearchDTO
    })
}

/**
 * 新增
 * @param menuAddDTO
 */
export function addMenu(menuAddDTO: any) {
    return Request.axiosInstance({
        url: '/mix-admin/menu/add',
        method: 'post',
        data: menuAddDTO
    })
}

/**
 * 删除菜单
 * @param menuId
 */
export function deleteMenu(menuId: number) {
    return Request.axiosInstance({
        url: '/mix-admin/menu/delete/' + menuId,
        method: 'post'
    })
}

/**
 * 更新菜单
 * @param updateDTO
 */
export function updateMenu(updateDTO: any) {
    return Request.axiosInstance({
        url: '/mix-admin/menu/update',
        method: 'post',
        data: updateDTO
    })
}
