package com.centyun.mail.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Mail {
    private Long id;
    private Long tenantId;
    private Long packageId;
    private int providerId;
    private int shardingColumn;
    private String replyAddress;
    private String recipient;
    private Long templateId;
    private String templateName;
    private String subject;
    private int mailType; // 0标准邮件, 1个性邮件
    private int status;
    private String event;
    private int scheduled;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scheduledTime;
    private String emailId;
    private String replaceJson;
    private int unsubscribe;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date unsubscribeTime;
    private Integer openCount;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastOpenTime;
    private Long lastOpenIp;
    private String submitFailReason;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getShardingColumn() {
        return shardingColumn;
    }

    public void setShardingColumn(int shardingColumn) {
        this.shardingColumn = shardingColumn;
    }

    public String getReplyAddress() {
        return replyAddress;
    }

    public void setReplyAddress(String replyAddress) {
        this.replyAddress = replyAddress;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMailType() {
        return mailType;
    }

    public void setMailType(int mailType) {
        this.mailType = mailType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getScheduled() {
        return scheduled;
    }

    public void setScheduled(int scheduled) {
        this.scheduled = scheduled;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getReplaceJson() {
        return replaceJson;
    }

    public void setReplaceJson(String replaceJson) {
        this.replaceJson = replaceJson;
    }

    public int getUnsubscribe() {
        return unsubscribe;
    }

    public void setUnsubscribe(int unsubscribe) {
        this.unsubscribe = unsubscribe;
    }

    public Date getUnsubscribeTime() {
        return unsubscribeTime;
    }

    public void setUnsubscribeTime(Date unsubscribeTime) {
        this.unsubscribeTime = unsubscribeTime;
    }

    public Integer getOpenCount() {
        return openCount;
    }

    public void setOpenCount(Integer openCount) {
        this.openCount = openCount;
    }

    public Date getLastOpenTime() {
        return lastOpenTime;
    }

    public void setLastOpenTime(Date lastOpenTime) {
        this.lastOpenTime = lastOpenTime;
    }

    public Long getLastOpenIp() {
        return lastOpenIp;
    }

    public void setLastOpenIp(Long lastOpenIp) {
        this.lastOpenIp = lastOpenIp;
    }

    public String getSubmitFailReason() {
        return submitFailReason;
    }

    public void setSubmitFailReason(String submitFailReason) {
        this.submitFailReason = submitFailReason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
