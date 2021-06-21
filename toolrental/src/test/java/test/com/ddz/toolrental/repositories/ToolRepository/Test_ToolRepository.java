package test.com.ddz.toolrental.repositories.ToolRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;
import com.ddz.toolrental.repositories.ToolRepository;

class Test_ToolRepository {

	
	static ToolRepository repo;
	static List<Tool> allCompare;
	
	@BeforeAll
	static void InitializeRepository() {
		
		
		ToolType ladder = new ToolType();
		ladder.setName("Ladder");
		ladder.setRentalPrice(new BigDecimal("1.99"));
		ladder.setWeekdayCharge(true);
		ladder.setWeekendCharge(true);
		ladder.setHolidayCharge(false);
		
		ToolType chainsaw = new ToolType();	
		chainsaw.setName("Chainsaw");
		chainsaw.setRentalPrice(new BigDecimal("1.49"));
		chainsaw.setWeekdayCharge(true);
		chainsaw.setWeekendCharge(false);
		chainsaw.setHolidayCharge(true);
		
		ToolType jackhammer = new ToolType();	
		jackhammer.setName("Jackhammer");
		jackhammer.setRentalPrice(new BigDecimal("2.99"));
		jackhammer.setWeekdayCharge(true);
		jackhammer.setWeekendCharge(false);
		jackhammer.setHolidayCharge(false);
		
		
		Tool ladw = new Tool();
		ladw.setToolType(ladder);
		ladw.setToolBrand("Werner");
		ladw.setToolCode("LADW");
		
		Tool chns = new Tool();
		chns.setToolType(chainsaw);
		chns.setToolBrand("Stihl");
		chns.setToolCode("CHNS");
		
		Tool jakr = new Tool();
		jakr.setToolType(jackhammer);
		jakr.setToolBrand("Ridgid");
		jakr.setToolCode("JAKR");
		
		Tool jakd = new Tool();
		jakd.setToolType(jackhammer);
		jakd.setToolBrand("DeWalt");
		jakd.setToolCode("JAKD");
		
		
		
		ToolRepository tr = ToolRepository.getRepository();
		
		tr.save(ladw);
		tr.save(chns);
		tr.save(jakr);
		tr.save(jakd);
		
		
		repo = tr;
		
		allCompare = new ArrayList<Tool>();
		
		allCompare.add(ladw);
		allCompare.add(chns);
		allCompare.add(jakr);
		allCompare.add(jakd);
	}
	
	
	@Test
	void Test_FindOne() {
		
		Tool t = repo.findByToolCode("LADW").get();
		
		assertEquals("LADW", t.getToolCode());
		assertEquals("Werner", t.getToolBrand());
	}
	
	@Test
	void Test_FindAll() {
		List<Tool> all = repo.findAll();
		
		assertEquals(4, all.size());
		assertTrue(all.containsAll(allCompare));
	}
	
	@Test
	void Test_FindNotFound() {
		Optional<Tool> ot = repo.findByToolCode("FOO");
		
		assertFalse(ot.isPresent());
	}

}
