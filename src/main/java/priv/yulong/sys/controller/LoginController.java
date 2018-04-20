package priv.yulong.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import priv.yulong.sys.model.User;
import priv.yulong.sys.service.UserService;

@Controller
public class LoginController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/login")
	public ModelAndView penetrate() {
		return new ModelAndView("login");
	}

	@ResponseBody
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public String doLogin(String usercode, String password, HttpServletRequest request) {
		User user = userService.getUser(usercode);
		if (user != null && user.getUserPassword().equals(password)) {
			request.getSession().setAttribute("user", user);
			return "success";
		} else {
			return "false";
		}
	}
}
