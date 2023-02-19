package com.chiragreddy;

import java.io.IOException;

public class SendRequest {

	String name = "";
	String description = "";
	String username = "";
	String password = "";
	String token = "";
	
	public void main() throws Exception {
		//invoking execute operation
		SoapClient client = new SoapClient("main/resources/request.xml", "http://test-url.com");
		// passing parameters to the request
		String response = client.submitRequest("execute", name, description, username, password, token);  // assuming execute is operation name/soap Action
	}
	
}
