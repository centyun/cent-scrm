package com.centyun.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.base.domain.Dwz;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface DwzMapper {

    List<Dwz> listPageDwzs(@Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);
    
    Dwz getDwzByUrl(@Param("longUrl") String longUrl);
    
    int checkDwz(@Param("code") String code);

    Dwz getDwzById(@Param("id") String dwzId);

    void addDwz(Dwz dwz);

    Dwz getDwzByCode(@Param("code") String code);
    
}
