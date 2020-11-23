package com.lsqstudy.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsqstudy.common.util.DataResult;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.Role;
import com.lsqstudy.system.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import java.util.Set;

/**
 * 页面跳转控制器 作用：路由
 *
 */
@Controller
@RequestMapping("/system")
public class SysController {
	@Autowired
	private IAdministratorService administratorService;

	/**
	 * 跳转菜单管理
	 */
	@RequestMapping("/toMenuManager.html")
	public String toMenuManager() {

		return "/system/menu/menuManager";
	}

	/**
	 * 跳转菜单管理左边的的菜单树页面
	 */
	@RequestMapping("/toMenuLeft.html")
	public String toMenuLeft() {

		return "/system/menu/menuLeft";
	}

	/**
	 * 跳转菜单管理右边的菜单列表
	 */
	@RequestMapping("/toMenuRight.html")
	public String toMenuRight() {

		return "/system/menu/menuRight";
	}

	/**
	 * 跳转角色管理页面
	 */
	@RequestMapping("/toRoleManager.html")
	public String toRoleManager() {

		return "/system/role/roleManager";
	}
	/**
	 * 跳转权限管理页面
	 */
	@RequestMapping("/toPermissionManager.html")
	public String toPermissionManager() {
		
		return "/system/permission/permissionManager";
	}

	/**
	 * 跳转管理员管理页面
	 */
	@RequestMapping("/toAdministratorManager.html")
	public String toAdministratorManager() {

		return "/system/administrator/administratorManager";
	}

	/**
	 * 跳转日志管理页面
	 */
	@RequestMapping("/toadministratorLoginLogManager.html")
	public String toAdministratorLogManager() {
		return "/system/administratorLoginLog/administratorLoginLogManager";
	}

	/**
	 * 跳转公告管理页面
	 */
	@RequestMapping("/toNewsManager.html")
	public String toNewsManager() {

		return "/system/news/newsManager";
	}
	/**
	 * 跳转个人资料页面
	 */
	@RequestMapping("/toPersonalInfo.html")
	public ModelAndView topersonalInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();

		DataResult blogResult = administratorService.getAdministratorByToken(request, response);
		Administrator administrator = (Administrator) blogResult.getData();

		//清除角色
		Set<Role> roles = administrator.getRoles();
		if (roles.size()>0){
			administrator.getRoles().clear();
		}

		if (null != administrator) {
			String json = JSON.toJSONString(administrator);
			mv.addObject("administrator", json);
		}
		mv.setViewName("/system/administrator/administratorInfo");
		
		return mv;
	}
	
	/**
	 * 跳转个人资料页面
	 */
	@RequestMapping("/toChangePwd.html")
	public ModelAndView changePwd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		DataResult blogResult = administratorService.getAdministratorByToken(request, response);
		Administrator administrator = (Administrator) blogResult.getData();
		if (null != administrator) {
			mv.addObject("administrator", administrator);
		}
		
		mv.setViewName("/system/administrator/changePwd");
		
		return mv;
	}

	/**
	 * 跳转404页面
	 */
	@RequestMapping("/404.html")
	public String errorPage(HttpServletRequest request, HttpServletResponse response) {
		
		return "/system/errorPage/404";
	}
}
