package priv.yulong.sys.security;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import priv.yulong.sys.model.User;
import priv.yulong.sys.service.UserService;


public class UserAuthorizingRealm extends AuthorizingRealm {
	@Resource
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		return null;
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
			return new SimpleAuthenticationInfo(username, password, salt, this.getName());
		} else {
			throw new AuthenticationException();
		}

	}

}
