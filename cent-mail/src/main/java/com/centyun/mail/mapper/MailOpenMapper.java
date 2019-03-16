package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.MailOpen;

@Mapper
public interface MailOpenMapper {
	
	void addMailOpen(MailOpen mailOpen);
	
	MailOpen getMailOpenById(String id, String tenantId);
	
	List<MailOpen> getMailOpens(String tenantId);

}
