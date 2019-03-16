package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.Mail;

@Mapper
public interface MailMapper {
	
	void addMail(Mail mail);
	
	void updateMail(Mail mail);
	
	Mail getMailById(String id, String packageId, String tenantId);

	List<Mail> getMails(String packageId);

}
