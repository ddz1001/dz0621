package com.ddz.toolrental.core;

/**
 * 
 * A tool which can be rented by a customer.
 * Holds information about the tool's code, brand, and type.
 *
 * Information about pricing and charge rules are held by ToolType.
 * 
 * @author Dante Zitello
 * @see ToolType
 */
public class Tool {
	
	private ToolType toolType;
	private String toolBrand;
	private String toolCode;
	
	
	/**
	 * Default constructor
	 */
	public Tool() {
		
	}
	
	/**
	 * Basic constructor
	 * @param type  tool type
	 * @param brand  brand string
	 * @param code  code string
	 */
	public Tool(ToolType type, String brand, String code) {
		this.toolType = type;
		this.toolBrand = brand;
		this.toolCode = code;
	}
	
	/**
	 * Get the type of this tool
	 * @return tool type
	 */
	public ToolType getToolType() {
		return toolType;
	}
	
	/**
	 * Set this tool's type
	 * @param toolType  the type to set
	 */
	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}
	
	/**
	 * Get the brand of this tool
	 * @return tool brand
	 */
	public String getToolBrand() {
		return toolBrand;
	}
	
	/**
	 * Set this tool's brand
	 * @param toolBrand  brand string
	 */
	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}
	
	/**
	 * Get this tool's unique code
	 * @return tool code
	 */
	public String getToolCode() {
		return toolCode;
	}
	
	/**
	 * Set the tool's unique code
	 * @param toolCode 
	 */
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}
	
	
	
}
