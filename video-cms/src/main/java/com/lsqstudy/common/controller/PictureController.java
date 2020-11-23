package com.lsqstudy.common.controller;

import java.util.Map;

import com.lsqstudy.common.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

//上传图片处理
@Controller
@RequestMapping("/file")
public class PictureController {

	@Autowired
	private IPictureService pictureService;
	
	@RequestMapping("/pic/upload.html")
	@ResponseBody
	public String pictureUpload(MultipartFile file){
		//type的含义：1.头像图片；2.文章图片；3.banner图片，4.相册图片；5.微语图片,6为技能图标
		Map result = pictureService.uploadPicture(file);
		//为了保证兼容性，需要把result转换成就json格式的字符串
		String json=JSON.toJSONString(result);
	
		return json;
	}
	
 }
