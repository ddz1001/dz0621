package com.ddz.toolrental.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;

/**
 * Represents a ToolType repository
 * @author Dante Zitello
 *
 */
public class ToolTypeRepository {
	private static ToolTypeRepository repository;
	
	private Map<String, ToolType> map;
	
	
	private ToolTypeRepository() {
		map = new HashMap<String, ToolType>();
	}
	
	
	/**
	 * Find a ToolType by its name 
	 * @param name  tooltypes name
	 * @return Optional with found tooltype. If not found, then Optional.empty() is returned
	 */
	public Optional<ToolType> findByTypeName(String name) {
		
		if(map.containsKey(name)) {
			return Optional.of(map.get(name));
		}
		else {
			return Optional.empty();
		}
	}
	
	/**
	 * Find all ToolTypes held in this repository 
	 * @return List of all found types, or an empty list if none were found
	 */
	public List<ToolType> findAll() {
		return new ArrayList<ToolType>(map.values());
	}
	
	/**
	 * Save a ToolType to this repository
	 * @param toolType ToolType to save
	 */
	public void save(ToolType toolType) {
		
		map.put(toolType.getName(), toolType);
		
	}
	
	/**
	 * Get the repository singleton
	 * @return ToolTypeRepository
	 */
	public static ToolTypeRepository getRepository() {
		if(repository == null) {
			repository = new ToolTypeRepository();
		}
		
		return repository;
	}
}
