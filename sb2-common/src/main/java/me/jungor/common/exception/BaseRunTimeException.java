package me.jungor.common.exception;

import lombok.NonNull;
import me.jungor.common.constant.BaseRestCode;

/**
 * 通用业务异常
 *
 * @implNote 子类有参构造都委托给BaseRunTimeException(@ NonNull Enum code)，由该构造统一做NonNull校验。
 */
public class BaseRunTimeException extends RuntimeException {

	/**
	 * 业务枚举code 该枚举定义在自身业务包中
	 */
	private final Enum code;


	public Enum getCode() {
		return code;
	}


	public BaseRunTimeException() {
		this.code = BaseRestCode.S500;
	}


	public BaseRunTimeException(@NonNull Enum code) {
		this.code = code;
	}


	public BaseRunTimeException(@NonNull Enum code, String msg) {
		super(msg);
		this.code = code;
	}

}
