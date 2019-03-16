package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.core.domain.Module;

@Mapper
public interface ModuleMapper {

    List<Module> getModules();

}
