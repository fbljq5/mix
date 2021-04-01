package cn.liangjq.mix.api.fallback;

import cn.liangjq.mix.api.service.RemoteAuthService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteHelloServiceFallbackFactory implements FallbackFactory<RemoteAuthService> {

    @Override
    public RemoteAuthService create(Throwable throwable) {
        RemoteAuthServiceFallbackImpl remoteFallback = new RemoteAuthServiceFallbackImpl();
        remoteFallback.setCause(throwable);
        return remoteFallback;
    }
}