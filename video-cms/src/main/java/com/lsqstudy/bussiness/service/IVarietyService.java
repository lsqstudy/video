package com.lsqstudy.bussiness.service;


import com.lsqstudy.bussiness.domain.Variety;
import com.lsqstudy.bussiness.vo.VarietyVo;
import com.lsqstudy.common.util.DataGridView;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 14:58
 */

public interface IVarietyService {

    /**
     * 查询所有综艺
     * @return
     */
    public DataGridView findVarietyList(VarietyVo varietyVo);

    /**
     * 查询一个综艺
     * @param id
     * @return
     */
    public Variety findVarietyById(String id);

    /**
     * 修改综艺
     */
    public void updateVariety(VarietyVo varietyVo);
    /**
     * 根据id删除综艺
     */
    public void deleteVariety(String id);
    /**
     * 批量删除综艺
     */
    public void deleteBatchVariety(String [] ids);
}
