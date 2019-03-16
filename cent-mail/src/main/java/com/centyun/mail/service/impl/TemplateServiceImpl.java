package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.Template;
import com.centyun.mail.mapper.TemplateMapper;
import com.centyun.mail.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	
	@Autowired
	private TemplateMapper templateMapper;

	@Override
	public void addTemplate(Template template) {
		templateMapper.addTemplate(template);
	}

	@Override
	public void updateTemplate(Template template) {
		// TODO Auto-generated method stub
		templateMapper.updateTemplate(template);
	}

	@Override
	public void discardTemplate(Template template) {
		// TODO Auto-generated method stub
		templateMapper.discardTemplate(template);
	}

	@Override
	public Template getTemplateById(String id, String tenantId) {
		return templateMapper.getTemplateById(id, tenantId);
	}

	@Override
	public List<Template> getTemplates(String tenantId) {
		return templateMapper.getTemplates(tenantId);
	}

}
