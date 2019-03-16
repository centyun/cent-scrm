package com.centyun.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.cms.constant.SieConstant;
import com.centyun.cms.domain.Article;
import com.centyun.cms.mapper.ArticleMapper;
import com.centyun.cms.service.ArticleService;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void saveArticle(Article article) {
        if(article != null && article.getTenantId() != null) {
            String id = article.getId();
            if (StringUtils.isEmpty(id)) {
                SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(SieConstant.DATACENTER_ID, SieConstant.MACHINE_ID);
                article.setId(snowFlake.nextId());
                articleMapper.addArticle(article);
            } else {
                articleMapper.updateArticle(article);
            }
        }
    }

    @Override
    public Article getArticle(String tenantId) {
        return articleMapper.getArticle(tenantId);
    }

    @Override
    public PageInfo<Article> getPageArticles(DataTableParam dataTableParam, String tenantId) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Article> sites = articleMapper.getPageArticles(tenantId, StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Article> pageInfo = new PageInfo<>(sites);
        return pageInfo;
    }

}
