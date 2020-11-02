package com.pratap.springcloud.ui.model.request;

import java.math.BigDecimal;
/**
 * 
 * @author Pratap Narayan
 * Update happens only Coupon's discount & expDate field
 *
 */
public class CouponUpdateRequestModel {

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
