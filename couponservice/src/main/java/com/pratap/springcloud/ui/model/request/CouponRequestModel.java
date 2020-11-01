package com.pratap.springcloud.ui.model.request;

import java.math.BigDecimal;

public class CouponRequestModel {

	private String code;
	
	private BigDecimal discount;
	
	private String expDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
}
