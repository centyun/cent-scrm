package com.centyun.base.service;

import com.centyun.base.domain.Dwz;
import com.centyun.core.domain.User;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface DwzService {
    
    PageInfo<Dwz> listPageDwzs(DataTableParam dataTableParam);

    Dwz getDwzById(String dwzId);
    
    Dwz getDwzByLongUrl(Dwz dwz, User user);

    Dwz getDwzByCode(String dwzCode);

}
