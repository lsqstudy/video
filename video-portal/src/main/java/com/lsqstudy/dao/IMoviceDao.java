package com.lsqstudy.dao;

import com.lsqstudy.domain.Movice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-26 11:45
 */

public interface IMoviceDao extends MongoRepository<Movice, String> {

    //根据标题查询
    Page<Movice> findByTitleLike(String title, Pageable pageable);

    //根据演员进行模糊查询
    Page<Movice> findByActorLike(String actor, Pageable pageable);

    //根据类型进行模糊查询
    Page<Movice> findByTypeLike(String type, Pageable pageable);

    //根据is_banner查询
    Page<Movice>  findByIsBanner(Integer isBanner, Pageable pageable);

}
