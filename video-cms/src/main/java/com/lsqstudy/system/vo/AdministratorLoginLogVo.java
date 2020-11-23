package com.lsqstudy.system.vo;

import com.lsqstudy.system.domain.AdministratorLoginLog;
import com.lsqstudy.system.domain.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-18 22:58
 */

public class AdministratorLoginLogVo extends AdministratorLoginLog {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 设置接收到的时间数据的格式
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


    public AdministratorLoginLogVo() {
    }

    public AdministratorLoginLogVo(Integer page, Integer limit, Date startTime, Date endTime, Integer[] ids) {
        this.page = page;
        this.limit = limit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ids = ids;
    }

    // 接收多个id
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    /**
     * 返回父类类型的对象
     * @return
     */
    public AdministratorLoginLog getAdministratorLoginLog(){
        AdministratorLoginLog administratorLogin=new AdministratorLoginLog();
        BeanUtils.copyProperties(this, administratorLogin);
        return administratorLogin;
    }

}
