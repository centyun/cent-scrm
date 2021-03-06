package com.centyun.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.mail.domain.ProviderConfig;

@Mapper
public interface ProviderConfigMapper {
	
	void addProviderConfig(ProviderConfig providerConfig);
	
	void updateProviderConfig(ProviderConfig providerConfig);
	
	void deleteProviderConfig(ProviderConfig providerConfig);
	
	ProviderConfig getProviderConfigById(String id, String tenantId);
	
	List<ProviderConfig> getProviderConfigs(String tenantId);

}
