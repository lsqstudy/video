package com.lsqstudy.system.vo;


import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.domain.Permission;
import org.springframework.beans.BeanUtils;

public class PermissionVo extends Permission {
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;
	// 接收多个角色id
	private Integer[] ids;

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
	public Permission getPermission(){
		Permission permission=new Permission();
		BeanUtils.copyProperties(this, permission);
		return permission;
	}

}
