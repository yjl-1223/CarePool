package com.care.mvc.message.model.vo;

import java.sql.Date;
import java.util.List;

public class ReceiveMessage {

	private int rec_no;
	
	private int rowNum;
	
	private String send_id; //보낸사람
	
	private String rec_body;
	
	private Date rec_date;
	
	private String Mem_id;
	
	private String status;
	
	private List<ReceiveMessageImg> imgs;
	
	public ReceiveMessage() {
	}

	public ReceiveMessage(int rec_no, int rowNum, String send_id, String rec_body, Date rec_date, String Mem_id, String status) {
		this.rec_no = rec_no;
		this.rowNum = rowNum;
		this.send_id = send_id;
		this.rec_body = rec_body;
		this.rec_date = rec_date;
		this.Mem_id = Mem_id;
		this.status = status;
	}

	public int getRec_no() {
		return rec_no;
	}


	public void setRec_no(int rec_no) {
		this.rec_no = rec_no;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getSend_id() {
		return send_id;
	}

	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}


	public String getRec_body() {
		return rec_body;
	}


	public void setRec_body(String rec_body) {
		this.rec_body = rec_body;
	}


	public Date getRec_date() {
		return rec_date;
	}


	public void setRec_date(Date rec_date) {
		this.rec_date = rec_date;
	}

	public String getMem_id() {
		return Mem_id;
	}

	public void setMem_id(String mem_id) {
		Mem_id = mem_id;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ReceiveMessageImg> getImgs() {
		return imgs;
	}

	public void setImgs(List<ReceiveMessageImg> imgs) {
		this.imgs = imgs;
	}

	@Override
	public String toString() {
		return "ReceiveMessage [rec_no=" + rec_no + ", rowNum=" + rowNum + ", send_id=" + send_id + ", rec_body="
				+ rec_body + ", rec_date=" + rec_date + ", Mem_id=" + Mem_id + ", status=" + status + "]";
	}
}
