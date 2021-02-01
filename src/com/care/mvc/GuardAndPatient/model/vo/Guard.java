package com.care.mvc.GuardAndPatient.model.vo;

public class Guard {

	private int guard_no;
	
	private String guard_gen;
	
	private String guard_pat;
	
	private String memId;
	
	private String guardName;

	public Guard() {
		
	}
	
	public Guard(int guard_no, String guard_gen, String guard_pat, String memId, String guardName) {
		this.guard_no = guard_no;
		this.guard_gen = guard_gen;
		this.guard_pat = guard_pat;
		this.memId = memId;
		this.guardName = guardName;
	}

	public int getGuard_no() {
		return guard_no;
	}

	public void setGuard_no(int guard_no) {
		this.guard_no = guard_no;
	}

	public String getGuard_gen() {
		return guard_gen;
	}

	public void setGuard_gen(String guard_gen) {
		this.guard_gen = guard_gen;
	}

	public String getGuard_pat() {
		return guard_pat;
	}

	public void setGuard_pat(String guard_pat) {
		this.guard_pat = guard_pat;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getGuardName() {
		return guardName;
	}

	public void setGuardName(String guardName) {
		this.guardName = guardName;
	}

	@Override
	public String toString() {
		return "Guard [guard_no=" + guard_no + ", guard_gen=" + guard_gen + ", guard_pat=" + guard_pat + ", memId="
				+ memId + ", guardName=" + guardName + "]";
	}
	
}
