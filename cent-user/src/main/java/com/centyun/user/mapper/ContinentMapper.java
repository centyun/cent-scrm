package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.centyun.user.domain.Continent;

@Mapper
public interface ContinentMapper {
    
    List<Continent> listContinents();

}
