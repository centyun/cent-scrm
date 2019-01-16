package com.centyun.cms.service;

import com.centyun.cms.domain.Swiper;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface SwiperService {
    
    void saveSwiper(Swiper swiper);
    
    Swiper getSwiper(Long tenantId, Long siteId);

    PageInfo<Swiper> getPageSwipers(DataTableParam dataTableParam, Long tenantId);

}
