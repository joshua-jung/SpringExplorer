package me.jungor.common.constant;


public enum BaseRestCode {

	S200("SUCCESS"),
	S400("参数不正确"),
//	S401("没有登陆"),
//	S402("第一次登录，要修改密码"),
//	S403("无权限"),
//	S404("资源不存在"),
//	S405("http method不支持"),
//	S409("参数冲突"),
//	S408("内部程序之外的异常 主要是第三方资源连接  如 mysql,redis统一抛出该错误 如果业务需要特殊处理,开发人员捕获包装成业务异常"),
	S500("业务错误");


	private String desc;

	BaseRestCode(String desc) {
		this.desc = desc;
	}

	/**
	 * 子error code自己实现
	 */
	public String toString() {
		return super.name();
	}

}
