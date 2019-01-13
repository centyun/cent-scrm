package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.MailOpen;
import com.centyun.mail.mapper.MailOpenMapper;
import com.centyun.mail.service.MailOpenService;

@Service
public class MailOpenServiceImpl implements MailOpenService {
	
	@Autowired
	private MailOpenMapper mailOpenMapper;

	@Override
	public void addMailOpen(MailOpen mailOpen) {
		mailOpenMapper.addMailOpen(mailOpen);
	}

	@Override
	public MailOpen getMailOpenById(Long id, Long tenantId) {
		return mailOpenMapper.getMailOpenById(id, tenantId);
	}

	@Override
	public List<MailOpen> getMailOpens(Long tenantId) {
		return mailOpenMapper.getMailOpens(tenantId);
	}

}
