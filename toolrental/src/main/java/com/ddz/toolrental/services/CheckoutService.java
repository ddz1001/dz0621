package com.ddz.toolrental.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.ddz.toolrental.core.RentalAgreement;
import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.repositories.ToolRepository;

/**
 * Checkout service. Generates RentalAgreements for given tools.
 * @author Dante Zitello
 *
 */
public class CheckoutService {
	
	private ToolRepository repo;
	private static CheckoutService checkout;
	
	private CheckoutService() {
		repo = ToolRepository.getRepository();
	}
	
	/**
	 * Access CheckoutService's singleton
	 * @return checkout service
	 */
	public static CheckoutService getCheckoutService() {
		if(checkout == null) {
			checkout = new CheckoutService();
		}
		
		return checkout;
	}
	
	/**
	 * Create a RentalAgreement for a given tool, represented by its unique toolCode. 
	 * 
	 * @param toolCode  tool's code
	 * @param discountPercent  discount percentage in range of 0 - 100
	 * @param checkoutDate  checkout date for tool
	 * @param rentalDayCount  number of days to rent
	 * @return RentalAgreement containing rental information
	 * 
	 * @throws NullPointerException if toolCode or checkoutDate are null.
	 * @throws RuntimeException if discountPercent is not in range 0 - 100 or rentalDayCount not equal to or greater than 1.
	 */
	public RentalAgreement checkoutTool(String toolCode, int discountPercent, LocalDate checkoutDate, int rentalDayCount) {
		
		if(toolCode == null || checkoutDate == null) {
			throw new NullPointerException();
		}
		
		if(rentalDayCount < 1) {
			throw new RuntimeException("Rental days must be 1 or greater, but was (" + rentalDayCount + ").");
		}
		
		if( discountPercent < 0 || discountPercent > 100 ) {
			throw new RuntimeException("Discount percentage must be in range (0 - 100) but was " + discountPercent);
		}
		
		
		Tool tool = repo.findByToolCode(toolCode)
				.orElseThrow(() -> new RuntimeException("Tool with code (" + toolCode + ") not found") );
		
		BigDecimal discountDec = new BigDecimal(discountPercent).divide(new BigDecimal(100));
		
		return RentalAgreement.createRentalAgreement(tool, discountDec, checkoutDate, rentalDayCount);
	}
	
}
