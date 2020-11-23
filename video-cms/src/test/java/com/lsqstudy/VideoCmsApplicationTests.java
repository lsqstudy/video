package com.lsqstudy;

import com.lsqstudy.system.dao.IAdministratorDao;
import com.lsqstudy.system.dao.IAdministratorLoginLogDao;
import com.lsqstudy.system.domain.*;
import com.lsqstudy.system.service.IAdministratorService;
import com.lsqstudy.system.service.IMenuService;
import com.lsqstudy.system.service.IPermissionService;
import com.lsqstudy.system.service.IRoleService;
import com.lsqstudy.system.util.ActivierAdministrator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class VideoCmsApplicationTests {
	@Autowired
	private IAdministratorService administratorService;
	@Autowired
	private IAdministratorDao administratorDao;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IAdministratorLoginLogDao administratorLoginLogDao;




	@Test
	void save() {
		//管理员
		Administrator administrator =new Administrator();
		administrator.setAdministratorName("lsq");
		administrator.setRealName("lsq");
		administrator.setPassword("123456");
		administrator.setSex(1);
		administrator.setBirthday(new Date());
		administrator.setPhone("15626179675");
		administrator.setWeixin("lsqstudy");
		administrator.setQq("45562875879");
		administrator.setEmail("4146515487@qq.com");
		administrator.setAddress("广东省-广州市-白云区");
		administrator.setType(1);
		administrator.setInterest("跑步");
		administrator.setSkill("java");
		administrator.setPosition("管理员");
		administrator.setAvatarUrl("https://gitee.com/lsqpic/BlogPicBed-1/raw/master/img/2020/04/17/20200418164903.jpg");
		administrator.setCreateTime(new Date());
		administrator.setUpdateTime(new Date());
		administrator.setAvailable(1);
		administrator.setUpdateTime(new Date());
		administrator.setCreateTime(new Date());

		//菜单
		Menu menu=new Menu();
		menu.setPid(0);
		menu.setIcon("&#xe68e;");
		menu.setTitle("卖剩鸭后台管理系统");
		menu.setHref(null);
		menu.setAvailable(1);
		menu.setSpread(1);
		menu.setTarget("");
		menu.setUpdateTime(new Date());
		menu.setCreateTime(new Date());

		//权限
		Permission queryPermission=new Permission();
		queryPermission.setAvailable(1);
		queryPermission.setPerName("查询");
		queryPermission.setPerCode("sys:query");
		queryPermission.setUpdateTime(new Date());
		queryPermission.setCreateTime(new Date());

		Permission addPermission=new Permission();
		addPermission.setAvailable(1);
		addPermission.setPerName("添加");
		addPermission.setPerCode("sys:add");
		addPermission.setUpdateTime(new Date());
		addPermission.setCreateTime(new Date());

		Permission updatePermission=new Permission();
		updatePermission.setAvailable(1);
		updatePermission.setPerName("修改");
		updatePermission.setPerCode("sys:update");
		updatePermission.setUpdateTime(new Date());
		updatePermission.setCreateTime(new Date());

		Permission deletePermission=new Permission();
		deletePermission.setAvailable(1);
		deletePermission.setPerName("删除");
		deletePermission.setPerCode("sys:delete");
		deletePermission.setUpdateTime(new Date());
		deletePermission.setCreateTime(new Date());

		//角色
		Role role =new Role();
		role.setAvailable(1);
		role.setRoleDesc("拥有所有权限");
		role.setRoleName("超级管理员");
		role.setUpdateTime(new Date());
		role.setCreateTime(new Date());

		//建立对象之间的关系
		administrator.getRoles().add(role);
		menu.getRoles().add(role);
		queryPermission.getRoles().add(role);
		addPermission.getRoles().add(role);
		updatePermission.getRoles().add(role);
		deletePermission.getRoles().add(role);

		queryPermission.getRoles().add(role);
		addPermission.getRoles().add(role);
		updatePermission.getRoles().add(role);
		deletePermission.getRoles().add(role);

		role.getAdministrators().add(administrator);
		role.getPermissions().add(queryPermission);
		role.getPermissions().add(addPermission);
		role.getPermissions().add(updatePermission);
		role.getPermissions().add(deletePermission);
		role.getMenus().add(menu);

		//保存操作
		roleService.addRole(role);
		administratorService.addAdministrator(administrator);
		menuService.addMenu(menu);
		permissionService.addPermission(queryPermission);
		permissionService.addPermission(addPermission);
		permissionService.addPermission(updatePermission);
		permissionService.addPermission(deletePermission);


	}

	@Test
	@Transactional
	void find() {
		//查询管理员
		Administrator admin = administratorService.findAdministratorById(1);
		System.out.println(admin);

		// 查询角色
		List<String> roles = roleService.findRoleListByAid(1);
		for (String role : roles) {
			System.out.println(role);
		}

		System.out.println("==============================");
		// 查询权限
		List<String> permissions = permissionService.findPermissionByAid(1);
		for (String permission : permissions) {
			System.out.println(permission);
		}
	}

	//分页，排序，模糊查询
	@Test
	@Transactional
	void findBySortAndPageAndLike() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime=sdf.parse("2020-11-19 18:56:33");
		Date endTime=sdf.parse("2020-11-20 18:56:33");

		Specification<AdministratorLoginLog> spec=new Specification<AdministratorLoginLog>() {
			@Override
			public Predicate toPredicate(Root<AdministratorLoginLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>(1);
				//2、构造查询条件
				Predicate p1 = cb.greaterThanOrEqualTo(root.get("loginTime").as(Date.class), startTime);
				list.add(p1);
				Predicate p2 = cb.lessThanOrEqualTo(root.get("loginTime").as(Date.class), endTime);
				list.add(p2);
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));

			}
		};


		List<AdministratorLoginLog> list = administratorLoginLogDao.findAll(spec);
		for (AdministratorLoginLog log : list) {
			System.out.println(log);
		}
	}

	@Test
	void testStat() {
		//查询管理员
		List<Integer> doubles = administratorLoginLogDao.selectOnlineStatList("2020-11-19");
		for (Integer doub : doubles) {
			System.out.println(doub);
		}
	}

}
