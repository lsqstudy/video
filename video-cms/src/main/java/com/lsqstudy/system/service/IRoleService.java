package com.lsqstudy.system.service;

import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.system.domain.Role;
import com.lsqstudy.system.vo.RoleVo;

import java.util.List;

/**
 * 角色管理的服务接口
 *
 */
public interface IRoleService {

	/**
	 * 查询所有角色
	 * 
	 * @param roleVo
	 * @return
	 */
	public DataGridView findRoleList(RoleVo roleVo);

	/**
	 * 根据管理员id查询角色
	 * 
	 * @param Aid
	 * @return
	 */
	public List<String> findRoleListByAid(Integer Aid);

	/**
	 * 添加角色
	 * 
	 */
	public void addRole(Role role);

	/**
	 * 修改角色
	 * 
	 * @param roleVo
	 */
	public void updateRole(RoleVo roleVo);

	/**
	 * 根据id删除角色
	 * 
	 * @param roleid
	 */
	public void deleteRole(Integer roleid);

	/**
	 * 批量删除角色
	 * 
	 */
	public void deleteBatchRole(Integer[] ids);

	/**
	 * 加载角色管理分配菜单的json
	 * 
	 * @param roleid
	 * @return
	 */
	public DataGridView initRoleMenuTreeJson(Integer roleid);

	/**
	 * 保存角色和菜单的关系
	 * 
	 * @param roleVo
	 */
	public void saveRoleMenu(RoleVo roleVo);

	/**
	 * 加载角色的分配权限的数据
	 */
	public DataGridView findRolePermission(Integer id);

	/**
	 * 保存角色和权限的关系
	 * 
	 * @param roleVo
	 */
	public void saveRolePermission(RoleVo roleVo);

}
