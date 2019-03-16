package com.centyun.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ModuleVO {

    private String id;
    private String parentId;
    private String name;
    private String englishName;
    private String icon;
    private String code;
    private String url;
    private Integer orderNo;
    private Integer status; // 0 无效, 1 正常
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private List<ModuleVO> children;
    private boolean active;

    public ModuleVO() {
    }

    public ModuleVO(Module module) {
        this.id = module.getId();
        this.parentId = module.getParentId();
        this.name = module.getName();
        this.englishName = module.getEnglishName();
        this.icon = module.getIcon();
        this.code = module.getCode();
        this.url = module.getUrl();
        this.orderNo = module.getOrderNo();
        this.status = module.getStatus();
        this.createTime = module.getCreateTime();
    }

    public final String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    public final String getParentId() {
        return parentId;
    }

    public final void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getEnglishName() {
        return englishName;
    }

    public final void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public final String getIcon() {
        return icon;
    }

    public final void setIcon(String icon) {
        this.icon = icon;
    }

    public final String getCode() {
        return code;
    }

    public final void setCode(String code) {
        this.code = code;
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url = url;
    }

    public final Integer getOrderNo() {
        return orderNo;
    }

    public final void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public final Integer getStatus() {
        return status;
    }

    public final void setStatus(Integer status) {
        this.status = status;
    }

    public final Date getCreateTime() {
        return createTime;
    }

    public final void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public final List<ModuleVO> getChildren() {
        return children;
    }

    public final void setChildren(List<ModuleVO> children) {
        this.children = children;
    }

    public final void addChild(ModuleVO adminMenuVo) {
        if (children == null) {
            children = new ArrayList<ModuleVO>();
        }
        this.children.add(adminMenuVo);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
