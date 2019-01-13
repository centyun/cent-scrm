package com.centyun.mail.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.Webhook;

@Mapper
public interface WebhookMapper {
	
	void addWebhook(Webhook webhook);

}
