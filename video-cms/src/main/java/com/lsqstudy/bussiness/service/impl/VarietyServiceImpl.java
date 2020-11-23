package com.lsqstudy.bussiness.service.impl;

import com.lsqstudy.bussiness.dao.IVarietyDao;
import com.lsqstudy.bussiness.domain.Variety;
import com.lsqstudy.bussiness.service.IVarietyService;
import com.lsqstudy.bussiness.vo.VarietyVo;
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
public class VarietyServiceImpl implements IVarietyService {
    @Autowired
    private IVarietyDao varietyDao;

    @Override
    public DataGridView findVarietyList(VarietyVo varietyVo) {
        if (StringUtils.isBlank(varietyVo.getTitle())){
            varietyVo.setTitle(null);
        }
        if (StringUtils.isBlank(varietyVo.getAlex())){
            varietyVo.setAlex(null);
        }
        if (null!=varietyVo.getType()&&varietyVo.getType().size()==0){
            varietyVo.setType(null);
        }


        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(varietyVo.getPage() - 1, varietyVo.getLimit(), sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("_id")//忽略属性
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withIgnoreCase(true)//忽略大小写
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //改变默认字符串匹配方式：模糊查询

        Example<Variety> example = Example.of(varietyVo.getVariety(),matcher);
        Page<Variety> page = varietyDao.findAll(example, pageable);

        return new DataGridView(page.getTotalElements(), page.getContent());
    }

    @Override
    public Variety findVarietyById(String id) {
        Optional<Variety> opt = varietyDao.findById(id);

        return opt.isPresent() == true ? opt.get() : null;
    }


    @Override
    public void updateVariety(VarietyVo varietyVo) {
        Optional<Variety> opt = varietyDao.findById(varietyVo.getId());
        if (opt.isPresent()) {
            Variety variety = opt.get();
            if (null != varietyVo.getTitle()) {
                variety.setTitle(varietyVo.getTitle());
            }
            if (null != varietyVo.getAlex()) {
                variety.setAlex(varietyVo.getAlex());
            }
            if (null != varietyVo.getType()) {
                variety.getType().addAll(varietyVo.getType());
            }
            if (null != varietyVo.getYear()) {
                variety.setYear(varietyVo.getYear());
            }
            if (null != varietyVo.getInfo()) {
                variety.setInfo(varietyVo.getInfo());
            }

            if (null != varietyVo.getPoster()) {
                variety.setPoster(varietyVo.getPoster());
                if (null != varietyVo.getIsBanner()) {
                    variety.setIsBanner(varietyVo.getIsBanner());
                }

            }
            if (null != varietyVo.getDescribe()) {
                variety.setDescribe(varietyVo.getDescribe());
            }

            variety.setUpdateTime(new Date());

            varietyDao.save(variety);
        }


    }

    @Override
    public void deleteVariety(String id) {
        varietyDao.deleteById(id);
    }

    @Override
    public void deleteBatchVariety(String[] ids) {
        for (String id : ids) {
            deleteVariety(id);
        }
    }

}
