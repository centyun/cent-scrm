package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.Sender;

@Mapper
public interface SenderMapper {
	
	void addSender(Sender sender);
	
	void updateSender(Sender sender);
	
	void deleteSender(Sender sender);
	
	Sender getSenderById(String id, String tenantId);
	
	List<Sender> getSenders(String tenantId);

}
