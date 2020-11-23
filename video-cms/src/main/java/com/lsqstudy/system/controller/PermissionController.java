package com.lsqstudy.system.controller;

import com.alibaba.fastjson.JSON;
import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.ResultObj;
import com.lsqstudy.system.domain.Permission;
import com.lsqstudy.system.service.IPermissionService;
import com.lsqstudy.system.vo.PermissionVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限管理控制器
 * 
 */
@RestController
@RequestMapping("system/permission")
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;

	/**
	 * 加载权限列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllPermission.html")
	public String loadAllPermission(PermissionVo permissionVo) {
		DataGridView permissionList = permissionService.findPermissionList(permissionVo);
		String json = JSON.toJSONString(permissionList);

		return json;
	}

	/**
	 * 添加权限
	 */
	@RequiresPermissions(value="sys:add")
	@RequestMapping("/addPermission.html")
	public String addPermission(Permission permission) {
		ResultObj result = null;
		try {
			permissionService.addPermission(permission);
			result = ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.ADD_ERROR;
		}

		String json = JSON.toJSONString(result);

		return json;

	}

	/**
	 * 修改权限
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/updatePermission.html")
	public String updatePermission(PermissionVo permissionVo) {
		ResultObj result = null;
		try {
			permissionService.updatePermission(permissionVo);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}

		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 删除权限
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("deletePermission.html")
	public String deletePermission(PermissionVo permissionVo) {
		ResultObj result=null;
		try {
			permissionService.deletePermission(permissionVo.getId());
			result= ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result =ResultObj.DELETE_ERROR;
		}
		
		String json=JSON.toJSONString(result);
		
		return json;
	}

	/**
	 * 批量删除权限
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteBatchPermission.html")
	public String deleteBatchPermission(PermissionVo permissionVo) {
		ResultObj result=null;
		try {
			permissionService.deleteBatchPermission(permissionVo.getIds());
			result= ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result= ResultObj.DELETE_ERROR;
		}
		
		String json=JSON.toJSONString(result);
		
		return json;
	}

}
