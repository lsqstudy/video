package com.lsqstudy.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsqstudy.common.util.DataResult;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 工作台的控制器
 * @author LSQ
 *
 */
@Controller
@RequestMapping("/system/desk")
public class DeskManagerController {
	@Autowired
	private IAdministratorService administratorService;
	
	/**
	 * 跳转到工作台的页面
	 */
	@RequestMapping("/toDeskManager")
	public String toDeskManager(Model model,HttpServletRequest request,HttpServletResponse response) {
		DataResult blogResult = administratorService.getAdministratorByToken(request, response);
		Administrator administrator = (Administrator) blogResult.getData();
		model.addAttribute("administrator", administrator);
		
		return "/system/main/deskManager";
	}

}
