package com.lsqstudy.bussiness.service;


import com.lsqstudy.bussiness.domain.Movice;
import com.lsqstudy.bussiness.vo.MoviceVo;
import com.lsqstudy.common.util.DataGridView;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 14:58
 */

public interface IMoviceService {

    /**
     * 查询所有电影
     * @return
     */
    public DataGridView findMoviceList(MoviceVo moviceVo);

    /**
     * 查询一个电影
     * @param id
     * @return
     */
    public Movice findMoviceById(String id);

    /**
     * 添加电影
     */
    public void addMovice(Movice movice);
    /**
     * 修改电影
     */
    public void updateMovice(MoviceVo moviceVo);
    /**
     * 根据id删除电影
     */
    public void deleteMovice(String id);
    /**
     * 批量删除电影
     */
    public void deleteBatchMovice(String [] ids);
}
