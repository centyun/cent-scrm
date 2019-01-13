package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.Template;

@Mapper
public interface TemplateMapper {
	
	void addTemplate(Template template);
	
	void updateTemplate(Template template);
	
	void discardTemplate(Template template);
	
	Template getTemplateById(Long id, Long tenantId);
	
	List<Template> getTemplates(Long tenantId);

}
