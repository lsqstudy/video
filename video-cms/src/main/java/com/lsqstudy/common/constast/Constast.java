package com.lsqstudy.common.constast;

/**
 * 常量接口
 *
 */
public interface Constast {

	String USER_LOGIN_ERROR_MSG = "用户名或密码不正确";
	String USER_LOGIN_CODE_ERROR_MSG ="验证码不正确";

	/**
	 * 登录session的kdy
	 */
	String USER_LOGIN_TOKEN ="Admin_TOKEN";

	/**
	 * 可用状态
	 */
	Integer AVAILABLE_TRUE = 1;
	Integer AVAILABLE_FALSE = 0;
	
	/**
	 * 用户类型
	 */
	Integer USER_TYPE_SUPER = 1;
	Integer USER_TYPE_NORMAL= 2;
	
	/**
	 * 是否展开
	 */
	Integer SPREAD_TRUE = 1;
	Integer SPREAD_FALSE = 0;
	
	
	/**
	 * 操作状态
	 */
	String ADD_SUCCESS="添加成功";
	String ADD_ERROR="添加失败";
	
	String UPDATE_SUCCESS="更新成功";
	String UPDATE_ERROR="更新失败";
	
	String DELETE_SUCCESS="删除成功";
	String DELETE_ERROR="删除失败";
	
	String RESET_SUCCESS="重置成功";
	String RESET_ERROR="重置失败";
	
	String DISPATCH_SUCCESS="分配成功";
	String DISPATCH_ERROR="分配失败";
	
	String AUTHORIZED_SUCCESS="授权成功";
	String AUTHORIZED_ERROR="授权失败";
	
	String LOGIN_EXPIRE="登录已过期，请重新登录！";
	
	Integer CODE_SUCCESS=0; //操作成功
	Integer CODE_ERROR=-1;//失败
	
	
	/**
	 * 公用常量
	 */
	Integer CODE_ZERO = 0;
	Integer CODE_ONE = 1;
	Integer CODE_TWO = 2;
	Integer CODE_THREE = 3;
	
	/**
	 * 未登录：403
	 */
	Integer CODE_UNLOGIN =403;
	String UNLOGIN_MSG ="未登录！";
	/**
	 * 未授权：405
	 */
	Integer CODE_UNAUTHORIZED =405;
	String UNAUTHORIZED_MSG ="权限不足!";
	/**
	 * 默认密码配置
	 */
	String USER_DEFAULT_PWD = "123456";
	

}
