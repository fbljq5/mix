package cn.liangjq.mix.api.fallback;

import cn.liangjq.mix.api.service.RemoteAuthService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 远程管理验证服务断路器实现
 * @Author: liangjianqiang
 * @Date: 2021/4/22
 */
@Component
public class RemoteAuthServiceFallbackFactory implements FallbackFactory<RemoteAuthService> {

    @Override
    public RemoteAuthService create(Throwable throwable) {
        RemoteAuthServiceFallbackImpl remoteFallback = new RemoteAuthServiceFallbackImpl();
        remoteFallback.setCause(throwable);
        return remoteFallback;
    }
}