package com.lsqstudy.service.impl;

import com.lsqstudy.common.Constast;
import com.lsqstudy.dao.IMoviceDao;
import com.lsqstudy.domain.Cartoon;
import com.lsqstudy.domain.Movice;
import com.lsqstudy.domain.TVPlay;
import com.lsqstudy.service.IMoviceService;
import com.lsqstudy.vo.VideoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 15:10
 */
@Service
public class MoviceServiceImpl implements IMoviceService {
    @Autowired
    private IMoviceDao moviceDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Movice> findAll(VideoVo videoVo) {
        if (StringUtils.isEmpty(videoVo.getType())){
            videoVo.setType(null);
        }
        if (StringUtils.isEmpty(videoVo.getYear())){
            videoVo.setYear(null);
        }
        if (StringUtils.isEmpty(videoVo.getRegion())){
            videoVo.setRegion(null);
        }

        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(videoVo.getPage(), videoVo.getLimit(), sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("_id")//忽略属性
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withIgnoreCase(true)//忽略大小写
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //改变默认字符串匹配方式：模糊查询

        Example<Movice> example = Example.of(videoVo.getMovice(),matcher);
        Page<Movice> page = moviceDao.findAll(example, pageable);

        return  page;
    }


    @Override
    public Page<Movice> findByFilter(VideoVo videoVo) {
        Integer page = videoVo.getPage();
        Integer limit = videoVo.getLimit();
        Integer year = videoVo.getYear();
        List<String> type = videoVo.getType();
        String region = videoVo.getRegion();

        Query query = new Query();
        //设置起始数
        query.skip((page - 1) * limit);
        //设置查询条数
        query.limit(limit);
        if (null!=type||null!=year||null!=region){
            //组装查询条件
            Criteria criteria = new Criteria();
            if (null != type) {
                criteria.and("type").in(type);
            }
            if (null!=region) {
                criteria.and("region").is(region);
            }
            if (null != year && year == 2) {//更早的年份
                criteria.and("year").lt(Constast.YEAR);
            } else if (null != year && year != 2) {//按年份查询
                criteria.and("year").is(year);
            }
            query.addCriteria(criteria);
        }

        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        query.with(sort);

        List<Movice> list = mongoTemplate.find(query, Movice.class);


        /**
         * 注意：查询偏移量(offset)和限制条数(limit)可能会直接影响最终查询文档的数量，因为这些值会传递到服务器，
         * 并可能限制服务器执行计数操作的范围和顺序。所以要使用没有分页数据的对象来查。
         * 所以问题的解决办法是把分页的offset和limit去掉即可
         */
        BasicQuery basicQuery = new BasicQuery(query.getQueryObject().toJson());
        // 查询记录总数
        Long totalCount = mongoTemplate.count(basicQuery, Movice.class);
        totalCount = totalCount+videoVo.getLimit();//处理少了一页的问题
        //设置分页条件
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<Movice> resultPage = new PageImpl<>(list, pageable, totalCount);


        return resultPage;
    }

    @Override
    public Movice findMoviceById(String id) {
        Movice movice = mongoTemplate.findById(id, Movice.class);
        return movice;
    }

    @Override
    public Map<String,Object> search(VideoVo videoVo) {
        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //设置分页条件
        Pageable pageable = PageRequest.of(videoVo.getPage(), videoVo.getLimit(), sort);
        Page<Movice> titlePage = moviceDao.findByTitleLike(videoVo.getContent(),pageable);;
        Page<Movice> typePage = moviceDao.findByTypeLike(videoVo.getContent(), pageable);
        Page<Movice> actorPage = moviceDao.findByActorLike(videoVo.getContent(), pageable);

        //组装返回数据
        Map<String,Object> map=new HashMap<>();
        //处理数据
        List<Movice> list = new ArrayList<>();
        list.addAll(titlePage.getContent());
        list.addAll(typePage.getContent());
        list.addAll(actorPage.getContent());
        map.put("list", list);
        //处理总页数
        Integer totalPage=titlePage.getTotalPages()>typePage.getTotalPages()?titlePage.getTotalPages():typePage.getTotalPages();
        totalPage=totalPage>actorPage.getTotalPages()?totalPage:actorPage.getTotalPages();
        map.put("totalPage",totalPage);

        //组装Page

        return map;
    }

    @Override
    public Page<Movice> finBannerList(VideoVo videoVo) {
        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //设置分页条件
        Pageable pageable = PageRequest.of(videoVo.getPage(), videoVo.getLimit(), sort);
        Page<Movice> p = moviceDao.findByIsBanner(Constast.IS_BANNER,pageable);

        return p;
    }


}
