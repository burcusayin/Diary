package com.google.gwt.diary.client;

import com.google.gwt.diary.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.RichTextArea;

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
		final Button saveButton = new Button("Save");
		final Button submitButton = new Button("Submit");
		final Label errorLabel = new Label();
		
		final RichTextArea area = new RichTextArea();
		final RichTextToolbar toolbar = new RichTextToolbar(area);
		

		final TextBox username = new TextBox();
		username.setText("Username");
		final TextBox password = new TextBox();
		password.setText("Password");
		
	
		
		// We can add style names to widgets
		saveButton.addStyleName("saveButton");
		submitButton.addStyleName("submitButton");
		
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("textArea").add(area);
		RootPanel.get("textAreaToolbar").add(toolbar);
		RootPanel.get("saveButtonContainer").add(saveButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

	
		RootPanel.get("usernameContainer").add(username);
		RootPanel.get("passwordContainer").add(password);
		RootPanel.get("submitButtonContainer").add(submitButton);		
		
	
		
		// Focus the cursor on the name field when the app loads
		area.setFocus(true);

		
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending diary to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		
	    area.setVisible(false);
		toolbar.setVisible(false);
	    saveButton.setVisible(false);
		RootPanel.get("facebook").setVisible(false);
		RootPanel.get("twitter").setVisible(false);	    
		RootPanel.get("mail").setVisible(false);		    
			

		
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				username.setVisible(false);
				password.setVisible(false);
				submitButton.setVisible(false);
			    area.setVisible(true);
			    toolbar.setVisible(true);
			    saveButton.setVisible(true);
				RootPanel.get("facebook").setVisible(true);
				RootPanel.get("twitter").setVisible(true);	
				RootPanel.get("mail").setVisible(true);					
			}
		});
		
		
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				saveButton.setEnabled(true);
				saveButton.setFocus(true);
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
				errorLabel.setText("");
				String textToServer = area.getText();
				
				if(isItEmpty(area,errorLabel))
				{
				// Then, we send the input to the server.
				saveButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
				}
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		saveButton.addClickHandler(handler);
		area.addKeyUpHandler(handler);
	}
	
	public boolean isItEmpty(RichTextArea text, Label label)
	{
		String textContent = text.getText();
		if (!FieldVerifier.isValidName(textContent)) {
			label.setText("Please write something for your diary!");
			return false;
		}
		else
		{
			return true;
		}
	}
	
}