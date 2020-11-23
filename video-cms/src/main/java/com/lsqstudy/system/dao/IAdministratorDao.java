package com.lsqstudy.system.dao;

import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface IAdministratorDao  extends JpaRepository<Administrator, Integer>, JpaSpecificationExecutor<Administrator> {

    Administrator findAllByAvailableAndId(Integer available,Integer id);

    Administrator findByAdministratorName(String name);
}
