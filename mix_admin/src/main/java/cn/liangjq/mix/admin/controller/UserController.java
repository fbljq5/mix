package cn.liangjq.mix.admin.controller;

import cn.liangjq.mix.common.config.JwtConfig;
import cn.liangjq.mix.common.dto.LoginVO;
import cn.liangjq.mix.common.entity.User;
import cn.liangjq.mix.common.result.R;
import cn.liangjq.mix.admin.service.IUserService;
import cn.liangjq.mix.utils.JWTUtils;
import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;
    private final JwtConfig jwtConfig;

    @GetMapping("/info")
    public R getUserInfo(HttpServletRequest request) {
        String tokenStr = request.getHeader(jwtConfig.getTokenHeader());
        try {
            if (StringUtils.isBlank(tokenStr) || JWTUtils.checkToken(tokenStr, jwtConfig.getSecret())) {
                return R.fail("没有提供token或者token无效");
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return R.fail("token无效");
        }
        // 根据token获取用户信息
        User userInfo = userService.getUserByName(JWTUtils.getUserName(tokenStr, jwtConfig.getSecret()));
        return R.ok(userInfo);
    }

}
