package com.care.mvc.care.model.vo;

public class PatientWanted {
	private String wantedGrade;
	
	private String wantedGen;
	
	private String wantedAge;
	
	private String wantedIll;
	

	public PatientWanted() {
	}
	
	public PatientWanted(String wantedGrade, String wantedGen, String wantedAge, String wantedIll) {
		super();
		this.wantedGrade = wantedGrade;
		this.wantedGen = wantedGen;
		this.wantedAge = wantedAge;
		this.wantedIll = wantedIll;
	}

	public String getWantedGrade() {
		return wantedGrade;
	}


	public void setWantedGrade(String wantedGrade) {
		this.wantedGrade = wantedGrade;
	}


	public String getWantedGen() {
		return wantedGen;
	}


	public void setWantedGen(String wantedGen) {
		this.wantedGen = wantedGen;
	}


	public String getWantedAge() {
		return wantedAge;
	}

	public void setWantedAge(String wantedAge) {
		this.wantedAge = wantedAge;
	}


	public String getWantedIll() {
		return wantedIll;
	}


	public void setWantedIll(String wantedIll) {
		this.wantedIll = wantedIll;
	}


	@Override
	public String toString() {
		return "PatientWanted [wantedGrade=" + wantedGrade + ", wantedGen=" + wantedGen + ", wantedAge=" + wantedAge
				+ ", wantedIll=" + wantedIll + "]";
	}
	
}
