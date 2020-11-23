package com.lsqstudy.bussiness.controller;

import com.alibaba.fastjson.JSON;
import com.lsqstudy.bussiness.domain.Variety;
import com.lsqstudy.bussiness.service.IVarietyService;
import com.lsqstudy.bussiness.vo.VarietyVo;
import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.ResultObj;
import com.lsqstudy.system.service.IAdministratorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 综艺管理控制器
 * 
 *
 */
@RestController
@RequestMapping("/bussiness/variety")
public class VarietyController {

	@Autowired
	private IVarietyService varietyService;
	@Autowired
	private IAdministratorService administratorService;

	/**
	 * 加载综艺列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllVariety.html")
	public String loadAllVariety(VarietyVo varietyVo) {
		DataGridView result = varietyService.findVarietyList(varietyVo);

		String json = JSON.toJSONString(result);
		return json;
	}
	/**
	 * 获取综艺列表中的一条综艺
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadOneVariety.html")
	public String loadOneVariety(VarietyVo varietyVo) {
		varietyVo.setPage(1);
		varietyVo.setLimit(1);
		DataGridView result = varietyService.findVarietyList(varietyVo);
		
		String json = JSON.toJSONString(result);
		return json;
	}

	/**
	 * 修改综艺
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/updateVariety.html")
	public String updateVariety(VarietyVo varietyVo) {
		ResultObj result = null;
		try {
			varietyService.updateVariety(varietyVo);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 删除综艺
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteVariety.html")
	public String deleteVariety(VarietyVo varietyVo) {
		ResultObj result = null;
		try {
			varietyService.deleteVariety(varietyVo.getId());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 批量删除综艺
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteBatchVariety.html")
	public String deleteBatchVariety(VarietyVo varietyVo) {
		ResultObj result = null;
		try {
			varietyService.deleteBatchVariety(varietyVo.getIds());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 根据id查询综艺
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadVarietyById.html")
	public String loadVarietyById(String id) {
		Variety variety = varietyService.findVarietyById(id);
		String json = JSON.toJSONString(variety);

		return json;
	}

}
