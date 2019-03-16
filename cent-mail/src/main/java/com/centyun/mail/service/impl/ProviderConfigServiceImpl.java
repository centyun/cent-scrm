package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.mail.domain.ProviderConfig;
import com.centyun.mail.mapper.ProviderConfigMapper;
import com.centyun.mail.service.ProviderConfigService;

@Service
public class ProviderConfigServiceImpl implements ProviderConfigService {
	
	@Autowired
	private ProviderConfigMapper providerConfigMapper;

	@Override
	public void addProviderConfig(ProviderConfig providerConfig) {
		providerConfigMapper.addProviderConfig(providerConfig);
	}

	@Override
	public void updateProviderConfig(ProviderConfig providerConfig) {
		providerConfigMapper.updateProviderConfig(providerConfig);
	}

	@Override
	public void deleteProviderConfig(ProviderConfig providerConfig) {
		providerConfigMapper.deleteProviderConfig(providerConfig);
	}

	@Override
	public ProviderConfig getProviderConfigById(String id, String tenantId) {
		return providerConfigMapper.getProviderConfigById(id, tenantId);
	}

	@Override
	public List<ProviderConfig> getProviderConfigs(String tenantId) {
		return providerConfigMapper.getProviderConfigs(tenantId);
	}

}
