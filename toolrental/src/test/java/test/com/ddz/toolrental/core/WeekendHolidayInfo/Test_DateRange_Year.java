package test.com.ddz.toolrental.core.WeekendHolidayInfo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.WeekendHolidayInfo;

class Test_DateRange_Year {

	@Test
	void Test_FullYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2010-01-01"),
						LocalDate.parse("2011-01-01"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 365, drc.getTotalDays());
		assertEquals( 259, drc.getBetweenWeekdays()); //subtract off holidays and weekends
		assertEquals( 1, drc.getBetweenJuly4ths());
		assertEquals( 1, drc.getBetweenLaborDays());
		assertEquals( 2, drc.getBetweenHolidays());
		assertEquals( 104, drc.getBetweenWeekends() ); //There are 104 weekend days in a year

	}
	
	@Test
	void Test_YearExtraSaturday() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2017-01-01"),
						LocalDate.parse("2018-01-01"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 365, drc.getTotalDays()); 
		assertEquals( 258, drc.getBetweenWeekdays()); //subtract off holidays and weekends
		assertEquals( 1, drc.getBetweenJuly4ths());
		assertEquals( 1, drc.getBetweenLaborDays());
		assertEquals( 2, drc.getBetweenHolidays());
		assertEquals( 105, drc.getBetweenWeekends() ); //2017 started on a saturday, so an extra weekend is added
	}
	
	@Test
	void Test_LeapYear() {
		WeekendHolidayInfo drc = 
				WeekendHolidayInfo.calculateFromRange( 
						LocalDate.parse("2024-01-01"), //Next leap year is 2024
						LocalDate.parse("2025-01-01"));
		
		//System.out.println(drc.getBetweenWeekends());
		
		assertEquals( 366, drc.getTotalDays());
		assertEquals( 260, drc.getBetweenWeekdays());
		assertEquals( 1, drc.getBetweenJuly4ths());
		assertEquals( 1, drc.getBetweenLaborDays());
		assertEquals( 2, drc.getBetweenHolidays());
		assertEquals( 104, drc.getBetweenWeekends() ); //Still 104
	}

}
