package com.chiragreddy.JSONParser;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Format {

	@JsonProperty("action1")
	public List<Action1> actions = new ArrayList<>();
	
}
