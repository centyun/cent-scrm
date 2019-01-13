package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.ExcludeMail;

@Mapper
public interface ExcludeMailMapper {
	
	void addExcludeMail(ExcludeMail excludeMail);
	
	ExcludeMail getExcludeMailById(Long id, Long tenantId);
	
	List<ExcludeMail> getExcludeMails(Long tenantId);

}
