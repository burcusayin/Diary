package com.google.gwt.diary.client; 

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.diary.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Diary implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
	    final DiaryUI ui = new DiaryUI();
	    ui.createUI();
	    
		ui.getSubmitButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ui.getDialogBox().hide();
			    ui.getSubmitButton().setEnabled(true);
			    ui.getSubmitButton().setFocus(true);
					
			}
		});
		
		class MyHandlerLogin implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */

			public void onClick(ClickEvent event) {
				sendLoginInfosToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendLoginInfosToServer();
				}
			}
			
			private void sendLoginInfosToServer()
			{
				String nameToServer = ui.getUsername().getText();
				String passwordToServer = ui.getPassword().getText();
				
				ui.getErrorLabel().setText("");
				ui.getUsername().setText("");
				ui.getPassword().setText("");
				ui.getSubmitButton().setEnabled(false);
				ui.getTextToServerLabel().setText(nameToServer + passwordToServer);
				ui.getServerResponseLabel().setText("");
				greetingService.takeLogin(nameToServer,passwordToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
							}

							public void onSuccess(String result) {
								Window.alert("Result is: " + result);
								System.out.println("Result is: " + result);
								String res = "OK";
								if(result.equalsIgnoreCase(res))
								{
									ui.getDialogBox().setText("Remote Procedure Call");
									ui.getServerResponseLabel()
											.removeStyleName("serverResponseLabelError");
									ui.getServerResponseLabel().setHTML(result);
									ui.getDialogBox().center();
									ui.getCloseButton().setFocus(true);
									
									ui.getUsername().setVisible(false);
									ui.getPassword().setVisible(false);
									ui.getSubmitButton().setVisible(false);
								    ui.getArea().setVisible(true);
								    ui.getToolbar().setVisible(true);
								    ui.getSaveButton().setVisible(true);
								    
								    ui.createPlugins();
								}
								else
								{
									Window.alert("You should fill all areas!");
									ui.getSubmitButton().setEnabled(true);
									ui.getSubmitButton().setFocus(true);
								}
							}
							});
				ui.getErrorLabel().setText("");
			}
		}
		
		ui.getArea().setFocus(true);
		
		// Add a handler to close the DialogBox
		ui.getCloseButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ui.getDialogBox().hide();
				ui.getSaveButton().setEnabled(true);
				ui.getSaveButton().setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */

			public void onClick(ClickEvent event) {
				sendDiaryToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendDiaryToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendDiaryToServer() {
				// First, we validate the input.
				ui.getErrorLabel().setText("");
				String textToServer = ui.getArea().getText();

				// Then, we send the input to the server.
				ui.getSaveButton().setEnabled(false);
				ui.getTextToServerLabel().setText(textToServer);
				ui.getServerResponseLabel().setText("");
				greetingService.takeDiary(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
							}

							public void onSuccess(String result) {
								String res = "OK";
								Window.alert("Result is: " + result);
								if(result.equalsIgnoreCase(res))
								{
									ui.getDialogBox().setText("Remote Procedure Call");
									ui.getServerResponseLabel()
									.removeStyleName("serverResponseLabelError");
									ui.getServerResponseLabel().setHTML(result);
									ui.getDialogBox().center();
									ui.getCloseButton().setFocus(true);
								}
								else
								{
									Window.alert("You should write something for your diary!");
									ui.getSaveButton().setEnabled(true);
									ui.getSaveButton().setFocus(true);
								}
							}
						});
				}
			}	
			
			
			// Add a handler to send the name to the server
			MyHandler handler = new MyHandler();
			ui.getSaveButton().addClickHandler(handler);
			ui.getArea().addKeyUpHandler(handler);
			
			MyHandlerLogin login = new MyHandlerLogin();
			ui.getSubmitButton().addClickHandler(login);
			ui.getUsername().addKeyUpHandler(login);
			ui.getPassword().addKeyUpHandler(login);
	}
}
