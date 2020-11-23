package com.lsqstudy.bussiness.vo;

import com.lsqstudy.bussiness.domain.Cartoon;
import org.springframework.beans.BeanUtils;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-21 17:46
 */

public class CartoonVo  extends Cartoon {
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
    public Cartoon getCartoon(){
        Cartoon cartoon=new Cartoon();
        BeanUtils.copyProperties(this, cartoon);
        return cartoon;
    }

}
