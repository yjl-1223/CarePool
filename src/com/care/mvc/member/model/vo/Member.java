package com.care.mvc.member.model.vo;

import java.sql.Date;

public class Member {
	private String memId;
	
	private String memRole;
	
	private String memName;
	
	private String memPwd;
	
	private String memEmail;
	
	private String memPhone;
	
	private String memAddr;
	
	private String memBirth;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private String status;

	public Member() {
	}

	public Member(String memId, String memRole, String memName, String memPwd, String memEmail, String memPhone,
			String memAddr, String memBirth, Date createDate, Date modifyDate, String status) {
		super();
		this.memId = memId;
		this.memRole = memRole;
		this.memName = memName;
		this.memPwd = memPwd;
		this.memEmail = memEmail;
		this.memPhone = memPhone;
		this.memAddr = memAddr;
		this.memBirth = memBirth;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemRole() {
		return memRole;
	}

	public void setMemRole(String memRole) {
		this.memRole = memRole;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemAddr() {
		return memAddr;
	}

	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}

	public String getMemBirth() {
		return memBirth;
	}

	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [memId=" + memId + ", memRole=" + memRole + ", memName=" + memName + ", memPwd=" + memPwd
				+ ", memEmail=" + memEmail + ", memPhone=" + memPhone + ", memAddr=" + memAddr + ", memBirth="
				+ memBirth + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + "]";
	}

}


