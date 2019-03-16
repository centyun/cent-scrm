package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.BlackList;

public interface BlackListService {
	
	void addBlackList(BlackList blackList);
	
	void deleteBlackList(BlackList blackList);
	
	BlackList getBlackListById(String id, String tenantId);
	
	List<BlackList> getBlackLists(String tenantId);

}
