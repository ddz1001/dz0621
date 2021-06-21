package test.com.ddz.toolrental.repositories.ToolTypeRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ddz.toolrental.core.ToolType;
import com.ddz.toolrental.repositories.ToolTypeRepository;

class Test_ToolTypeRepository {
	
	static ToolTypeRepository repo;
	static List<ToolType> allCompare;
	
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
	
		
		ToolTypeRepository tr = ToolTypeRepository.getRepository();
		
		tr.save(jackhammer);
		tr.save(chainsaw);
		tr.save(ladder);
		
		
		repo = tr;
		
		allCompare = new ArrayList<ToolType>();
		
		allCompare.add(ladder);
		allCompare.add(chainsaw);
		allCompare.add(jackhammer);
	}
	
	
	@Test
	void Test_FindOne() {
		
		ToolType t = repo.findByTypeName("Ladder").get();
		assertEquals("Ladder", t.getName());
	}
	
	@Test
	void Test_FindAll() {
		List<ToolType> all = repo.findAll();
		
		assertEquals(3, all.size());
		assertTrue(all.containsAll(allCompare));
	}
	
	@Test
	void Test_FindNotFound() {
		Optional<ToolType> ot = repo.findByTypeName("FOO");
		
		assertFalse(ot.isPresent());
	}


}
