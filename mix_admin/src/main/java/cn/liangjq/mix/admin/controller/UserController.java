package cn.liangjq.mix.admin.controller;

import cn.liangjq.mix.admin.service.IUserService;
import cn.liangjq.mix.common.config.JwtConfig;
import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.UserRequest;
import cn.liangjq.mix.common.dto.UserVO;
import cn.liangjq.mix.common.result.R;
import cn.liangjq.mix.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ： liangjq
 * @description ： 用户对外接口
 * @date ： 2021/4/22
 */
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "用户管理接口")
public class UserController {

    private final IUserService userService;
    private final JwtConfig jwtConfig;

    @GetMapping("/info")
    @ApiOperation("获得登录用户信息")
    public R<UserVO> getUserInfo(HttpServletRequest request) {
        String tokenStr = request.getHeader(jwtConfig.getTokenHeader());
        // 根据token获取用户信息
        UserVO userInfo = userService.getUserByName(JWTUtils.getUserName(tokenStr, jwtConfig.getSecret()));
        return R.ok(userInfo);
    }

    @PostMapping("/list")
    @ApiOperation("分页获得用户信息")
    public R<PageResponse> pageUserInfo(@RequestBody UserRequest request) {
        return userService.pageUser(request);
    }

}
