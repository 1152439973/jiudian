package com.gx.po;


//这张表把数据库的2个表打在一起啦，分别是（Attribute类型表，   Attributedetails类型对应具体的分类）
public class AttributePo {
  

	private Integer id;         //属性ID
	
	private Integer far_id;
	
	private String attributeName;//属性名称
	
	private String attributeDetailsName;//属性明细名称
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFar_id() {
		return far_id;
	}
	
	public void setFar_id(Integer far_id) {
		this.far_id = far_id;
	}
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeDetailsName() {
		return attributeDetailsName;
	}

	public void setAttributeDetailsName(String attributeDetailsName) {
		this.attributeDetailsName = attributeDetailsName;
	}
	
	
	
	
	
	
}
