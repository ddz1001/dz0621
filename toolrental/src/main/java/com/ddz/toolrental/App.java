package com.ddz.toolrental;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ddz.toolrental.core.RentalAgreement;
import com.ddz.toolrental.services.CheckoutService;
import com.ddz.toolrental.services.ToolRepositoryLoader;

/**
 *  Basic driver class for demonstration
 * @author Dante Zitello
 *
 */
public class App 
{	
    public static void main( String[] args )
    {
    	try {
	    	ToolRepositoryLoader loaderService = ToolRepositoryLoader.getRepositoryLoader();
			loaderService.loadRepositories("resources/xsd/tooltype.xsd",
					   "resources/xsd/tool.xsd",
					   "resources/tooltypes",
					   "resources/tools");
			
			CheckoutService svc = CheckoutService.getCheckoutService();
	    	
	    	
	    	
	    	RentalAgreement lag = svc.checkoutTool(
					"JAKR", 50, LocalDate.parse("2020-07-02"), 4);
			
			
			System.out.println(lag.buildAgreementString());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
