package cn.liangjq.mix.api.fallback;

import cn.liangjq.mix.api.service.RemoteAdminService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author ： liangjq
 * @description ： 远程管理数据服务断路器实现
 * @date ： 2021/4/22
 */
@Slf4j
@Component
public class RemoteAdminServiceFallbackFactory implements FallbackFactory<RemoteAdminService> {

    private Logger logger = LoggerFactory.getLogger(RemoteAdminServiceFallbackFactory.class);

    @Override
    public RemoteAdminService create(Throwable cause) {
        logger.error("fallback 原因：" + cause.getMessage());
        return new RemoteAdminServiceFallbackImpl();
    }
}
