package com.ddz.toolrental.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents rental pricing information for a given period.
 * 
 * @author Dante Zitello
 *
 */
public class RentalPriceInfo {

	private BigDecimal dailyPrice;
	private BigDecimal periodPrice;
	private BigDecimal discount;
	private BigDecimal discountAmount;
	private BigDecimal total;
	
	/**
	 * Get the daily price.
	 * @return daily price
	 */
	public BigDecimal getDailyPrice() {
		return dailyPrice;
	}
	
	/**
	 * Get the period price. That is, the price for the period without
	 * a discount. 
	 * @return period price
	 */
	public BigDecimal getPeriodPrice() {
		return periodPrice;
	}

	/**
	 * Get the discount percentage
	 * @return discount percentage
	 */
	public BigDecimal getDiscountPercentage() {
		return discount;
	}
	
	/**
	 * Get the discount amount. That is, the discount applied to the
	 * period price.
	 * @return discount amount
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	
	/**
	 * Get the sum total. Period price subtracted by the discount amount.
	 * @return sum total
	 */
	public BigDecimal getTotal() {
		return total;
	}
	
	
	/**
	 * Factory for RentalPriceInfo. Calculates the pricing information
	 * from the provided parameters. All inputs and results are rounded up to 2 significant figures
	 * (2.589 becomes 2.59).
	 * @param dailyPrice  daily price
	 * @param discount  discount to apply
	 * @param applicableDays  number of days to be charged
	 * @return object containing results
	 */
	public static RentalPriceInfo calculate(BigDecimal dailyPrice, BigDecimal discount, int applicableDays) {
		
		RentalPriceInfo rp = new RentalPriceInfo();
		
		rp.dailyPrice = dailyPrice.setScale(2, RoundingMode.HALF_UP);
		rp.periodPrice = dailyPrice.multiply(new BigDecimal(applicableDays)).setScale(2, RoundingMode.HALF_UP);
		rp.discount = discount.setScale(2, RoundingMode.HALF_UP);
		rp.discountAmount = rp.periodPrice.multiply(rp.discount).setScale(2, RoundingMode.HALF_UP);
		rp.total = rp.periodPrice.subtract(rp.discountAmount);
		
		return rp;
		
	}	
}
