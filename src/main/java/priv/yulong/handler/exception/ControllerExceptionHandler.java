package priv.yulong.handler.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class ControllerExceptionHandler extends SimpleMappingExceptionResolver {

	private Properties messageMappings;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if ((handler instanceof HandlerMethod)
				&& ((HandlerMethod) handler).getMethodAnnotation(ResponseBody.class) != null) {
			String message = findMatchingMessage(messageMappings, ex);
			try {
				PrintWriter writer = response.getWriter();
				message = message == null ? "系统报错" : message;
				writer.write(message + "【" + ex.getMessage() + "】");
				writer.flush();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			String viewName = determineViewName(ex, request);
			if (viewName != null) {
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				return getModelAndView(viewName, ex, request);
			}

		}
		return super.doResolveException(request, response, handler, ex);
	}

	private String findMatchingMessage(Properties messageMappings, Exception ex) {
		for (Enumeration<?> names = messageMappings.propertyNames(); names.hasMoreElements();) {
			String messageMapping = (String) names.nextElement();
			int depth = getDepth(messageMapping, ex);
			if (depth >= 0) {
				return messageMappings.getProperty(messageMapping);
			}
		}
		return null;
	}

	public Properties getMessageMappings() {
		return messageMappings;
	}

	public void setMessageMappings(Properties messageMappings) {
		this.messageMappings = messageMappings;
	}

}
