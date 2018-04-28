package priv.yulong.sys.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import priv.yulong.sys.service.UserService;

@Controller
public class LoginController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		model.addAttribute("msg", "您已经退出登录");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, boolean rememberMe, Model model) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		Subject subject = SecurityUtils.getSubject();
		String msg = null;
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			msg = e.getMessage();
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		if (msg == null) {
			return "redirect:/";
		}
		model.addAttribute("msg", msg);
		return "login";
	}

	@RequestMapping(value = "/unAuthorization")
	public String unAuthorization() {
		return "unAuthorization";
	}

	@RequestMapping(value = "/")
	public String manager() {
		return "manager";
	}

}
