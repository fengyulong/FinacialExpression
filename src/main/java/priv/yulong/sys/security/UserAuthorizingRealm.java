package priv.yulong.sys.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import priv.yulong.sys.model.Permission;
import priv.yulong.sys.model.Role;
import priv.yulong.sys.model.User;
import priv.yulong.sys.service.PermissionService;
import priv.yulong.sys.service.RoleService;
import priv.yulong.sys.service.UserService;

public class UserAuthorizingRealm extends AuthorizingRealm {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private PermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		List<Role> roleList = roleService.getByUserId(user.getId());
		List<Permission> permissionList = permissionService.getPermissionsByUserId(user.getId());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Role role : roleList) {
			info.addRole(role.getCode());
		}
		for (Permission permission : permissionList) {
			info.addStringPermission(permission.getCode());
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		User user = userService.getUserByName(username);
		if (user != null) {
			String password = user.getPassword();
			ByteSource salt = ByteSource.Util.bytes(username);
			return new SimpleAuthenticationInfo(user, password, salt, this.getName());
		} else {
			throw new AuthenticationException();
		}

	}

}
