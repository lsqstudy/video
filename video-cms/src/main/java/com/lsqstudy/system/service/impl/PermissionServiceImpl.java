package com.lsqstudy.system.service.impl;


import com.lsqstudy.common.constast.Constast;
import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.system.dao.IAdministratorDao;
import com.lsqstudy.system.dao.IPermissionDao;
import com.lsqstudy.system.dao.IRoleDao;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.domain.Permission;
import com.lsqstudy.system.domain.Role;
import com.lsqstudy.system.service.IPermissionService;
import com.lsqstudy.system.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.util.*;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private IPermissionDao permissionDao;
	@Autowired
	private IAdministratorDao administratorDao;

	@Override
	public DataGridView findPermissionList(PermissionVo permissionVo) {
		//设置排序条件
		Sort sort = Sort.by(Sort.Order.desc("updateTime"));
		//处理分页条件
		Pageable pageable = PageRequest.of(permissionVo.getPage()-1,permissionVo.getLimit(),sort);
		//创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
				.withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

		Example<Permission> example = Example.of(permissionVo.getPermission(),matcher);
		Page<Permission> page = permissionDao.findAll(example, pageable);

		//清空关联对象
		List<Permission> list=page.getContent();
		for (Permission permission : list) {
			permission.getRoles().clear();
		}

		return new DataGridView(page.getTotalElements(), page.getContent());
	}

	@Override
	public List<String> findPermissionByAid(Integer Aid) {

		Administrator administrator = administratorDao.findAllByAvailableAndId(Constast.AVAILABLE_TRUE,Aid);
		List<String> perimssionList = new ArrayList<>();
		for (Role role : administrator.getRoles()) {
				Set<Permission> permissions = role.getPermissions();
				for (Permission permission : permissions) {
					perimssionList.add(permission.getPerCode());
				}
		}

		return perimssionList;
	}

	@Override
	public void addPermission(Permission permission) {
		permission.setUpdateTime(new Date());
		permission.setCreateTime(new Date());

		permissionDao.save(permission);

	}

	@Override
	public void updatePermission(PermissionVo permissionVo) {
		Optional<Permission> opt = permissionDao.findById(permissionVo.getId());
		if (opt.isPresent()){
			Permission permission = opt.get();
			if (null!=permissionVo.getPerCode()){
				permission.setPerCode(permissionVo.getPerCode());
			}
			if (null!=permissionVo.getPerName()){
				permission.setPerName(permissionVo.getPerName());
			}
			if (null!=permissionVo.getAvailable()){
				permission.setAvailable(permissionVo.getAvailable());
			}
			permission.setUpdateTime(new Date());

			permissionDao.save(permission);
		}
	}

	@Override
	public void deletePermission(Integer id) {
		// 删除权限表的数据,会自动根据角色id删除sys_role_permission里面的数据
		permissionDao.deleteById(id);
	}

	@Override
	public void deleteBatchPermission(Integer[] ids) {
		for (Integer pid : ids) {
			deletePermission(pid);
		}

	}

}
