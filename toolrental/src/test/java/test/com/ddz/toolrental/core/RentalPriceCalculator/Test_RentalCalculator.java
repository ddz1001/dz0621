package test.com.ddz.toolrental.core.RentalPriceCalculator;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.RentalPriceCalculator;

class Test_RentalCalculator {

	@Test
	void Test_NoDiscount_OneDay() {
		RentalPriceCalculator rp = RentalPriceCalculator.calculate(
				new BigDecimal("5.00"), new BigDecimal(0), 1);
		
		//The way assertEquals handles BigDecimals is obtuse, but this lets
		//you see if its equal, less, or greater
		assertEquals(0, rp.getDailyPrice().compareTo(new BigDecimal(5)));
		assertEquals(0, rp.getPeriodPrice().compareTo(new BigDecimal(5)));
		assertEquals(0, rp.getDiscountAmount().compareTo(new BigDecimal(0)));
		assertEquals(0, rp.getDiscountPercentage().compareTo(new BigDecimal(0)));
		assertEquals(0, rp.getTotal().compareTo(new BigDecimal(5)));
				
	}
	
	@Test
	void Test_Discount_OneDay() {
		RentalPriceCalculator rp = RentalPriceCalculator.calculate(
				new BigDecimal("5.00"), new BigDecimal("0.50"), 1);
		

		assertEquals(0, rp.getDailyPrice().compareTo(new BigDecimal(5)));
		assertEquals(0, rp.getPeriodPrice().compareTo(new BigDecimal(5)));
		assertEquals(0, rp.getDiscountAmount().compareTo(new BigDecimal("2.50")));
		assertEquals(0, rp.getDiscountPercentage().compareTo(new BigDecimal("0.50")));
		assertEquals(0, rp.getTotal().compareTo(new BigDecimal("2.50")));
	}
	
	@Test
	void Test_NoDiscount_ManyDays() {
		RentalPriceCalculator rp = RentalPriceCalculator.calculate(
				new BigDecimal("2.00"), new BigDecimal("0.00"), 5);
		

		assertEquals(0, rp.getDailyPrice().compareTo(new BigDecimal("2.00")));
		assertEquals(0, rp.getPeriodPrice().compareTo(new BigDecimal("10.00")));
		assertEquals(0, rp.getDiscountAmount().compareTo(new BigDecimal("0.00")));
		assertEquals(0, rp.getDiscountPercentage().compareTo(new BigDecimal("0.00")));
		assertEquals(0, rp.getTotal().compareTo(new BigDecimal("10.00")));
	}
	
	@Test
	void Test_Discount_ManyDays() {
		RentalPriceCalculator rp = RentalPriceCalculator.calculate(
				new BigDecimal("2.00"), new BigDecimal("0.20"), 5);
	

		assertEquals(0, rp.getDailyPrice().compareTo(new BigDecimal("2.00")));
		assertEquals(0, rp.getPeriodPrice().compareTo(new BigDecimal("10.00")));
		assertEquals(0, rp.getDiscountAmount().compareTo(new BigDecimal("2.00")));
		assertEquals(0, rp.getDiscountPercentage().compareTo(new BigDecimal("0.20")));
		assertEquals(0, rp.getTotal().compareTo(new BigDecimal("8.00")));
	}
	
	
	@Test
	void Test_FullDiscount() {
		RentalPriceCalculator rp = RentalPriceCalculator.calculate(
				new BigDecimal("2.00"), new BigDecimal("1.00"), 5);
	

		assertEquals(0, rp.getDailyPrice().compareTo(new BigDecimal("2.00")));
		assertEquals(0, rp.getPeriodPrice().compareTo(new BigDecimal("10.00")));
		assertEquals(0, rp.getDiscountAmount().compareTo(new BigDecimal("10.00")));
		assertEquals(0, rp.getDiscountPercentage().compareTo(new BigDecimal("1.00")));
		assertEquals(0, rp.getTotal().compareTo(new BigDecimal("0.00")));
	}
	
	
	
	@Test
	void Test_Rounding1() {
		RentalPriceCalculator rp = RentalPriceCalculator.calculate(
				new BigDecimal("2.249"), new BigDecimal("0.231"), 5);
	

		assertEquals(0, rp.getDailyPrice().compareTo(new BigDecimal("2.25")));
		assertEquals(0, rp.getPeriodPrice().compareTo(new BigDecimal("11.25")));
		assertEquals(0, rp.getDiscountAmount().compareTo(new BigDecimal("2.59"))); //rounded up from 2.5875
		assertEquals(0, rp.getDiscountPercentage().compareTo(new BigDecimal("0.23")));
		assertEquals(0, rp.getTotal().compareTo(new BigDecimal("8.66")));
	}
	
	@Test
	void Test_Rounding2() {
		RentalPriceCalculator rp = RentalPriceCalculator.calculate(
				new BigDecimal("12.77"), new BigDecimal("0.599"), 1);
	

		assertEquals(0, rp.getDailyPrice().compareTo(new BigDecimal("12.77")));
		assertEquals(0, rp.getPeriodPrice().compareTo(new BigDecimal("12.77")));
		assertEquals(0, rp.getDiscountAmount().compareTo(new BigDecimal("7.66"))); //rounded down from 7.662
		assertEquals(0, rp.getDiscountPercentage().compareTo(new BigDecimal("0.60")));
		assertEquals(0, rp.getTotal().compareTo(new BigDecimal("5.11")));
	}
	
	

}
