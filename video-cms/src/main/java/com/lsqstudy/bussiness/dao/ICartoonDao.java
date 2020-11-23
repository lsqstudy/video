package com.lsqstudy.bussiness.dao;

import com.lsqstudy.bussiness.domain.Cartoon;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-26 11:45
 */

public interface ICartoonDao extends MongoRepository<Cartoon, String> {

}
