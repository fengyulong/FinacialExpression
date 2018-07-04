package priv.yulong.expression.function.financial;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import priv.yulong.datafetch.datasourse.service.DatasourceService;

public abstract class FinancialFunctionBase {
	protected WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
	protected DatasourceService datasourceService = wac.getBean(DatasourceService.class);

}
