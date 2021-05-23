package cn.liangjq.mix.admin.controller;

import cn.liangjq.mix.common.dto.menu.MenuListDTO;
import cn.liangjq.mix.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ： liangjianqiang
 * @description ： 菜单对外接口
 * @date ： 2021/5/24
 */
@RestController
@RequestMapping("/menu")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "菜单管理接口")
public class MenuController {

    @PostMapping("/list")
    @ApiOperation("获得所有菜单信息（带有是否拥有菜单布尔值）")
    public R<List<MenuListDTO>> listRoleInfo(Long roleId) {
        //TODO
        return null;
    }

}
