package com.centyun.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.core.domain.Audit;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface AuditMapper {

    void addAudit(Audit audit);

    List<Audit> getAudits(@Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);

}
