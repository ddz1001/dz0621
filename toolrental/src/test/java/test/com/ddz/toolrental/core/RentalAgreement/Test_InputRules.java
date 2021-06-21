package test.com.ddz.toolrental.core.RentalAgreement;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.RentalAgreement;
import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;

class Test_InputRules {

	
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
	void Test_NullInputs() {
		assertThrows(NullPointerException.class, () -> {
			RentalAgreement.createRentalAgreement(null, new BigDecimal(1), LocalDate.now(), 1);
		});
		
		assertThrows(NullPointerException.class, () -> {
			RentalAgreement.createRentalAgreement(testTool, null, LocalDate.now(), 1);
		});
		
		assertThrows(NullPointerException.class, () -> {
			RentalAgreement.createRentalAgreement(testTool, new BigDecimal(1), null, 1);
		});
	}
	
	@Test
	void Test_ZeroDays() {
		assertThrows(RuntimeException.class, () -> {
			RentalAgreement.createRentalAgreement(testTool, new BigDecimal(1), LocalDate.now(), 0);
		});
	}
	
	@Test
	void Test_NegativeDays() {
		assertThrows(RuntimeException.class, () -> {
			RentalAgreement.createRentalAgreement(testTool, new BigDecimal(1), LocalDate.now(), -2);
		});
	}
	
	@Test
	void Test_PercentageTooHigh() {
		assertThrows(RuntimeException.class, () -> {
			RentalAgreement.createRentalAgreement(testTool, new BigDecimal("1.01"), LocalDate.now(), 1);
		});
	}
	
	@Test
	void Test_PercentageNegative() {
		assertThrows(RuntimeException.class, () -> {
			RentalAgreement.createRentalAgreement(testTool, new BigDecimal("-0.25"), LocalDate.now(), 1);
		});
	}
	

}
