package com.centyun.cms.service;

import com.centyun.cms.domain.Article;
import com.centyun.core.table.DataTableParam;
import com.github.pagehelper.PageInfo;

public interface ArticleService {
    
    void saveArticle(Article article);
    
    Article getArticle(Long tenantId);

    PageInfo<Article> getPageArticles(DataTableParam dataTableParam, Long tenantId);

}
