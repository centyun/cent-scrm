package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.ExcludeMail;

public interface ExcludeMailService {
	
	void addExcludeMail(ExcludeMail excludeMail);
	
	ExcludeMail getExcludeMailById(String id, String tenantId);
	
	List<ExcludeMail> getExcludeMails(String tenantId);

}
