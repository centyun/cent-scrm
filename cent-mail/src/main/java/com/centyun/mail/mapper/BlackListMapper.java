package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.BlackList;

@Mapper
public interface BlackListMapper {
	
	void addBlackList(BlackList blackList);
	
	void deleteBlackList(BlackList blackList);
	
	BlackList getBlackListById(String id, String tenantId);
	
	List<BlackList> getBlackLists(String tenantId);

}
