package com.ddz.toolrental.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RentalPriceCalculator {

	private BigDecimal dailyPrice;
	private BigDecimal periodPrice;
	private BigDecimal discount;
	private BigDecimal discountAmount;
	private BigDecimal total;
	

	public BigDecimal getDailyPrice() {
		return dailyPrice;
	}
	
	public BigDecimal getPeriodPrice() {
		return periodPrice;
	}

	public BigDecimal getDiscountPercentage() {
		return discount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public BigDecimal getTotal() {
		return total;
	}
	
	
	
	public static RentalPriceCalculator calculate(BigDecimal dailyPrice, BigDecimal discount, int applicableDays) {
		
		RentalPriceCalculator rp = new RentalPriceCalculator();
		
		rp.dailyPrice = dailyPrice.setScale(2, RoundingMode.HALF_UP);
		rp.periodPrice = dailyPrice.multiply(new BigDecimal(applicableDays)).setScale(2, RoundingMode.HALF_UP);
		rp.discount = discount.setScale(2, RoundingMode.HALF_UP);
		rp.discountAmount = rp.periodPrice.multiply(rp.discount).setScale(2, RoundingMode.HALF_UP);
		rp.total = rp.periodPrice.subtract(rp.discountAmount);
		
		return rp;
		
	}	
}
