package cn.liangjq.mix.common.base.constant;

/**
 * @Description:
 * @Author: liangjianqiang
 * @Date: 2021/4/7
 */
public class BaseConstant {
    /**
     * header 中token的key
     */
    public static final String HEADER_TOKEN_KEY = "MIX_TOKEN";

    /**
     * 登录用户的token
     */
    public final static String LOGIN_TOKEN_KEY = "LOGIN_TOKEN_KEY";

    /**
     * 用户id
     */
    public static final String USERID = "userId";

    /**
     * 用户名
     */
    public static final String USER_NAME = "userName";

    /**
     * 角色列表
     */
    public static final String ROLE = "roles";

    /**
     * 默认过期时间(10分钟)
     */
    public static final long EXPIRED_PERIOD = 1000 * 60 * 1;

    /**
     * token 生成盐
     */
    public static final String SECRET = "MIX_SEC";

}
