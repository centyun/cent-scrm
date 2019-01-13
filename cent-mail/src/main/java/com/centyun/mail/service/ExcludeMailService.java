package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.ExcludeMail;

public interface ExcludeMailService {
	
	void addExcludeMail(ExcludeMail excludeMail);
	
	ExcludeMail getExcludeMailById(Long id, Long tenantId);
	
	List<ExcludeMail> getExcludeMails(Long tenantId);

}
