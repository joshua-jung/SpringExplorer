package me.jungor.common.exception;


import me.jungor.common.constant.BaseRestCode;

/**
 * S400 参数异常
 */
public class ParameterException extends BaseRunTimeException {

	public ParameterException() {
		super(BaseRestCode.S400);
	}


	public ParameterException(String msg) {
		super(BaseRestCode.S400, msg);
	}

	public ParameterException(Enum code) {
		super(code);
	}

}