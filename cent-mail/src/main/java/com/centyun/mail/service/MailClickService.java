package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.MailClick;

public interface MailClickService {
	
	void addMailClick(MailClick MailClick);
	
	MailClick getMailClickById(String id, String tenantId);
	
	List<MailClick> getMailClicks(String tenantId);
}
