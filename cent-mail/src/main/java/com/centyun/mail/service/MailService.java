package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.Mail;

public interface MailService {
	
	void addMail(Mail mail);
	
	void updateMail(Mail mail);
	
	Mail getMailById(String id, String packageId, String tenantId);

	List<Mail> getMails(String packageId);

}
