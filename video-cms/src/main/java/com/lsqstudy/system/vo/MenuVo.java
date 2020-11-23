package com.lsqstudy.system.vo;


import com.lsqstudy.system.domain.Menu;
import org.springframework.beans.BeanUtils;

public class MenuVo extends Menu {
	/**
	 * 分页参数
	 */
	private Integer page;
	private Integer limit;

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
	public Menu getMenu(){
		Menu menu=new Menu();
		BeanUtils.copyProperties(this, menu);
		return menu;
	}

}
