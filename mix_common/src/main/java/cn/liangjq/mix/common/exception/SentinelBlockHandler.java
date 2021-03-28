package cn.liangjq.mix.common.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/** 
 * @Description: sentinel 异常处理类
 * 
 * @Author: liangjianqiang
 * 
 * @Date: 2021/3/28 
 */ 
public class SentinelBlockHandler {

    public static String handleException(BlockException blockException) {
        return "sentinel exception";
    }

    public static String handleError() {
        return "sentinel error";
    }
}