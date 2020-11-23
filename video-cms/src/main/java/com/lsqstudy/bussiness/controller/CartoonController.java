package com.lsqstudy.bussiness.controller;

import com.alibaba.fastjson.JSON;
import com.lsqstudy.bussiness.domain.Cartoon;
import com.lsqstudy.bussiness.service.ICartoonService;
import com.lsqstudy.bussiness.vo.CartoonVo;
import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.ResultObj;
import com.lsqstudy.system.service.IAdministratorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 动漫管理控制器
 * 
 *
 */
@RestController
@RequestMapping("bussiness/cartoon")
public class CartoonController {

	@Autowired
	private ICartoonService cartoonService;
	@Autowired
	private IAdministratorService administratorService;

	/**
	 * 加载动漫列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllCartoon.html")
	public String loadAllCartoon(CartoonVo cartoonVo) {
		DataGridView result = cartoonService.findCartoonList(cartoonVo);

		String json = JSON.toJSONString(result);
		return json;
	}
	/**
	 * 获取动漫列表中的一条动漫
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadOneCartoon.html")
	public String loadOneCartoon(CartoonVo cartoonVo) {
		cartoonVo.setPage(1);
		cartoonVo.setLimit(1);
		DataGridView result = cartoonService.findCartoonList(cartoonVo);
		
		String json = JSON.toJSONString(result);
		return json;
	}

	/**
	 * 修改动漫
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/updateCartoon.html")
	public String updateCartoon(CartoonVo cartoonVo) {
		ResultObj result = null;
		try {
			cartoonService.updateCartoon(cartoonVo);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 删除动漫
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteCartoon.html")
	public String deleteCartoon(CartoonVo cartoonVo) {
		ResultObj result = null;
		try {
			cartoonService.deleteCartoon(cartoonVo.getId());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 批量删除动漫
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteBatchCartoon.html")
	public String deleteBatchCartoon(CartoonVo cartoonVo) {
		ResultObj result = null;
		try {
			cartoonService.deleteBatchCartoon(cartoonVo.getIds());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 根据id查询动漫
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadCartoonById.html")
	public String loadCartoonById(String id) {
		Cartoon cartoon = cartoonService.findCartoonById(id);
		String json = JSON.toJSONString(cartoon);

		return json;
	}

}
