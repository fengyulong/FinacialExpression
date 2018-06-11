package priv.yulong.sys.security;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class ShiroFreeMarkerConfig extends FreeMarkerConfigurer {
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();
		Configuration cfg = this.getConfiguration();
		cfg.setSharedVariable("shiro", new ShiroTags());// shiro标签
		//cfg.setNumberFormat("#");
	}
}