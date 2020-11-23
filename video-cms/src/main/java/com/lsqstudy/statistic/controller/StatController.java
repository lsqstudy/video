package com.lsqstudy.statistic.controller;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.lsqstudy.statistic.service.IStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 统计分析
 *
 */
@RequestMapping("/stat")
@Controller
public class StatController {
	
	@Autowired
	private IStatService statService;
/*	@Autowired
	private IUserService userService;*/
	
	
	/**
	 * 跳转到系统压力访问页面
	 */
	@RequestMapping("/toOnlineStat.html")
	public String toOnlineStat() {
		
		return "/stat/onlineInfoStat";
	}
	
	/**
	 * 加载系统压力访问统计数据
	 */
	@RequestMapping("/loadOnlineStat.html")
	@ResponseBody
	public String loadOnlineStat(String date){
		List<Integer> entities=statService.findOnlineStatList(date);
		for (int i = 0; i < entities.size(); i++) {
			if(null==entities.get(i)) {
				entities.set(i, 0);
			}
		}
		String json=JSON.toJSONString(entities);
		
		return json;
	}
	
	/**
	 * Excel导出用户数据
	 */
	/*@RequestMapping("/exportUserInfo.html")
	public ResponseEntity<Object> exportUserInfo(UserVo userVo,HttpServletResponse response) {
		//查询用户信息
		List<User> users=userService.findUserListByUserVo(userVo);
		String fileName="用户数据.xls";
		String sheetName="用户数据";
		//生成excel表格
		ByteArrayOutputStream bos= ExprotUserUtils.exportUser(users,sheetName);
		
		try {
			fileName=URLEncoder.encode(fileName,"UTF-8");//处理文件名乱码
			//创建封装响应头信息的对象
			HttpHeaders header=new HttpHeaders();
			//封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
			header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			//设置下载的文件的名称
			header.setContentDispositionFormData("attachment", fileName);
			return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/


}
