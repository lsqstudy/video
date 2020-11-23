package com.lsqstudy.system.realm;

import java.util.List;

import com.lsqstudy.system.domain.Administrator;
import com.lsqstudy.system.service.IAdministratorService;
import com.lsqstudy.system.service.IPermissionService;
import com.lsqstudy.system.service.IRoleService;
import com.lsqstudy.system.util.ActivierAdministrator;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class AdministratorRealm extends AuthorizingRealm {
	@Autowired
	private IAdministratorService administratorService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String administratorName = token.getPrincipal().toString();

		// 以前登陆的逻辑是 把用户和密码全部发到数据库 去匹配
		// 在shrio里面是先根据用户名把用户对象查询出来，再来做密码匹配

		// 根据用户名查询用户
		Administrator administrator = administratorService.findAdministratorByAdName(administratorName);
		System.out.println("shiro验证的管理员："+administrator);
		if (null != administrator) {
			// 查询角色
			List<String> roles = roleService.findRoleListByAid(administrator.getId());
			System.out.println("shiro验证的角色："+roles);
			// 查询权限
			List<String> permissions = permissionService.findPermissionByAid(administrator.getId());
			System.out.println("shiro验证的权限："+permissions);
			// 构造ActiverUser
			ActivierAdministrator activierAd = new ActivierAdministrator(administrator, roles, permissions);

			/**
			 * 不加密，不加盐，同时必须配置文件中的加密注入去掉
			 * 参数说明
			 * 参数1:可以传到任意对象
			 * 参数2:从数据库里面查询出来的密码
			 * 参数3:当前类名
			 */
			// SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(activierAd, administrator.getPassword(), this.getName());


			// 创建盐
			ByteSource credentialsSalt = ByteSource.Util.bytes(administrator.getSalt());
			/**
			 * 加盐
			 * 参数说明
			 * 参数1:可以传到任意对象
			 * 参数2:从数据库里面查询出来的散列之后的密码
			 * 参数3:盐
			 * 参数4:当前类名
			 */
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activierAd, administrator.getPassword(), credentialsSalt, this.getName());
			return info;
		} else {
			//用户不存在  shiro会抛 UnknowAccountException
			return null;
		}
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ActivierAdministrator activierUser = (ActivierAdministrator) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		List<String> roles = activierUser.getRoles();
		List<String> permissions = activierUser.getPermissions();
		//添加角色
		if (null != roles && roles.size() > 0) {
			info.addRoles(roles);
		}
		//添加权限
		if (null != permissions && permissions.size() > 0) {
			info.addStringPermissions(permissions);
		}
		return info;
	}

}
