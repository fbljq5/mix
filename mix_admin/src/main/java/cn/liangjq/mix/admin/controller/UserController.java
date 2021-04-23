package cn.liangjq.mix.admin.controller;

import cn.liangjq.mix.admin.service.IUserService;
import cn.liangjq.mix.common.config.JwtConfig;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.entity.vo.UserVO;
import cn.liangjq.mix.common.result.R;
import cn.liangjq.mix.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ： liangjq
 * @description ： 用户对外接口
 * @date ： 2021/4/22
 */
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final JwtConfig jwtConfig;

    @GetMapping("/info")
    public R<UserVO> getUserInfo(HttpServletRequest request) {
        String tokenStr = request.getHeader(jwtConfig.getTokenHeader());
        // 根据token获取用户信息
        UserVO userInfo = userService.getUserByName(JWTUtils.getUserName(tokenStr, jwtConfig.getSecret()));
        return R.ok(userInfo);
    }

}
