package test.com.ddz.toolrental.core.RentalAgreement;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.RentalAgreement;
import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;

class Test_WithHolidays {

	
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
	void Test_EndAfterHoliday() {
		RentalAgreement ra = RentalAgreement.createRentalAgreement(
				testTool, new BigDecimal("0.10"), LocalDate.parse("2021-09-05"), 4); //labor day
		
		
		assertSame(testTool, ra.getTool());
		assertEquals(3, ra.getChargeDays());
		assertEquals(LocalDate.parse("2021-09-05"), ra.getCheckoutDate());
		assertEquals(LocalDate.parse("2021-09-09"), ra.getDueDate());
		
		assertEquals(0, ra.getDailyCharge().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getPreDiscount().compareTo(new BigDecimal("5.97")) );
		assertEquals(0, ra.getDiscountPercent().compareTo(new BigDecimal("0.10")) );
		assertEquals(0, ra.getDiscountAmount().compareTo(new BigDecimal("0.60")) );
		assertEquals(0, ra.getTotal().compareTo(new BigDecimal("5.37")) );
		
	}
	
	
	
	
	@Test
	void Test_EndOnHoliday() {
		RentalAgreement ra = RentalAgreement.createRentalAgreement(
				testTool, new BigDecimal("0.10"), LocalDate.parse("2021-09-03"), 3);
		
		
		assertSame(testTool, ra.getTool());
		assertEquals(0, ra.getChargeDays());
		assertEquals(LocalDate.parse("2021-09-03"), ra.getCheckoutDate());
		assertEquals(LocalDate.parse("2021-09-06"), ra.getDueDate());
		
		assertEquals(0, ra.getDailyCharge().compareTo(new BigDecimal("1.99")) );
		assertEquals(0, ra.getPreDiscount().compareTo(new BigDecimal("0.00")) );
		assertEquals(0, ra.getDiscountPercent().compareTo(new BigDecimal("0.10")) );
		assertEquals(0, ra.getDiscountAmount().compareTo(new BigDecimal("0.00")) );
		assertEquals(0, ra.getTotal().compareTo(new BigDecimal("0.00")) );
	}

}
