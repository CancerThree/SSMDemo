package com.cmb.zh.Util;

public class CommonException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonVal.ErrorCode errorCode = null;
	private Throwable cause = null;
	
	public CommonException(){
		super();
	}
	
	public CommonException(CommonVal.ErrorCode error){
		super();
		this.errorCode = error;
	}
	
	public CommonException(CommonVal.ErrorCode error, Throwable cause){
		super();
		this.errorCode = error;
		this.cause = cause;
	}
	
	public CommonException (Throwable cause){
		super();
		this.cause = cause;
	}
	
	public Throwable getCause(){
		return cause;
	}
	
	public CommonVal.ErrorCode getErrorCode(){
		if (errorCode == null){
			return CommonVal.ErrorCode.ERROR_UNKOWN_ERROR;
		}
		return errorCode;
	}
	
	public String getMessage(){
		return getErrorCode().toString();
	}
}
