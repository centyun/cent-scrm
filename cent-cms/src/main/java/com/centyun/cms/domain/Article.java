package com.centyun.cms.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Article {

    private Long id;
    private Long tenantId; // 租户id
    private Long siteId; // 站点id
    private Long navId; // 导航id
    private String title;
    private int pcDisplay;
    private int mobileDisplay;
    private int toTop;
    private String tags;
    private String pcContent;
    private String mobileContent;
    private String mainImage;
    private String thumbnail;
    private String seoTitle;
    private String seoKeyword;
    private String seoDescription;
    private String author;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date publishTime;
    private int visitNum;
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

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getNavId() {
        return navId;
    }

    public void setNavId(Long navId) {
        this.navId = navId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeyword() {
        return seoKeyword;
    }

    public void setSeoKeyword(String seoKeyword) {
        this.seoKeyword = seoKeyword;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public int getPcDisplay() {
        return pcDisplay;
    }

    public void setPcDisplay(int pcDisplay) {
        this.pcDisplay = pcDisplay;
    }

    public int getMobileDisplay() {
        return mobileDisplay;
    }

    public void setMobileDisplay(int mobileDisplay) {
        this.mobileDisplay = mobileDisplay;
    }

    public int getToTop() {
        return toTop;
    }

    public void setToTop(int toTop) {
        this.toTop = toTop;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPcContent() {
        return pcContent;
    }

    public void setPcContent(String pcContent) {
        this.pcContent = pcContent;
    }

    public String getMobileContent() {
        return mobileContent;
    }

    public void setMobileContent(String mobileContent) {
        this.mobileContent = mobileContent;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public int getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
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
