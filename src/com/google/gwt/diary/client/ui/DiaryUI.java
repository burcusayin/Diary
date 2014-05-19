package com.google.gwt.diary.client.ui;

import com.google.gwt.diary.client.RichTextToolbar;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DiaryUI {
	
	Button saveButton;
	Button viewButton;
	Button submitButton;
	Button addNewMember;
	Button registerButton;
	Button backButton;
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
	FlexTable flexTable;
	
	TextBox name;
	TextBox surname;
	TextBox phone;
	TextBox address;
	TextBox email;
	TextBox usernameNew;
	TextBox passwordNew;
	FormPanel form;
	
	public DiaryUI() {
		super();
		this.saveButton = new Button("Save");
		this.viewButton = new Button("View my diaries!");
		this.submitButton = new Button("Submit");
		this.addNewMember = new Button("I'm a new member!");
		this.backButton = new Button("Return previous page!");
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
		this.name = new TextBox();
		this.surname = new TextBox();
		this.phone = new TextBox();
		this.email = new TextBox();
		this.address = new TextBox();
		this.usernameNew = new TextBox();
		this.passwordNew = new TextBox();
		this.registerButton = new Button("Register");
		this.form = new FormPanel();
		this.flexTable = new FlexTable();
	}

	public void createUI() {
		
		this.username.setText("Username");
		this.password.setText("Password");
				
		// We can add style names to widgets
		this.saveButton.addStyleName("saveButton");
		this.viewButton.addStyleName("viewButton");
		this.submitButton.addStyleName("submitButton");
		this.addNewMember.addStyleName("registerButton");
		this.backButton.addStyleName("backButton");
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element;
		RootPanel.get("textArea").add(area);
		RootPanel.get("textArea").setWidth("500");
		RootPanel.get("textAreaToolbar").add(toolbar);
		RootPanel.get("saveButtonContainer").add(saveButton);
		RootPanel.get("viewButtonContainer").add(viewButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		RootPanel.get("usernameContainer").add(username);
		RootPanel.get("passwordContainer").add(password);
		RootPanel.get("submitButtonContainer").add(submitButton);	
		RootPanel.get("registerButtonContainer").add(addNewMember);

		// Focus the cursor on the name field when the app loads
		this.username.setFocus(true);
		this.password.setFocus(true);
		this.submitButton.setEnabled(true);
		this.submitButton.setFocus(true);
		this.addNewMember.setFocus(true);
		
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
		this.saveButton.setVisible(false);
		this.viewButton.setVisible(false);
		
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

	public void createForm()
	{
	    form.setAction("/myFormHandler");
	 
	    // Create a panel to hold all of the form widgets.
	    VerticalPanel panel = new VerticalPanel();
	    form.setWidget(panel);

	    name.setName("textBox1");
	    name.setText("Name");
	    panel.add(name);
	    
	    surname.setName("textBox2");
	    surname.setText("Surname");
	    panel.add(surname);

	    phone.setName("textBox3");
	    phone.setText("Phone number");
	    panel.add(phone);

	    address.setName("textBox4");
	    address.setText("Address");
	    panel.add(address);

	    email.setName("textBox5");
	    email.setText("Email");
	    panel.add(email);

	    usernameNew.setName("textBox6");
	    usernameNew.setText("usernameNew");
	    panel.add(usernameNew);

	    passwordNew.setName("textBox7");
	    passwordNew.setText("passwordNew");
	    panel.add(passwordNew);

		this.registerButton.addStyleName("registerButton");
		form.setWidth("200");
		form.setHeight("250");
	
	    RootPanel.get().add(form);
	    RootPanel.get().setWidgetPosition(form, 600, 130);
	    RootPanel.get().add(registerButton);
	    RootPanel.get().setWidgetPosition(registerButton, 650, 380);
	}
	
	public void returnback()
	{
		this.form.clear();
		this.form.setVisible(false);
		this.username.setEnabled(true);
		this.username.setFocus(true);
		this.username.setVisible(true);
		this.password.setEnabled(true);
		this.password.setFocus(true);
		this.password.setVisible(true);
		this.submitButton.setEnabled(true);
		this.submitButton.setFocus(true);
		this.submitButton.setVisible(true);
		this.addNewMember.setEnabled(true);
		this.addNewMember.setFocus(true);
		this.addNewMember.setVisible(true);
		this.registerButton.setEnabled(false);
		this.registerButton.setFocus(false);
		this.registerButton.setVisible(false);
	}
	
	public void createDiaryTable()
	{
		
		flexTable.setBorderWidth(5);
		flexTable.setText(0, 0, "DIARY_ID");
		flexTable.setText(0, 1, "TITLE");
		flexTable.setText(0, 2, "DIARY_DATE");
		flexTable.setText(0, 3, "DIARY_TIME");
		flexTable.setText(0, 4, "USERNAME");
		RootPanel.get().add(backButton);
		RootPanel.get().add(flexTable);
		RootPanel.get().setWidgetPosition(backButton, 610, 550);
		RootPanel.get().setWidgetPosition(flexTable, 500, 250);
		backButton.setVisible(true);
		flexTable.setVisible(true);
	}
	
	public Button getSaveButton() {
		return this.saveButton;
	}
	
	public Button getBackButton() {
		return this.backButton;
	}
	
	public FlexTable getFlexTable() {
		return flexTable;
	}

	public Button getViewButton() {
		return this.viewButton;
	}
	
	public HTMLPanel getHtmlPanel() {
		return this.panel;
	}

	public Button getAddNewMember() {
		return addNewMember;
	}

	public Button getRegisterButton() {
		return registerButton;
	}

	public TextBox getName() {
		return name;
	}

	public TextBox getSurname() {
		return surname;
	}


	public TextBox getPhone() {
		return phone;
	}

	public TextBox getAddress() {
		return address;
	}


	public TextBox getEmail() {
		return email;
	}

	public TextBox getUsernameNew() {
		return usernameNew;
	}

	public TextBox getPasswordNew() {
		return passwordNew;
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
