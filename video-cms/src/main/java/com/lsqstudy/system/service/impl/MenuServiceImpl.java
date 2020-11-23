package com.lsqstudy.system.service.impl;

import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.system.dao.IAdministratorDao;
import com.lsqstudy.system.dao.IMenuDao;
import com.lsqstudy.system.dao.IRoleDao;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.domain.Role;
import com.lsqstudy.system.service.IMenuService;
import com.lsqstudy.system.vo.AdministratorVo;
import com.lsqstudy.system.vo.MenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuDao menuDao;
    @Autowired
    private IAdministratorDao administratorDao;

    @Override
    public List<Menu> findMenuListByMenuVo(MenuVo menuVo) {
        Example<Menu> example = Example.of(menuVo.getMenu());
        List<Menu> menuList = menuDao.findAll(example);
        return menuList;
    }

    @Override
    public DataGridView findMenuByMenuVo(MenuVo menuVo) {
        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(menuVo.getPage() - 1, menuVo.getLimit(),sort);
        Page<Menu> page = null;
        if (null != menuVo.getId()) {//如果id不等于null，同时查询出当前菜单下的子菜单
            menuVo.setPid(menuVo.getId());
            page = menuDao.findAllByIdOrPid(menuVo.getId(), menuVo.getId(), pageable);
        } else if (null != menuVo.getTitle()) {//如果title不为null，就进行模糊查询
            page = menuDao.findAllByTitleContaining(menuVo.getTitle(),pageable);
        } else {
            Example<Menu> example = Example.of(menuVo.getMenu());
            page = menuDao.findAll(example, pageable);
        }

        //清空关联对象
        List<Menu> list=page.getContent();
        for (Menu menu : list) {
            menu.getRoles().clear();
        }

        return new DataGridView(page.getTotalElements(), page.getContent());
    }

    @Override
    public List<Menu> findMenuListByAdministratorId(MenuVo menuVo, Integer id) {
        //根据id查询用户
        Optional<Administrator> opt = administratorDao.findById(id);
        Administrator administrator=opt.isPresent()==true?opt.get():null;
        //拿到用户角色
        Set<Role> roles = administrator.getRoles();
        List<Menu> menus=new ArrayList<>();
        for (Role role : roles) {
            menus.addAll(role.getMenus());
        }
        return menus;
    }

    @Override
    public void addMenu(Menu menu) {
        menu.setUpdateTime(new Date());
        menu.setCreateTime(new Date());

        menuDao.save(menu);
    }

    @Override
    public void updateMenu(MenuVo menuVo) {
        Optional<Menu> opt = menuDao.findById(menuVo.getId());
        if (opt.isPresent()) {
            Menu menu = opt.get();
            if (null != menuVo.getPid()) {
                menu.setPid(menuVo.getPid());
            }
            if (null != menuVo.getTitle()) {
                menu.setTitle(menuVo.getTitle());
            }
            if (null != menuVo.getHref()) {
                menu.setHref(menuVo.getHref());
            }
            if (null != menuVo.getSpread()) {
                menu.setSpread(menuVo.getSpread());
            }
            if (null != menuVo.getTarget()) {
                menu.setTarget(menuVo.getTarget());
            }
            if (null != menuVo.getIcon()) {
                menu.setIcon(menuVo.getIcon());
            }
            if (null != menuVo.getAvailable()) {
                menu.setAvailable(menuVo.getAvailable());
            }

            menu.setUpdateTime(new Date());

            menuDao.save(menu);
        }
    }

    @Override
    public Long findMenuCountByPid(Integer pid) {
        long count = menuDao.countByPid(pid);

        return count;
    }

    @Override
    public void deleteMenu(MenuVo menuVo) {
        // 删除菜单表的数据,springdata会自动 根据菜单id删除sys_role_menu里面的数据
        menuDao.deleteById(menuVo.getId());
    }

}
