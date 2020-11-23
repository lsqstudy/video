package com.lsqstudy.common.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {
	
	Map uploadPicture(MultipartFile uploadFile);

}
