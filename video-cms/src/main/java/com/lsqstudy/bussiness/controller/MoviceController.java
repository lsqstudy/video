package com.lsqstudy.bussiness.controller;

import com.alibaba.fastjson.JSON;
import com.lsqstudy.bussiness.domain.Movice;
import com.lsqstudy.bussiness.service.IMoviceService;
import com.lsqstudy.bussiness.vo.MoviceVo;
import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.DataResult;
import com.lsqstudy.common.util.ResultObj;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.service.IAdministratorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 电影管理控制器
 * 
 *
 */
@RestController
@RequestMapping("bussiness/movice")
public class MoviceController {

	@Autowired
	private IMoviceService moviceService;
	@Autowired
	private IAdministratorService administratorService;

	/**
	 * 加载电影列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllMovice.html")
	public String loadAllMovice(MoviceVo moviceVo) {
		DataGridView result = moviceService.findMoviceList(moviceVo);

		String json = JSON.toJSONString(result);
		return json;
	}
	/**
	 * 获取电影列表中的一条电影
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadOneMovice.html")
	public String loadOneMovice(MoviceVo moviceVo) {
		moviceVo.setPage(1);
		moviceVo.setLimit(1);
		DataGridView result = moviceService.findMoviceList(moviceVo);
		
		String json = JSON.toJSONString(result);
		return json;
	}


	/**
	 * 修改电影
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/updateMovice.html")
	public String updateMovice(MoviceVo moviceVo) {
		ResultObj result = null;
		try {
			moviceService.updateMovice(moviceVo);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 删除电影
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteMovice.html")
	public String deleteMovice(MoviceVo moviceVo) {
		ResultObj result = null;
		try {
			moviceService.deleteMovice(moviceVo.getId());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 批量删除电影
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteBatchMovice.html")
	public String deleteBatchMovice(MoviceVo moviceVo) {
		ResultObj result = null;
		try {
			moviceService.deleteBatchMovice(moviceVo.getIds());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 根据id查询电影
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadMoviceById.html")
	public String loadMoviceById(String id) {
		Movice movice = moviceService.findMoviceById(id);
		String json = JSON.toJSONString(movice);

		return json;
	}

}
