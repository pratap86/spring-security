package com.pratap.springcloud.exceptions;

public class CouponServiceException extends RuntimeException {

	private static final long serialVersionUID = -3431630135015643813L;

	public CouponServiceException(String message) {
		super(message);
	}
	
}
