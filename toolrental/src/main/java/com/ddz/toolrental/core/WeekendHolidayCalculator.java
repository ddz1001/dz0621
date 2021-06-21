package com.ddz.toolrental.core;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class WeekendHolidayCalculator {

	LocalDate start;
	LocalDate end;
	
	long totaldays;
	long weekdays;
	long weekends;
	long holidays;
	
	long laborDays;
	long july4ths;
	
	private WeekendHolidayCalculator(LocalDate start, LocalDate end) {
		this.start = LocalDate.from(start);
		this.end = LocalDate.from(end);
		
		this.weekends = 0;
		this.holidays = 0;
		
		this.laborDays = 0;
		this.july4ths = 0;
	}
	
	

	
	
	static public WeekendHolidayCalculator create(LocalDate startInclusive, LocalDate endExclusive) {
		
		WeekendHolidayCalculator dt = new WeekendHolidayCalculator(startInclusive, endExclusive);
		dt.computeTotalDays();
		dt.computeWeekends();
		dt.computeHolidays();
		dt.computeWeekdays();
		
		return dt;
		
	}
	
	private void computeTotalDays() {
		this.totaldays = ChronoUnit.DAYS.between(start, end);
	}
	
	private void computeWeekdays() {
	    long days = this.totaldays;
	    
	    days-=weekends;
	    days-=holidays;
	    
	    this.weekdays = days;
	}
	
	private void computeWeekends() {
		int startW = start.getDayOfWeek().getValue();
	    int endW = end.getDayOfWeek().getValue();

	    long days = this.totaldays;
	    long result = 0; 
	    
	    
	    if(days == 0) {
	    	this.weekends = 0;
	    }
	    
	    if(days % 7 != 0) {
	    	if(days < 7) {
	    		
	    		
	    		if(endW == 7) {
	    			result+=1;
	    		}
	    		else if(startW == 7) {
	    			result+=1;
	    		}
	    		else if(startW > endW) {
	    			result+=2;
	    		}
	    	}
	    	
	    	else {
	    		long weekdays = days - 2*(days/7); //remove weekdays
	    		
	    		
	    		if(endW == 7) {
	    			weekdays -= 1;
	    		}
	    		else if(startW == 7) {
	    			weekdays -= 1;
	    		}
	    		else if(startW > endW) {
	    			weekdays -= 2;
	    		}
	    		
	    		result = days - weekdays;
	    		
	    	}
	    }
	    else {
	    	result = days - 5*(days/7); //remove weekdays
	    }
	    
	    this.weekends = result;
	}
	
	
	private void computeHolidays() {
		
		//Check number of Labor Days in range
		
		LocalDate laborDayS = getLaborDayDate( start.getYear() );
		LocalDate laborDayE = getLaborDayDate( end.getYear() );
		
		LocalDate july4thS = getObservedJuly4thDate( start.getYear() );
		LocalDate july4thE = getObservedJuly4thDate( end.getYear() );
		
		
		int yearsApart = end.getYear() - start.getYear();
		
		if(yearsApart == 0) {
		
			if( (start.equals(laborDayS) || start.isBefore(laborDayS) ) &&  
					end.isAfter( laborDayS )) {
				this.laborDays++;
			}
		}
		else { //If spanning more than one year
			
			if( (start.equals(laborDayS) || start.isBefore(laborDayS) )) {
				this.laborDays++;
			}
			
			if (end.isAfter( laborDayE )) {
				this.laborDays++;
			}
			
			if(yearsApart > 1) {
				this.laborDays += yearsApart - 1;
			}
			
		}
		
		
		//Check for number of Observed July 4ths
		
		if(yearsApart == 0) {
			
			if( (start.equals(july4thS) || start.isBefore(july4thS) ) &&  
					end.isAfter( july4thS )) {
				this.july4ths++;
			}
		}
		else { //If spanning more than one year
			
			if( (start.equals(july4thS) || start.isBefore(july4thS) )) {
				this.july4ths++;
			}
			
			if (end.isAfter( july4thE )) {
				this.july4ths++;
			}
			
			if(yearsApart > 1) {
				this.july4ths += yearsApart - 1;
			}
			
		}
		
		this.holidays = this.july4ths + this.laborDays;
	}
	
	
	private LocalDate getLaborDayDate(int year) {
		
		LocalDate ld = LocalDate.of(year, 
				Month.SEPTEMBER.getValue(), 
				DayOfWeek.MONDAY.getValue());
		
		TemporalAdjuster ad = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
		
		return ld.with(ad);
		
	}
	
	private LocalDate getObservedJuly4thDate(int year) {
		LocalDate ld = LocalDate.of(year, Month.JULY, 4);
		

		if(ld.getDayOfWeek() == DayOfWeek.SATURDAY) {
			return LocalDate.of(year, Month.JULY, 3);
		}
		else if(ld.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return LocalDate.of(year, Month.JULY, 5);
		}
		else {
			return ld;
		}
		                                             
	}
	
	public long getBetweenWeekends() {
		return this.weekends;
	}
	
	
	public long getBetweenHolidays() {
		return this.laborDays + this.july4ths;
	}
	
	public long getBetweenLaborDays() {
		return this.laborDays;
	}
	
	public long getBetweenJuly4ths() {
		return this.july4ths;
	}
	
	public long getBetweenWeekdays() {
		return this.weekdays;
	}
	
	public long getTotalDays() {
		return totaldays;
	}
	
	
	
}
