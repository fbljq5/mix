package cn.liangjq.mix.admin.exception;

import cn.liangjq.mix.common.result.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @Description: 全局异常处理器
 * @Author: liangjq
 * @Date: 2021/5/19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String failMessage = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> {
                    return String.format("[%s]\n", fieldError.getDefaultMessage());
                }).collect(Collectors.joining());
        return R.fail(failMessage);
    }

    @ExceptionHandler(OperationException.class)
    @ResponseBody
    public R<String> handlerOperationException(OperationException e) {
        String failMessage = e.getMessage();
        return R.fail(failMessage);
    }
}