package com.centyun.cms.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 轮播图
 * @author yinww
 *
 */

public class Swiper {
    private Long id;
    private Long tenantId;
    private String groupName;
    private String title;
    private String picture;
    private String link;
    private int openTarget; // 0 在当前页打开, 1在新页签打开
    private int orderNo;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
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
