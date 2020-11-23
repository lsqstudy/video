package com.lsqstudy.dao;

import com.lsqstudy.domain.TVPlay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-26 11:45
 */

public interface ITVPlayDao extends MongoRepository<TVPlay, String> {

    //根据标题查询
    Page<TVPlay> findByTitleLike(String title, Pageable pageable);

    //根据演员进行模糊查询
    Page<TVPlay> findByActorLike(String actor, Pageable pageable);

    //根据类型进行模糊查询
    Page<TVPlay> findByTypeLike(String type, Pageable pageable);

    //根据is_banner查询
    Page<TVPlay>  findByIsBanner(Integer isBanner, Pageable pageable);

}
