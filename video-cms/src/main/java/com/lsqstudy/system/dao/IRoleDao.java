package com.lsqstudy.system.dao;

import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface IRoleDao extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    /**
     * 根据available与id查询
     * @param available
     * @param id
     * @return
     */
    List<Role> findAllByAvailableAndId(Integer available, Integer id);

    /**
     * 根据available查询
     * @param available
     * @return
     */
    List<Role> findAllByAvailable(Integer available);


    /**
     * 根据角色名称或角色描述进行模糊查询
     * @param roleName
     * @param roleDesc
     * @param pageable
     * @return
     */
    Page<Role> findAllByRoleNameContaining(String roleName,Pageable pageable);


}