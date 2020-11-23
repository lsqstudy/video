package com.lsqstudy.bussiness.service.impl;

import com.lsqstudy.bussiness.dao.ICartoonDao;
import com.lsqstudy.bussiness.domain.Cartoon;
import com.lsqstudy.bussiness.service.ICartoonService;
import com.lsqstudy.bussiness.vo.CartoonVo;
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
public class CartoonServiceImpl implements ICartoonService {
    @Autowired
    private ICartoonDao cartoonDao;

    @Override
    public DataGridView findCartoonList(CartoonVo cartoonVo) {
        if (StringUtils.isBlank(cartoonVo.getTitle())){
            cartoonVo.setTitle(null);
        }
        if (StringUtils.isBlank(cartoonVo.getAlex())){
            cartoonVo.setAlex(null);
        }
        if (null!=cartoonVo.getType()&&cartoonVo.getType().size()==0){
            cartoonVo.setType(null);
        }

        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(cartoonVo.getPage() - 1, cartoonVo.getLimit(), sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("_id")//忽略属性
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withIgnoreCase(true)//忽略大小写
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //改变默认字符串匹配方式：模糊查询

        Example<Cartoon> example = Example.of(cartoonVo.getCartoon(),matcher);
        Page<Cartoon> page = cartoonDao.findAll(example, pageable);

        return new DataGridView(page.getTotalElements(), page.getContent());
    }

    @Override
    public Cartoon findCartoonById(String id) {
        Optional<Cartoon> opt = cartoonDao.findById(id);

        return opt.isPresent() == true ? opt.get() : null;
    }


    @Override
    public void updateCartoon(CartoonVo cartoonVo) {
        Optional<Cartoon> opt = cartoonDao.findById(cartoonVo.getId());
        if (opt.isPresent()) {
            Cartoon cartoon = opt.get();
            if (null != cartoonVo.getTitle()) {
                cartoon.setTitle(cartoonVo.getTitle());
            }
            if (null != cartoonVo.getAlex()) {
                cartoon.setAlex(cartoonVo.getAlex());
            }
            if (null != cartoonVo.getType()) {
                cartoon.getType().addAll(cartoonVo.getType());
            }
            if (null != cartoonVo.getYear()) {
                cartoon.setYear(cartoonVo.getYear());
            }
            if (null != cartoonVo.getInfo()) {
                cartoon.setInfo(cartoonVo.getInfo());
            }

            if (null != cartoonVo.getPoster()) {
                cartoon.setPoster(cartoonVo.getPoster());
                if (null != cartoonVo.getIsBanner()) {
                    cartoon.setIsBanner(cartoonVo.getIsBanner());
                }

            }
            if (null != cartoonVo.getDescribe()) {
                cartoon.setDescribe(cartoonVo.getDescribe());
            }

            cartoon.setUpdateTime(new Date());

            cartoonDao.save(cartoon);
        }


    }

    @Override
    public void deleteCartoon(String id) {
        cartoonDao.deleteById(id);
    }

    @Override
    public void deleteBatchCartoon(String[] ids) {
        for (String id : ids) {
            deleteCartoon(id);
        }
    }

}
