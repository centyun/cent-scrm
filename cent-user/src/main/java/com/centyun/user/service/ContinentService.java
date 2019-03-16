package com.centyun.user.service;

import java.util.List;

import com.centyun.core.table.DataTableParam;
import com.centyun.user.domain.Continent;
import com.github.pagehelper.PageInfo;

public interface ContinentService {
    
    PageInfo<Continent> listContinents(DataTableParam dataTableParam);
    
    List<Continent> listAllContinents();

}
