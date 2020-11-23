package com.lsqstudy.system.controller;

import com.lsqstudy.common.util.ResultObj;
import com.lsqstudy.system.service.IAdministratorLoginLogService;
import com.lsqstudy.system.vo.AdministratorLoginLogVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

/**
 * 日志管理控制器
 * 
 *
 */
@RestController
@RequestMapping("system/administratorLoginLog")
public class AdministratorLoginLogController {

	@Autowired
	private IAdministratorLoginLogService administratorLoginLogService;

	/**
	 * 加载日志列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllAdministratorLoginLog.html")
	public String loadAllAdministratorLoginLog(AdministratorLoginLogVo administratorLoginLogVo) {
		String json = JSON.toJSONString(administratorLoginLogService.findAdministratorLoginLogList(administratorLoginLogVo));

		return json;
	}

	/**
	 * 删除日志
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteAdministratorLoginLog.html")
	public String deleteAdministratorLoginLog(AdministratorLoginLogVo administratorLoginLogVo) {
		ResultObj result = null;
		try {
			administratorLoginLogService.deleteAdministratorLoginLog(administratorLoginLogVo.getId());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 批量删除日志
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteBatchAdministratorLoginLog.html")
	public String deleteBatchAdministratorLoginLog(AdministratorLoginLogVo administratorLoginLogVo) {
		ResultObj result = null;
		try {
			administratorLoginLogService.deleteBatchAdministratorLoginLog(administratorLoginLogVo.getIds());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

}
