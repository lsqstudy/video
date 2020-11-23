package com.lsqstudy.system.controller;

import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lsqstudy.common.constast.Constast;
import com.lsqstudy.common.util.CookieUtils;
import com.lsqstudy.common.util.DataResult;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.AdministratorLoginLog;
import com.lsqstudy.system.service.IAdministratorLoginLogService;
import com.lsqstudy.system.service.IAdministratorService;
import com.lsqstudy.system.util.ActivierAdministrator;
import com.lsqstudy.system.util.MD5Utils;
import com.lsqstudy.system.vo.AdministratorVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;

@Controller
@RequestMapping("system")
public class AdministratorLoginController {
	@Autowired
	private IAdministratorService administratorService;
	@Autowired
	private IAdministratorLoginLogService administratorLoginLogService;

	// 去登录页面
	@RequestMapping(value = "/login.html")
	public ModelAndView administratorLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		// 从token中获取管理员信息
		DataResult blogResult = administratorService.getAdministratorByToken(request, response);
		if (blogResult.getStatus() == 200) {// 如果从token中获取到管理员信息
			mv.setViewName("redirect:index.html");
		} else {
			mv.setViewName("system/main/login");
		}

		return mv;
	}

	// 登录
	@RequestMapping(value = "/doLogin.html", method = RequestMethod.POST)
	public ModelAndView doLogin(AdministratorVo administratorVo, HttpServletRequest request,
								HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();

		// 保存输入信息
		mv.addObject("administratorVo", administratorVo);

		// 获取验证码
		String code = (String) request.getSession().getAttribute("code");
		// if (administratorVo.getCode().equals(code)) {
		if (true) {
			// 得到主体
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(administratorVo.getAdministratorName(),
					administratorVo.getPassword());
			try {
				subject.login(token);
				// 得到查询出来的管理员
				ActivierAdministrator activierAd = (ActivierAdministrator) subject.getPrincipal();
				Administrator administrator = activierAd.getAdministrator();
				String cookieToken = IdUtil.simpleUUID();
				// 设置session
				request.getSession().setAttribute(cookieToken, administrator);
				// 设置cookie
				CookieUtils.setCookie(request, response, "Admin_TOKEN", cookieToken);
				// 记录登陆日志 向sys_administrator_login_log里面插入数据
				AdministratorLoginLog adminLoginLog = new AdministratorLoginLog();
				adminLoginLog.setLoginTime(new Date());
				adminLoginLog.setLoginName(administrator.getAdministratorName());
				adminLoginLog.setLoginIp(request.getRemoteAddr());
				adminLoginLog.setRealName(administrator.getRealName());
				administratorLoginLogService.addAdministratorLoginLog(adminLoginLog);

				mv.setViewName("redirect:index.html");
			} catch (AuthenticationException e) {// 登录失败，返回登录页面
				e.printStackTrace();
				mv.addObject("result", Constast.USER_LOGIN_ERROR_MSG);
				mv.setViewName("system/main/login");
			}
		} else {// 验证码错误，登录失败，返回登录页面
			mv.addObject("result", Constast.USER_LOGIN_CODE_ERROR_MSG);
			mv.setViewName("system/main/login");
		}

		return mv;
	}

	// 去主页面
	@RequiresPermissions(value="sys:query")
	@RequestMapping(value = "/index.html")
	public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();

		DataResult blogResult = administratorService.getAdministratorByToken(request, response);
		Administrator administrator = (Administrator) blogResult.getData();

		if (null != administrator) {
			mv.addObject("administrator", administrator);
			mv.setViewName("system/main/index");
		} else {
			mv.setViewName("system/main/login");
		}

		return mv;
	}

	// 退出登录
	@RequestMapping(value = "/logout.html")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();

		DataResult dataResult = administratorService.deleteByToken(request, response);
		if (dataResult.getStatus() == 200) {
			mv.setViewName("system/main/login");
		}

		return mv;
	}

	/**
	 * 检验密码：解锁，密码验证
	 */
	@RequestMapping("/checkPwd.html")
	@ResponseBody
	public String checkPwd(Integer id, String password) {
		Integer result = Constast.CODE_ERROR;
		try {
			Administrator administrator = administratorService.findAdministratorById(id);
			//对密码进行加密
			String pwd = MD5Utils.md5(password, administrator.getSalt(), 2);
			if (null != administrator && administrator.getPassword().equals(pwd)) {
				result = Constast.CODE_SUCCESS;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = JSON.toJSONString(result);

		return json;
	}

	/**
	 * 得到登陆验证码
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/getCode.html")
	public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
		// 定义图形验证码的长和宽
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 20);
		session.setAttribute("code", lineCaptcha.getCode());
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(lineCaptcha.getImage(), "JPEG", outputStream);
	}

}
