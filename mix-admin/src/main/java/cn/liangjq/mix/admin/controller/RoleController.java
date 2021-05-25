package cn.liangjq.mix.admin.controller;

import cn.liangjq.mix.admin.service.IRoleService;
import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.role.*;
import cn.liangjq.mix.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ： liangjq
 * @description ： 角色对外接口
 * @date ： 2021/4/25
 */
@RestController
@RequestMapping("/role")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "角色管理接口")
public class RoleController {

    private final IRoleService roleService;

    @PostMapping("/page")
    @ApiOperation("分页获得角色信息")
    public R<PageResponse<RoleVO>> pageRoleInfo(@RequestBody RolePageRequest request) {
        return roleService.pageRole(request);
    }

    @PostMapping("/list")
    @ApiOperation("获得所有角色信息（带有是否拥有角色布尔值）")
    public R<List<RoleListDTO>> listRoleInfo(Long userId) {
        return roleService.listRole(userId);
    }

    @GetMapping("/find/{roleId}")
    @ApiOperation("查找角色信息")
    public R<RoleUpdateDTO> updateRole(@PathVariable("roleId") Long roleId) {
        return roleService.findRole(roleId);
    }

    @PostMapping("/add")
    @ApiOperation("新增角色")
    public R<String> addRole(@RequestBody RoleAddDTO roleAddDTO) {
        return roleService.addRole(roleAddDTO);
    }

    @PostMapping("/delete/{roleId}")
    @ApiOperation("删除角色")
    public R<String> deleteRole(@PathVariable("roleId") Long roleId) {
        return roleService.deleteRole(roleId);
    }

    @PostMapping("/update")
    @ApiOperation("更新角色信息")
    public R<String> updateRole(@RequestBody RoleUpdateDTO updateDTO) {
        return roleService.updateRole(updateDTO);
    }

    @PostMapping("/assignMenus")
    @ApiOperation("分配菜单")
    public R<String> assignMenus(@RequestBody RoleAssignMenusDTO roleAssignMenusDTO) {
        return roleService.assignMenus(roleAssignMenusDTO);
    }
}
