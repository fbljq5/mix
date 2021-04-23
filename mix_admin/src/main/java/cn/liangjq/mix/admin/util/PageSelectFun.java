package cn.liangjq.mix.admin.util;

import java.util.List;

/**
 * @Description: PageSelectFun 函数式接口
 * @Author: liangjq
 * @Date: 2021/4/23
 */
@FunctionalInterface
public interface PageSelectFun {
    /**
     * @return 函数式接口
     */
    List<?> queryList();
}