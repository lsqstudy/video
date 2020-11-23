package com.lsqstudy.bussiness.service;

import com.lsqstudy.bussiness.domain.TVPlay;
import com.lsqstudy.bussiness.vo.TVPlayVo;
import com.lsqstudy.common.util.DataGridView;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 14:58
 */

public interface ITVPlayService {

    /**
     * 查询所有电视剧
     * @return
     */
    public DataGridView findTVPlayList(TVPlayVo tvPlayVo);

    /**
     * 查询一个电视剧
     * @param id
     * @return
     */
    public TVPlay findTVPlayById(String id);

    /**
     * 修改电视剧
     */
    public void updateTVPlay(TVPlayVo tvPlayVo);
    /**
     * 根据id删除电视剧
     */
    public void deleteTVPlay(String id);
    /**
     * 批量删除电视剧
     */
    public void deleteBatchTVPlay(String [] ids);
}
