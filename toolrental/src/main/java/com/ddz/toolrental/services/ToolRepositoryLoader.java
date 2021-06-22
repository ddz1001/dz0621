package com.ddz.toolrental.services;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ddz.toolrental.core.Tool;
import com.ddz.toolrental.core.ToolType;
import com.ddz.toolrental.repositories.ToolRepository;
import com.ddz.toolrental.repositories.ToolTypeRepository;

/**
 * Service for loading the ToolRepository and ToolTypeRepository
 * The current implementation loads the Tool and ToolType data from xml files, which are validated against xsd schemas
 * @author Dante Zitello
 *
 */
public class ToolRepositoryLoader {
	
	private final ToolRepository toolRepo;
	private final ToolTypeRepository typeRepo;
	
	private static ToolRepositoryLoader loader;
	
	private ToolRepositoryLoader() {
		toolRepo = ToolRepository.getRepository();
		typeRepo = ToolTypeRepository.getRepository();
	}
	
	/**
	 * Get service singleton
	 * @return ToolRepositoryLoader
	 */
	public static ToolRepositoryLoader getRepositoryLoader() {
		if(loader == null) {
			loader = new ToolRepositoryLoader();
		}
		
		return loader;
	}
	
	/**
	 * Load ToolType and Tool repositories with provided xml data.
	 * The ToolTypeRepository is loaded first, then the ToolRepository is loaded.
	 * 
	 * @param toolTypeXsdPath  toolType xsd schema path
	 * @param toolXsdPath  tool xsd schema path
	 * @param toolTypeDirPath  directory holding ToolType xml files
	 * @param toolDirPath  directory holding Tool xml files
	 * @throws IOException if there are IO related problems
	 * @throws RuntimeException if xsd file paths are not files, or tool/tooltype directory paths are not directories
	 */
	public void loadRepositories(String toolTypeXsdPath, String toolXsdPath, String toolTypeDirPath, String toolDirPath) throws IOException {
		
		//Get schema files
		File toolSchema = new File(toolXsdPath);
		File typeSchema = new File(toolTypeXsdPath);
		
		//Get directories
		File toolDir = new File(toolDirPath);
		File typeDir = new File(toolTypeDirPath);
		
		
		//Check that the files point to the correct resource types
		if(!toolSchema.isFile()) {
			throw new RuntimeException("Tool XSD file: " + toolSchema.getAbsolutePath() + " is not a file");
		}
		if(!typeSchema.isFile()) {
			throw new RuntimeException("ToolType XSD file: " + typeSchema.getAbsolutePath() + " is not a file");
		}
		if(!toolDir.isDirectory()) {
			throw new RuntimeException("Tool directory: " + toolDir.getAbsolutePath() + " is not a directory");
		}
		if(!typeDir.isDirectory()) {
			throw new RuntimeException("ToolType directory: " + typeDir.getAbsolutePath() + " is not a directory");
		}
		
		//First, tool types must be parsed and saved, since the tools rely on these being saved first
		
		//Define the filter to use
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		};
		
		//Iterate over type xml files
		ToolType type;
		for( File f : typeDir.listFiles( filter ) ) {
			type = parseType(typeSchema, f);
			typeRepo.save(type);
		}
		
		//Iterate over tool xml files
		Tool tool;
		for( File f : toolDir.listFiles( filter ) ) {
			tool = parseTool(toolSchema, f);
			toolRepo.save(tool);
		}
				
	}
	

	/**
	 * Validate an xml file against a schema
	 * @param xsd
	 * @param xml
	 * @return
	 * @throws IOException
	 */
	private boolean validateFile(File xsd, File xml) throws IOException {
        try {
            SchemaFactory factory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsd);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            
            return true;
            
        } catch (SAXException e) {
        	e.printStackTrace();
            return false;
        }
	}
	
	/**
	 * Parse a ToolType out of an xml file
	 * @param xsd
	 * @param typeXML
	 * @return
	 * @throws IOException if there were problems reading the file
	 * @throws RuntimeException if file did not validate against schema
 	 * @throws RuntimeException if there were parsing errors
	 */
	public ToolType parseType(File xsd, File typeXML) throws IOException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			ToolTypeHandler tth = new ToolTypeHandler();
			
			
			if(!validateFile(xsd, typeXML)) {
				throw new RuntimeException("XML File: " + typeXML.getAbsolutePath() + 
						" did not match against schema: " + xsd.getAbsolutePath());
			}
			
			saxParser.parse(typeXML, tth);
			return tth.getToolType();
			
		} catch (SAXException | ParserConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Parse a Tool out of an xml file
	 * @param xsd
	 * @param toolXML
	 * @return
	 * @throws IOException if there were problems reading the file
	 * @throws RuntimeException if file did not validate against schema
	 * @throws RuntimeException if there were parsing errors
	 */
	public Tool parseTool(File xsd, File toolXML) throws IOException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			ToolHandler tth = new ToolHandler();
			
			
			if(!validateFile(xsd, toolXML)) {
				throw new RuntimeException("XML File: " + toolXML.getAbsolutePath() + 
						" did not match against schema: " + xsd.getAbsolutePath());
			}
			
			saxParser.parse(toolXML, tth);
			return tth.getTool();
			
		} catch (SAXException | ParserConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}
	
	
	/**
	 * SAX handler for Tool xml files
	 * @author Dante Zitello
	 *
	 */
	private class ToolHandler extends DefaultHandler {
	    
		StringBuilder charBuffer;
		Tool tool;
		
		static final String CODE = "Code";
		static final String BRAND = "Brand";
		static final String TYPE = "Type";
		
		public ToolHandler() {
			charBuffer = new StringBuilder(256);
			tool = new Tool();
		}
		
		@Override
	    public void characters(char[] ch, int start, int length) throws SAXException {
	        charBuffer.append(ch, start, length);
	    }

	    @Override
	    public void startDocument() throws SAXException {
	       
	    }

	    @Override
	    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
	    	charBuffer.setLength(0); //clear current buffer
	    }

	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException {
	        if(qName.contentEquals(CODE)) {
	        	tool.setToolCode(charBuffer.toString());
	        }
	        else if(qName.contentEquals(BRAND)) {
	        	tool.setToolBrand(charBuffer.toString());
	        }
	        else if(qName.contentEquals(TYPE)) {
	        	ToolType type = typeRepo.findByTypeName(charBuffer.toString())
	        				.orElseThrow( () -> new RuntimeException("ToolType: " + charBuffer.toString() + 
	        										" not found in repository"));
	        	
	        	tool.setToolType(type);
	        	
	        }
	    }
	    
	    
	    public Tool getTool() {
	    	return this.tool;
	    }
	}
	
	/**
	 * SAX handler for ToolType xml files
	 * @author Dante Zitello
	 *
	 */
	private class ToolTypeHandler extends DefaultHandler {
	    
		StringBuilder charBuffer;
		ToolType type;
		
		static final String TYPE_NAME = "TypeName";
		static final String DAILY_CHARGE = "DailyCharge";
		static final String WEEKDAY_CHARGE = "WeekdayCharge";
		static final String WEEKEND_CHARGE = "WeekendCharge";
		static final String HOLIDAY_CHARGE = "HolidayCharge";
		
		public ToolTypeHandler() {
			charBuffer = new StringBuilder(256);
			type = new ToolType();
		}
		
		@Override
	    public void characters(char[] ch, int start, int length) throws SAXException {
	        charBuffer.append(ch, start, length);
	    }

	    @Override
	    public void startDocument() throws SAXException {
	       
	    }

	    @Override
	    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
	    	charBuffer.setLength(0); //clear current buffer
	    }

	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException {
	        if(qName.contentEquals(TYPE_NAME)) {
	        	type.setName(charBuffer.toString());
	        }
	        else if(qName.contentEquals(DAILY_CHARGE)) {
	        	type.setRentalPrice( new BigDecimal(charBuffer.toString()));
	        }
	        else if(qName.contentEquals(WEEKDAY_CHARGE)) {
	        	type.setWeekdayCharge( Boolean.parseBoolean(charBuffer.toString()) );
	        }
	        else if(qName.contentEquals(WEEKEND_CHARGE)) {
	        	type.setWeekendCharge( Boolean.parseBoolean(charBuffer.toString()) );
	        }
	        else if(qName.contentEquals(HOLIDAY_CHARGE)) {
	        	type.setHolidayCharge( Boolean.parseBoolean(charBuffer.toString()) );
	        }
	    }
	    
	    
	    public ToolType getToolType() {
	    	return this.type;
	    }
	}
	
	
	
}
