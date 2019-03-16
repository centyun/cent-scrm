package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.Mail;
import com.centyun.mail.mapper.MailMapper;
import com.centyun.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private MailMapper mailMapper;

	@Override
	public void addMail(Mail mail) {
		mailMapper.addMail(mail);
	}

	@Override
	public void updateMail(Mail mail) {
		mailMapper.updateMail(mail);
	}

	@Override
	public Mail getMailById(String id, String packageId, String tenantId) {
		return mailMapper.getMailById(id, packageId, tenantId);
	}

	@Override
	public List<Mail> getMails(String packageId) {
		return mailMapper.getMails(packageId);
	}

}
