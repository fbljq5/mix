package cn.liangjq.mix.common.result;


import cn.liangjq.mix.common.constant.Constants;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 统一响应信息
 * @Author: liangjq
 * @Date: 2021/4/14
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 默认成功编码
    public static final int SUCCESS = Constants.SUCCESS;
    // 默认成功字符串
    private static final String DEFAULT_OK_MSG = "成功";

    // 默认失败编码
    public static final int ERROR = Constants.ERROR;
    // 默认失败字符串
    private static final String DEFAULT_ERROR_MSG = "失败";


    private int code;

    private String message;

    private T result;

    public static <T> R<T> ok() {
        return restResult(null, SUCCESS, DEFAULT_OK_MSG);
    }

    public static <T> R<T> ok(T result) {
        return restResult(result, SUCCESS, DEFAULT_OK_MSG);
    }

    public static <T> R<T> ok(T result, String message) {
        return restResult(result, SUCCESS, message);
    }

    public static <T> R<T> fail() {
        return restResult(null, ERROR, DEFAULT_ERROR_MSG);
    }

    public static <T> R<T> fail(String message) {
        return restResult(null, ERROR, message);
    }

    public static <T> R<T> fail(T result) {
        return restResult(result, ERROR, DEFAULT_ERROR_MSG);
    }

    public static <T> R<T> fail(T result, String message) {
        return restResult(result, ERROR, message);
    }

    public static <T> R<T> fail(int code, String message) {
        return restResult(null, code, message);
    }

    private static <T> R<T> restResult(T result, int code, String message) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setResult(result);
        apiResult.setMessage(message);
        return apiResult;
    }

}
