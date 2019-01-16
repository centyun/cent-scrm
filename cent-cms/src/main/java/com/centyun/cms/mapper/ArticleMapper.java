package com.centyun.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.centyun.cms.domain.Article;
import com.centyun.core.table.KeyValuePair;

@Mapper
public interface ArticleMapper {
    
    void addArticle(Article article);
    
    void updateArticle(Article article);
    
    Article getArticle(@Param("tenantId") Long tenantId);

    List<Article> getPageArticles(@Param("tenantId") Long tenantId, @Param("searchValue") String searchValue,
            @Param("orders") List<KeyValuePair> orders);

}
