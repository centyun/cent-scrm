package com.centyun.cms.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Site {
    private Long id;
    private Long tenantId; // 租户id
    private String name;
    private String domain; // 主域名
    private String otherDomain; // 其他域名
    private String template;
    private String mobileTemplate;
    private String language;
    private int defaultSite; // 0 不是默认站点, 1 是默认站点
    private Long creator;
    private String creatorName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    private Long editor;
    private String editorName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date editTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getOtherDomain() {
        return otherDomain;
    }

    public void setOtherDomain(String otherDomain) {
        this.otherDomain = otherDomain;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getMobileTemplate() {
        return mobileTemplate;
    }

    public void setMobileTemplate(String mobileTemplate) {
        this.mobileTemplate = mobileTemplate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDefaultSite() {
        return defaultSite;
    }

    public void setDefaultSite(int defaultSite) {
        this.defaultSite = defaultSite;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getEditor() {
        return editor;
    }

    public void setEditor(Long editor) {
        this.editor = editor;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

}