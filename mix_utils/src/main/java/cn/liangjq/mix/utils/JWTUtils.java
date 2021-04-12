package cn.liangjq.mix.utils;

import cn.liangjq.mix.common.base.constant.BaseConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.Date;


/**
 * @Description: JWT 相关的工具方法
 * @Author: liangjianqiang
 * @Date: 2021/4/7
 */
@Slf4j
public class JWTUtils {


    /**
     * 从请求中获得token串
     *
     * @param request
     * @return
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        if (null == request) {
            return null;
        }
        return request.getHeader(BaseConstant.HEADER_TOKEN_KEY);
    }

    /**
     * 生成token
     *
     * @param key         毫秒数前缀
     * @param userId      生成用户id
     * @param mileSeconds token有效毫秒数
     * @return token字符串
     */
    public static String createToken(String key, Long userId, String userName, Long mileSeconds) {
        // 输入数据校验
        if (!StringUtils.hasText(key)
                || null == userId
                || null == mileSeconds) {
            return null;
        }
        long finalMileSeconds = System.currentTimeMillis() + mileSeconds;
        return JWT.create()
                .withClaim(BaseConstant.USERID, userId)
                .withExpiresAt(new Date(finalMileSeconds))
                // 加个随机值防止token重复
                .withClaim("RANDOM", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(BaseConstant.SECRET));
    }

    /**
     * 创建token
     *
     * @param userName
     * @param roles
     * @return
     */
    public static String createToken(String userName, String roles) {
        // 输入数据校验
        if (!StringUtils.hasText(userName)
                || !StringUtils.hasText(roles)) {
            return null;
        }
        System.out.println(new Date(System.currentTimeMillis() + BaseConstant.EXPIRED_PERIOD));
        return JWT.create()
                .withClaim(BaseConstant.USER_NAME, userName)
                .withClaim(BaseConstant.ROLE, roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + BaseConstant.EXPIRED_PERIOD))
                // 加个随机值防止token重复
                .withClaim("RANDOM", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(BaseConstant.SECRET));
    }

    /**
     * 创建token
     *
     * @param userName
     * @param roles
     * @param expireMileseconds
     * @return
     */
    public static String createToken(String userName, String roles, Long expireMileseconds) {
        // 输入数据校验
        if (!StringUtils.hasText(userName)
                || !StringUtils.hasText(roles)) {
            return null;
        }

        return JWT.create()
                .withClaim(BaseConstant.USER_NAME, userName)
                .withClaim(BaseConstant.ROLE, roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireMileseconds))
                // 加个随机值防止token重复
                .withClaim("RANDOM", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(BaseConstant.SECRET));
    }

    /**
     * 判断token 是否应景过期
     *
     * @param tokenStr
     * @return
     */
    public static boolean isExpire(String tokenStr) throws Exception {
        DecodedJWT jwt = JWTUtils.verify(tokenStr);
        Date expiresAt = jwt.getExpiresAt();
        System.out.println(expiresAt);
        return jwt.getExpiresAt().compareTo(new Date()) >= 0;
    }


    /**
     * 解密后返回
     *
     * @param tokenStr
     * @return
     */
    private static DecodedJWT verify(String tokenStr) throws Exception {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(BaseConstant.SECRET)).build();
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
    public static String getUserName(String tokenStr) {
        if (!StringUtils.hasText(tokenStr)) {
            return null;
        }
        DecodedJWT jwt = null;
        try {
            jwt = JWTUtils.verify(tokenStr);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return jwt.getClaim(BaseConstant.USER_NAME).asString();
    }

    /**
     * 从给定的token字符串中获得用户角色
     *
     * @param tokenStr
     * @return
     */
    public static String getUserRole(String tokenStr) {
        if (!StringUtils.hasText(tokenStr)) {
            return null;
        }
        DecodedJWT jwt = null;
        try {
            jwt = JWTUtils.verify(tokenStr);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return jwt.getClaim(BaseConstant.ROLE).asString();
    }

    /**
     * 检查token是否有效
     *
     * @param tokenStr
     * @return
     */
    public static Boolean checkToken(String tokenStr) throws Exception {
        if (!StringUtils.hasText(tokenStr)) {
            return false;
        }
        // 判断是否过期
        return JWTUtils.isExpire(tokenStr);
    }
}
