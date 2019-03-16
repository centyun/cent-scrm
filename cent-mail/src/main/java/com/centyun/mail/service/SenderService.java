package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.Sender;

public interface SenderService {
	
	void addSender(Sender sender);
	
	void updateSender(Sender sender);
	
	void deleteSender(Sender sender);
	
	Sender getSenderById(String id, String tenantId);
	
	List<Sender> getSenders(String tenantId);

}
