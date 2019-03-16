package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.MailOpen;

public interface MailOpenService {
	
	void addMailOpen(MailOpen mailOpen);
	
	MailOpen getMailOpenById(String id, String tenantId);
	
	List<MailOpen> getMailOpens(String tenantId);

}
