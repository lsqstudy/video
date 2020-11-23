package com.lsqstudy.bussiness.vo;

import com.lsqstudy.bussiness.domain.TVPlay;
import org.springframework.beans.BeanUtils;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-21 17:46
 */

public class TVPlayVo extends TVPlay {
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
    public TVPlay getTVPlay(){
        TVPlay tvPlay=new TVPlay();
        BeanUtils.copyProperties(this, tvPlay);
        return tvPlay;
    }

}
