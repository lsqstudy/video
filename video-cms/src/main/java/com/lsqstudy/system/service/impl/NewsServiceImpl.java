package com.lsqstudy.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.system.dao.INewsDao;
import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.domain.News;
import com.lsqstudy.system.service.INewsService;
import com.lsqstudy.system.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;


@Service
@Transactional
public class NewsServiceImpl implements INewsService {

    @Autowired
    private INewsDao newsDao;

    @Override
    public DataGridView findNewsList(NewsVo NewsVo) {
        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(NewsVo.getPage() - 1, NewsVo.getLimit(), sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

        Example<News> example = Example.of(NewsVo.getNews(),matcher);
        Page<News>  page = newsDao.findAll(example, pageable);

        return new DataGridView(page.getTotalElements(), page.getContent());
    }

    @Override
    public News findNewsById(Integer id) {
        Optional<News> opt = newsDao.findById(id);

        return opt.isPresent() == true ? opt.get() : null;
    }

    @Override
    public void addNews(News news) {
        news.setUpdateTime(new Date());
        news.setCreateTime(new Date());

        newsDao.save(news);
    }

    @Override
    public void updateNews(NewsVo newsVo) {
        Optional<News> opt = newsDao.findById(newsVo.getId());
        if (opt.isPresent()) {
            News news = opt.get();
            if (null != newsVo.getTitle()) {
                news.setTitle(newsVo.getTitle());
            }
            if (null != newsVo.getContent()) {
                news.setContent(newsVo.getContent());
            }
            if (null != newsVo.getPublisher()) {
                news.setPublisher(newsVo.getPublisher());
            }
            if (null != newsVo.getAvailable()) {
                news.setAvailable(newsVo.getAvailable());
            }
            news.setUpdateTime(new Date());

            newsDao.save(news);
        }


    }

    @Override
    public void deleteNews(Integer id) {
        newsDao.deleteById(id);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer integer : ids) {
            deleteNews(integer);
        }
    }

}
