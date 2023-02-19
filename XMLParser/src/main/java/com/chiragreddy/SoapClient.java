package com.chiragreddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;

public class SoapClient {

	private static final String SOAP_CONTET_TYPE_HEADER = "text/xml";
	public String soapRequestTemplateFile;
	public String targetUrl;
	public Properties properties;
	
	public SoapClient(final String soapRequestTemplateFile, final String targetUrl) throws IOException {
		this.soapRequestTemplateFile = soapRequestTemplateFile;
		this.targetUrl = targetUrl;
		properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("main/java/properties"));
	}
	
	public String submitRequest(final String soapAction, Object... params ) throws Exception {
		String responsePayload = null;
		Reader responseReader = null;
		PostMethod postAction = null;
		try {
			postAction = new PostMethod(targetUrl);
			postAction.setRequestEntity(new ByteArrayRequestEntity(makeRequest(params), SOAP_CONTET_TYPE_HEADER));
			postAction.setRequestHeader("Content-Type", "text/xml;charset=UTF-8");
			HttpClient httpClient = new HttpClient();
			int resultCode = httpClient.executeMethod(postAction);
			responseReader = new InputStreamReader(postAction.getResponseBodyAsStream(), "UTF8");
			final StringBuffer strBuffer = new StringBuffer();
			char[] readCharBuffer = new char[1024];
			int numCharsRead = -1;
			while( (numCharsRead = responseReader.read(readCharBuffer)) != -1) {
				strBuffer.append(readCharBuffer, 0, numCharsRead);
			}
			if(resultCode!=200) {
				throw new Exception("Unexpected status code");
			}
			responsePayload = strBuffer.toString();
		}
		finally {
			if(postAction!=null)
				postAction.releaseConnection();
			if(responseReader!=null)
				responseReader.close();
		}
		
		return responsePayload;
		
	}
	
	// Create SOAP Request from the string template file
	
	private byte[] makeRequest(final Object... params) throws IOException {
		byte[] retVal = null;
		retVal = MessageFormat.format(this.loadRequestTemplate(soapRequestTemplateFile), params).getBytes();
		return retVal;
	}
	
	// Load the requestTemplate string with xml template file
	
	private String loadRequestTemplate(final String soapRequestTemplateFile) throws IOException {
		String requestTemplate = null;
		
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(soapRequestTemplateFile)))){
			final StringBuffer buffer = new StringBuffer();
			String aLine = null;
			while((aLine = bufferedReader.readLine())!=null) {
				buffer.append(aLine);
			}
			requestTemplate = buffer.toString();
		}
		
		return requestTemplate;
	}
	
}
