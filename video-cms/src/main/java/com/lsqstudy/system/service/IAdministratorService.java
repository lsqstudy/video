package com.lsqstudy.system.service;

import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.DataResult;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.vo.AdministratorVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAdministratorService {

	DataGridView findAdministratorList(AdministratorVo administratorVo);
	
	Administrator findAdministratorByAdministrator(Administrator administrator);

	Administrator findAdministratorById(Integer id);
	
	Administrator findAdministratorByAdName(String administratorName);

	DataResult checkData(String content, Integer type);

	DataResult addAdministrator(Administrator administrator);

	DataResult administratorlogin(String administratorName, String password, HttpServletRequest request, HttpServletResponse response);

	DataResult getAdministratorByToken(HttpServletRequest request, HttpServletResponse response);

	DataResult deleteByToken(HttpServletRequest request, HttpServletResponse response);

	/**
	 *
	 * @param administrator
	 * @param isDeleteImg 是否要删除管理员头像或管理员相关的图片
	 * @return
	 */
	DataResult updateAdministrator(Administrator administrator, boolean isDeleteImg);

	void deleteAdministratorById(Integer id);

	/**
	 * 批量删除管理员
	 */
	public void deleteBatchAdministratorByIds(Integer [] ids);

	/**
	 * 重置密码
	 */
	public void resetAdministratorPwd(Administrator administrator);

	DataGridView findAdministratorRole(Integer id);

	void saveAdministratorRole(AdministratorVo administratorVo);

}
