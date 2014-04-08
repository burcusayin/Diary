package com.google.gwt.diary.server;
 
import com.google.gwt.diary.client.GreetingService;
import com.google.gwt.diary.server.model.DiaryProperties;
import com.google.gwt.diary.server.model.Person;
import com.google.gwt.diary.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	public String takeDiary(String input) throws IllegalArgumentException {
		String result = null;
		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);

		boolean res = isItValid(input);
		if(res)
		{
			DiaryProperties diary = new DiaryProperties();
			diary.setContent(input);
			result = "OK";
		}
		else
		{
			result = "FAIL";
		}
		
		return result;
	}
	
	public String takeLogin(String input1, String input2) throws IllegalArgumentException {
		
		String result = null;
		// Escape data from the client to avoid cross-site script vulnerabilities.
		input1 = escapeHtml(input1);
		input2 = escapeHtml(input2);
		
		boolean res1 = isItValid(input1);
		boolean res2 = isItValid(input2);
		
		if( res1 && res2)
		{
			Person person = new Person();
			person.setUserName(input1);
			person.setPassword(input2);
			result = "OK";
		}
		else
		{
			result = "FAIL";
		}
		
		return result;
	}
	

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	public boolean isItValid(String text)
	{
		if (!FieldVerifier.isValidName(text)) 
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
