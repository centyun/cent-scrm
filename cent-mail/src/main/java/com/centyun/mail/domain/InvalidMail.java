package com.centyun.mail.domain;

import java.util.Date;

public class InvalidMail {
	private String id;
	private String tenantId;
	private String pacageId;
	private String recipient;
	private String reason;
	private Date occurTime;

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

	public String getPacageId() {
		return pacageId;
	}

	public void setPacageId(String pacageId) {
		this.pacageId = pacageId;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

}
