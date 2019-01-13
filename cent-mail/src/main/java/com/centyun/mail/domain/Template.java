package com.centyun.mail.domain;

import java.util.Date;

public class Template {

	private Long id;
	private Long tenantId;
	private Long providerId;
	private Long datasourceId;
	private String name;
	private String mailField;
	private String replaceFields;
	private String sender;
	private String senderName;
	private String replyAddress;
	private String subject;
	private String content;
	private int status; // 0关闭的模板, 1有效的模板
	private int unsubscribe;
	private String unsubscribeLanguage;
	private Long creator;
	private String creatorName;
	private Date createTime;
	private Long editor;
	private String editorName;
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

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(Long datasourceId) {
		this.datasourceId = datasourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailField() {
		return mailField;
	}

	public void setMailField(String mailField) {
		this.mailField = mailField;
	}

	public String getReplaceFields() {
		return replaceFields;
	}

	public void setReplaceFields(String replaceFields) {
		this.replaceFields = replaceFields;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
