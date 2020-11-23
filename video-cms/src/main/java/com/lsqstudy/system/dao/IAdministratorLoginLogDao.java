package com.lsqstudy.system.dao;

import com.lsqstudy.system.domain.AdministratorLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-18 22:54
 */

public interface IAdministratorLoginLogDao extends JpaRepository<AdministratorLoginLog, Integer>, JpaSpecificationExecutor<AdministratorLoginLog> {

    @Query(value="select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'00')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'01')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'02')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'03')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'04')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'05')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'06')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'07')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'08')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'09')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'10')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'11')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'12')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'13')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'14')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'15')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'16')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'17')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'18')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'19')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'20')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'21')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'22')" +
            " UNION all  " +
            "select count(1) from sys_administrator_login_log WHERE DATE_FORMAT(login_time, '%Y-%m-%d%H') = CONCAT(?1,'23')",nativeQuery=true)
    List<Integer> selectOnlineStatList(String date);

}
