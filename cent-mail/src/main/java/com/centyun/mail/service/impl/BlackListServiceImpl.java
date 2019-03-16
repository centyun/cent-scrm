package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.BlackList;
import com.centyun.mail.mapper.BlackListMapper;
import com.centyun.mail.service.BlackListService;

@Service
public class BlackListServiceImpl implements BlackListService {
	
	@Autowired
	private BlackListMapper blackListMapper;

	@Override
	public void addBlackList(BlackList blackList) {
		blackListMapper.addBlackList(blackList);
	}

	@Override
	public void deleteBlackList(BlackList blackList) {
		blackListMapper.deleteBlackList(blackList);
	}

	@Override
	public BlackList getBlackListById(String id, String tenantId) {
		return blackListMapper.getBlackListById(id, tenantId);
	}

	@Override
	public List<BlackList> getBlackLists(String tenantId) {
		return blackListMapper.getBlackLists(tenantId);
	}

}
