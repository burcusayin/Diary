package com.google.gwt.diary.client.ui;

import com.google.gwt.diary.client.RichTextToolbar;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DiaryUI {
	
	Button saveButton;
	Button submitButton;
	Label errorLabel;
	RichTextArea area;
	RichTextToolbar toolbar;
	TextBox username;
	TextBox password;
	DialogBox dialogBox;
	Button closeButton;
	Label textToServerLabel;
	HTML serverResponseLabel;
	VerticalPanel dialogVPanel;
	HTMLPanel panel;
	
	
	public DiaryUI() {
		super();
		this.saveButton = new Button("Save");
		this.submitButton = new Button("Submit");
		this.errorLabel = new Label();
		this.area = new RichTextArea();
		this.toolbar = new RichTextToolbar(area);
		this.username = new TextBox();
		this.password =  new TextBox();
		this.dialogBox = new DialogBox();
		this.closeButton = new Button("Close");
		this.textToServerLabel =  new Label();
		this.serverResponseLabel = new HTML();
		this.dialogVPanel = new VerticalPanel();
	}

	public void createUI() {
		
		this.username.setText("Username");
		this.password.setText("Password");
				
		// We can add style names to widgets
		this.saveButton.addStyleName("saveButton");
		this.submitButton.addStyleName("submitButton");
		
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
		this.username.setFocus(true);
		this.password.setFocus(true);
		this.submitButton.setEnabled(true);
		this.submitButton.setFocus(true);
		
		this.dialogBox.setText("Remote Procedure Call");
		this.dialogBox.setAnimationEnabled(true);
	
		// We can set the id of a widget by accessing its Element
		this.closeButton.getElement().setId("closeButton");
		
		this.dialogVPanel.addStyleName("dialogVPanel");
		this.dialogVPanel.add(new HTML("<b>Sending to the server:</b>"));
		this.dialogVPanel.add(textToServerLabel);
		this.dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		this.dialogVPanel.add(serverResponseLabel);
		this.dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		this.dialogVPanel.add(closeButton);
		this.dialogBox.setWidget(dialogVPanel);

		
		this.area.setVisible(false);
		this.toolbar.setVisible(false);
		this. saveButton.setVisible(false);
		
	}
	
	public void createPlugins()
	{
		 String html =	"<a id='facebook' href='http://www.facebook.com/sharer.php?u=http://127.0.0.1:8888/Diary.html?gwt.codesvr=127.0.0.1:9997' target='_blank'  >"
	    		    +  "<img src='http://www.simplesharebuttons.com/images/somacro/facebook.png' alt='Facebook' /></a>"
	    
	                +  "<a id='twitter' href='http://twitter.com/share?text=&url=' target='_blank'  >"
	    		    +  "<img src='http://www.simplesharebuttons.com/images/somacro/twitter.png' alt='Twitter' /></a>"
	    		
	    		
	    
	                +  "<a id='mail' href='mailto:?Body='>"
	    		    +  "<img src='http://www.simplesharebuttons.com/images/somacro/email.png' alt='Email' /></a>";
	    
	    
	    this.panel = new HTMLPanel(html);								    
	    this.panel.setSize("200px", "120px");
	    RootPanel.get("plugin").add(panel);
	}

	public Button getSaveButton() {
		return this.saveButton;
	}
	
	public HTMLPanel getHtmlPanel() {
		return this.panel;
	}

	public Button getSubmitButton() {
		return this.submitButton;
	}

	public Label getErrorLabel() {
		return this.errorLabel;
	}

	public RichTextArea getArea() {
		return this.area;
	}

	public RichTextToolbar getToolbar() {
		return this.toolbar;
	}

	public TextBox getUsername() {
		return this.username;
	}

	public TextBox getPassword() {
		return this.password;
	}

	public DialogBox getDialogBox() {
		return this.dialogBox;
	}

	public Button getCloseButton() {
		return this.closeButton;
	}

	public Label getTextToServerLabel() {
		return this.textToServerLabel;
	}

	public HTML getServerResponseLabel() {
		return this.serverResponseLabel;
	}

	public VerticalPanel getDialogVPanel() {
		return this.dialogVPanel;
	}

}
