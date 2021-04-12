package cn.liangjq.mix.auth.security.filter;

import cn.liangjq.mix.common.base.constant.BaseConstant;
import cn.liangjq.mix.utils.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description: 鉴权过滤器
 * @Author: liangjianqiang
 * @Date: 2021/3/25
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 在过滤之前和之后执行的事件
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(BaseConstant.HEADER_TOKEN_KEY);
        // 若请求头中没有Authorization信息直接放行
        if (tokenHeader == null) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }

    /**
     * 从token中获取用户信息并新建一个token
     *
     * @param tokenHeader 字符串形式的Token请求头
     * @return 带用户名和密码以及权限的Authentication
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        // 从Token中解密获取用户名
        String username = JWTUtils.getUserName(tokenHeader);
        // 从Token中解密获取用户角色
        String role = JWTUtils.getUserRole(tokenHeader);

        if (StringUtils.isBlank(username) || StringUtils.isBlank(role) || "[]".equals(role)) {
            return null;
        }

        // 将[ROLE_XXX,ROLE_YYY]格式的角色字符串转换为数组
        String[] roles = StringUtils.strip(role, "[]").split(", ");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String s : roles) {
            authorities.add(new SimpleGrantedAuthority(s));
        }
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        return null;
    }
}