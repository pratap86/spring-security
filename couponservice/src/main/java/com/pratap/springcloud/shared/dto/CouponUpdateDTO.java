package com.pratap.springcloud.shared.dto;

import java.math.BigDecimal;

public class CouponUpdateDTO {

	private BigDecimal discount;

	private String expDate;

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
