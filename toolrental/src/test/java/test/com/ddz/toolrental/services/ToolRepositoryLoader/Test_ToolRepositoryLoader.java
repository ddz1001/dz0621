package test.com.ddz.toolrental.services.ToolRepositoryLoader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

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
		
		
		System.out.println( toolRepo.findAll().toString() );
		
		
	}

}
