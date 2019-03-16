package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.InvalidMail;

public interface InvalidMailService {
	
	void addInvalidMail(InvalidMail invalidMail);
	
	InvalidMail getInvalidMailById(String id, String tenantId);
	
	List<InvalidMail> getInvalidMails(String tenantId);

}
