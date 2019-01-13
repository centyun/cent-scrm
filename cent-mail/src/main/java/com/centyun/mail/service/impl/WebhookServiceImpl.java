package com.centyun.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.Webhook;
import com.centyun.mail.mapper.WebhookMapper;
import com.centyun.mail.service.WebhookService;

@Service
public class WebhookServiceImpl implements WebhookService {
	
	@Autowired
	private WebhookMapper webhookMapper;

	@Override
	public void addWebhook(Webhook webhook) {
		webhookMapper.addWebhook(webhook);
	}

}
