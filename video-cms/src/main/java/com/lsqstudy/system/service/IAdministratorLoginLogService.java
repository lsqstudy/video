package com.lsqstudy.system.service;


import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.system.domain.AdministratorLoginLog;
import com.lsqstudy.system.vo.AdministratorLoginLogVo;

/**
 * 日志管理的服务接口
 *
 */
public interface IAdministratorLoginLogService {
	/**
	 * 查询所有日志
	 * @return
	 */
	public DataGridView findAdministratorLoginLogList(AdministratorLoginLogVo administratorLoginLogVo);
	/**
	 * 添加日志
	 */
	public void addAdministratorLoginLog(AdministratorLoginLog administratorLoginLog);
	/**
	 * 根据id删除日志
	 */
	public void deleteAdministratorLoginLog(Integer id);
	/**
	 * 批量删除日志
	 */
	public void deleteBatchAdministratorLoginLog(Integer [] ids);

}
