package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.MailDataSource;

public interface MailDataSourceService {
	
	void addMailDataSource(MailDataSource mailDataSource);
	
	void updateMailDataSource(MailDataSource mailDataSource);
	
	void deleteMailDataSource(MailDataSource mailDataSource);
	
	MailDataSource getMailDataSourceById(String id, String tenantId);
	
	List<MailDataSource> getMailDataSources(String tenantId);

}
