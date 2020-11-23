package com.lsqstudy.bussiness.service;


import com.lsqstudy.bussiness.domain.Cartoon;
import com.lsqstudy.bussiness.vo.CartoonVo;
import com.lsqstudy.common.util.DataGridView;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 14:58
 */

public interface ICartoonService {

    /**
     * 查询所有动漫
     * @return
     */
    public DataGridView findCartoonList(CartoonVo cartoonVo);

    /**
     * 查询一个动漫
     * @param id
     * @return
     */
    public Cartoon findCartoonById(String id);

    /**
     * 修改动漫
     */
    public void updateCartoon(CartoonVo cartoonVo);
    /**
     * 根据id删除动漫
     */
    public void deleteCartoon(String id);
    /**
     * 批量删除动漫
     */
    public void deleteBatchCartoon(String [] ids);
}
