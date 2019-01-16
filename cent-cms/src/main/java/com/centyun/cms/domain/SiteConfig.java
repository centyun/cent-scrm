package com.centyun.cms.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SiteConfig {
    private Long siteId;
    private Long tenantId; // 租户id
    private String logo; // 站点logo地址
    private Integer logoWidth;
    private Integer logoHeight;
    private String pcBanner;
    private String mobileBanner;
    private String background; // 背景图
    private String phone;
    private String qq;
    private String email;
    private String webQrcode; // 网站二维码
    private String wxmpQrcode; // 微信公众号二维码
    private String copyright;
    private String icp; // 网站备案信息
    private String seoTitle;
    private String seoKeyword;
    private String seoDescription;
    private String traceCode; // 追踪代码
    private Long editor;
    private String editorName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date editTime;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getLogoWidth() {
        return logoWidth;
    }

    public void setLogoWidth(Integer logoWidth) {
        this.logoWidth = logoWidth;
    }

    public Integer getLogoHeight() {
        return logoHeight;
    }

    public void setLogoHeight(Integer logoHeight) {
        this.logoHeight = logoHeight;
    }

    public String getPcBanner() {
        return pcBanner;
    }

    public void setPcBanner(String pcBanner) {
        this.pcBanner = pcBanner;
    }

    public String getMobileBanner() {
        return mobileBanner;
    }

    public void setMobileBanner(String mobileBanner) {
        this.mobileBanner = mobileBanner;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebQrcode() {
        return webQrcode;
    }

    public void setWebQrcode(String webQrcode) {
        this.webQrcode = webQrcode;
    }

    public String getWxmpQrcode() {
        return wxmpQrcode;
    }

    public void setWxmpQrcode(String wxmpQrcode) {
        this.wxmpQrcode = wxmpQrcode;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
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

    public String getTraceCode() {
        return traceCode;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
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
