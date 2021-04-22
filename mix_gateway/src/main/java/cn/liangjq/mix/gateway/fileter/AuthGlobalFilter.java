package cn.liangjq.mix.gateway.fileter;

import cn.liangjq.mix.common.config.JwtConfig;
import cn.liangjq.mix.common.constant.Constants;
import cn.liangjq.mix.utils.JWTUtils;
import cn.liangjq.mix.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Description: 网关全局过滤器
 * @Author: liangjq
 * @Date: 2021/4/12
 */
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final RedisUtil redisUtil;
    private final JwtConfig jwtConfig;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        //非校验接口，用户必须已获得token
        if (!antPathMatcher.match("/**/auth/login/**", path)) {
            List<String> tokenList = request.getHeaders().get(jwtConfig.getTokenHeader());
            if (null == tokenList) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response);
            } else {
                String token = tokenList.get(0);
                String tokenRedis = redisUtil.get(token);
                if (null == tokenRedis) {
                    ServerHttpResponse response = exchange.getResponse();
                    return out(response);
                }
                // 判断token中数据
                Boolean isExpired = true;
                try {
                    isExpired = JWTUtils.isExpired(tokenList.get(0),jwtConfig.getSecret());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                if (isExpired) {
                    ServerHttpResponse response = exchange.getResponse();
                    return out(response);
                }
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> out(ServerHttpResponse response) {
        JSONObject message = new JSONObject();
        message.put("success", false);
        message.put("code", 28004);
        message.put("data", "鉴权失败，请检查token是否有效");
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}