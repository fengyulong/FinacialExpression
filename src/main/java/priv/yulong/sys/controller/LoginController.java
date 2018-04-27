package priv.yulong.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import priv.yulong.sys.service.UserService;

@Controller
public class LoginController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/login")
	public ModelAndView penetrate() {
		return new ModelAndView("login");
	}


}
