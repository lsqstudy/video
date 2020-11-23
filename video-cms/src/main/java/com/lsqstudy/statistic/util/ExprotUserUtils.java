package com.lsqstudy.statistic.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 * 用户数据库导出
 * 
 *
 */
public class ExprotUserUtils {

/*
	 // 导出用户数据
	public static ByteArrayOutputStream exportUser(List<User> users, String sheetName) {
		// 一组装excel文档
		// 1,创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 2,创建样式
		HSSFCellStyle baseStyle = ExprotHSSFCellStyle.createBaseStyle(workbook);
		HSSFCellStyle subTitleStyle = ExprotHSSFCellStyle.createSubTitleStyle(workbook);
		HSSFCellStyle tableTitleStyle = ExprotHSSFCellStyle.createTableTitleStyle(workbook);
		HSSFCellStyle titleStyle = ExprotHSSFCellStyle.createTitleStyle(workbook);
		// 3在工作簿创建sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 4,设置sheet
		// sheet.setDefaultColumnWidth(25);
		// 4,设置sheet
		sheet.setDefaultColumnWidth(30);
		// 5,合并
		CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 15);
		CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 15);
		sheet.addMergedRegion(region1);
		sheet.addMergedRegion(region2);
		// 6,创建第一行
		int index = 0;
		HSSFRow row1 = sheet.createRow(index);
		// 6.1在第一行里面创建一个单元格
		HSSFCell row1_cell1 = row1.createCell(0);
		// 6.2设置标题样式
		row1_cell1.setCellStyle(titleStyle);
		// 6.3设置单元格内容
		row1_cell1.setCellValue("用户数据列表");

		// 7,第二行
		index++;
		HSSFRow row2 = sheet.createRow(index);
		// 7.1在第一行里面创建一个单元格
		HSSFCell row2_cell1 = row2.createCell(0);
		// 7.2设置标题样式
		row2_cell1.setCellStyle(subTitleStyle);
		// 7.3设置单元格内容
		row2_cell1.setCellValue("总条数:" + users.size() + "   导出时间:" + new Date().toLocaleString());

		// 8第三行
		String[] titles = { "用户名", "真实姓名", "性别", "用户职业", "用户生日", "用户电话", "用户微信", "用户QQ", "用户邮箱", "用户地址", "用户兴趣", "用户技能",
				"用户头像", "是否可用", "录入时间" };
		index++;
		HSSFRow row3 = sheet.createRow(index);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell row3_cell = row3.createCell(i);
			row3_cell.setCellStyle(tableTitleStyle);
			row3_cell.setCellValue(titles[i]);
		}
		// 9第四行
		for (int i = 0; i < users.size(); i++) {
			index++;
			User user = users.get(i);
			HSSFRow row = sheet.createRow(index);
			// 7.1设置行高
			row2.setHeightInPoints(150);
			// 9.1创建列用户名
			HSSFCell row_userName = row.createCell(0);
			row_userName.setCellStyle(baseStyle);
			row_userName.setCellValue(user.getUserName());
			// 9.2创建列用户姓名
			HSSFCell row_userRealName = row.createCell(1);
			row_userRealName.setCellStyle(baseStyle);
			row_userRealName.setCellValue(user.getRealName());
			// 9.3创建列性别
			HSSFCell row_sex = row.createCell(2);
			row_sex.setCellStyle(baseStyle);
			row_sex.setCellValue(user.getSex() == 1 ? "男" : "女");
			// 9.4创建列用户职位
			HSSFCell row_career = row.createCell(3);
			row_career.setCellStyle(baseStyle);
			row_career.setCellValue(user.getCareer());
			// 9.5创建列用户生日
			HSSFCell row_birthday = row.createCell(4);
			row_birthday.setCellStyle(baseStyle);
			row_birthday.setCellValue(user.getBirthday().toLocaleString());
			// 9.6创建列用户电话
			HSSFCell row_phone = row.createCell(5);
			row_phone.setCellStyle(baseStyle);
			row_phone.setCellValue(user.getPhone());
			// 9.7创建列用户微信
			HSSFCell row_weixin = row.createCell(6);
			row_weixin.setCellStyle(baseStyle);
			row_weixin.setCellValue(user.getWeixin());
			// 9.8创建列用户QQ
			HSSFCell row_qq = row.createCell(7);
			row_qq.setCellStyle(baseStyle);
			row_qq.setCellValue(user.getQq());
			// 9.9创建列用户邮箱
			HSSFCell row_email = row.createCell(8);
			row_email.setCellStyle(baseStyle);
			row_email.setCellValue(user.getEmail());
			// 9.10创建列用户地址
			HSSFCell row_address = row.createCell(9);
			row_address.setCellStyle(baseStyle);
			row_address.setCellValue(user.getAddress());
			// 9.11创建列用户兴趣
			HSSFCell row_interest = row.createCell(10);
			row_interest.setCellStyle(baseStyle);
			row_interest.setCellValue(user.getInterest());
			// 9.12创建列用户技能
			HSSFCell row_skill = row.createCell(11);
			row_skill.setCellStyle(baseStyle);
			row_skill.setCellValue(user.getSkill());
			// 9.13创建列用户头像
			HSSFCell row_avatar = row.createCell(12);
			row_avatar.setCellStyle(baseStyle);
			row_avatar.setCellValue("");

			// 9.14创建列是否可用
			HSSFCell row_available = row.createCell(13);
			row_available.setCellStyle(baseStyle);
			row_available.setCellValue(user.getAvailable());
			// 9.15创建列录入时间
			HSSFCell row_createtime = row.createCell(14);
			row_createtime.setCellStyle(baseStyle);
			row_createtime.setCellValue(user.getCreateTime().toLocaleString());
		}
		// 到此excel组装完成

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// 把workbook里面的数据写到outputStream
		try {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream;
	}*/

}
