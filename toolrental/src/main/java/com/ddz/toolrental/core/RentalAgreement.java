package com.ddz.toolrental.core;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

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
	
	public Tool getTool() {
		return tool;
	}
	
	public int getRentalDays() {
		return rentalDays;
	}
	
	public LocalDate getCheckoutDate() {
		return checkDate;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public int getChargeDays() {
		return chargeDays;
	}
	
	public BigDecimal getDailyCharge() {
		return dailyCharge;
	}
	
	public BigDecimal getPreDiscount() {
		return preDiscount;
	}
	
	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}
	
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	
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
		WeekendHolidayCalculator hl = WeekendHolidayCalculator.create(startExclusive, endInclusive);
		
		int chargeDays = calculateChargeDays(hl.getBetweenWeekdays(),
									hl.getBetweenWeekends(),
									hl.getBetweenHolidays(),
									tt.isWeekdayCharge(),
									tt.isWeekendCharge(),
									tt.isHolidayCharge() );
		
		//Calculate rental price
		RentalPriceCalculator rpc = RentalPriceCalculator.calculate(tt.getRentalPrice(), discountPercentage, chargeDays);
		
		
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
