import {Request} from '@/utils/request'

/**
 * 获得角色列表
 * @param userId
 */
export function getRoleList(userId: any) {
    return Request.axiosInstance({
        url: '/mix-admin/role/list',
        method: 'post',
        params: userId
    })
}

/**
 * 分页查询角色
 * @param rolePageRequest
 */
export function pageRole(rolePageRequest: any) {
    return Request.axiosInstance({
        url: '/mix-admin/role/page',
        method: 'post',
        data: rolePageRequest
    })
}

/**
 * 新增角色
 * @param roleAddDTO
 */
export function addRole(roleAddDTO: any) {
    return Request.axiosInstance({
        url: '/mix-admin/role/add',
        method: 'post',
        data: roleAddDTO
    })
}

/**
 * 删除角色
 * @param roleId
 */
export function deleteRole(roleId: number) {
    return Request.axiosInstance({
        url: '/mix-admin/role/delete/' + roleId,
        method: 'post'
    })
}

/**
 * 更新角色
 * @param updateDTO
 */
export function updateRole(updateDTO: any) {
    return Request.axiosInstance({
        url: '/mix-admin/role/update',
        method: 'post',
        data: updateDTO
    })
}

/**
 * 分配菜单
 * @param roleAssignMenusDTO
 */
export function assignMenus(roleAssignMenusDTO: any) {
    return Request.axiosInstance({
        url: '/mix-admin/role/assignMenus',
        method: 'post',
        data: roleAssignMenusDTO
    })
}

