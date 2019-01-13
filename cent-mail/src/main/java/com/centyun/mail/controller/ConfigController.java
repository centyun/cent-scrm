package com.centyun.mail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/config")
public class ConfigController extends BaseController {
	/*
	@Autowired
	private ProviderConfigService providerConfigService;

	@Autowired
	private MailDataSourceService mailDataSourceService;

	@Autowired
	private SenderService senderConfigService;
	*/
	@RequestMapping(value = "/index.html")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.addObject("products", getProducts("/config/index.html"));
        model.setViewName("config/provider-index");
        return model;
	}

}
