package com.lsqstudy.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.lsqstudy.common.constast.Constast;
import com.lsqstudy.common.util.CookieUtils;
import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.DataResult;
import com.lsqstudy.system.dao.IAdministratorDao;
import com.lsqstudy.system.dao.IRoleDao;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.domain.Role;
import com.lsqstudy.system.service.IAdministratorService;
import com.lsqstudy.system.util.MD5Utils;
import com.lsqstudy.system.vo.AdministratorVo;
import com.sun.el.parser.AstMinus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Ids;

import javax.persistence.Column;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class AdministratorServiceImpl implements IAdministratorService {

    @Autowired
    private IAdministratorDao administratorDao;
    @Autowired
    private IRoleDao roleDao;
    // ftp相关配置
    // @Value("${FTP_ADDRESS}")
    // private String FTP_ADDRESS;
    // @Value("${FTP_PORT}")
    // private Integer FTP_PORT;
    // @Value("${FTP_USERNAME}")
    // private String FTP_USERNAME;
    // @Value("${FTP_PASSWORD}")
    // private String FTP_PASSWORD;

    // @Value("${FTP_BASEPATH}")
    // private String FTP_BASEPATH;
    // @Value("${FTP_DIR_USER}")
    // private String FTP_DIR_USER;
    // @Value("${FTP_TEMP}")
    // private String FTP_TEMP;

    @Override
    public DataGridView findAdministratorList(AdministratorVo administratorVo) {
        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(administratorVo.getPage() - 1, administratorVo.getLimit(),sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

        //创建实例
        Example<Administrator> example = Example.of(administratorVo.getAdministrator(), matcher);
        Page<Administrator> page = administratorDao.findAll(example, pageable);

        //清空关联对象
        List<Administrator> list=page.getContent();
        for (Administrator administrator : list) {
            administrator.getRoles().clear();
        }

        return new DataGridView(page.getTotalElements(),list);
    }

    @Override
    public Administrator findAdministratorByAdministrator(Administrator administrator) {
        Example<Administrator> example = Example.of(administrator);
        Optional<Administrator> optional = administratorDao.findOne(example);
        Administrator admin = null;
        if (optional.isPresent()) {
            admin = optional.get();
        }

        return admin;
    }

    @Override
    public Administrator findAdministratorById(Integer id) {
        Optional<Administrator> optional = administratorDao.findById(id);
        Administrator administrator = null;
        if (optional.isPresent()) {
            administrator = optional.get();
        }

        return administrator;
    }

    @Override
    public Administrator findAdministratorByAdName(String administratorName) {
        Administrator administrator = administratorDao.findByAdministratorName(administratorName);

        return administrator;
    }

    @Override
    public DataResult checkData(String content, Integer type) {
        // 创建查询条件
        Administrator administrator = new Administrator();
        // 进行数据校验1，2，3分别表示administratorname,phone,email
        // 管理员名校验
        if (1 == type) {
            administrator.setAdministratorName(content);
            // 电话校验
        } else if (2 == type) {
            administrator.setPhone(content);
            // 邮箱校验
        } else {
            administrator.setEmail(content);
        }

        //去查询
        Example<Administrator> example = Example.of(administrator);
        Optional<Administrator> optional = administratorDao.findOne(example);
        Administrator admin = null;
        if (optional.isPresent()) {
            admin = optional.get();
        }

        if (admin != null) {
            return DataResult.ok(true);
        }

        return DataResult.ok(false);
    }

    @Override
    public DataResult addAdministrator(Administrator administrator) {
        // 补全信息
        // 如果头像为空，就设置默认头像
/*
		if (StringUtils.isBlank(administrator.getAvatarUrl())) {
			// 随机生成头像
			Random random = new Random();
			int number = random.nextInt(10);// 产生0-9的随机数
			administrator.setAvatarUrl("/portal/res/avatar/avatar" + number + ".jpg");
		} else {
			// 把头像从ftp服务中的 临时目录 移动到 目的目录
			String newPath = FtpUtil.renameOrChangePath(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
					administrator.getAvatarUrl(), FTP_TEMP, FTP_DIR_USER, null);
			administrator.setAvatarUrl(newPath);
		}
*/

        //对密码进行md5加密
        //加密的盐uuid
        String salt = UUID.randomUUID().toString();
        administrator.setSalt(salt);

        String password = null;
        if (StringUtils.isBlank(administrator.getPassword())) {
            // 设置默认密码,加密
            password = MD5Utils.md5(Constast.USER_DEFAULT_PWD, salt, 2);
        } else {
            password = MD5Utils.md5(Constast.USER_DEFAULT_PWD, salt, 2);
        }
        administrator.setPassword(password);

        if (null == administrator.getType()) {
            // 设置管理员类型 都是普通管理员type=2
            administrator.setType(Constast.USER_TYPE_NORMAL);
        }
        administrator.setCreateTime(new Date());
        administrator.setUpdateTime(new Date());

        administratorDao.save(administrator);

        return DataResult.ok();
    }

    @Override
    public DataResult administratorlogin(String administratorName, String password, HttpServletRequest request,
                                         HttpServletResponse response) {

        Administrator administrator = new Administrator();
        administrator.setAdministratorName(administratorName);


        Example<Administrator> example = Example.of(administrator);
        Optional<Administrator> optional = administratorDao.findOne(example);
        Administrator administratorInfo = optional.get();

        if (administratorInfo == null) {
            return DataResult.build(400, Constast.USER_LOGIN_ERROR_MSG);
        }

        if (!password.equals(administratorInfo.getPassword())) {
            return DataResult.build(400, Constast.USER_LOGIN_ERROR_MSG);
        }

        // 生成 token
        String token = UUID.randomUUID().toString();
        // 保存管理员之前把对象密码清空
        administrator.setPassword(null);

        // 把管理员信息写入Session中
        HttpSession session = request.getSession();
        session.setAttribute(token, JSON.toJSONString(administratorInfo));

        // 单位：秒。30秒有效期，默认30分钟,这里就不设置了session的过期时间是从session不活动的时候开始计算，
        // 如果session一直活动，session就总不会过期，从该Session未被访问,开始计时; 一旦Session被访问,计时清0;
        // session.setMaxInactiveInterval(30);

        // 设置cookie的逻辑 ，cookie的有效期为关闭浏览器就失效
        // Cookie cookie = new Cookie("BLOG_TOKEN", token);
        // cookie.setPath("/");
        // response.addCookie(cookie);

        CookieUtils.setCookie(request, response, Constast.USER_LOGIN_TOKEN, token);

        // 返回 token
        return DataResult.ok(token);
    }

    // 根据token获取管理员信息
    @Override
    public DataResult getAdministratorByToken(HttpServletRequest request, HttpServletResponse response) {
        String token = null;
        // 获取cookie中的token
        token = CookieUtils.getCookieValue(request, Constast.USER_LOGIN_TOKEN);

        // 根据token从Session中获取管理员信息
        Administrator administrator = (Administrator) request.getSession().getAttribute(token);

        // 判断是否为空
        if (null == administrator) {
            return DataResult.build(Constast.CODE_ERROR, Constast.LOGIN_EXPIRE);
        }
        return DataResult.ok(administrator);
    }

    // 根据token删除管理员信息
    @Override
    public DataResult deleteByToken(HttpServletRequest request, HttpServletResponse response) {
        // 获取token
        String token = CookieUtils.getCookieValue(request, Constast.USER_LOGIN_TOKEN);
        // 根据token从redis中查询管理员信息
        request.getSession().removeAttribute(token);

        return DataResult.ok();
    }

    @Override
    public DataResult updateAdministrator(Administrator administrator, boolean isDeleteImg) {

        Optional<Administrator> opt = administratorDao.findById(administrator.getId());

        if (opt.isPresent()) {
            Administrator admin = opt.get();

            //补充信息
            //重新加密密码
            //如果输入密码不为空，则用输入的密码加密，否则用原始密码加密
            if (null != administrator.getPassword()) {
                String password = MD5Utils.md5(administrator.getPassword(), admin.getSalt(), 2);
                admin.setPassword(password);
            }

            if (null != administrator.getAdministratorName()) {
                admin.setAdministratorName(administrator.getAdministratorName());
            }
            if (null != administrator.getRealName()) {
                admin.setRealName(administrator.getRealName());
            }
            if (null != administrator.getSex()) {
                admin.setSex(administrator.getSex());
            }
            if (null != administrator.getBirthday()) {
                admin.setBirthday(administrator.getBirthday());
            }
            if (null != administrator.getPhone()) {
                admin.setPhone(administrator.getPhone());
            }
            if (null != administrator.getWeixin()) {
                admin.setWeixin(administrator.getWeixin());
            }
            if (null != administrator.getQq()) {
                admin.setQq(administrator.getQq());
            }
            if (null != administrator.getEmail()) {
                admin.setEmail(administrator.getEmail());
            }
            if (null != administrator.getAddress()) {
                admin.setAddress(administrator.getAddress());
            }
            if (null != administrator.getType()) {
                admin.setType(administrator.getType());
            }
            if (null != administrator.getInterest()) {
                admin.setInterest(administrator.getInterest());
            }
            if (null != administrator.getSkill()) {
                admin.setSkill(administrator.getSkill());
            }
            if (null != administrator.getPosition()) {
                admin.setPosition(administrator.getPosition());
            }
            if (null != administrator.getAvatarUrl()) {
                admin.setAvatarUrl(administrator.getAvatarUrl());
            }
            if (null != administrator.getAvailable()) {
                admin.setAvailable(administrator.getAvailable());
            }
            admin.setUpdateTime(new Date());


            // 把头像从ftp服务中的 临时目录 移动到 目的目录
            // String newPath = FtpUtil.renameOrChangePath(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
            // 		administrator.getAvatarUrl(), FTP_TEMP, FTP_DIR_USER, null);
            // administrator.setAvatarUrl(newPath);

            // if (isDeleteImg && !StringUtils.isBlank(administrator.getAvatarUrl())) {
            // 	try {
            // 		// 尝试删除原来的图片
            // 		Administrator us = administratorDao.selectAdministratorById(administrator.getId());
            // 		if (us != null) {
            // 			// 判断是否更改了图片
            // 			if (us.getAvatarUrl() != null) {
            // 				if (!us.getAvatarUrl().equals(administrator.getAvatarUrl())) {
            // 					String path = us.getAvatarUrl();
            // 					// 删除,返回true则成功
            // 					FtpUtil.deleteFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, path);
            // 				}
            // 			}
            // 		}
            // 	} catch (Exception e) {
            // 		e.printStackTrace();
            // 	}
            // }

            administratorDao.save(admin);

            return DataResult.ok(Constast.UPDATE_SUCCESS);
        }

        return DataResult.build(Constast.CODE_ERROR, Constast.UPDATE_ERROR);
    }

    // 删除管理员
    @Override
    public void deleteAdministratorById(Integer id) {
        // try {
        //     // 尝试删除原来的图片
        //     Optional<Administrator> opt = administratorDao.findById(id);
        //     if (opt.isPresent()) {
        //         Administrator administrator = opt.get();
        //         // 判断是否存在图片
        //         if (administrator.getAvatarUrl() != null) {
        //             String path = administrator.getAvatarUrl();
        //             // 删除,返回true则成功
        //             FtpUtil.deleteFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, path);
        //         }
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        administratorDao.deleteById(id);

    }

    @Override
    public void deleteBatchAdministratorByIds(Integer[] ids) {
        for (Integer uid : ids) {
            deleteAdministratorById(uid);
        }
    }

    @Override
    public void resetAdministratorPwd(Administrator administrator) {
        Administrator admin = findAdministratorById(administrator.getId());
        // 设置默认密码
        // 设置默认密码,加密
        String password = MD5Utils.md5(Constast.USER_DEFAULT_PWD, admin.getSalt(), 2);
        admin.setPassword(password);
        // 更新
        administratorDao.save(admin);
    }

    @Override
    public DataGridView findAdministratorRole(Integer id) {
        // 1,查询所有可用的角色
        List<Role> allRole = roleDao.findAllByAvailable(Constast.AVAILABLE_TRUE);

        // 2,根据管理员ID查询已拥有的角色
        Set<Role> administratorRole=null;
        Optional<Administrator> opt = administratorDao.findById(id);
        if (opt.isPresent()){
            Administrator administrator = (Administrator) opt.get();
            administratorRole =administrator.getRoles();
        }

        List<Map<String, Object>> data = new ArrayList<>();
        for (Role r1 : allRole) {
            Boolean LAY_CHECKED = false;
            for (Role r2 : administratorRole) {
                if (r1.getId() == r2.getId()) {
                    LAY_CHECKED = true;
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("roleId", r1.getId());
            map.put("roleName", r1.getRoleName());
            map.put("roleDesc", r1.getRoleDesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    @Override
    public void saveAdministratorRole(AdministratorVo administratorVo) {
        Integer[] roleIds = administratorVo.getIds();
        // 根据管理员id删除sys_role_administrator里面的数据
        //只要把管理员先删除，然后重新设置角色保存即可
        Optional<Administrator> opt = administratorDao.findById(administratorVo.getId());
        if(opt.isPresent()){
            Administrator admin=opt.get();

            //先删除原来的用户和角色
            admin.getRoles().clear();
            administratorDao.save(admin);

            if (null!=roleIds){//当取消所有分配的角色是，roleIds为null
                //先根据ids查询所有角色，再设置给admin
                for (Integer roleId : roleIds) {
                    Optional<Role> roleOpt = roleDao.findById(roleId);
                    if (roleOpt.isPresent()){
                        admin.getRoles().add(roleOpt.get());
                    }
                }
            }

            //重新保存管理员
            administratorDao.save(admin);
        }
    }

}
