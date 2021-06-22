package com.ddz.toolrental.core;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Represents a rental agreement to be presented to a customer.
 * Holds information about the tool being rented, checkout date,
 * due date, rental price, and any applicable discounts.
 * 
 * @author Dante Zitello
 *
 */
public class RentalAgreement {
	
	
	private Tool tool;
	private int rentalDays;
	private LocalDate checkDate;
	private LocalDate dueDate;
	
	private int chargeDays;
	private BigDecimal dailyCharge;
	private BigDecimal preDiscount;
	private BigDecimal discountPercent;
	private BigDecimal discountAmount;
	private BigDecimal total;
	
	/**
	 * Get the tool in this RentalAgreement
	 * 
	 * @return tool
	 */
	public Tool getTool() {
		return tool;
	}
	
	/**
	 * Get the total number of days that the tool
	 * is being rented for.
	 * @return total rental days
	 */
	public int getRentalDays() {
		return rentalDays;
	}
	
	/**
	 * Get the checkout date for this RentalAgreement.
	 * @return checkout date
	 */
	public LocalDate getCheckoutDate() {
		return checkDate;
	}
	
	/**
	 * Get the indicated due date for this
	 * RentalAgreement.
	 * @return due date
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	/**
	 * Get total number of days that are actually
	 * being charged for. This will be different
	 * from <code>getRentalDays()</code> depending on 
	 * the tool's charging rules. 
	 * @return total charge days
	 */
	public int getChargeDays() {
		return chargeDays;
	}
	
	/**
	 * Get the daily charge for the tool
	 * @return daily rental charge, formatted as 
	 * decimal with 2 significant figures
	 */
	public BigDecimal getDailyCharge() {
		return dailyCharge;
	}
	
	/**
	 * Get the pre-discount price. This is equivalent to
	 * <code>chargeDays * dailyCharge</code>.
	 * @return pre-discount price
	 */
	public BigDecimal getPreDiscount() {
		return preDiscount;
	}
	
	/**
	 * Get the discount percentage as a decimal in the range
	 * 0.00 to 1.00
	 * @return discount percentage
	 */
	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}
	
	/**
	 * Get the discount amount. This is equivalent to
	 * <code>discountPercentage * preDiscount</code>.
	 * @return discount amount
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	
	/**
	 * Get the total price. This is equivalent to
	 * <code> preDiscount - (discountPercentage * preDiscount)</code>.
	 * @return sum total
	 */
	public BigDecimal getTotal() {
		return total;
	}
	
	/**
	 * Build a formatted string representing the RentalAgreement
	 * as it would be presented to a clerk or customer.
	 * @return formatted string
	 */
	public String buildAgreementString() {
		//Format the dates
		String fmtString = "MM/dd/yy";
		
		String checkout = this.checkDate.format(
				DateTimeFormatter.ofPattern(fmtString));
		
		String due = this.dueDate.format(
				DateTimeFormatter.ofPattern(fmtString));
		
		//Format the prices
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		String dailyrental = nf.format(this.dailyCharge);
		String prediscount = nf.format(this.preDiscount);
		String dpercentage = "%" + this.discountPercent.multiply(
				new BigDecimal("100.0")).setScale(0);
		String damount = nf.format(this.discountAmount);
		String total = nf.format(this.total);
		
		StringBuilder sb = new StringBuilder(1024);
		
		sb.append("Tool Code: ").append(tool.getToolCode()).append("\n");
		sb.append("Tool Type: ").append(tool.getToolType().getName()).append("\n");
		sb.append("Tool Brand: ").append(tool.getToolBrand()).append("\n");
		sb.append("Rental Days: ").append(this.rentalDays).append("\n");
		sb.append("Checkout Date: ").append(checkout).append("\n");
		sb.append("Due Date: ").append(due).append("\n");
		sb.append("Daily Rental Charge: ").append(dailyrental).append("\n");
		sb.append("Charge Days: ").append(this.chargeDays).append("\n");
		sb.append("Pre-Discount Charge: ").append(prediscount).append("\n");
		sb.append("Discount Percent: ").append(dpercentage).append("\n");
		sb.append("Discount Amount: ").append(damount).append("\n");
		sb.append("Final Charge: ").append(total).append("\n");
		
		return sb.toString();
	}
	
	/**
	 * Factory method for creating a RentalAgreement.
	 * 
	 * @param tool  tool to be rented
	 * @param discountPercentage  discount to be applied, in range 0.00 to 1.00
	 * @param checkout  date of checkout
	 * @param days  number of days in rental period
	 * @return Rental agreement from provided data
	 * 
	 * @throws NullPointerException if tool, discountPercentage, or checkout are null.
	 * @throws RuntimeException if discountPercentage is not in range 0.00 - 1.00, or 
	 * days is not greater than 1.
	 */
	public static RentalAgreement createRentalAgreement(Tool tool, BigDecimal discountPercentage, LocalDate checkout, int days) {
		
		//Check if any of the provided arguments are null
		if( tool == null || discountPercentage == null || checkout == null ) {
			throw new NullPointerException();
		}
		
		//Check that discountPercentage is in range 0.00 to 1.00
		if( discountPercentage.compareTo(new BigDecimal("0.00")) < 0 || 
			discountPercentage.compareTo(new BigDecimal("1.00")) > 0 ) {
			throw new RuntimeException("discountPercentage must be within range (0.00 - 1.00), but was (" 
					+ discountPercentage.toString() + ").");
		}
		
		//Check that days is 1 or more
		if( days < 1 ) {
			throw new RuntimeException("Rental days must be 1 or greater, but was (" + days + ").");
		}
		
		
		//Get the type of tool, which holds price and charge information
		ToolType tt = tool.getToolType();
		
		//Calculate the charge days. First day is EXCLUSIVE, last day is INCLUSIVE
		LocalDate startExclusive = checkout.plusDays(1);
		LocalDate endInclusive = startExclusive.plusDays(days);
		WeekendHolidayInfo hl = WeekendHolidayInfo.calculateFromRange(startExclusive, endInclusive);
		
		int chargeDays = calculateChargeDays(hl.getBetweenWeekdays(),
									hl.getBetweenWeekends(),
									hl.getBetweenHolidays(),
									tt.isWeekdayCharge(),
									tt.isWeekendCharge(),
									tt.isHolidayCharge() );
		
		//Calculate rental price
		RentalPriceInfo rpc = RentalPriceInfo.calculate(tt.getRentalPrice(), discountPercentage, chargeDays);
		
		
		//Fill out relevant information into agreement
		RentalAgreement ra = new RentalAgreement();
		
		ra.tool = tool;
		ra.rentalDays = days;
		ra.checkDate = checkout;
		ra.dueDate = checkout.plusDays(days);
		
		ra.chargeDays = chargeDays;
		ra.dailyCharge = rpc.getDailyPrice();
		ra.preDiscount = rpc.getPeriodPrice();
		ra.discountPercent = rpc.getDiscountPercentage();
		ra.discountAmount = rpc.getDiscountAmount();
		ra.total = rpc.getTotal();
	
		
		return ra;
	}
	
	/**
	 * Calculate charge days
	 * @param weekdays  number of weekdays in range
	 * @param weekends  number of weekends in range
	 * @param holidays  number of holidays in range
	 * @param dayCharge  whether or not there is a weekday charge
	 * @param weCharge  whether or not there is a weekend charge
	 * @param holCharge  whether or not there is a holiday charge
	 * @return total number of charge days
	 */
	private static int calculateChargeDays(long weekdays, long weekends, long holidays, boolean dayCharge, boolean weCharge, boolean holCharge) {
		int chargedays = 0;
		
		if(dayCharge) {
			chargedays += weekdays;
		}
		
		if(weCharge) {
			chargedays += weekends;
		}
		
		if(holCharge) {
			chargedays += holidays;
		}
		
		return chargedays;
	}
	
}
