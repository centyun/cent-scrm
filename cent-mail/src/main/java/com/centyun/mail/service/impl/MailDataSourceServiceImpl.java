package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.MailDataSource;
import com.centyun.mail.mapper.MailDataSourceMapper;
import com.centyun.mail.service.MailDataSourceService;

@Service
public class MailDataSourceServiceImpl implements MailDataSourceService {
	
	@Autowired
	private MailDataSourceMapper mailDataSourceMapper;

	@Override
	public void addMailDataSource(MailDataSource mailDataSource) {
		mailDataSourceMapper.addMailDataSource(mailDataSource);
	}

	@Override
	public void updateMailDataSource(MailDataSource mailDataSource) {
		mailDataSourceMapper.updateMailDataSource(mailDataSource);
	}

	@Override
	public void deleteMailDataSource(MailDataSource mailDataSource) {
		mailDataSourceMapper.deleteMailDataSource(mailDataSource);
	}

	@Override
	public MailDataSource getMailDataSourceById(String id, String tenantId) {
		return mailDataSourceMapper.getMailDataSourceById(id, tenantId);
	}

	@Override
	public List<MailDataSource> getMailDataSources(String tenantId) {
		return mailDataSourceMapper.getMailDataSources(tenantId);
	}

}
