package cn.liangjq.mix.auth.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Description: 验证过滤器
 * @Author: liangjq
 * @Date: 2021/3/25
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 验证操作 接收并解析用户凭证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 从输入流中获取到登录的信息
        try {
            String json = getRequestJsonString(request);
            JSONObject jsonObject = JSONObject.parseObject(json);
            String username = (String) jsonObject.get("username");
            String password = (String) jsonObject.get("password");
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证【失败】调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        String returnData = "";
        // 账号过期
        if (failed instanceof AccountExpiredException) {
            returnData = "账号过期";
        }
        // 密码错误
        else if (failed instanceof BadCredentialsException) {
            returnData = "用户不存在或密码不正确";
        }
        // 密码过期
        else if (failed instanceof CredentialsExpiredException) {
            returnData = "密码过期";
        }
        // 账号不可用
        else if (failed instanceof DisabledException) {
            returnData = "账号不可用";
        }
        //账号锁定
        else if (failed instanceof LockedException) {
            returnData = "账号锁定";
        }
        // 用户不存在
        else if (failed instanceof InternalAuthenticationServiceException) {
            returnData = "用户不存在或密码不正确";
        }
        // 其他错误
        else {
            returnData = "未知异常";
        }

        // 处理编码方式 防止中文乱码
        response.setContentType("text/json;charset=utf-8");
        // 将反馈塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(returnData));
    }

    /**
     * 从请求中获得json字符串
     *
     * @param request
     * @return
     * @throws IOException
     */
    private String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    /**
     * 获得request中的字符串
     *
     * @param request
     * @return
     * @throws IOException
     */
    private byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 获得请求中的json字符串
     *
     * @param request
     * @return
     * @throws IOException
     */
    private String getRequestJsonString(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8").replaceAll("%22", "\"");
            // POST
        } else {
            return getRequestPostStr(request);
        }
    }
}