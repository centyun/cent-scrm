package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.MailProvider;

@Mapper
public interface MailProviderMapper {
	
	List<MailProvider> getMailProviders();

}
