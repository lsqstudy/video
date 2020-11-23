package com.lsqstudy.system.vo;

import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.Menu;
import org.springframework.beans.BeanUtils;

public class AdministratorVo extends Administrator {

	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;

	// 接收多个角色id
	private Integer[] ids;
	// 验证码
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	/**
	 * 返回父类类型的对象
	 * @return
	 */
	public Administrator getAdministrator(){
		Administrator administrator=new Administrator();
		BeanUtils.copyProperties(this, administrator);
		return administrator;
	}



}
