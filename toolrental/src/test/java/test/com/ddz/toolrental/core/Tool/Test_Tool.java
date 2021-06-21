package test.com.ddz.toolrental.core.Tool;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;

class Test_Tool {

	static Tool testTool;
	
	
	@BeforeAll
	static void InitializeTool() {
		testTool = new Tool();
		
		ToolType tt = new ToolType();
		
		tt.setName("Ladder");
		tt.setRentalPrice(new BigDecimal("1.99"));
		tt.setWeekdayCharge(true);
		tt.setWeekendCharge(true);
		tt.setHolidayCharge(false);
		
		testTool.setToolType(tt);
		testTool.setToolBrand("Werner");
		testTool.setToolCode("LADW");
	}
	
	
	
	@Test
	void HasCorrectValues() {
		assertEquals("Werner", testTool.getToolBrand());
		assertEquals("LADW", testTool.getToolCode());
		assertTrue( testTool.getToolType().isWeekdayCharge() );
		assertTrue( testTool.getToolType().isWeekendCharge());
		assertFalse( testTool.getToolType().isHolidayCharge() );
		assertEquals(0, testTool.getToolType().getRentalPrice().compareTo(new BigDecimal("1.99")));
	}
}
