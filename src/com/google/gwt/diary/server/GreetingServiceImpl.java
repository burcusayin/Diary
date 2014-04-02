package com.google.gwt.diary.server;

import com.google.gwt.diary.client.GreetingService;
import com.google.gwt.diary.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.google.gwt.diary.server.model.*;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"You should write something!");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello! " + "!<br><br>I am running " + serverInfo 
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
	
	public String takeDiary(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"You should write something for your diary!");
		}


		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);

		DiaryProperties diary = new DiaryProperties(input);
		
		return diary.getContent();
	}
	
	public String takeLogin(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"You should write something for your username and password!");
		}


		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		
		String[] ar = null;
		ar = input.split(" ");
		
		Person person = new Person();
		person.setUserName(ar[0]);
		person.setPassword(ar[1]);
		
		return person.getName() + " " + person.getPassword();
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
}
