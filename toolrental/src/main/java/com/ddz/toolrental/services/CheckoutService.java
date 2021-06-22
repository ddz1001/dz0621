package com.ddz.toolrental.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.ddz.toolrental.core.RentalAgreement;
import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.repositories.ToolRepository;

public class CheckoutService {
	
	private ToolRepository repo;
	private static CheckoutService checkout;
	
	CheckoutService() {
		repo = ToolRepository.getRepository();
	}
	
	public static CheckoutService getCheckoutService() {
		if(checkout == null) {
			checkout = new CheckoutService();
		}
		
		return checkout;
	}
	
	
	public RentalAgreement checkoutTool(String toolCode, int rentalDayCount, int discountPercent, LocalDate start) {
		
		if(toolCode == null || start == null) {
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
		
		return RentalAgreement.createRentalAgreement(tool, discountDec, start, rentalDayCount);
	}
	
}
