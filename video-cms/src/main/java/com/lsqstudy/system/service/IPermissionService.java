package com.lsqstudy.system.service;

import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.system.domain.Permission;
import com.lsqstudy.system.vo.PermissionVo;

import java.util.List;


public interface IPermissionService {
	
	/**
	 * 查询所有的权限
	 * @param permissionVo
	 * @return
	 */
	public DataGridView findPermissionList(PermissionVo permissionVo);
	/**
	 * 根据管理员ID查询权限
	 */
	public List<String> findPermissionByAid(Integer Aid);

	public void addPermission(Permission permission);

	public void updatePermission(PermissionVo permissionVo);

	public void deletePermission(Integer id);

	public void deleteBatchPermission(Integer[] ids);

}
