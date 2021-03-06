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

import javax.validation.Valid;
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
    public R<PageResponse<RolePageDTO>> pageRoleInfo(@Valid @RequestBody RoleSearchDTO request) {
        return roleService.pageRole(request);
    }

    @PostMapping("/list")
    @ApiOperation("获得所有角色信息（带有是否拥有角色布尔值）")
    public R<List<RoleListDTO>> listRoleInfo(@Valid Long userId) {
        return roleService.listRole(userId);
    }

    @GetMapping("/find/{roleId}")
    @ApiOperation("查找角色信息")
    public R<RoleUpdateDTO> updateRole(@Valid @PathVariable("roleId") Long roleId) {
        return roleService.findRole(roleId);
    }

    @PostMapping("/add")
    @ApiOperation("新增角色")
    public R<String> addRole(@Valid @RequestBody RoleAddDTO roleAddDTO) {
        return roleService.addRole(roleAddDTO);
    }

    @PostMapping("/delete/{roleId}")
    @ApiOperation("删除角色")
    public R<String> deleteRole(@Valid @PathVariable("roleId") Long roleId) {
        return roleService.deleteRole(roleId);
    }

    @PostMapping("/update")
    @ApiOperation("更新角色信息")
    public R<String> updateRole(@Valid @RequestBody RoleUpdateDTO updateDTO) {
        return roleService.updateRole(updateDTO);
    }

    @PostMapping("/switchStatus")
    @ApiOperation("切换角色状态")
    public R<String> switchStatus(@Valid @RequestBody RoleSwitchStatusDTO roleSwitchStatusDTO) {
        return roleService.switchStatus(roleSwitchStatusDTO);
    }

    @PostMapping("/assignMenus")
    @ApiOperation("分配菜单")
    public R<String> assignMenus(@Valid @RequestBody RoleAssignMenusDTO roleAssignMenusDTO) {
        return roleService.assignMenus(roleAssignMenusDTO);
    }
}
