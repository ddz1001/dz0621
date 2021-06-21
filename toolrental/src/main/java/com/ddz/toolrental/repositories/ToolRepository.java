package com.ddz.toolrental.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ddz.toolrental.core.Tool;

public class ToolRepository {

	private static ToolRepository repository;
	
	private Map<String, Tool> map;
	
	
	ToolRepository() {
		map = new HashMap<String, Tool>();
	}
	
	
	public Optional<Tool> findByToolCode(String toolCode) {
		
		if(map.containsKey(toolCode)) {
			return Optional.of(map.get(toolCode));
		}
		else {
			return Optional.empty();
		}
	}
	
	public List<Tool> findAll() {
		return new ArrayList<Tool>(map.values());
	}
	
	public void save(Tool tool) {
		
		map.put(tool.getToolCode(), tool);
		
	}
	
	
	
	
	public static ToolRepository getRepository() {
		if(repository == null) {
			repository = new ToolRepository();
		}
		
		return repository;
	}
	
	
	
	
}
