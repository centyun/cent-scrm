package com.centyun.cms.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Navigation {

    private String id;
    private String tenantId; // 租户id
    private String siteId; // 站点id
    private String name;
    private int type;
    private int style;
    private String link;
    private int openTarget;
    private String pcParentId;
    private int pcDisplay;
    private int pcLevel;
    private int pcOrderNo;
    private String pcCoverImage;
    private String description;
    private String mobileParentId;
    private int mobileDisplay;
    private int mobileLevel;
    private int mobileOrderNo;
    private String mobileCoverImage;
    private String seoTitle;
    private String seoKeyword;
    private String seoDescription;
    private String author;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date publishTime;
    private int visitNum;
    private String creator;
    private String creatorName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    private String editor;
    private String editorName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date editTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getOpenTarget() {
        return openTarget;
    }

    public void setOpenTarget(int openTarget) {
        this.openTarget = openTarget;
    }

    public String getPcParentId() {
        return pcParentId;
    }

    public void setPcParentId(String pcParentId) {
        this.pcParentId = pcParentId;
    }

    public int getPcDisplay() {
        return pcDisplay;
    }

    public void setPcDisplay(int pcDisplay) {
        this.pcDisplay = pcDisplay;
    }

    public int getPcLevel() {
        return pcLevel;
    }

    public void setPcLevel(int pcLevel) {
        this.pcLevel = pcLevel;
    }

    public int getPcOrderNo() {
        return pcOrderNo;
    }

    public void setPcOrderNo(int pcOrderNo) {
        this.pcOrderNo = pcOrderNo;
    }

    public String getPcCoverImage() {
        return pcCoverImage;
    }

    public void setPcCoverImage(String pcCoverImage) {
        this.pcCoverImage = pcCoverImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobileParentId() {
        return mobileParentId;
    }

    public void setMobileParentId(String mobileParentId) {
        this.mobileParentId = mobileParentId;
    }

    public int getMobileDisplay() {
        return mobileDisplay;
    }

    public void setMobileDisplay(int mobileDisplay) {
        this.mobileDisplay = mobileDisplay;
    }

    public int getMobileLevel() {
        return mobileLevel;
    }

    public void setMobileLevel(int mobileLevel) {
        this.mobileLevel = mobileLevel;
    }

    public int getMobileOrderNo() {
        return mobileOrderNo;
    }

    public void setMobileOrderNo(int mobileOrderNo) {
        this.mobileOrderNo = mobileOrderNo;
    }

    public String getMobileCoverImage() {
        return mobileCoverImage;
    }

    public void setMobileCoverImage(String mobileCoverImage) {
        this.mobileCoverImage = mobileCoverImage;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
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
