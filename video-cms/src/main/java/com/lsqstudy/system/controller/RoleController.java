package com.lsqstudy.system.controller;

import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.ResultObj;
import com.lsqstudy.system.domain.Role;
import com.lsqstudy.system.service.IRoleService;
import com.lsqstudy.system.vo.RoleVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

/**
 * 角色管理控制器
 * 
 */
@RestController
@RequestMapping("system/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	/**
	 * 加载角色列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllRole.html")
	public String loadAllRole(RoleVo roleVo) {
		DataGridView roleList = roleService.findRoleList(roleVo);
		String json = JSON.toJSONString(roleList);

		return json;
	}

	/**
	 * 添加角色
	 */
	@RequiresPermissions(value="sys:add")
	@RequestMapping("/addRole.html")
	public String addRole(Role role) {
		ResultObj result = null;
		try {
			roleService.addRole(role);
			result = ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.ADD_ERROR;
		}

		String json = JSON.toJSONString(result);

		return json;

	}

	/**
	 * 修改角色
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/updateRole.html")
	public String updateRole(RoleVo roleVo) {
		ResultObj result = null;
		try {
			roleService.updateRole(roleVo);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}

		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 删除角色
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("deleteRole.html")
	public String deleteRole(RoleVo roleVo) {
		ResultObj result=null;
		try {
			roleService.deleteRole(roleVo.getId());
			result= ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result =ResultObj.DELETE_ERROR;
		}
		
		String json=JSON.toJSONString(result);
		
		return json;
	}

	/**
	 * 批量删除角色
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteBatchRole.html")
	public String deleteBatchRole(RoleVo roleVo) {
		ResultObj result=null;
		try {
			roleService.deleteBatchRole(roleVo.getIds());
			result= ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result= ResultObj.DELETE_ERROR;
		}
		
		String json=JSON.toJSONString(result);
		
		return json;
	}

	/**
	 * 加载角色管理分配菜单的json
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("initRoleMenuTreeJson.html")
	public String initRoleMenuTreeJson(Integer id) {
		DataGridView result = roleService.initRoleMenuTreeJson(id);
		String json=JSON.toJSONString(result);

		return json;
	}

	/**
	 * 保存角色和菜单的关系
	 */
	@RequiresPermissions(value="sys:add")
	@RequestMapping("/saveRoleMenu.html")
	public String  saveRoleMenu(RoleVo roleVo) {
		ResultObj result=null;
		try {
			roleService.saveRoleMenu(roleVo);
			result= ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result= ResultObj.DISPATCH_ERROR;
		}
		
		String json=JSON.toJSONString(result);
		
		return json;
	}
	
	/**
	 * 加载角色的分配权限的数据
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/initRolePermission.html")
	public String initRolePermission(RoleVo roleVo) {
		String json = JSON.toJSONString(roleService.findRolePermission(roleVo.getId()));

		return json;
	}
	
	/**
	 * 保存角色和权限的关系
	 */
	@RequiresPermissions(value="sys:add")
	@RequestMapping("/saveRolePermission.html")
	public String  saveRolePermission(RoleVo roleVo) {
		ResultObj result=null;
		try {
			roleService.saveRolePermission(roleVo);
			result= ResultObj.AUTHORIZED_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result= ResultObj.AUTHORIZED_ERROR;
		}
		
		String json=JSON.toJSONString(result);
		
		return json;
	}

}
