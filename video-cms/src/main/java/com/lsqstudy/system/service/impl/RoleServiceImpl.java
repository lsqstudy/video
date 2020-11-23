package com.lsqstudy.system.service.impl;

import com.lsqstudy.common.constast.Constast;
import com.lsqstudy.common.util.DataGridView;
import com.lsqstudy.common.util.TreeNode;
import com.lsqstudy.system.dao.IAdministratorDao;
import com.lsqstudy.system.dao.IMenuDao;
import com.lsqstudy.system.dao.IPermissionDao;
import com.lsqstudy.system.dao.IRoleDao;
import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.domain.Menu;
import com.lsqstudy.system.domain.Permission;
import com.lsqstudy.system.domain.Role;
import com.lsqstudy.system.service.IRoleService;
import com.lsqstudy.system.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.security.Permissions;
import java.util.*;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IMenuDao menuDao;
    @Autowired
    private IPermissionDao permissionDao;
    @Autowired
    private IAdministratorDao administratorDao;


    @Override
    public DataGridView findRoleList(RoleVo roleVo) {
        //设置排序条件
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        //处理分页条件
        Pageable pageable = PageRequest.of(roleVo.getPage() - 1, roleVo.getLimit(), sort);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

        Example<Role> example = Example.of(roleVo.getRole(), matcher);
        Page<Role> page = roleDao.findAll(example, pageable);

        //清空关联对象
        List<Role> list = page.getContent();
        for (Role role : list) {
            role.getAdministrators().clear();
            role.getMenus().clear();
            role.getPermissions().clear();
        }

        return new DataGridView(page.getTotalElements(), page.getContent());
    }

    @Override
    public List<String> findRoleListByAid(Integer Aid) {
        Administrator administrator = administratorDao.findAllByAvailableAndId(Constast.AVAILABLE_TRUE, Aid);
        List<String> roleList = new ArrayList<String>();
        Set<Role> roles = administrator.getRoles();
        for (Role role : roles) {
            roleList.add(role.getRoleName());
        }

        return roleList;
    }

    @Override
    public void addRole(Role role) {
        role.setUpdateTime(new Date());
        role.setCreateTime(new Date());

        roleDao.save(role);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        Optional<Role> opt = roleDao.findById(roleVo.getId());
        if (opt.isPresent()) {
            Role role = opt.get();

            //补全信息
            if (null != roleVo.getRoleName()) {
                role.setRoleName(roleVo.getRoleName());
            }
            if (null != roleVo.getRoleDesc()) {
                role.setRoleDesc(roleVo.getRoleDesc());
            }
            if (null != roleVo.getAvailable()) {
                role.setAvailable(roleVo.getAvailable());
            }
            role.setUpdateTime(new Date());

            //更新
            roleDao.save(role);
        }

    }

    @Override
    public void deleteRole(Integer id) {
        // 删除角色表的数据,会自动根据角色id删除sys_role_meun里面的数据，根据角色id删除sys_role_administrator里面的数据
        roleDao.deleteById(id);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer rid : ids) {
            deleteRole(rid);
        }
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer id) {
        // 查询所有的可用的菜单
        List<Menu> allMenu = menuDao.findAllByAvailable(Constast.AVAILABLE_TRUE);
        // 根据角色ID查询当前角色拥有的菜单
        Optional<Role> roleopt = roleDao.findById(id);
        Set<Menu> roleMenu = null;
        if (roleopt.isPresent()) {
            Role role = roleopt.get();
            roleMenu = role.getMenus();
        }

        //拼接返回数据
        List<TreeNode> data = new ArrayList<>();
        for (Menu m1 : allMenu) {
            String checkArr = Constast.CODE_ZERO + "";
            for (Menu m2 : roleMenu) {
                if (m1.getId() == m2.getId()) {
                    checkArr = Constast.CODE_ONE + "";
                    break;
                }
            }

            Integer mid = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread() == Constast.SPREAD_TRUE ? true : false;
            data.add(new TreeNode(mid, pid, title, spread, checkArr));
        }
        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer[] mids = roleVo.getIds();
        //根据rid删除sys_role_menu里面所有数据
        //只要把角色先删除，然后重新设置菜单后保存即可
        Optional<Role> opt = roleDao.findById(roleVo.getId());
        if (opt.isPresent()) {
            Role role = opt.get();

            //清除以菜单中已经分配的当前角色
            //拿到角色中的菜单
            Set<Menu> menus = role.getMenus();
            //清除菜单中的当前角色
            for (Menu menu : menus) {
                //删除菜单中当前角色
                menu.getRoles().remove(role);
                //更新菜单与角色的关系
                menuDao.save(menu);
            }

            //清除角色的下的菜单
            role.getMenus().clear();

            //根据mids查询菜单，然后为菜单当前角色，并且为当前角色重新分配菜单
            if (null != mids) {//当取消所有分配的菜单时，mids有可能为null
                //先根据mids查询所有菜单，再设置给role
                for (Integer mid : mids) {
                    Optional<Menu> menuOpt = menuDao.findById(mid);
                    if (menuOpt.isPresent()) {
                        Menu menu = menuOpt.get();
                        //为菜单添加当前角色
                        menu.getRoles().add(role);
                        //更新菜单与角色的关系
                        menuDao.save(menu);

                        //为当前角色重新分配菜单
                        role.getMenus().add(menu);
                    }
                }
            }

            //更新当前角色
            roleDao.save(role);
        }

    }

    @Override
    public DataGridView findRolePermission(Integer id) {
        // 1,查询所有可用的权限
        List<Permission> allPermission = permissionDao.findAllByAvailable(Constast.AVAILABLE_TRUE);
        // 2,根据角色ID查询已拥有的权限
        // 根据角色ID查询当前角色拥有的菜单
        Optional<Role> roleopt = roleDao.findById(id);
        Set<Permission> rolerPermission = null;
        Set<Menu> roleMenu = null;
        if (roleopt.isPresent()) {
            Role role = roleopt.get();
            rolerPermission = role.getPermissions();
        }


        List<Map<String, Object>> data = new ArrayList<>();
        for (Permission per1 : allPermission) {
            Boolean LAY_CHECKED = false;
            for (Permission per2 : rolerPermission) {
                if (per1.getId() == per2.getId()) {
                    LAY_CHECKED = true;
                }
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleId", per1.getId());
            map.put("perName", per1.getPerName());
            map.put("perCode", per1.getPerCode());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    @Override
    public void saveRolePermission(RoleVo roleVo) {
        Integer[] pids = roleVo.getIds();
        //根据rid删除sys_role_permission里面所有数据
        //只要把角色先删除，然后重新设置权限后保存即可
        Optional<Role> opt = roleDao.findById(roleVo.getId());
        if (opt.isPresent()) {
            Role role = opt.get();
            //清除权限中已经分配的当前角色
            //拿到角色中的权限
            Set<Permission> permissions = role.getPermissions();
            //清除权限中的当前角色
            for (Permission permission : permissions) {
                //删除权限中当前角色
                permission.getRoles().remove(role);
                //更新权限与角色的关系
                permissionDao.save(permission);
            }

            //清除角色的下的权限
            role.getPermissions().clear();

            //根据mids查询菜单，然后为权限当前角色，并且为当前角色重新分配权限
            if (null != pids) {//当取消所有分配的权限时，mids有可能为null
                //先根据mids查询所有权限，再设置给role
                for (Integer pid : pids) {
                    Optional<Permission> pOpt = permissionDao.findById(pid);
                    if (pOpt.isPresent()) {
                        Permission permission = pOpt.get();
                        //为权限添加当前角色
                        permission.getRoles().add(role);
                        //更新权限与角色的关系
                        permissionDao.save(permission);

                        //为当前角色重新分配权限
                        role.getPermissions().add(permission);
                    }
                }
            }

            //更新当前角色
            roleDao.save(role);
        }
    }

}
