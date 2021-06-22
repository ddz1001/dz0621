package test.com.ddz.toolrental.services.ToolRepositoryLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;



import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;
import com.ddz.toolrental.repositories.ToolRepository;
import com.ddz.toolrental.repositories.ToolTypeRepository;
import com.ddz.toolrental.services.ToolRepositoryLoader;

class Test_ToolRepositoryLoader {

	@Test
	void Test_LoadTypesAndTools() throws IOException {
		
		ToolRepositoryLoader loaderService = ToolRepositoryLoader.getRepositoryLoader();
		ToolRepository toolRepo = ToolRepository.getRepository();
		ToolTypeRepository typeRepo = ToolTypeRepository.getRepository();
		
		
		loaderService.loadRepositories("resources/xsd/tooltype.xsd",
									   "resources/xsd/tool.xsd",
									   "resources/tooltypes",
									   "resources/tools");
		
		//Check that the correct objects were loaded
		
		ToolType ladder = typeRepo.findByTypeName("Ladder").get();
		ToolType chainsaw = typeRepo.findByTypeName("Chainsaw").get();
		ToolType jackhammer = typeRepo.findByTypeName("Jackhammer").get();
		
		Tool ladw = toolRepo.findByToolCode("LADW").get();
		Tool chns = toolRepo.findByToolCode("CHNS").get();
		Tool jakr = toolRepo.findByToolCode("JAKR").get();
		Tool jakd = toolRepo.findByToolCode("JAKD").get();
		
		
		//Check tool types
		assertEquals(0, ladder.getRentalPrice().compareTo(new BigDecimal("1.99")));
		assertTrue(ladder.isWeekdayCharge());
		assertTrue(ladder.isWeekendCharge());
		assertFalse(ladder.isHolidayCharge());
		
		assertEquals(0, chainsaw.getRentalPrice().compareTo(new BigDecimal("1.49")));
		assertTrue(chainsaw.isWeekdayCharge());
		assertFalse(chainsaw.isWeekendCharge());
		assertTrue(chainsaw.isHolidayCharge());
		
		assertEquals(0, jackhammer.getRentalPrice().compareTo(new BigDecimal("2.99")));
		assertTrue(jackhammer.isWeekdayCharge());
		assertFalse(jackhammer.isWeekendCharge());
		assertFalse(jackhammer.isHolidayCharge());
		
		
		
		//Check that tools have correct types
		assertSame(ladder, ladw.getToolType());
		assertSame(chainsaw, chns.getToolType());
		assertSame(jackhammer, jakr.getToolType());
		assertSame(jackhammer, jakd.getToolType());
		
		
		//Check tool attributes
		assertEquals("Werner", ladw.getToolBrand());
		assertEquals("Stihl", chns.getToolBrand());
		assertEquals("Ridgid", jakr.getToolBrand());
		assertEquals("DeWalt", jakd.getToolBrand());
		
		assertEquals("LADW", ladw.getToolCode());
		assertEquals("CHNS", chns.getToolCode());
		assertEquals("JAKR", jakr.getToolCode());
		assertEquals("JAKD", jakd.getToolCode());
		
	}

}
