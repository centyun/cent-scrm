package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.MailPackage;

@Mapper
public interface MailPackageMapper {
	
	void addMailPackage(MailPackage mailPackage);
	
	MailPackage getMailPackageById(Long id, Long tenantId);
	
	List<MailPackage> getMailPackages(Long tenantId);

}
