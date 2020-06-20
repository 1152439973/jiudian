package com.gx.po;
 
//商品表
public class CommodityPo {
	
	private Integer id;                 //商品id
	
	private Integer commodityTypeID;    //商品类别id
	
	private Integer uOMID;              //计量单位id
	
	private String commodityName;       //商品名称
	
	private String salePrice;           //价格
	
	
	
	//宽展字段
	private String commodityTypeName;    //商品类别名称
	
	private String uOMName;              //计量单位名称
	
	
	
	//宽展字段
	private Integer huiyuanjia;           //会员价
		
	
	
	

	public Integer getHuiyuanjia() {
			return huiyuanjia;
		}

		public void setHuiyuanjia(Integer huiyuanjia) {
			this.huiyuanjia = huiyuanjia;
		}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommodityTypeID() {
		return commodityTypeID;
	}

	public void setCommodityTypeID(Integer commodityTypeID) {
		this.commodityTypeID = commodityTypeID;
	}

	public Integer getuOMID() {
		return uOMID;
	}

	public void setuOMID(Integer uOMID) {
		this.uOMID = uOMID;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getCommodityTypeName() {
		return commodityTypeName;
	}

	public void setCommodityTypeName(String commodityTypeName) {
		this.commodityTypeName = commodityTypeName;
	}

	public String getuOMName() {
		return uOMName;
	}

	public void setuOMName(String uOMName) {
		this.uOMName = uOMName;
	}

}
