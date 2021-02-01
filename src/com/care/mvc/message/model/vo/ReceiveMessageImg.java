package com.care.mvc.message.model.vo;

public class ReceiveMessageImg {
	
	private int rec_img_no;
	
	private String rec_img_path;
	
	private String rec_img_name_org;
	
	private String rec_img_name_sav;
	
	private int rec_no;

	public ReceiveMessageImg() {
	}

	public ReceiveMessageImg(int rec_img_no, String rec_img_path, String rec_img_name_org, String rec_img_name_sav,
			int rec_no) {
		super();
		this.rec_img_no = rec_img_no;
		this.rec_img_path = rec_img_path;
		this.rec_img_name_org = rec_img_name_org;
		this.rec_img_name_sav = rec_img_name_sav;
		this.rec_no = rec_no;
	}

	public int getRec_img_no() {
		return rec_img_no;
	}

	public void setRec_img_no(int rec_img_no) {
		this.rec_img_no = rec_img_no;
	}

	public String getRec_img_path() {
		return rec_img_path;
	}

	public void setRec_img_path(String rec_img_path) {
		this.rec_img_path = rec_img_path;
	}

	public String getRec_img_name_org() {
		return rec_img_name_org;
	}

	public void setRec_img_name_org(String rec_img_name_org) {
		this.rec_img_name_org = rec_img_name_org;
	}

	public String getRec_img_name_sav() {
		return rec_img_name_sav;
	}

	public void setRec_img_name_sav(String rec_img_name_sav) {
		this.rec_img_name_sav = rec_img_name_sav;
	}

	public int getRec_no() {
		return rec_no;
	}

	public void setRec_no(int rec_no) {
		this.rec_no = rec_no;
	}

	@Override
	public String toString() {
		return "ReceiveMessageImg [rec_img_no=" + rec_img_no + ", rec_img_path=" + rec_img_path + ", rec_img_name_org="
				+ rec_img_name_org + ", rec_img_name_sav=" + rec_img_name_sav + ", rec_no=" + rec_no + "]";
	}
	
}
