package com.lsqstudy.common.util;

import com.lsqstudy.common.constast.Constast;

public class ResultObj {
	
	
	private Integer code=0;
	private String msg;
	
	/**
	 * 添加成功
	 */
	public static final ResultObj ADD_SUCCESS=new ResultObj(Constast.CODE_SUCCESS, Constast.ADD_SUCCESS);
	/**
	 * 添加失败
	 */
	public static final ResultObj ADD_ERROR=new ResultObj(Constast.CODE_ERROR, Constast.ADD_ERROR); 
	/**
	 * 更新成功
	 */
	public static final ResultObj UPDATE_SUCCESS=new ResultObj(Constast.CODE_SUCCESS, Constast.UPDATE_SUCCESS); 
	/**
	 * 更新失败
	 */
	public static final ResultObj UPDATE_ERROR=new ResultObj(Constast.CODE_ERROR, Constast.UPDATE_ERROR); 
	/**
	 * 删除成功
	 */
	public static final ResultObj DELETE_SUCCESS=new ResultObj(Constast.CODE_SUCCESS, Constast.DELETE_SUCCESS); 
	/**
	 * 删除失败
	 */
	public static final ResultObj DELETE_ERROR=new ResultObj(Constast.CODE_ERROR, Constast.DELETE_ERROR); 
	
	/**
	 * 重置成功
	 */
	public static final ResultObj RESET_SUCCESS=new ResultObj(Constast.CODE_SUCCESS, Constast.RESET_SUCCESS); 
	/**
	 * 重置失败
	 */
	public static final ResultObj RESET_ERROR=new ResultObj(Constast.CODE_ERROR, Constast.RESET_ERROR); 
	/**
	 * 分配成功
	 */
	public static final ResultObj DISPATCH_SUCCESS=new ResultObj(Constast.CODE_SUCCESS, Constast.DISPATCH_SUCCESS); 
	/**
	 * 分配失败
	 */
	public static final ResultObj DISPATCH_ERROR=new ResultObj(Constast.CODE_ERROR, Constast.DISPATCH_ERROR); 
	/**
	 * 授权成功
	 */
	public static final ResultObj AUTHORIZED_SUCCESS=new ResultObj(Constast.CODE_SUCCESS, Constast.DISPATCH_SUCCESS); 
	/**
	 * 授权失败
	 */
	public static final ResultObj AUTHORIZED_ERROR=new ResultObj(Constast.CODE_ERROR, Constast.DISPATCH_ERROR); 
	/**
	 * 登录已过期
	 */
	public static final ResultObj LOGIN_EXPIRE=new ResultObj(Constast.CODE_ERROR, Constast.LOGIN_EXPIRE); 
	
	
	/**
	 * 状态码0
	 */
	public static final ResultObj STATUS_TRUE=new ResultObj(Constast.CODE_SUCCESS); 
	/**
	 * 状态码-1
	 */
	public static final ResultObj STATUS_FALSE=new ResultObj(Constast.CODE_ERROR); 

	
	/**
	 * 未登陆：403
	 */
	public static final ResultObj UNLOGIN=new ResultObj(Constast.CODE_UNLOGIN,Constast.UNLOGIN_MSG); 
	/**
	 * 未授权，权限不足：403
	 */
	public static final ResultObj UNAUTHORIZED=new ResultObj(Constast.CODE_UNAUTHORIZED,Constast.UNAUTHORIZED_MSG); 
	
	
	
	public ResultObj(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public ResultObj(Integer code) {
		super();
		this.code = code;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	

}
