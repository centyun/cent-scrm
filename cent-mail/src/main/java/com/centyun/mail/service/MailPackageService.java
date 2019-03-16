package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.MailPackage;

public interface MailPackageService {
	
	void addMailPackage(MailPackage mailPackage);
	
	MailPackage getMailPackageById(String id, String tenantId);
	
	List<MailPackage> getMailPackages(String tenantId);

}
