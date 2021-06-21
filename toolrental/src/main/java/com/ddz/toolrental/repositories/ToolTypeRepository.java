package com.ddz.toolrental.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;

public class ToolTypeRepository {
	private static ToolTypeRepository repository;
	
	private Map<String, ToolType> map;
	
	
	ToolTypeRepository() {
		map = new HashMap<String, ToolType>();
	}
	
	
	public Optional<ToolType> findByTypeName(String name) {
		
		if(map.containsKey(name)) {
			return Optional.of(map.get(name));
		}
		else {
			return Optional.empty();
		}
	}
	
	public List<ToolType> findAll() {
		return new ArrayList<ToolType>(map.values());
	}
	
	public void save(ToolType toolType) {
		
		map.put(toolType.getName(), toolType);
		
	}
	
	
	
	
	public static ToolTypeRepository getRepository() {
		if(repository == null) {
			repository = new ToolTypeRepository();
		}
		
		return repository;
	}
}
