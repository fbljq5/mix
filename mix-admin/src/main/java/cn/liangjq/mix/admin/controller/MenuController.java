package cn.liangjq.mix.admin.controller;

import cn.liangjq.mix.admin.service.IMenuService;
import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.menu.*;
import cn.liangjq.mix.common.dto.role.RoleSearchDTO;
import cn.liangjq.mix.common.dto.role.RolePageDTO;
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
 * @description ： 菜单对外接口
 * @date ： 2021/5/24
 */
@RestController
@RequestMapping("/menu")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "菜单管理接口")
public class MenuController {

    private final IMenuService menuService;

    @PostMapping("/list")
    @ApiOperation("获得所有菜单信息")
    public R<List<MenuListDTO>> listAll() {
        List<MenuListDTO> list = menuService.listAll();
        return R.ok(list);
    }

    @PostMapping("/list/{roleId}")
    @ApiOperation("获得所有菜单信息（带有是否拥有菜单布尔值）")
    public R<List<MenuListDTO>> listAllByRoleId(@PathVariable Long roleId) {
        List<MenuListDTO> list = menuService.listAllByRoleId(roleId);
        return R.ok(list);
    }

    @PostMapping("/page")
    @ApiOperation("分页获得菜单信息")
    public R<List<MenuPageDTO>> pageRoleInfo(@Valid @RequestBody MenuSearchDTO searchDTO) {
        return menuService.pageMenu(searchDTO);
    }

    @PostMapping("/add")
    @ApiOperation("新增菜单信息")
    public R<String> addMenu(@Valid @RequestBody MenuAddDTO menuAddDTO) {
        return menuService.addMenu(menuAddDTO);
    }

    @PutMapping("/update")
    @ApiOperation("修改菜单信息")
    public R<String> addMenu(@Valid @RequestBody MenuUpdateDTO menuUpdateDTO) {
        return menuService.updateMenu(menuUpdateDTO);
    }

    @DeleteMapping("/delete/{menuId}")
    @ApiOperation("删除菜单信息")
    public R<String> deleteMenu(@Valid @PathVariable("menuId") Long menuId) {
        return menuService.deleteMenu(menuId);
    }
}
