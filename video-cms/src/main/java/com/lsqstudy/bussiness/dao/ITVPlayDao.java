package com.lsqstudy.bussiness.dao;

import com.lsqstudy.bussiness.domain.TVPlay;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-26 11:45
 */

public interface ITVPlayDao extends MongoRepository<TVPlay, String> {

}
