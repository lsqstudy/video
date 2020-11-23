package com.lsqstudy.bussiness.service.impl;

import com.lsqstudy.bussiness.dao.ITVPlayDao;
import com.lsqstudy.bussiness.domain.TVPlay;
import com.lsqstudy.bussiness.service.ITVPlayService;
import com.lsqstudy.bussiness.vo.TVPlayVo;
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
public class TVPlayServiceImpl implements ITVPlayService {
    @Autowired
    private ITVPlayDao tvPlayDao;

    @Override
    public DataGridView findTVPlayList(TVPlayVo tvPlayVo) {
        if (StringUtils.isBlank(tvPlayVo.getTitle())){
            tvPlayVo.setTitle(null);
        }
        if (StringUtils.isBlank(tvPlayVo.getAlex())){
            tvPlayVo.setAlex(null);
        }
        if (null!=tvPlayVo.getType()&&tvPlayVo.getType().size()==0){
            tvPlayVo.setType(null);
        }


        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(tvPlayVo.getPage() - 1, tvPlayVo.getLimit(), sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("_id")//忽略属性
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withIgnoreCase(true)//忽略大小写
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //改变默认字符串匹配方式：模糊查询

        Example<TVPlay> example = Example.of(tvPlayVo.getTVPlay(),matcher);
        Page<TVPlay> page = tvPlayDao.findAll(example, pageable);

        return new DataGridView(page.getTotalElements(), page.getContent());
    }

    @Override
    public TVPlay findTVPlayById(String id) {
        Optional<TVPlay> opt = tvPlayDao.findById(id);

        return opt.isPresent() == true ? opt.get() : null;
    }


    @Override
    public void updateTVPlay(TVPlayVo tvPlayVo) {
        Optional<TVPlay> opt = tvPlayDao.findById(tvPlayVo.getId());
        if (opt.isPresent()) {
            TVPlay tvPlay = opt.get();
            if (null != tvPlayVo.getTitle()) {
                tvPlay.setTitle(tvPlayVo.getTitle());
            }
            if (null != tvPlayVo.getAlex()) {
                tvPlay.setAlex(tvPlayVo.getAlex());
            }
            if (null != tvPlayVo.getType()) {
                tvPlay.getType().addAll(tvPlayVo.getType());
            }
            if (null != tvPlayVo.getYear()) {
                tvPlay.setYear(tvPlayVo.getYear());
            }
            if (null != tvPlayVo.getInfo()) {
                tvPlay.setInfo(tvPlayVo.getInfo());
            }

            if (null != tvPlayVo.getPoster()) {
                tvPlay.setPoster(tvPlayVo.getPoster());
                if (null != tvPlayVo.getIsBanner()) {
                    tvPlay.setIsBanner(tvPlayVo.getIsBanner());
                }

            }
            if (null != tvPlayVo.getDescribe()) {
                tvPlay.setDescribe(tvPlayVo.getDescribe());
            }

            tvPlay.setUpdateTime(new Date());

            tvPlayDao.save(tvPlay);
        }


    }

    @Override
    public void deleteTVPlay(String id) {
        tvPlayDao.deleteById(id);
    }

    @Override
    public void deleteBatchTVPlay(String[] ids) {
        for (String id : ids) {
            deleteTVPlay(id);
        }
    }

}
