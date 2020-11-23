package com.lsqstudy.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsqstudy.common.constast.Constast;
import com.lsqstudy.common.util.*;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.service.IAdministratorService;
import com.lsqstudy.system.service.IMenuService;
import com.lsqstudy.system.vo.MenuVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

/**
 * 菜单管理控制器
 * @RestController 表示返回json字符串，不能返回视图
 */
@RestController  
@RequestMapping("system/menu")
public class MenuController {

	@Autowired
	private IMenuService menuService;
	@Autowired
	private IAdministratorService administratorService;

	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadIndexLeftMenuJson.html")
	public String loadIndexLeftMenuJson(MenuVo menuVo, HttpServletRequest request, HttpServletResponse response) {
		// 得到当前登陆的管理员对象
		DataResult dataResult = administratorService.getAdministratorByToken(request, response);
		Administrator administrator = (Administrator) dataResult.getData();

		List<Menu> menus = null;
		menuVo.setAvailable(Constast.AVAILABLE_TRUE);// 只查询可用的
		if (administrator.getType() == Constast.USER_TYPE_SUPER) {
			menus = menuService.findMenuListByMenuVo(menuVo);
		} else {
			menus=this.menuService.findMenuListByAdministratorId(menuVo, administrator.getId());
		}

		List<TreeNode> nodes = new ArrayList<>();
		// 把list里面的数据放到nodes
		for (Menu menu : menus) {
			Integer id = menu.getId();
			Integer pid = menu.getPid();
			String title = menu.getTitle();
			String icon = menu.getIcon();
			String href = menu.getHref();
			Boolean spread = menu.getSpread() == Constast.SPREAD_TRUE ? true : false;
			String target = menu.getTarget();
			nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
		}
		List<TreeNode> builderNode = TreeNodeBuilder.builder(nodes, 1);
		String json = JSON.toJSONString(builderNode);

		return json;
	}

	/**
	 * 加载菜单管理左边的菜单树
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadMenuManagerLeftTreeJson.html")
	public String loadMenuManagerLeftTreeJson(MenuVo menuVo) {
		// menuVo.setAvailable(Constast.AVAILABLE_TRUE);// 只查询可用的
		List<Menu> list = menuService.findMenuListByMenuVo(menuVo);

		List<TreeNode> nodes = new ArrayList<>();
		// 把list里面的数据放到nodes
		for (Menu menu : list) {
			Integer id = menu.getId();
			Integer pid = menu.getPid();
			String title = menu.getTitle();
			String icon = menu.getIcon();
			String href = menu.getHref();
			Boolean spread = menu.getSpread() == Constast.SPREAD_TRUE ? true : false;
			String target = menu.getTarget();
			nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
		}
		
		DataGridView result = new DataGridView(nodes);
		String json = JSON.toJSONString(result);
		
		return json;
	}
	/**
	 * 加载菜单列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllMenu.html")
	public String loadAllMenu(MenuVo menuVo) {
		DataGridView dataGridView = menuService.findMenuByMenuVo(menuVo);
		String json = JSON.toJSONString(dataGridView);

		return json;
	}

	/**
	 * 添加菜单
	 */
	@RequiresPermissions(value="sys:add")
	@RequestMapping("/addMenu.html")
	public String addMenu(Menu menu) {
		ResultObj result = null;
		try {
			menuService.addMenu(menu);
			result = ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.ADD_ERROR;
		}

		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 修改菜单
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/updateMenu.html")
	public String updateMenu(MenuVo menuVo) {
		ResultObj result = null;
		try {
			menuService.updateMenu(menuVo);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 根据id判断当前菜单有没有子节点 有返回code>=0 没有 返回code<0
	 */
	@RequestMapping("/checkMenuHasChildren.html")
	public String checkMenuHasChildren(MenuVo menuVo) {
		ResultObj result = null;
		// 根据pid查询菜单数量
		Long count = menuService.findMenuCountByPid(menuVo.getId());
		if (count > 0) {
			result = ResultObj.STATUS_TRUE;
		} else {
			result = ResultObj.STATUS_FALSE;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 删除菜单
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteMenu.html")
	public String deleteMenu(MenuVo menuVo) {
		ResultObj result = null;
		try {
			menuService.deleteMenu(menuVo);
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

}
