package test.com.ddz.toolrental.core.WeekendHolidayCalculator;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.WeekendHolidayCalculator;

class Test_DateRange_July4th {


	
	///////////////////Observed on Monday
	@Test
	void Test_StartSundayEndAfter_Monday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-07-03"),
						LocalDate.parse("2021-07-22"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndAfter_Monday_SameYear() {
	WeekendHolidayCalculator drc = 
		WeekendHolidayCalculator.create( 
				LocalDate.parse("2021-07-01"),
				LocalDate.parse("2021-07-22"));
	
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartMondayEndAfter_Monday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-07-05"),
						LocalDate.parse("2021-07-22"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndSunday_Monday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-07-01"),
						LocalDate.parse("2021-07-04"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndMonday_Monday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-07-01"),
						LocalDate.parse("2021-07-05"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndTuesday_Monday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-07-01"),
						LocalDate.parse("2021-07-06"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndBefore_Monday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-07-01"),
						LocalDate.parse("2021-07-03"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartAfterEndAfterMonday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-07-06"),
						LocalDate.parse("2021-07-09"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	
	
	
	
	///////////////////////////////// Observed Friday
	
	@Test
	void Test_StartFridayEndAfter_Friday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2020-07-03"),
						LocalDate.parse("2020-07-22"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartSaturdayEndAfter_Friday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2020-07-04"),
						LocalDate.parse("2020-07-22"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndAfter_Friday_SameYear() {
	WeekendHolidayCalculator drc = 
		WeekendHolidayCalculator.create( 
				LocalDate.parse("2020-07-01"),
				LocalDate.parse("2020-07-22"));
	
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartThursdayEndAfter_Friday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2020-07-02"),
						LocalDate.parse("2020-07-22"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndFriday_Friday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2020-07-01"),
						LocalDate.parse("2020-07-03"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndSaturday_Friday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2020-07-01"),
						LocalDate.parse("2020-07-04"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndBefore_Friday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2020-06-19"),
						LocalDate.parse("2020-07-02"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartAfterEndAfterFriday_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2020-07-05"),
						LocalDate.parse("2020-07-09"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	
	
	
	///////////////////////////////// Observed Thursday
	
	@Test
	void Test_StartOnEndAfter_SameDay_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2019-07-04"),
						LocalDate.parse("2019-07-22"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndAfter_SameDay_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2019-07-03"),
						LocalDate.parse("2019-07-22"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndOn_SameDay_SameYear() {
	WeekendHolidayCalculator drc = 
		WeekendHolidayCalculator.create( 
				LocalDate.parse("2019-07-01"),
				LocalDate.parse("2019-07-04"));
	
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartBeforeEndBefore_SameDay_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2019-07-01"),
						LocalDate.parse("2019-07-03"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartAfterEndAfter_SameDay_SameYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2019-07-05"),
						LocalDate.parse("2019-07-09"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	
	
	
	
	/////////////////////// One year apart
	
	@Test
	void Test_StartBeforeEndAfter_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2019-07-01"),
						LocalDate.parse("2020-07-09"));
		
		assertEquals( 2, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartAfterEndAfter_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2019-07-05"),
						LocalDate.parse("2020-07-09"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	
	@Test
	void Test_StartBeforeEndBefore_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2019-07-01"),
						LocalDate.parse("2020-07-01"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartAfterEndBefore_OneYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2019-07-07"),
						LocalDate.parse("2020-07-01"));
		
		assertEquals( 0, drc.getBetweenJuly4ths() );
	}
	
	
	/////////////////////// Several years apart
	
	@Test
	void Test_StartBeforeEndAfter_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2018-07-01"),
						LocalDate.parse("2020-07-09"));
		
		assertEquals( 3, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartAfterEndAfter_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2018-07-05"),
						LocalDate.parse("2020-07-09"));
		
		assertEquals( 2, drc.getBetweenJuly4ths() );
	}
	
	
	@Test
	void Test_StartBeforeEndBefore_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2018-07-01"),
						LocalDate.parse("2020-07-01"));
		
		assertEquals( 2, drc.getBetweenJuly4ths() );
	}
	
	@Test
	void Test_StartAfterEndBefore_NYear() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2018-07-07"),
						LocalDate.parse("2020-07-01"));
		
		assertEquals( 1, drc.getBetweenJuly4ths() );
	}

	
	
}
