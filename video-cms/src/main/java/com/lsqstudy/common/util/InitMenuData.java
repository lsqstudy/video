package com.lsqstudy.common.util;


import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 初始化菜单数据
 *
 */
public class InitMenuData {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		
//		IMenuDao menuDao= context.getBean(IMenuDao.class);
//		
//		menuDao.insertMenu(new Menu(1, 0, "LSQ博客系统", null, 1, null, "&#xe68e;", 1));
//		menuDao.insertMenu(new Menu(2, 1, "基础管理", null, 0, null, "&#xe653;", 1));
//		menuDao.insertMenu(new Menu(3, 1, "业务管理", null, 0, null, "&#xe663;", 1));
//		menuDao.insertMenu(new Menu(4, 1, "系统管理", null, 1, null, "&#xe716;", 1));
//		menuDao.insertMenu(new Menu(5, 1, "统计分析", null, 0, null, "&#xe629;", 1));
//		
//		
//		menuDao.insertMenu(new Menu(6, 2, "客户管理", null, 0, null, "&#xe770;", 1));
//		menuDao.insertMenu(new Menu(7, 2, "导航栏管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(8, 2, "Banner管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(9, 2, "底部连接管理", null, 0, null, "&#xe657;", 1));
//		
//		menuDao.insertMenu(new Menu(10, 3, "文章管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(11, 3, "评论管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(12, 3, "微语管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(13, 3, "留言管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(14, 3, "相册管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(15, 3, "用户管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(16, 3, "微语管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(17, 3, "技能管理", null, 0, null, "&#xe657;", 1));
//		menuDao.insertMenu(new Menu(18, 3, "日志管理", null, 0, null, "&#xe657;", 1));
//		
//		menuDao.insertMenu(new Menu(19, 4, "菜单管理", "/admin/sys//toMenuManager.html", 0, null, "&#xe60f;", 1));
//		menuDao.insertMenu(new Menu(20, 4, "角色管理", null, 0, null, "&#xe66f;", 1));
//		menuDao.insertMenu(new Menu(21, 4, "用户管理", null, 0, null, "&#xe770;", 1));
//		menuDao.insertMenu(new Menu(22, 4, "日志管理", null, 0, null, "&#xe655;", 1));
//		menuDao.insertMenu(new Menu(23, 4, "公告管理", null, 0, null, "&#xe645;", 1));
//		menuDao.insertMenu(new Menu(24, 4, "数据源监控", null, 0, null, "&#xe857;", 1));
//		
//		menuDao.insertMenu(new Menu(25, 5, "客户地区统计", null, 0, null, "&#xe63c;", 1));
//		menuDao.insertMenu(new Menu(19, 5, "公司年度月份销售额", null, 0, null, "&#xe62c;", 1));
//		menuDao.insertMenu(new Menu(20, 5, "业务员年度销售额", null, 0, null, "&#xe62d;", 1));
		
		System.out.println("初始化完成");
		
	}
}
