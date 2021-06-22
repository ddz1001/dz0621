package com.ddz.toolrental.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ddz.toolrental.core.Tool;

/**
 * Repository for ToolTypes. 
 * @author Dante Zitello
 *
 */
public class ToolRepository {

	private static ToolRepository repository;
	
	private Map<String, Tool> map;
	
	
	private ToolRepository() {
		map = new HashMap<String, Tool>();
	}
	
	/**
	 * Find a Tool by its toolCode 
	 * @param toolCode  tool's tool code
	 * @return Optional with found tool. If not found, then Optional.empty() is returned
	 */
	public Optional<Tool> findByToolCode(String toolCode) {
		
		if(map.containsKey(toolCode)) {
			return Optional.of(map.get(toolCode));
		}
		else {
			return Optional.empty();
		}
	}
	
	/**
	 * Find all Tools held in this repository 
	 * @return List of all found tools, or an empty list if none were found
	 */
	public List<Tool> findAll() {
		return new ArrayList<Tool>(map.values());
	}
	
	/**
	 * Save a Tool to this repository
	 * @param tool Tool to save
	 */
	public void save(Tool tool) {
		
		map.put(tool.getToolCode(), tool);
		
	}
	
	/**
	 * Get the repository singleton
	 * @return ToolRepository
	 */
	public static ToolRepository getRepository() {
		if(repository == null) {
			repository = new ToolRepository();
		}
		
		return repository;
	}
	
	
	
	
}
