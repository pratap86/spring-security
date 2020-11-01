package com.pratap.springcloud.ui.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CouponRequestModel {

	@NotNull(message = "Coupon must has a code value")
	@Size(min = 2, message = "code should contains atleast 2 chars")
	private String code;
	
	@NotNull(message = "coupon must have a discount")
	@DecimalMax(value = "15.0", inclusive = false, message = "discount must be less than 15.0")
    @Digits(integer=2, fraction=2)
	private BigDecimal discount;
	
	@NotNull(message = "Coupon must have a expDate")
	@Size(min = 8, message = "expDate should have a valid date(YYYYMMDD)")
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
