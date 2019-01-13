package com.centyun.mail.service;

import java.util.List;

import com.centyun.mail.domain.ProviderConfig;

public interface ProviderConfigService {
	
	void addProviderConfig(ProviderConfig providerConfig);
	
	void updateProviderConfig(ProviderConfig providerConfig);
	
	void deleteProviderConfig(ProviderConfig providerConfig);
	
	ProviderConfig getProviderConfigById(Long id, Long tenantId);
	
	List<ProviderConfig> getProviderConfigs(Long tenantId);

}
