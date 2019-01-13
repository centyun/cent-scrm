package com.centyun.mail.domain;

import java.util.Date;

public class MailPackage {

	private Long id;
	private Long tenantId;
	private Long templateId;
	private Long providerId;
	private String sender;
	private String senderName;
	private String replyAddress;
	private String sendTo;
	private String subject;
	private String content;
	private int hasAttachment;
	private int scheduled;
	private Date scheduledTime;
	private int status;
	private String campaignId;
	private String mailingId;
	private int labelId;
	private int unsubscribe;
	private String unsubscribeLanguage;
	private int unsubscribeCount;
	private int total;
	private int requestCount;
	private int deliverCount;
	private int bounceCount;
	private int softBounceCount;
	private int invalidAddressCount;
	private int spamCount;
	private int repeatAddressCount;
	private int openCount;
	private int openUniqueCount;
	private int clickCount;
	private int clickUniqueCount;
	private int providerExcludeCount;
	private int creator;
	private String creatorName;
	private Date createTime;

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

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReplyAddress() {
		return replyAddress;
	}

	public void setReplyAddress(String replyAddress) {
		this.replyAddress = replyAddress;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHasAttachment() {
		return hasAttachment;
	}

	public void setHasAttachment(int hasAttachment) {
		this.hasAttachment = hasAttachment;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getMailingId() {
		return mailingId;
	}

	public void setMailingId(String mailingId) {
		this.mailingId = mailingId;
	}

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public int getUnsubscribe() {
		return unsubscribe;
	}

	public void setUnsubscribe(int unsubscribe) {
		this.unsubscribe = unsubscribe;
	}

	public String getUnsubscribeLanguage() {
		return unsubscribeLanguage;
	}

	public void setUnsubscribeLanguage(String unsubscribeLanguage) {
		this.unsubscribeLanguage = unsubscribeLanguage;
	}

	public int getUnsubscribeCount() {
		return unsubscribeCount;
	}

	public void setUnsubscribeCount(int unsubscribeCount) {
		this.unsubscribeCount = unsubscribeCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}

	public int getDeliverCount() {
		return deliverCount;
	}

	public void setDeliverCount(int deliverCount) {
		this.deliverCount = deliverCount;
	}

	public int getBounceCount() {
		return bounceCount;
	}

	public void setBounceCount(int bounceCount) {
		this.bounceCount = bounceCount;
	}

	public int getSoftBounceCount() {
		return softBounceCount;
	}

	public void setSoftBounceCount(int softBounceCount) {
		this.softBounceCount = softBounceCount;
	}

	public int getInvalidAddressCount() {
		return invalidAddressCount;
	}

	public void setInvalidAddressCount(int invalidAddressCount) {
		this.invalidAddressCount = invalidAddressCount;
	}

	public int getSpamCount() {
		return spamCount;
	}

	public void setSpamCount(int spamCount) {
		this.spamCount = spamCount;
	}

	public int getRepeatAddressCount() {
		return repeatAddressCount;
	}

	public void setRepeatAddressCount(int repeatAddressCount) {
		this.repeatAddressCount = repeatAddressCount;
	}

	public int getOpenCount() {
		return openCount;
	}

	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}

	public int getOpenUniqueCount() {
		return openUniqueCount;
	}

	public void setOpenUniqueCount(int openUniqueCount) {
		this.openUniqueCount = openUniqueCount;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public int getClickUniqueCount() {
		return clickUniqueCount;
	}

	public void setClickUniqueCount(int clickUniqueCount) {
		this.clickUniqueCount = clickUniqueCount;
	}

	public int getProviderExcludeCount() {
		return providerExcludeCount;
	}

	public void setProviderExcludeCount(int providerExcludeCount) {
		this.providerExcludeCount = providerExcludeCount;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
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

}
