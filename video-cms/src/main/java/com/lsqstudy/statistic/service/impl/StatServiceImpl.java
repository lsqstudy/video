package com.lsqstudy.statistic.service.impl;

import java.util.List;

import com.lsqstudy.statistic.service.IStatService;
import com.lsqstudy.system.dao.IAdministratorLoginLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements IStatService {
	
	@Autowired
	private IAdministratorLoginLogDao administratorLoginLogDao;

	@Override
	public List<Integer> findOnlineStatList(String date) {
		return administratorLoginLogDao.selectOnlineStatList(date);
	}

}
