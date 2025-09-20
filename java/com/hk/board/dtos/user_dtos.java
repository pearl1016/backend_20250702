package com.hk.board.dtos;

import java.io.Serializable;
import java.util.Date;

public class user_dtos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int tseq;
	private String tid;
	private String tpassword;
	private String tname;
	private String taddress;
	private String tphone; 
	private String temail;
	private String tenabled;
	private String trole;
	private Date regdate; 
	
	
	public user_dtos() {
		super();
	}

	// All fields constructor
	public user_dtos(int tseq, String tid, String tpassword, String tname, String taddress, String tphone, String temail, String tenabled,
			String trole, Date regdate) {
		super();
		this.tseq = tseq;
		this.tid = tid;
		this.tpassword = tpassword;
		this.tname = tname;
		this.taddress = taddress;
		this.tphone = tphone;
		this.temail = temail;
		this.tenabled = tenabled;
		this.trole = trole;
		this.regdate = regdate;
	}
	
	// Add other overloaded constructors here as needed, but make sure the parameter list is unique.
	
	public int getTseq() {
		return tseq;
	}

	public void setTseq(int tseq) {
		this.tseq = tseq;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTpassword() {
		return tpassword;
	}

	public void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTaddress() {
		return taddress;
	}

	public void setTaddress(String taddress) {
		this.taddress = taddress;
	}

	public String getTphone() {
		return tphone;
	}

	public void setTphone(String tphone) {
		this.tphone = tphone;
	}

	public String getTemail() {
		return temail;
	}

	public void setTemail(String temail) {
		this.temail = temail;
	}

	public String getTenabled() {
		return tenabled;
	}

	public void setTenabled(String tenabled) {
		this.tenabled = tenabled;
	}

	public String getTrole() {
		return trole;
	}

	public void setTrole(String trole) {
		this.trole = trole;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "user_dtos [tseq=" + tseq + ", tid=" + tid + ", tpassword=" + tpassword + ", tname=" + tname + ", taddress=" + taddress
				+ ", tphone=" + tphone + ", temail=" + temail + ", tenabled=" + tenabled + ", trole=" + trole
				+ ", regdate=" + regdate + "]";
	}
}