package test.com.ddz.toolrental.core.RentalAgreement;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.RentalAgreement;
import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;

class Test_WeekdaysOnly {

	static Tool testTool;
	
	
	@BeforeAll
	static void InitializeTool() {
		testTool = new Tool();
		
		ToolType tt = new ToolType();
		
		tt.setName("Ladder");
		tt.setRentalPrice(new BigDecimal("1.99"));
		tt.setWeekdayCharge(true);
		tt.setWeekendCharge(false);
		tt.setHolidayCharge(false);
		
		testTool.setToolType(tt);
		testTool.setToolBrand("Werner");
		testTool.setToolCode("LADW");
	}

	@Test
	void Test_OneDay() {
		RentalAgreement ra = RentalAgreement.createRentalAgreement(
				testTool, new BigDecimal("0.10"), LocalDate.parse("2021-06-21"), 1);
		
		
		assertSame(testTool, ra.getTool());
		assertEquals(1, ra.getChargeDays());
		assertEquals(LocalDate.parse("2021-06-21"), ra.getCheckoutDate());
		assertEquals(LocalDate.parse("2021-06-22"), ra.getDueDate());
		
		assertEquals(0, ra.getDailyCharge().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getPreDiscount().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getDiscountPercent().compareTo(new BigDecimal("0.10")) );
		assertEquals(0, ra.getDiscountAmount().compareTo(new BigDecimal("0.20")) );
		assertEquals(0, ra.getTotal().compareTo(new BigDecimal("1.79")) );
		
	}
	
	
	
	
	@Test
	void Test_ManyDays() {
		RentalAgreement ra = RentalAgreement.createRentalAgreement(
				testTool, new BigDecimal("0.10"), LocalDate.parse("2021-06-21"), 4);
		
		
		assertSame(testTool, ra.getTool());
		assertEquals(4, ra.getChargeDays());
		assertEquals(LocalDate.parse("2021-06-21"), ra.getCheckoutDate());
		assertEquals(LocalDate.parse("2021-06-25"), ra.getDueDate());
		
		assertEquals(0, ra.getDailyCharge().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getPreDiscount().compareTo(new BigDecimal("7.96")) );
		assertEquals(0, ra.getDiscountPercent().compareTo(new BigDecimal("0.10")) );
		assertEquals(0, ra.getDiscountAmount().compareTo(new BigDecimal("0.80")) );
		assertEquals(0, ra.getTotal().compareTo(new BigDecimal("7.16")) );
	}
	
	@Test
	void Test_StartOnWeekend() {
		RentalAgreement ra = RentalAgreement.createRentalAgreement(
				testTool, new BigDecimal("0.10"), LocalDate.parse("2021-06-20"), 4);
		
		
		assertSame(testTool, ra.getTool());
		assertEquals(4, ra.getChargeDays());
		assertEquals(LocalDate.parse("2021-06-20"), ra.getCheckoutDate());
		assertEquals(LocalDate.parse("2021-06-24"), ra.getDueDate());
		
		assertEquals(0, ra.getDailyCharge().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getPreDiscount().compareTo(new BigDecimal("7.96")) );
		assertEquals(0, ra.getDiscountPercent().compareTo(new BigDecimal("0.10")) );
		assertEquals(0, ra.getDiscountAmount().compareTo(new BigDecimal("0.80")) );
		assertEquals(0, ra.getTotal().compareTo(new BigDecimal("7.16")) );
	}
	
	@Test
	void Test_StartOnHoliday() {
		RentalAgreement ra = RentalAgreement.createRentalAgreement(
				testTool, new BigDecimal("0.10"), LocalDate.parse("2021-07-05"), 4); //july 4th observed on 5th
		
		
		assertSame(testTool, ra.getTool());
		assertEquals(4, ra.getChargeDays());
		assertEquals(LocalDate.parse("2021-07-05"), ra.getCheckoutDate());
		assertEquals(LocalDate.parse("2021-07-09"), ra.getDueDate());
		
		assertEquals(0, ra.getDailyCharge().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getPreDiscount().compareTo(new BigDecimal("7.96")) );
		assertEquals(0, ra.getDiscountPercent().compareTo(new BigDecimal("0.10")) );
		assertEquals(0, ra.getDiscountAmount().compareTo(new BigDecimal("0.80")) );
		assertEquals(0, ra.getTotal().compareTo(new BigDecimal("7.16")) );
	}

}
