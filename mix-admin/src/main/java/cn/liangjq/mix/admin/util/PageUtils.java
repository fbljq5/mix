package cn.liangjq.mix.admin.util;

import cn.liangjq.mix.common.dto.PageBaseRequest;
import cn.liangjq.mix.common.dto.PageResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: 分页工具类
 * @Author: liangjq
 * @Date: 2021/4/23
 */
public class PageUtils {

    /**
     * 调用分页插件完成分页 并 封装
     *
     * @param pageRequest 分页请求对象
     * @return 分页返回对象
     */
    public static PageResponse setPageResult(PageBaseRequest pageRequest, PageSelectFun fun) {

        //设置 页码/页距
        Integer page = pageRequest.getPage();
        Integer size = pageRequest.getSize();
        if (null == page || null == size) {
            PageHelper.startPage(0, 0, true, null, true);
        } else {
            PageHelper.startPage(page, size);
        }
        //待插入的代码片段 即要查询的语句
        List<?> select = fun.queryList();

        //查询结果进行包装
        PageInfo<?> pageInfo = new PageInfo<>(select);

        //再将包装后的对象再封装到写好的返回对象 使返回内容更加明了
        PageResponse pageResponse = new PageResponse();
        return setPageResponse(pageInfo, pageResponse);
    }

    private static PageResponse setPageResponse(PageInfo<?> pageInfo, PageResponse pageResponse) {
        pageResponse.setPage(pageInfo.getPageNum());
        pageResponse.setSize(pageInfo.getPageSize());
        pageResponse.setTotalSize(pageInfo.getTotal());
        pageResponse.setTotalPages(pageInfo.getPages());
        pageResponse.setList(pageInfo.getList());
        return pageResponse;
    }
}