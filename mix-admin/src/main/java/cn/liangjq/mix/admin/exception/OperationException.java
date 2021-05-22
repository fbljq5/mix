package cn.liangjq.mix.admin.exception;

/**
 * @author ： liangjianqiang
 * @description ： 自定义操作异常
 * @date ： 2021/5/22
 */
public class OperationException extends RuntimeException {

    private String code;
    private String msg;

    public OperationException() {
        super();
    }

    public OperationException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface.getCode());
        this.code = errorInfoInterface.getCode();
        this.msg = errorInfoInterface.getMsg();
    }

    public OperationException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getCode(), cause);
        this.code = errorInfoInterface.getCode();
        this.msg = errorInfoInterface.getMsg();
    }

    public OperationException(String errorMsg) {
        super(errorMsg);
        this.msg = errorMsg;
    }

    public OperationException(String errorCode, String errorMsg) {
        super(errorCode);
        this.code = errorCode;
        this.msg = errorMsg;
    }

    public OperationException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.code = errorCode;
        this.msg = errorMsg;
    }


    public String getErrorCode() {
        return code;
    }

    public void setErrorCode(String errorCode) {
        this.code = errorCode;
    }

    public String getErrorMsg() {
        return msg;
    }

    public void setErrorMsg(String errorMsg) {
        this.msg = errorMsg;
    }

    public String getMessage() {
        return msg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
