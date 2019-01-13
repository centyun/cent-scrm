package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.MailProvider;
import com.centyun.mail.mapper.MailProviderMapper;
import com.centyun.mail.service.MailProviderService;

@Service
public class MailProviderServiceImpl implements MailProviderService {
	
	@Autowired
	private MailProviderMapper mailProviderMapper;

	@Override
	public List<MailProvider> getMailProviders() {
		return mailProviderMapper.getMailProviders();
	}

}
