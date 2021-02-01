package com.care.mvc.care.model.vo;

import java.sql.Date;

public class CareImage {
	private int imgNo;
	
	private int careNo;
	
	private Date imgDate;
	
	private String imgPath;
	
	private String imgNameOrg;
	
	private String imgNameSav;

	public CareImage() {
	}

	public int getImgNo() {
		return imgNo;
	}

	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}

	public int getCareNo() {
		return careNo;
	}

	public void setCareNo(int careNo) {
		this.careNo = careNo;
	}

	public Date getImgDate() {
		return imgDate;
	}

	public void setImgDate(Date imgDate) {
		this.imgDate = imgDate;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgNameOrg() {
		return imgNameOrg;
	}

	public void setImgNameOrg(String imgNameOrg) {
		this.imgNameOrg = imgNameOrg;
	}

	public String getImgNameSav() {
		return imgNameSav;
	}

	public void setImgNameSav(String imgNameSav) {
		this.imgNameSav = imgNameSav;
	}

	public CareImage(int imgNo, int careNo, Date imgDate, String imgPath, String imgNameOrg, String imgNameSav) {
		super();
		this.imgNo = imgNo;
		this.careNo = careNo;
		this.imgDate = imgDate;
		this.imgPath = imgPath;
		this.imgNameOrg = imgNameOrg;
		this.imgNameSav = imgNameSav;
	}

	@Override
	public String toString() {
		return "CareImage [imgNo=" + imgNo + ", careNo=" + careNo + ", imgDate=" + imgDate + ", imgPath=" + imgPath
				+ ", imgNameOrg=" + imgNameOrg + ", imgNameSav=" + imgNameSav + "]";
	}

}
