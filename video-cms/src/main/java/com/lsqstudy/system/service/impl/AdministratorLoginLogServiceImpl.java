package com.lsqstudy.system.service.impl;

import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.system.dao.IAdministratorLoginLogDao;
import com.lsqstudy.system.domain.AdministratorLoginLog;
import com.lsqstudy.system.service.IAdministratorLoginLogService;
import com.lsqstudy.system.vo.AdministratorLoginLogVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-18 23:00
 */
@Service
@Transactional
public class AdministratorLoginLogServiceImpl implements IAdministratorLoginLogService {
    @Autowired
    private IAdministratorLoginLogDao administratorLoginLogDao;

    @Override
    public DataGridView findAdministratorLoginLogList(AdministratorLoginLogVo administratorLoginLogVo) {
        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("loginTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(administratorLoginLogVo.getPage() - 1, administratorLoginLogVo.getLimit(),sort);
        //构建查询条件
        Specification<AdministratorLoginLog> spec=new Specification<AdministratorLoginLog>() {
            @Override
            public Predicate toPredicate(Root<AdministratorLoginLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>(1);
                //2、构造查询条件
                if (!StringUtils.isBlank(administratorLoginLogVo.getLoginName())){
                    list.add(cb.like(root.get("loginName").as(String.class),"%"+administratorLoginLogVo.getLoginName()+"%"));
                }
                if (!StringUtils.isBlank(administratorLoginLogVo.getLoginIp())){
                    list.add(cb.like(root.get("loginIp").as(String.class),"%"+administratorLoginLogVo.getLoginIp()+"%"));
                }
                if (null!=administratorLoginLogVo.getStartTime()){
                    list.add(cb.greaterThanOrEqualTo(root.get("loginTime").as(Date.class), administratorLoginLogVo.getStartTime()));
                }

                if (null!=administratorLoginLogVo.getEndTime()){
                    list.add(cb.lessThanOrEqualTo(root.get("loginTime").as(Date.class), administratorLoginLogVo.getEndTime()));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        Page<AdministratorLoginLog> page = administratorLoginLogDao.findAll(spec, pageable);

        return new DataGridView(page.getTotalElements(),page.getContent());
    }

    @Override
    public void addAdministratorLoginLog(AdministratorLoginLog administratorLoginLog) {
        administratorLoginLog.setLoginTime(new Date());
        administratorLoginLogDao.save(administratorLoginLog);
    }

    @Override
    public void deleteAdministratorLoginLog(Integer id) {
        administratorLoginLogDao.deleteById(id);
    }

    @Override
    public void deleteBatchAdministratorLoginLog(Integer[] ids) {
        for (Integer integer : ids) {
            this.deleteAdministratorLoginLog(integer);
        }
    }
}
