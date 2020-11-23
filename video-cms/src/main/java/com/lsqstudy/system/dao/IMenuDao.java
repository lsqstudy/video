package com.lsqstudy.system.dao;

import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IMenuDao extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {

    Long countByPid(Integer pid);

    Page<Menu> findAllByIdOrPid(Integer id, Integer pid, Pageable pageable);

    Page<Menu> findAllByTitleContaining(String title, Pageable pageable);

    List<Menu> findAllByAvailable(Integer available);

    List<Menu> findAllByAvailableAndId(Integer available, Integer id);

}