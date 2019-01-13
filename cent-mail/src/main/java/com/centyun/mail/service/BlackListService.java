package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.BlackList;

public interface BlackListService {
	
	void addBlackList(BlackList blackList);
	
	void deleteBlackList(BlackList blackList);
	
	BlackList getBlackListById(Long id, Long tenantId);
	
	List<BlackList> getBlackLists(Long tenantId);

}
