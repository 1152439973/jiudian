package com.gx.po;
 //客房设置表
public class RoomSetPo {
	
	private Integer id;
	
	private Integer guestRoomLevelID;                    /*客房等级ID*/    
	
	private Integer roomStateID;                         /*房态ID*/
	
	private String roomNumber;                           /* 房间号*/
	
	private String roomAmount;                           /*床位数量*/
	
	private double standardPriceDay;                     /*标准客房/天*/
	
	private double standardPrice;                        /* 标准房价/小时*/
	 
	private String maxDuration;                          /*最大时间*/
	 
	private String firstDuration;                        /*最小时间*/
	
	private double firstPrice;                           /* 首段价格*/
	
	
	//拓展上面的字段，因为有关联关系（下面这二个字段应该是AttributePo表里面的字段）
	
	private String guestRoomLevelName;         /*房间类型，单人普通房  双人，还是什么房子*/
	
	private String roomName;                    /*房间的状态，有没有人*/
	
	
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGuestRoomLevelID() {
		return guestRoomLevelID;
	}

	public void setGuestRoomLevelID(Integer guestRoomLevelID) {
		this.guestRoomLevelID = guestRoomLevelID;
	}

	public Integer getRoomStateID() {
		return roomStateID;
	}

	public void setRoomStateID(Integer roomStateID) {
		this.roomStateID = roomStateID;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomAmount() {
		return roomAmount;
	}

	public void setRoomAmount(String roomAmount) {
		this.roomAmount = roomAmount;
	}

	public double getStandardPriceDay() {
		return standardPriceDay;
	}

	public void setStandardPriceDay(double standardPriceDay) {
		this.standardPriceDay = standardPriceDay;
	}

	public double getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}

	public String getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(String maxDuration) {
		this.maxDuration = maxDuration;
	}

	public String getFirstDuration() {
		return firstDuration;
	}

	public void setFirstDuration(String firstDuration) {
		this.firstDuration = firstDuration;
	}

	public double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}
	
	
	
	//拓展字段属性
	
	public String getGuestRoomLevelName() {
		return guestRoomLevelName;
	}
	
	public void setGuestRoomLevelName(String guestRoomLevelName) {
		this.guestRoomLevelName = guestRoomLevelName;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		return "RoomSetPo [id=" + id + ", guestRoomLevelID=" + guestRoomLevelID + ", roomStateID=" + roomStateID
				+ ", roomNumber=" + roomNumber + ", roomAmount=" + roomAmount + ", standardPriceDay=" + standardPriceDay
				+ ", standardPrice=" + standardPrice + ", maxDuration=" + maxDuration + ", firstDuration="
				+ firstDuration + ", firstPrice=" + firstPrice + ", guestRoomLevelName=" + guestRoomLevelName
				+ ", roomName=" + roomName + "]";
	}
	

}
