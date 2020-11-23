package com.lsqstudy.dao;

import com.lsqstudy.domain.Cartoon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-26 11:45
 */

public interface ICartoonDao extends MongoRepository<Cartoon, String> {

    //根据标题查询
    Page<Cartoon>  findByTitleLike(String title, Pageable pageable);

    //根据演员进行模糊查询
    Page<Cartoon>  findByActorLike(String actor, Pageable pageable);

    //根据类型进行模糊查询
    Page<Cartoon>  findByTypeLike(String type, Pageable pageable);

    //根据is_banner查询
    Page<Cartoon>  findByIsBanner(Integer isBanner, Pageable pageable);

}
