package com.lsqstudy.system.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.DataResult;
import com.lsqstudy.common.util.ResultObj;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.News;
import com.lsqstudy.system.service.IAdministratorService;
import com.lsqstudy.system.service.INewsService;
import com.lsqstudy.system.vo.NewsVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

/**
 * 消息管理控制器
 * 
 *
 */
@RestController
@RequestMapping("/system/news")
public class NewsController {

	@Autowired
	private INewsService newsService;
	@Autowired
	private IAdministratorService administratorService;

	/**
	 * 加载消息列表返回DataGridView
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadAllNews.html")
	public String loadAllNews(NewsVo newsVo) {
		DataGridView result = newsService.findNewsList(newsVo);

		String json = JSON.toJSONString(result);
		return json;
	}
	/**
	 * 获取消息列表中的一条消息
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadOneNews.html")
	public String loadOneNews(NewsVo newsVo) {
		newsVo.setPage(1);
		newsVo.setLimit(1);
		DataGridView result = newsService.findNewsList(newsVo);
		
		String json = JSON.toJSONString(result);
		return json;
	}

	/**
	 * 添加消息
	 */
	@RequiresPermissions(value="sys:add")
	@RequestMapping("/addNews.html")
	public String addNews(News news, HttpServletRequest request, HttpServletResponse response) {
		ResultObj result = null;
		try {
			// 补全信息
			news.setCreateTime(new Date());
			DataResult blogData = administratorService.getAdministratorByToken(request, response);
			if (blogData.getStatus() == 200) {
				Administrator administrator = (Administrator) blogData.getData();
				news.setPublisher(administrator.getRealName());
			} else {
				result = ResultObj.ADD_ERROR;
			}
			// 添加
			newsService.addNews(news);
			result = ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.ADD_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 修改消息
	 */
	@RequiresPermissions(value="sys:update")
	@RequestMapping("/updateNews.html")
	public String updateNews(NewsVo newsVo) {
		ResultObj result = null;
		try {
			newsService.updateNews(newsVo);
			result = ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.UPDATE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 删除消息
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteNews.html")
	public String deleteNews(NewsVo newsVo) {
		ResultObj result = null;
		try {
			newsService.deleteNews(newsVo.getId());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 批量删除消息
	 */
	@RequiresPermissions(value="sys:delete")
	@RequestMapping("/deleteBatchNews.html")
	public String deleteBatchNews(NewsVo newsVo) {
		ResultObj result = null;
		try {
			newsService.deleteBatchNews(newsVo.getIds());
			result = ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultObj.DELETE_ERROR;
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 根据id查询消息
	 */
	@RequiresPermissions(value="sys:query")
	@RequestMapping("/loadNewsById.html")
	public String loadNewsById(Integer id) {
		News news = newsService.findNewsById(id);
		String json = JSON.toJSONString(news);

		return json;
	}

}
