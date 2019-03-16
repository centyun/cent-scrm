package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.InvalidMail;
import com.centyun.mail.mapper.InvalidMailMapper;
import com.centyun.mail.service.InvalidMailService;

@Service
public class InvalidMailServiceImpl implements InvalidMailService {
	
	@Autowired
	private InvalidMailMapper invalidMailMapper;

	@Override
	public void addInvalidMail(InvalidMail invalidMail) {
		invalidMailMapper.addInvalidMail(invalidMail);
	}

	@Override
	public InvalidMail getInvalidMailById(String id, String tenantId) {
		return invalidMailMapper.getInvalidMailById(id, tenantId);
	}

	@Override
	public List<InvalidMail> getInvalidMails(String tenantId) {
		return invalidMailMapper.getInvalidMails(tenantId);
	}

}
