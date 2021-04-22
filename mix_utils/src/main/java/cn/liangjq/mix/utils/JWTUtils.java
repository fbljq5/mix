package cn.liangjq.mix.utils;

import cn.liangjq.mix.common.constant.Constants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;


/**
 * @Description: JWT 相关的工具方法
 * @Author: liangjq
 * @Date: 2021/4/7
 */
@Slf4j
public class JWTUtils {

    /**
     * 创建token
     *
     * @param userName
     * @param roles
     * @param expireSeconds
     * @param secret
     * @return
     */
    public static String createToken(String userName, String roles, Long expireSeconds, String secret) {
        // 输入数据校验
        if (StringUtils.isBlank(userName)
                || StringUtils.isBlank(roles)) {
            return null;
        }

        return JWT.create()
                .withClaim(Constants.USER_NAME, userName)
                .withClaim(Constants.ROLE, roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireSeconds * 1000))
                // 加个随机值防止token重复
                .withClaim("RANDOM", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 判断token 是否已经过期
     *
     * @param tokenStr
     * @param secret
     * @return
     * @throws Exception
     */
    public static boolean isExpired(String tokenStr, String secret) throws Exception {
        if (StringUtils.isBlank(tokenStr)) {
            return true;
        }
        DecodedJWT jwt = JWTUtils.verify(tokenStr, secret);
        return jwt.getExpiresAt().compareTo(new Date()) < 0;
    }


    /**
     * 解密后返回
     *
     * @param tokenStr
     * @return
     */
    private static DecodedJWT verify(String tokenStr, String secret) throws Exception {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT verify = jwtVerifier.verify(tokenStr);
        if (null == verify) {
            throw new RuntimeException();
        }
        return verify;
    }

    /**
     * 从给定的token字符串中获得用户名称
     *
     * @param tokenStr
     * @return
     */
    public static String getUserName(String tokenStr, String secret) {
        if (StringUtils.isBlank(tokenStr)) {
            return null;
        }
        DecodedJWT jwt = null;
        try {
            jwt = JWTUtils.verify(tokenStr, secret);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return jwt.getClaim(Constants.USER_NAME).asString();
    }

    /**
     * 从给定的token字符串中获得用户角色
     *
     * @param tokenStr
     * @return
     */
    public static String getUserRole(String tokenStr, String secret) {
        if (StringUtils.isBlank(tokenStr)) {
            return null;
        }
        DecodedJWT jwt = null;
        try {
            jwt = JWTUtils.verify(tokenStr, secret);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return jwt.getClaim(Constants.ROLE).asString();
    }
}
