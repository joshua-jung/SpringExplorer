package me.jungor.common.exception;

import me.jungor.common.constant.BaseRestCode;

/**
 * S500 业务异常基类
 */
public class BusinessException extends BaseRunTimeException {

    public BusinessException() {
        super(BaseRestCode.S500);
    }


    public BusinessException(String msg) {
        super(BaseRestCode.S500, msg);
    }


    public BusinessException(Enum code) {
        super(code);
    }


}
