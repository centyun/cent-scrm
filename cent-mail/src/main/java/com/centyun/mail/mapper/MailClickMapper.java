package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.MailClick;

@Mapper
public interface MailClickMapper {
	
	void addMailClick(MailClick MailClick);
	
	MailClick getMailClickById(String id, String tenantId);
	
	List<MailClick> getMailClicks(String tenantId);

}
