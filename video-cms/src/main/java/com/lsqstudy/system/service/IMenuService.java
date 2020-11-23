package com.lsqstudy.system.service;

import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.vo.MenuVo;

import java.util.List;


/**
 * 菜单管理的服务接口
 *
 */
public interface IMenuService {

	/**
	 * 查询所有菜单返回 List<Menu>
	 */
	public List<Menu> findMenuListByMenuVo(MenuVo menuVo);

	/**
	 * 分页，查询所有菜单返回List
	 * 
	 * @param menuVo
	 * @return
	 */
	public DataGridView findMenuByMenuVo(MenuVo menuVo);

	/**
	 * 根据管理员id查询管理员的可用菜单
	 * 
	 * @param menuVo
	 * @param id
	 * @return
	 */
	public List<Menu> findMenuListByAdministratorId(MenuVo menuVo, Integer id);

	/**
	 * 根据pid查询菜单数量
	 * 
	 * @param pid
	 * @return
	 */
	public Long findMenuCountByPid(Integer pid);

	/**
	 * 添加菜单
	 *
	 */
	public void addMenu(Menu menu);

	/**
	 * 修改菜单
	 *
	 * @param menuVo
	 */
	public void updateMenu(MenuVo menuVo);

	/**
	 * 根据id删除菜单
	 *
	 * @param menuVo
	 */
	public void deleteMenu(MenuVo menuVo);

}
