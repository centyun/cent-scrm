package com.centyun.mail.domain;

import java.util.Date;

public class InvalidMail {
	private Long id;
	private Long tenantId;
	private Long pacageId;
	private Long recipient;
	private String reason;
	private Date occurTime;

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

	public Long getPacageId() {
		return pacageId;
	}

	public void setPacageId(Long pacageId) {
		this.pacageId = pacageId;
	}

	public Long getRecipient() {
		return recipient;
	}

	public void setRecipient(Long recipient) {
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
