package test.com.ddz.toolrental.services.CheckoutService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.RentalAgreement;
import com.ddz.toolrental.services.CheckoutService;
import com.ddz.toolrental.services.ToolRepositoryLoader;

class Test_CheckoutService {

	
	static CheckoutService svc;
	
	@BeforeAll
	static void LoadRepos() throws IOException {
		ToolRepositoryLoader loaderService = ToolRepositoryLoader.getRepositoryLoader();
		loaderService.loadRepositories("resources/xsd/tooltype.xsd",
				   "resources/xsd/tool.xsd",
				   "resources/tooltypes",
				   "resources/tools");
		
		svc = CheckoutService.getCheckoutService();
	}
	
	////// DEMONSTRATION TESTS
	@Test
	void Test_LadderWithDiscount() {
		
		RentalAgreement lag = svc.checkoutTool(
				"LADW", 3, 10, LocalDate.parse("2020-07-02"));
		
		assertEquals(3, lag.getRentalDays());
		assertEquals(2, lag.getChargeDays()); //weekends, minus july4th
		assertEquals(LocalDate.parse("2020-07-02"), lag.getCheckoutDate());
		assertEquals(LocalDate.parse("2020-07-05"), lag.getDueDate());
		assertEquals(0, lag.getDailyCharge().compareTo(new BigDecimal("1.99")));
		assertEquals(0, lag.getPreDiscount().compareTo(new BigDecimal("3.98")));
		assertEquals(0, lag.getDiscountPercent().compareTo(new BigDecimal("0.10")));
		assertEquals(0, lag.getDiscountAmount().compareTo(new BigDecimal("0.40")));
		assertEquals(0, lag.getTotal().compareTo(new BigDecimal("3.58")));
		
		System.out.println(lag.buildAgreementString());
	}
	
	
	@Test
	void Test_ChainsawWithDiscount() {
		
		RentalAgreement lag = svc.checkoutTool(
				"CHNS", 5, 25, LocalDate.parse("2015-07-02"));
		
		assertEquals(5, lag.getRentalDays());
		assertEquals(3, lag.getChargeDays()); 
		assertEquals(LocalDate.parse("2015-07-02"), lag.getCheckoutDate());
		assertEquals(LocalDate.parse("2015-07-07"), lag.getDueDate());
		assertEquals(0, lag.getDailyCharge().compareTo(new BigDecimal("1.49")));
		assertEquals(0, lag.getPreDiscount().compareTo(new BigDecimal("4.47")));
		assertEquals(0, lag.getDiscountPercent().compareTo(new BigDecimal("0.25")));
		assertEquals(0, lag.getDiscountAmount().compareTo(new BigDecimal("1.12")));
		assertEquals(0, lag.getTotal().compareTo(new BigDecimal("3.35")));
		
		System.out.println(lag.buildAgreementString());
	}

	@Test
	void Test_JackhammerWithNoDiscount_LaborDay() {
		
		RentalAgreement lag = svc.checkoutTool(
				"JAKD", 6, 0, LocalDate.parse("2015-09-03"));
		
		assertEquals(6, lag.getRentalDays());
		assertEquals(3, lag.getChargeDays()); 
		assertEquals(LocalDate.parse("2015-09-03"), lag.getCheckoutDate());
		assertEquals(LocalDate.parse("2015-09-09"), lag.getDueDate());
		assertEquals(0, lag.getDailyCharge().compareTo(new BigDecimal("2.99")));
		assertEquals(0, lag.getPreDiscount().compareTo(new BigDecimal("8.97")));
		assertEquals(0, lag.getDiscountPercent().compareTo(new BigDecimal("0.00")));
		assertEquals(0, lag.getDiscountAmount().compareTo(new BigDecimal("0.00")));
		assertEquals(0, lag.getTotal().compareTo(new BigDecimal("8.97")));
		
		System.out.println(lag.buildAgreementString());
	}
	
	
	
	@Test
	void Test_JackhammerWithNoDiscount_July4th() {
		
		RentalAgreement lag = svc.checkoutTool(
				"JAKR", 9, 0, LocalDate.parse("2015-07-02"));
		
		assertEquals(9, lag.getRentalDays());
		assertEquals(5, lag.getChargeDays()); 
		assertEquals(LocalDate.parse("2015-07-02"), lag.getCheckoutDate());
		assertEquals(LocalDate.parse("2015-07-11"), lag.getDueDate());
		assertEquals(0, lag.getDailyCharge().compareTo(new BigDecimal("2.99")));
		assertEquals(0, lag.getPreDiscount().compareTo(new BigDecimal("14.95")));
		assertEquals(0, lag.getDiscountPercent().compareTo(new BigDecimal("0.00")));
		assertEquals(0, lag.getDiscountAmount().compareTo(new BigDecimal("0.00")));
		assertEquals(0, lag.getTotal().compareTo(new BigDecimal("14.95")));
		
		System.out.println(lag.buildAgreementString());
	}
	
	@Test
	void Test_JackhammerWithDiscount_July4th() {
		
		RentalAgreement lag = svc.checkoutTool(
				"JAKR", 4, 50, LocalDate.parse("2020-07-02"));
		
		assertEquals(4, lag.getRentalDays());
		assertEquals(1, lag.getChargeDays()); 
		assertEquals(LocalDate.parse("2020-07-02"), lag.getCheckoutDate());
		assertEquals(LocalDate.parse("2020-07-06"), lag.getDueDate());
		assertEquals(0, lag.getDailyCharge().compareTo(new BigDecimal("2.99")));
		assertEquals(0, lag.getPreDiscount().compareTo(new BigDecimal("2.99")));
		assertEquals(0, lag.getDiscountPercent().compareTo(new BigDecimal("0.50")));
		assertEquals(0, lag.getDiscountAmount().compareTo(new BigDecimal("1.50")));
		assertEquals(0, lag.getTotal().compareTo(new BigDecimal("1.49")));
		
		System.out.println(lag.buildAgreementString());
	}
	
	
	@Test
	void Test_JackhammerWithInvalidDiscount() {
		
		assertThrows( RuntimeException.class, () -> {
			RentalAgreement lag = svc.checkoutTool(
					"JAKR", 5, 101, LocalDate.parse("2015-09-03"));
		});
	}
	
	
	
	/////// MISC TESTS
	
	
	@Test 
	void Test_InvalidInputs() {
		
		assertThrows( RuntimeException.class, () -> {
			RentalAgreement lag = svc.checkoutTool(
					"foo", 1, 0, LocalDate.parse("2015-09-03"));
		});
		
		assertThrows( NullPointerException.class, () -> {
			RentalAgreement lag = svc.checkoutTool(
					null, 1, 0, LocalDate.parse("2015-09-03"));
		});
		
		assertThrows( RuntimeException.class, () -> {
			RentalAgreement lag = svc.checkoutTool(
					"JAKD", 0, 0, LocalDate.parse("2015-09-03"));
		});
		
		assertThrows( RuntimeException.class, () -> {
			RentalAgreement lag = svc.checkoutTool(
					"JAKD", 1, -1, LocalDate.parse("2015-09-03"));
		});
		
		assertThrows( RuntimeException.class, () -> {
			RentalAgreement lag = svc.checkoutTool(
					"JAKD", 1, 101, LocalDate.parse("2015-09-03"));
		});
		
		assertThrows( NullPointerException.class, () -> {
			RentalAgreement lag = svc.checkoutTool(
					"JAKD", 1, 10, null);
		});
		
	}
	
	
	
}
