package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.table.KeyValuePair;
import com.centyun.user.domain.Consume;

@Mapper
public interface ConsumeMapper {

    List<Consume> getPageConsumes(@Param("tenantId") String tenantId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);

    Consume getConsumeById(@Param("id") String id);

    void addConsume(Consume consume);

}
