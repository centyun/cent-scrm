package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.Template;

public interface TemplateService {
	
	void addTemplate(Template template);
	
	void updateTemplate(Template template);
	
	void discardTemplate(Template template);
	
	Template getTemplateById(String id, String tenantId);
	
	List<Template> getTemplates(String tenantId);

}
