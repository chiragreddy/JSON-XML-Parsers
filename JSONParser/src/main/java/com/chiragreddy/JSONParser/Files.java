package com.chiragreddy.JSONParser;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Files {

	@JsonProperty("fileName")
	public String fileName;
	
	@JsonProperty("menuName")
	public String menuName;
	
	@JsonProperty("formats")
	public List<Format> formats = new ArrayList<>();
	
}
