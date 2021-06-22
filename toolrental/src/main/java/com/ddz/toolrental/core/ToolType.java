package com.ddz.toolrental.core;

import java.math.BigDecimal;

/**
 * Represents a ToolType. Only holds information about pricing,
 * and charging rules.
 * The Tool class holds information about branding.
 * 
 * @author Dante Zitello
 * @see Tool
 */
public class ToolType {
	
	private String name;
	
	private BigDecimal rentalPrice;
	
	private boolean weekendCharge;
	private boolean weekdayCharge;
	private boolean holidayCharge;
	
	/**
	 * Empty constructor
	 */
	public ToolType() {
		
	}
	
	
	/**
	 * Basic constructor
	 * @param name  Name of type
	 * @param rentalPrice  price of type
	 * @param weekdayCharge  weekdays are charged
	 * @param weekendCharge  weekends are charged
	 * @param holidayCharge  holidays are charged
	 */
	ToolType(String name, BigDecimal rentalPrice, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
		this.name = name;
		this.rentalPrice = rentalPrice;
		this.weekendCharge = weekendCharge;
		this.weekdayCharge = weekdayCharge;
		this.holidayCharge = holidayCharge;
	}


	/**
	 * Get the name of this ToolType
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this ToolType
	 * @param name name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the rental price of this ToolType
	 * @return rental price
	 */
	public BigDecimal getRentalPrice() {
		return rentalPrice;
	}
	
	/**
	 * Set the rental price of this ToolType
	 * @param rentalPrice  price to set
	 */
	public void setRentalPrice(BigDecimal rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	
	/**
	 * Check whether this ToolType has a weekend charge.
	 * @return true if there is, false otherwise
	 */
	public boolean isWeekendCharge() {
		return weekendCharge;
	}
	
	/**
	 * Set whether this ToolType has a weekend charge.
	 * @param weekendCharge value
	 */
	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}

	/**
	 * Check whether this ToolType has a weekday charge.
	 * @return true if there is, false otherwise
	 */
	public boolean isWeekdayCharge() {
		return weekdayCharge;
	}
	
	/**
	 * Set whether this ToolType has a weekday charge.
	 * @param weekdayCharge value
	 */
	public void setWeekdayCharge(boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}

	/**
	 * Check whether this ToolType has a holiday charge.
	 * @return true if there is, false otherwise
	 */
	public boolean isHolidayCharge() {
		return holidayCharge;
	}
	
	/**
	 * Set whether this ToolType has a holiday charge.
	 * @param holidayCharge value
	 */
	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}
	
	
	
	
	
	
}
