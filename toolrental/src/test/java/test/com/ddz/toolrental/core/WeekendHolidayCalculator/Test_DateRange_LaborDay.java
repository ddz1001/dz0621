package test.com.ddz.toolrental.core.WeekendHolidayCalculator;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.WeekendHolidayCalculator;

class Test_DateRange_LaborDay {

	
	//////////////////////////////////////////////Same year
	@Test
	void Test_StartBeforeLaborDayEndAfter_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2021-09-07"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartOnLaborDayEndAfter_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-06"),
						LocalDate.parse("2021-09-07"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndOn_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2021-09-06"));
		
		assertEquals( 0, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndBefore_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2021-09-03"));
		
		assertEquals( 0, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndAfter_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2021-10-15"));
		
		assertEquals( 0, drc.getBetweenLaborDays() );
		
	}

	
	
	////////////////////////////////////////////One year apart
	@Test
	void Test_StartBeforeLaborDayEndAfter_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2022-09-07"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartOnLaborDayEndAfter_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-06"),
						LocalDate.parse("2022-09-07"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndBefore_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2022-09-03"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndOn_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2022-09-05"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndAfter_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2022-10-15"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndBefore_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2022-09-01"));
		
		assertEquals( 0, drc.getBetweenLaborDays() );
		
	}
	
	
	
	
	
	////////////////////////////////////////////Many years apart
	@Test
	void Test_StartBeforeLaborDayEndAfter_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2023-09-07"));
		
		assertEquals( 3, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartOnLaborDayEndAfter_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-06"),
						LocalDate.parse("2023-09-07"));
		
		assertEquals( 3, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndBefore_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2023-09-03"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndOn_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2023-09-04"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndAfter_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2023-09-05"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndBefore_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2023-09-02"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	
	/////////////////////////////////////////////////////// Misc cases
	
	@Test
	void Test_100Years() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("1900-09-03"),
						LocalDate.parse("2000-09-05"));
		
		assertEquals( 101, drc.getBetweenLaborDays() ); //101 since we start counting at 1
		
	}
	
	
}
