package com.gx.po;

import java.sql.Timestamp;

//要接待的服务对象表
public class ReceiveTargetPo {
	
	private Integer id;                  //接待对象ID
	
	private Integer targetTypeID;       //对象类别ID
	
	private String principal;           //负责人
	
	private String teamName;            //团队名称

	private String teamCode;            //团队编号
	
	private Timestamp registerTime;     //登记时间
	
	private String contactPhoneNUmber;   //联系电话
	

	//扩展字段
	private String targetTypeName;   //对象的具体类别
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTargetTypeID() {
		return targetTypeID;
	}

	public void setTargetTypeID(Integer targetTypeID) {
		this.targetTypeID = targetTypeID;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public String getTargetTypeName() {
		return targetTypeName;
	}

	public void setTargetTypeName(String targetTypeName) {
		this.targetTypeName = targetTypeName;
	}
	
	public String getContactPhoneNUmber() {
		return contactPhoneNUmber;
	}
	
	public void setContactPhoneNUmber(String contactPhoneNUmber) {
		this.contactPhoneNUmber = contactPhoneNUmber;
	}
	

}
