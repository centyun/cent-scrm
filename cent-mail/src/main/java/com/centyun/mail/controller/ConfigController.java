package com.centyun.mail.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.centyun.web.controller.WebBaseController;

@Controller
@RequestMapping(value = "/config")
public class ConfigController extends WebBaseController {
	/*
	@Autowired
	private ProviderConfigService providerConfigService;

	@Autowired
	private MailDataSourceService mailDataSourceService;

	@Autowired
	private SenderService senderConfigService;
	*/
	@RequestMapping(value = "/index.html")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("products", getAvailableProducts(request));
        model.setViewName("config/provider-index");
        return model;
	}

}
