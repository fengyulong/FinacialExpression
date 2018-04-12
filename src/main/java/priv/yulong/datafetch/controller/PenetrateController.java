package priv.yulong.datafetch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PenetrateController {
	@RequestMapping(value = "/PenetrateQuery", method = RequestMethod.POST)
	public ModelAndView penetrate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("test");
		String param = request.getParameter("paramXML");
		model.addObject("xml", param);
		try {
			Document document = DocumentHelper.parseText(param);
			Element root = document.getRootElement();
			String unitCode = root.element("units").element("unit").attribute("code").getValue();
			String sTime = root.element("periods").element("period").attribute("stime").getValue();
			String eTime = root.element("periods").element("period").attribute("etime").getValue();
			String formula = root.element("formulas").element("formula").attribute("value").getValue();
			model.addObject("unitCode", unitCode);
			model.addObject("sTime", sTime);
			model.addObject("eTime", eTime);
			model.addObject("formula", formula);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return model;
	}

	// <scheme>
	// <units>
	// <unit code='#601002160'/>
	// </units>
	// <user code='admin'/>
	// <periods>
	// <period stime='2018-3-1' etime='2018-3-31'/>
	// </periods>
	// <formulas>
	// <formula zb='ZM0100020' value='abs(zw(ye,1001))'/>
	// </formulas>
	// </scheme>
}
