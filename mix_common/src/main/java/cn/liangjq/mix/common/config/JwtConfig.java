package cn.liangjq.mix.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: jwt配置
 * @Author: liangjianqiang
 * @Date: 2021/4/22
 */
@ConfigurationProperties(prefix = "jwt.config")
@Component
@Data
public class JwtConfig {
    private String tokenHeader;
    private String secret;
    private String name;
    private Long expiresSecond;
}