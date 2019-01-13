package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.BlackList;

@Mapper
public interface BlackListMapper {
	
	void addBlackList(BlackList blackList);
	
	void deleteBlackList(BlackList blackList);
	
	BlackList getBlackListById(Long id, Long tenantId);
	
	List<BlackList> getBlackLists(Long tenantId);

}
