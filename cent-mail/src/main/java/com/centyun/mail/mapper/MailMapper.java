package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.Mail;

@Mapper
public interface MailMapper {
	
	void addMail(Mail mail);
	
	void updateMail(Mail mail);
	
	Mail getMailById(Long id, Long packageId, Long tenantId);

	List<Mail> getMails(Long packageId);

}
