package com.centyun.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.cms.domain.Swiper;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface SwiperMapper {
    
    void addSwiper(Swiper swiper);
    
    void updateSwiper(Swiper swiper);
    
    Swiper getSwiper(@Param("tenantId") Long tenantId);

    List<Swiper> getPageSwipers(@Param("tenantId") Long tenantId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);

}
