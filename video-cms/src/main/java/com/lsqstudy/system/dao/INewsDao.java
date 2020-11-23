package com.lsqstudy.system.dao;

import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-19 12:04
 */

public interface INewsDao extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {

    List<News> findAllByAvailable(Integer available);

    List<News> findAllByAvailableAndId(Integer available, Integer id);
}
