package com.sun.app.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="room_booking")
public class RoomBooking {

	@Id
	@Column(name="ROOM_NUMBER")
	private Integer roomNumber;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="CHECK_IN_TIME")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
	private Date  checkINTime;
	
	@Column(name="CHECK_OUT_TIME")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
	private Date checkOUTTime;
	
	@Column(name="customer_id")
	private Integer customerId;
	
	@Transient
	private boolean isAvilable;
	
	
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Date getCheckINTime() {
		return checkINTime;
	}
	public void setCheckINTime(Date checkINTime) {
		this.checkINTime = checkINTime;
	}
	public Date getCheckOUTTime() {
		return checkOUTTime;
	}
	public void setCheckOUTTime(Date checkOUTTime) {
		this.checkOUTTime = checkOUTTime;
	}
	public boolean isAvilable() {
		return isAvilable;
	}
	public void setAvilable(boolean isAvilable) {
		this.isAvilable = isAvilable;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}	
	
}
