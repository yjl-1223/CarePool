package com.care.mvc.GuardAndPatient.model.vo;

public class Patient {
	
	private int pat_no;
	
	private int guard_no;
	
	private String pat_place;
	
	private String pat_period;
	
	private String pat_hop_time;
	
	private String pat_name;
	
	private int pat_age;
	
	private String pat_gen;
	
	private int pat_kg;
	
	private String pat_infect;
	
	private String pat_grade;
	
	private String pat_sanit;
	
	private String pat_paral;
	
	private String pat_move;
	
	private String pat_bed;

	private String pat_cogdis;
	
	private String pat_bathroom;
	
	private String pat_bowel_mn;
	
	private String pat_ostomy;
	
	private String pat_help_eat;
	
	private String pat_suction;
	
	private String pat_guard_gen;

	private String pat_etc;
	
	
	public Patient() {
		
	}

	public Patient(int pat_no, int guard_no, String pat_place, String pat_period, String pat_hop_time, String pat_name, int pat_age,
			String pat_gen, int pat_kg, String pat_infect, String pat_grade, String pat_sanit, String pat_paral,
			String pat_move, String pat_bed, String pat_cogdis, String pat_bathroom, String pat_bowel_mn,
			String pat_ostomy, String pat_help_eat, String pat_suction, String pat_guard_gen, String pat_etc) {
		this.pat_no = pat_no;
		this.guard_no = guard_no;
		this.pat_place = pat_place;
		this.pat_period = pat_period;
		this.pat_hop_time = pat_hop_time;
		this.pat_name = pat_name;
		this.pat_age = pat_age;
		this.pat_gen = pat_gen;
		this.pat_kg = pat_kg;
		this.pat_infect = pat_infect;
		this.pat_grade = pat_grade;
		this.pat_sanit = pat_sanit;
		this.pat_paral = pat_paral;
		this.pat_move = pat_move;
		this.pat_bed = pat_bed;
		this.pat_cogdis = pat_cogdis;
		this.pat_bathroom = pat_bathroom;
		this.pat_bowel_mn = pat_bowel_mn;
		this.pat_ostomy = pat_ostomy;
		this.pat_help_eat = pat_help_eat;
		this.pat_suction = pat_suction;
		this.pat_guard_gen = pat_guard_gen;
		this.pat_etc = pat_etc;
	}

	public int getPat_no() {
		return pat_no;
	}

	public void setPat_no(int pat_no) {
		this.pat_no = pat_no;
	}

	public int getGuard_no() {
		return guard_no;
	}

	public void setGuard_no(int guard_no) {
		this.guard_no = guard_no;
	}

	public String getPat_place() {
		return pat_place;
	}

	public void setPat_place(String pat_place) {
		this.pat_place = pat_place;
	}

	public String getPat_period() {
		return pat_period;
	}

	public void setPat_period(String pat_period) {
		this.pat_period = pat_period;
	}

	public String getPat_hop_time() {
		return pat_hop_time;
	}

	public void setPat_hop_time(String pat_hop_time) {
		this.pat_hop_time = pat_hop_time;
	}

	public String getPat_name() {
		return pat_name;
	}

	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}

	public int getPat_age() {
		return pat_age;
	}

	public void setPat_age(int pat_age) {
		this.pat_age = pat_age;
	}

	public String getPat_gen() {
		return pat_gen;
	}

	public void setPat_gen(String pat_gen) {
		this.pat_gen = pat_gen;
	}

	public int getPat_kg() {
		return pat_kg;
	}

	public void setPat_kg(int pat_kg) {
		this.pat_kg = pat_kg;
	}

	public String getPat_infect() {
		return pat_infect;
	}

	public void setPat_infect(String pat_infect) {
		this.pat_infect = pat_infect;
	}

	public String getPat_grade() {
		return pat_grade;
	}

	public void setPat_grade(String pat_grade) {
		this.pat_grade = pat_grade;
	}

	public String getPat_sanit() {
		return pat_sanit;
	}

	public void setPat_sanit(String pat_sanit) {
		this.pat_sanit = pat_sanit;
	}

	public String getPat_paral() {
		return pat_paral;
	}

	public void setPat_paral(String pat_paral) {
		this.pat_paral = pat_paral;
	}

	public String getPat_move() {
		return pat_move;
	}

	public void setPat_move(String pat_move) {
		this.pat_move = pat_move;
	}

	public String getPat_bed() {
		return pat_bed;
	}

	public void setPat_bed(String pat_bed) {
		this.pat_bed = pat_bed;
	}

	public String getPat_cogdis() {
		return pat_cogdis;
	}

	public void setPat_cogdis(String pat_cogdis) {
		this.pat_cogdis = pat_cogdis;
	}

	public String getPat_bathroom() {
		return pat_bathroom;
	}

	public void setPat_bathroom(String pat_bathroom) {
		this.pat_bathroom = pat_bathroom;
	}

	public String getPat_bowel_mn() {
		return pat_bowel_mn;
	}

	public void setPat_bowel_mn(String pat_bowel_mn) {
		this.pat_bowel_mn = pat_bowel_mn;
	}

	public String getPat_ostomy() {
		return pat_ostomy;
	}

	public void setPat_ostomy(String pat_ostomy) {
		this.pat_ostomy = pat_ostomy;
	}

	public String getPat_help_eat() {
		return pat_help_eat;
	}

	public void setPat_help_eat(String pat_help_eat) {
		this.pat_help_eat = pat_help_eat;
	}

	public String getPat_suction() {
		return pat_suction;
	}

	public void setPat_suction(String pat_suction) {
		this.pat_suction = pat_suction;
	}

	public String getPat_guard_gen() {
		return pat_guard_gen;
	}

	public void setPat_guard_gen(String pat_guard_gen) {
		this.pat_guard_gen = pat_guard_gen;
	}

	public String getPat_etc() {
		return pat_etc;
	}

	public void setPat_etc(String pat_etc) {
		this.pat_etc = pat_etc;
	}

	@Override
	public String toString() {
		return "Patient [pat_no=" + pat_no + ", guard_no=" + guard_no + ", pat_place=" + pat_place + ", pat_period="
				+ pat_period + ", pat_hop_time=" + pat_hop_time + ", pat_name=" + pat_name + ", pat_age=" + pat_age
				+ ", pat_gen=" + pat_gen + ", pat_kg=" + pat_kg + ", pat_infect=" + pat_infect + ", pat_grade="
				+ pat_grade + ", pat_sanit=" + pat_sanit + ", pat_paral=" + pat_paral + ", pat_move=" + pat_move
				+ ", pat_bed=" + pat_bed + ", pat_cogdis=" + pat_cogdis + ", pat_bathroom=" + pat_bathroom
				+ ", pat_bowel_mn=" + pat_bowel_mn + ", pat_ostomy=" + pat_ostomy + ", pat_help_eat=" + pat_help_eat
				+ ", pat_suction=" + pat_suction + ", pat_guard_gen=" + pat_guard_gen + ", pat_etc=" + pat_etc + "]";
	}


	
}
