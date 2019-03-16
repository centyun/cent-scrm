package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.ExcludeMail;
import com.centyun.mail.mapper.ExcludeMailMapper;
import com.centyun.mail.service.ExcludeMailService;

@Service
public class ExcludeMailServiceImpl implements ExcludeMailService {
	
	@Autowired
	private ExcludeMailMapper excludeMailMapper;

	@Override
	public void addExcludeMail(ExcludeMail excludeMail) {
		excludeMailMapper.addExcludeMail(excludeMail);
	}

	@Override
	public ExcludeMail getExcludeMailById(String id, String tenantId) {
		return excludeMailMapper.getExcludeMailById(id, tenantId);
	}

	@Override
	public List<ExcludeMail> getExcludeMails(String tenantId) {
		return excludeMailMapper.getExcludeMails(tenantId);
	}

}
