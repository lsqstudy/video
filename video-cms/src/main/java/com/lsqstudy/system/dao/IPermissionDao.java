package com.lsqstudy.system.dao;

import com.lsqstudy.system.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface IPermissionDao extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {

    List<Permission> findAllByAvailableAndId(Integer available, Integer id);

    List<Permission> findAllByAvailable(Integer available);


}