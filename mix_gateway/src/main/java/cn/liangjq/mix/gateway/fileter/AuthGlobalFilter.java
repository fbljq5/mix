package cn.liangjq.mix.gateway.fileter;

import com.alibaba.fastjson.JSONObject;
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

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        //非校验接口，用户必须已获得token
        if (!antPathMatcher.match("/**/login/**", path)) {
            List<String> tokenList = request.getHeaders().get("token");
            if (null == tokenList) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response);
            } else {
                // TODO 测试只需要带token则放行
                Boolean isCheck = true;
//                Boolean isCheck = JWTUtils.checkToken(tokenList.get(0));
                if (!isCheck) {
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
        message.put("data", "鉴权失败");
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}