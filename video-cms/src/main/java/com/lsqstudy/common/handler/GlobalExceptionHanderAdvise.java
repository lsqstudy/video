package com.lsqstudy.common.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.lsqstudy.common.util.ResultObj;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSON;

/**
 * 全局异常监控
 *
 */
@RestControllerAdvice
public class GlobalExceptionHanderAdvise {
	/**
	 * 未授权 只要当前项目的代码抛出UnauthorizedException就会回调
	 * @throws IOException 
	 */
	@ExceptionHandler(value = { UnauthorizedException.class })
	public void unauthorized(HttpServletResponse response) throws IOException {
		String json = JSON.toJSONString(ResultObj.UNAUTHORIZED);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
}
