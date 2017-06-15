package com.cmb.zh.Util;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommonVal {
	
	public static final String SESSION_KEY_USER = "user";
	public static final String SESSION_KEY_ONLINE = "online";
	
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	public static enum ErrorCode {
		OK("OK", 0),
		
		ERROR_UNKOWN_ERROR		("未知错误", 		-20000),
		
		ERROR_EMAIL_INVALID		("邮箱账号不合法", 	-10000),
		ERROR_PWD_INVALID		("密码不合法",		-10001),
		ERROR_USER_NAME_INVALID	("用户名不合法",	-10002),
		
		ERROR_PWD_INCORRECT		("密码错误", 		-10100),
		ERROR_USER_NOT_EXIST	("该用户不存在", 	-10101),
		ERROR_USER_EXISTS		("该用户已存在", 	-10102),
		
		ERROR_CONNECT_DB		("连接数据库出错", 	-10200),
		ERROR_WRITEDB_FAIL		("写入数据库出错",	-10201),
		
		ERROR_PARAM_INVALID		("参数错误", 		-10900)
		;
		
		private String errorInfo;
		private int errorCode;
		
		private ErrorCode(String str, int errorCode) {
			this.errorInfo = str;
			this.errorCode = errorCode;
		}
		
		public int getErrorCode(){
			return errorCode;
		}
		
		public String getErrorInfo(){
			return errorInfo;
		}
		
//		@Override
//		public String toString() {
//			return this.errorInfo;
//		}
	}
}
