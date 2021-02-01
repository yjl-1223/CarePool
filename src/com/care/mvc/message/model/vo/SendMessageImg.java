package com.care.mvc.message.model.vo;

public class SendMessageImg {

	private int send_img_no;
	
	private String send_img_path;
	
	private String send_img_name_org;
	
	private String send_img_name_sav;
	
	private int send_no;

	public SendMessageImg() {
	}

	public SendMessageImg(int send_img_no, String send_img_path, String send_img_name_org, String send_img_name_sav,
			int send_no) {
		super();
		this.send_img_no = send_img_no;
		this.send_img_path = send_img_path;
		this.send_img_name_org = send_img_name_org;
		this.send_img_name_sav = send_img_name_sav;
		this.send_no = send_no;
	}

	public int getSend_img_no() {
		return send_img_no;
	}

	public void setSend_img_no(int send_img_no) {
		this.send_img_no = send_img_no;
	}

	public String getSend_img_path() {
		return send_img_path;
	}

	public void setSend_img_path(String send_img_path) {
		this.send_img_path = send_img_path;
	}

	public String getSend_img_name_org() {
		return send_img_name_org;
	}

	public void setSend_img_name_org(String send_img_name_org) {
		this.send_img_name_org = send_img_name_org;
	}

	public String getSend_img_name_sav() {
		return send_img_name_sav;
	}

	public void setSend_img_name_sav(String send_img_name_sav) {
		this.send_img_name_sav = send_img_name_sav;
	}

	public int getSend_no() {
		return send_no;
	}

	public void setSend_no(int send_no) {
		this.send_no = send_no;
	}

	@Override
	public String toString() {
		return "SendMessageImg [send_img_no=" + send_img_no + ", send_img_path=" + send_img_path
				+ ", send_img_name_org=" + send_img_name_org + ", send_img_name_sav=" + send_img_name_sav + ", send_no="
				+ send_no + "]";
	}
}
