package test.com.ddz.toolrental.core.RentalAgreement;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.RentalAgreement;
import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;

class Test_WithWeekends {

	
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
	void Test_EndOnWeekend() {
		RentalAgreement ra = RentalAgreement.createRentalAgreement(
				testTool, new BigDecimal("0.10"), LocalDate.parse("2021-06-17"), 2);
		
		
		assertSame(testTool, ra.getTool());
		assertEquals(1, ra.getChargeDays());
		assertEquals(LocalDate.parse("2021-06-17"), ra.getCheckoutDate());
		assertEquals(LocalDate.parse("2021-06-19"), ra.getDueDate());
		
		assertEquals(0, ra.getDailyCharge().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getPreDiscount().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getDiscountPercent().compareTo(new BigDecimal("0.10")) );
		assertEquals(0, ra.getDiscountAmount().compareTo(new BigDecimal("0.20")) );
		assertEquals(0, ra.getTotal().compareTo(new BigDecimal("1.79")) );
		
	}
	
	
	
	
	@Test
	void Test_EndAfterWeekend() {
		RentalAgreement ra = RentalAgreement.createRentalAgreement(
				testTool, new BigDecimal("0.10"), LocalDate.parse("2021-06-17"), 6);
		
		
		assertSame(testTool, ra.getTool());
		assertEquals(4, ra.getChargeDays());
		assertEquals(LocalDate.parse("2021-06-17"), ra.getCheckoutDate());
		assertEquals(LocalDate.parse("2021-06-23"), ra.getDueDate());
		
		assertEquals(0, ra.getDailyCharge().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getPreDiscount().compareTo(new BigDecimal("7.96")) );
		assertEquals(0, ra.getDiscountPercent().compareTo(new BigDecimal("0.10")) );
		assertEquals(0, ra.getDiscountAmount().compareTo(new BigDecimal("0.80")) );
		assertEquals(0, ra.getTotal().compareTo(new BigDecimal("7.16")) );
	}
	
	

}
