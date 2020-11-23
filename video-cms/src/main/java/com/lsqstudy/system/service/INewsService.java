package com.lsqstudy.system.service;


import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.system.domain.News;
import com.lsqstudy.system.vo.NewsVo;

/**
 * 消息管理的服务接口
 *
 */
public interface INewsService {
	/**
	 * 查询所有消息
	 * @return
	 */
	public DataGridView findNewsList(NewsVo newsVo);
	
	/**
	 * 查询一个消息
	 * @param id
	 * @return
	 */
	public News findNewsById(Integer id);

	/**
	 * 添加消息
	 */
	public void addNews(News news);
	/**
	 * 修改消息
	 */
	public void updateNews(NewsVo newsVo);
	/**
	 * 根据id删除消息
	 */
	public void deleteNews(Integer id);
	/**
	 * 批量删除消息
	 */
	public void deleteBatchNews(Integer [] ids);
	
}
