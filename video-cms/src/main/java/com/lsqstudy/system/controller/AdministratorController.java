package com.lsqstudy.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.ExceptionUtil;
import com.lsqstudy.common.util.ResultObj;
import com.lsqstudy.common.util.DataResult;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.service.IAdministratorService;
import com.lsqstudy.system.vo.AdministratorVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import java.util.List;

@RestController
@RequestMapping("system/administrator")
public class AdministratorController {

	@Autowired
	private IAdministratorService administratorService;

	/**
	 * 加载管理员列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllAdministrator.html")
	public String loadAllAdministrator(AdministratorVo administratorVo) {
		DataGridView result = administratorService.findAdministratorList(administratorVo);

		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 添加管理员
	 */
	@RequiresPermissions(value="sys:add")
	@RequestMapping("/addAdministrator.html")
	public String addAdministrator(Administrator administrator) {

		ResultObj result = null;
		try {
			administratorService.addAdministrator(administrator);
			result = ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.ADD_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 修改管理员
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/updateAdministrator.html")
	public String updateAdministrator(AdministratorVo administratorVo) {
		ResultObj result = null;
		try {
			administratorService.updateAdministrator(administratorVo, true);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}
	/**
	 * 管理员修改自己的信息
	 */
	@RequestMapping("/updateSelfInfo.html")
	public String updateSelfInfo(AdministratorVo administratorVo) {
		ResultObj result = null;
		try {
			administratorService.updateAdministrator(administratorVo, true);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}
		String json = JSON.toJSONString(result);
		
		return json;
	}

	/**
	 * 删除管理员
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteAdministrator.html")
	public String deleteAdministrator(AdministratorVo administratorVo) {
		ResultObj result = null;
		try {
			administratorService.deleteAdministratorById(administratorVo.getId());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 批量删除管理员
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteBatchAdministrator.html")
	public String deleteBatchAdministrator(AdministratorVo administratorVo) {
		ResultObj result = null;
		try {
			administratorService.deleteBatchAdministratorByIds(administratorVo.getIds());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}

		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 重置管理员密码
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/resetAdministratorPwd.html")
	public String resetAdministratorPwd(AdministratorVo administratorVo) {
		ResultObj result = null;
		try {
			administratorService.resetAdministratorPwd(administratorVo);
			result = ResultObj.RESET_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.RESET_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	// ---------------------

	// 内容校验
	@RequestMapping("/check/{param}/{type}")
	private Object checkDate(@PathVariable String param, @PathVariable Integer type) {
		DataResult result = null;
		try {
			result = administratorService.checkData(param, type);
		} catch (Exception e) {
			result = DataResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		return result;
	}

	// 退出登录
	@RequestMapping("/logout.html")
	public String deleteAdministratorByToken(HttpServletRequest request, HttpServletResponse response) {
		DataResult result = null;

		try {
			result = administratorService.deleteByToken(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			result = DataResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 加载管理员管理的分配角色的数据
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/initAdministratorRole.html")
	public String initAdministratorRole(AdministratorVo administratorVo) {
		String json = JSON.toJSONString(administratorService.findAdministratorRole(administratorVo.getId()));

		return json;
	}

	/**
	 * 保存管理员和角色的关系
	 */
	@RequiresPermissions(value= {"sys:update","sys:add"})
	@RequestMapping("/saveAdministratorRole.html")
	public String saveAdministratorRole(AdministratorVo administratorVo) {
		ResultObj result = null;
		try {
			administratorService.saveAdministratorRole(administratorVo);
			result = ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DISPATCH_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

}
