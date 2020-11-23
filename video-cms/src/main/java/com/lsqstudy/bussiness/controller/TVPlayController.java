package com.lsqstudy.bussiness.controller;

import com.alibaba.fastjson.JSON;
import com.lsqstudy.bussiness.domain.TVPlay;
import com.lsqstudy.bussiness.service.ITVPlayService;
import com.lsqstudy.bussiness.vo.TVPlayVo;
import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.ResultObj;
import com.lsqstudy.system.service.IAdministratorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 电视剧管理控制器
 * 
 *
 */
@RestController
@RequestMapping("/bussiness/tvPlay")
public class TVPlayController {

	@Autowired
	private ITVPlayService tvPlayService;
	@Autowired
	private IAdministratorService administratorService;

	/**
	 * 加载电视剧列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllTVPlay.html")
	public String loadAllTVPlay(TVPlayVo tvPlayVo) {
		DataGridView result = tvPlayService.findTVPlayList(tvPlayVo);

		String json = JSON.toJSONString(result);
		return json;
	}
	/**
	 * 获取电视剧列表中的一条电视剧
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadOneTVPlay.html")
	public String loadOneTVPlay(TVPlayVo tvPlayVo) {
		tvPlayVo.setPage(1);
		tvPlayVo.setLimit(1);
		DataGridView result = tvPlayService.findTVPlayList(tvPlayVo);
		
		String json = JSON.toJSONString(result);
		return json;
	}

	/**
	 * 修改电视剧
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/updateTVPlay.html")
	public String updateTVPlay(TVPlayVo tvPlayVo) {
		ResultObj result = null;
		try {
			tvPlayService.updateTVPlay(tvPlayVo);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 删除电视剧
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteTVPlay.html")
	public String deleteTVPlay(TVPlayVo tvPlayVo) {
		ResultObj result = null;
		try {
			tvPlayService.deleteTVPlay(tvPlayVo.getId());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 批量删除电视剧
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteBatchTVPlay.html")
	public String deleteBatchTVPlay(TVPlayVo tvPlayVo) {
		ResultObj result = null;
		try {
			tvPlayService.deleteBatchTVPlay(tvPlayVo.getIds());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 根据id查询电视剧
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadTVPlayById.html")
	public String loadTVPlayById(String id) {
		TVPlay tvPlay = tvPlayService.findTVPlayById(id);
		String json = JSON.toJSONString(tvPlay);

		return json;
	}

}
