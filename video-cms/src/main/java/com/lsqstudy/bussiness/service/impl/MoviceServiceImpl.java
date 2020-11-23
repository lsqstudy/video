package com.lsqstudy.bussiness.service.impl;


import com.lsqstudy.bussiness.dao.IMoviceDao;
import com.lsqstudy.bussiness.domain.Movice;
import com.lsqstudy.bussiness.service.IMoviceService;
import com.lsqstudy.bussiness.vo.MoviceVo;
import com.lsqstudy.common.util.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 15:10
 */
@Service
public class MoviceServiceImpl implements IMoviceService {
    @Autowired
    private IMoviceDao moviceDao;

    @Override
    public DataGridView findMoviceList(MoviceVo moviceVo) {
        if (StringUtils.isBlank(moviceVo.getTitle())){
            moviceVo.setTitle(null);
        }
        if (StringUtils.isBlank(moviceVo.getAlex())){
            moviceVo.setAlex(null);
        }
        if (null!=moviceVo.getType()&&moviceVo.getType().size()==0){
            moviceVo.setType(null);
        }


        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(moviceVo.getPage() - 1, moviceVo.getLimit(), sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("_id")//忽略属性
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withIgnoreCase(true)//忽略大小写
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //改变默认字符串匹配方式：模糊查询

        Example<Movice> example = Example.of(moviceVo.getMovice(),matcher);
        Page<Movice> page = moviceDao.findAll(example, pageable);

        return new DataGridView(page.getTotalElements(), page.getContent());
    }

    @Override
    public Movice findMoviceById(String id) {
        Optional<Movice> opt = moviceDao.findById(id);

        return opt.isPresent() == true ? opt.get() : null;
    }

    @Override
    public void addMovice(Movice movice) {
        movice.setUpdateTime(new Date());
        movice.setCreateTime(new Date());

        // moviceDao.save(movice);
    }

    @Override
    public void updateMovice(MoviceVo moviceVo) {
        Optional<Movice> opt = moviceDao.findById(moviceVo.getId());
        if (opt.isPresent()) {
            Movice movice = opt.get();
            if (null != moviceVo.getTitle()) {
                movice.setTitle(moviceVo.getTitle());
            }
            if (null != moviceVo.getAlex()) {
                movice.setAlex(moviceVo.getAlex());
            }
            if (null != moviceVo.getType()) {
                movice.getType().addAll(moviceVo.getType());
            }
            if (null != moviceVo.getYear()) {
                movice.setYear(moviceVo.getYear());
            }
            if (null != moviceVo.getInfo()) {
                movice.setInfo(moviceVo.getInfo());
            }

            if (null != moviceVo.getPoster()) {
                movice.setPoster(moviceVo.getPoster());
                if (null != moviceVo.getIsBanner()) {//如果海报为null，就不要设置为Banner
                    movice.setIsBanner(moviceVo.getIsBanner());
                }
            }
            if (null != moviceVo.getDescribe()) {
                movice.setDescribe(moviceVo.getDescribe());
            }

            movice.setUpdateTime(new Date());

            moviceDao.save(movice);
        }


    }

    @Override
    public void deleteMovice(String id) {
        moviceDao.deleteById(id);
    }

    @Override
    public void deleteBatchMovice(String[] ids) {
        for (String id : ids) {
            deleteMovice(id);
        }
    }

}
