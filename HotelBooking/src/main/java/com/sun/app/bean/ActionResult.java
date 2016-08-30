package com.sun.app.bean;

import java.util.ArrayList;
import java.util.List;

public class ActionResult {
	
	private Object data;
	private List<String>  errors = new ArrayList<String>();
	private int statusCode=2000;
	private boolean status;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void addError(String error){
		 this.errors.add(error);
	}
	
	

}
