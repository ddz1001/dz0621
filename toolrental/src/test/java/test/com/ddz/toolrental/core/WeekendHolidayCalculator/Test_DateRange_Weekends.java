package test.com.ddz.toolrental.core.WeekendHolidayCalculator;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.WeekendHolidayCalculator;

class Test_DateRange_Weekends {
	
	//// Less than 7 days for a range
	
	@Test
	void Test_LessThan7DaysMidweekStartEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-03"),
						LocalDate.parse("2021-06-07"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 4, drc.getTotalDays()); 
		assertEquals( 2, drc.getBetweenWeekdays());
		assertEquals( 2, drc.getBetweenWeekends() );
		
			
		
	}
	
	@Test
	void Test_LessThan7DaysMidweekStartSatEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-04"),
						LocalDate.parse("2021-06-05"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 1, drc.getTotalDays());
		assertEquals( 1, drc.getBetweenWeekdays());
		assertEquals( 0, drc.getBetweenWeekends() );
	}
	
	@Test
	void Test_LessThan7DaysMidweekStartSunEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-04"),
						LocalDate.parse("2021-06-06"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 2, drc.getTotalDays());
		assertEquals( 1, drc.getBetweenWeekdays());
		assertEquals( 1, drc.getBetweenWeekends() );
	}
	
	@Test
	void Test_LessThan7DaysSatStartMidweekEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-12"),
						LocalDate.parse("2021-06-14"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 2, drc.getTotalDays());
		assertEquals( 0, drc.getBetweenWeekdays());
		assertEquals( 2, drc.getBetweenWeekends() );

	}
	
	@Test
	void Test_LessThan7DaysSunStartMidweekEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-13"),
						LocalDate.parse("2021-06-14"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 1, drc.getTotalDays());
		assertEquals( 0, drc.getBetweenWeekdays());
		assertEquals( 1, drc.getBetweenWeekends() );

	}
	
	@Test
	void Test_LessThan7NoWeekends() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-14"),
						LocalDate.parse("2021-06-17"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 3, drc.getTotalDays());
		assertEquals( 3, drc.getBetweenWeekdays());
		assertEquals( 0, drc.getBetweenWeekends() );

	}
	
	
	//// More than 7 days for a range
	
	@Test
	void Test_MoreThan7DaysMidweekStartEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-04"),
						LocalDate.parse("2021-06-21"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 17, drc.getTotalDays());
		assertEquals( 11, drc.getBetweenWeekdays());
		assertEquals( 6, drc.getBetweenWeekends() );
			
		
	}
	
	@Test
	void Test_MoreThan7DaysMidweekStartSatEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-04"),
						LocalDate.parse("2021-06-26"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 22, drc.getTotalDays());
		assertEquals( 16, drc.getBetweenWeekdays());
		assertEquals( 6, drc.getBetweenWeekends() );
	}
	
	@Test
	void Test_MoreThan7DaysMidweekStartSunEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-04"),
						LocalDate.parse("2021-06-27"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 23, drc.getTotalDays());
		assertEquals( 16, drc.getBetweenWeekdays());
		assertEquals( 7, drc.getBetweenWeekends() );
	}
	
	@Test
	void Test_MoreThan7DaysSatStartMidweekEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-05"),
						LocalDate.parse("2021-06-21"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 16, drc.getTotalDays());
		assertEquals( 10, drc.getBetweenWeekdays());
		assertEquals( 6, drc.getBetweenWeekends() );

	}
	
	@Test
	void Test_MoreThan7DaysSunStartMidweekEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-06"),
						LocalDate.parse("2021-06-21"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 15, drc.getTotalDays());
		assertEquals( 10, drc.getBetweenWeekdays());
		assertEquals( 5, drc.getBetweenWeekends() );

	}
	
	@Test
	void Test_SameStartEnd() {
		WeekendHolidayCalculator drc = 
				WeekendHolidayCalculator.create( 
						LocalDate.parse("2021-06-02"),
						LocalDate.parse("2021-06-30"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 28, drc.getTotalDays());
		assertEquals( 20, drc.getBetweenWeekdays());
		assertEquals( 8, drc.getBetweenWeekends() );

	}

}
