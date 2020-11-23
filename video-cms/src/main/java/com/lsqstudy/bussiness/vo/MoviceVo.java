package com.lsqstudy.bussiness.vo;

import com.lsqstudy.bussiness.domain.Movice;
import org.springframework.beans.BeanUtils;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-21 17:46
 */

public class MoviceVo extends Movice {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    // 接收多个角色id
    private String[] ids;

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

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
    /**
     * 返回父类类型的对象
     * @return
     */
    public Movice getMovice(){
        Movice movice=new Movice();
        BeanUtils.copyProperties(this, movice);
        return movice;
    }

}
