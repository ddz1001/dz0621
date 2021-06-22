package test.com.ddz.toolrental.core.WeekendHolidayInfo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.WeekendHolidayInfo;

class Test_DateRange_LaborDay {

	
	//////////////////////////////////////////////Same year
	@Test
	void Test_StartBeforeLaborDayEndAfter_SameYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2021-09-07"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartOnLaborDayEndAfter_SameYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-06"),
						LocalDate.parse("2021-09-07"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndOn_SameYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2021-09-06"));
		
		assertEquals( 0, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndBefore_SameYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2021-09-03"));
		
		assertEquals( 0, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndAfter_SameYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2021-10-15"));
		
		assertEquals( 0, drc.getBetweenLaborDays() );
		
	}

	
	
	////////////////////////////////////////////One year apart
	@Test
	void Test_StartBeforeLaborDayEndAfter_OneYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2022-09-07"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartOnLaborDayEndAfter_OneYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-06"),
						LocalDate.parse("2022-09-07"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndBefore_OneYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2022-09-03"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndOn_OneYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2022-09-05"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndAfter_OneYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2022-10-15"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndBefore_OneYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2022-09-01"));
		
		assertEquals( 0, drc.getBetweenLaborDays() );
		
	}
	
	
	
	
	
	////////////////////////////////////////////Many years apart
	@Test
	void Test_StartBeforeLaborDayEndAfter_NYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2023-09-07"));
		
		assertEquals( 3, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartOnLaborDayEndAfter_NYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-06"),
						LocalDate.parse("2023-09-07"));
		
		assertEquals( 3, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndBefore_NYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2023-09-03"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartBeforeLaborDayEndOn_NYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-01"),
						LocalDate.parse("2023-09-04"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndAfter_NYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2023-09-05"));
		
		assertEquals( 2, drc.getBetweenLaborDays() );
		
	}
	
	@Test
	void Test_StartAfterLaborDayEndBefore_NYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2021-09-08"),
						LocalDate.parse("2023-09-02"));
		
		assertEquals( 1, drc.getBetweenLaborDays() );
		
	}
	
	
	/////////////////////////////////////////////////////// Misc cases
	
	@Test
	void Test_100Years() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("1900-09-03"),
						LocalDate.parse("2000-09-05"));
		
		assertEquals( 101, drc.getBetweenLaborDays() ); //101 since we start counting at 1
		
	}
	
	
}
