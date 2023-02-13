package com.chiragreddy.JSONParser;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;

public class JSONParser {

	private static final Logger log = LoggerFactory.getLogger(JSONParser.class);
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("src\\main\\resources\\request.json");
		App app = mapper.readValue(file.getAbsoluteFile(), App.class);
		
		for(Files files: app.files) {
			
			log.info(files.fileName);
			log.info(files.menuName);
			
			for(Format format: files.formats) {
				
				for(Action1 action: format.actions) {
					
					log.info(action.formatName);
					log.info(action.description);
					
				}
				
			}
			
		}
		
	}

}