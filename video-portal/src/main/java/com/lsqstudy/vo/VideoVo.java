package com.lsqstudy.vo;

import com.lsqstudy.domain.Cartoon;
import com.lsqstudy.domain.Movice;
import com.lsqstudy.domain.TVPlay;
import com.lsqstudy.domain.Variety;
import org.springframework.beans.BeanUtils;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-21 17:46
 */

public class VideoVo extends Cartoon {
    /**
     * 分页参数
     */
    private Integer page=0;
    private Integer limit=24;
    //播放源下标
    private Integer urlIndex;
    //视频url的下标
    private Integer sourceIndex;

    //搜索内容
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUrlIndex() {
        return urlIndex;
    }

    public void setUrlIndex(Integer urlIndex) {
        this.urlIndex = urlIndex;
    }

    public Integer getSourceIndex() {
        return sourceIndex;
    }

    public void setSourceIndex(Integer sourceIndex) {
        this.sourceIndex = sourceIndex;
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
     * 返回cartoon类型的对象
     * @return
     */
    public Cartoon getCartoon(){
        Cartoon cartoon=new Cartoon();
        BeanUtils.copyProperties(this, cartoon);
        return cartoon;
    }

    /**
     * 返回Movice类型的对象
     * @return
     */
    public Movice getMovice(){
        Movice movice=new Movice();
        BeanUtils.copyProperties(this, movice);
        return movice;
    }

    /**
     * 返回 TVPlay 类型的对象
     * @return
     */
    public TVPlay getTVPlay(){
        TVPlay tvplay=new TVPlay();
        BeanUtils.copyProperties(this, tvplay);
        return tvplay;
    }

    /**
     * 返回Variety类型的对象
     * @return
     */
    public Variety getVariety(){
        Variety variety=new Variety();
        BeanUtils.copyProperties(this, variety);
        return variety;
    }

}
