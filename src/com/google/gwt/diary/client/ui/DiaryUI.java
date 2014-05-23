package com.google.gwt.diary.client.ui;

import com.google.gwt.diary.client.RichTextToolbar;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
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
	Button closeButton;
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
	
	TextBox title;
	TextBox holderName;
	TextBox holderSurname;
	TextBox holderEmail;
	TextBox holderPhone;
	Button closeButtonForHolder;
	Button addButton;
	Label holderNameLabel;
	Label holderSurnameLabel;
	Label holderEmailLabel;
	Label holderPhoneLabel;
	ListBox propertyrightListBox;
	HTML uppertextForVPanel;
	VerticalPanel vPanel;
	Button logoutButton;
	Button editButton;
	Button deleteButton;
	
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
		this.closeButton = new Button("Close");
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
		this.title = new TextBox();
		this.holderName = new TextBox();
		this.holderSurname = new TextBox();
		this.holderEmail = new TextBox();
		this.holderPhone = new TextBox();
		this.closeButtonForHolder = new Button("No Thanks!");
		this.addButton = new Button("Add");
		this.holderNameLabel = new Label();
		this.holderSurnameLabel = new Label();
		this.holderEmailLabel = new Label();
		this.holderPhoneLabel = new Label();
		this.propertyrightListBox = new ListBox();
		this.uppertextForVPanel = new HTML();
		this.vPanel = new VerticalPanel();
		this.logoutButton = new Button("Logout");
		this.editButton = new Button("Edit");
		this.deleteButton = new Button("Delete");
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
		this.logoutButton.addStyleName("logoutButton");
		this.editButton.addStyleName("editButton");
		this.deleteButton.addStyleName("deleteButton");
		
		
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
		RootPanel.get("logoutContainer").add(logoutButton);
		RootPanel.get().add(editButton);
		RootPanel.get().add(deleteButton);
		
		
		// Focus the cursor on the name field when the app loads
		this.username.setFocus(true);
		this.password.setFocus(true);
		this.submitButton.setEnabled(true);
		this.submitButton.setFocus(true);
		this.addNewMember.setFocus(true);
		this.logoutButton.setFocus(true);
	
		// We can set the id of a widget by accessing its Element
		this.closeButton.getElement().setId("closeButton");
				
		this.area.setVisible(false);
		this.toolbar.setVisible(false);
		this.saveButton.setVisible(false);
		this.viewButton.setVisible(false);
		this.logoutButton.setVisible(false);
		this.editButton.setVisible(false);
		this.deleteButton.setVisible(false);
	}
	
	public void createPlugins()
	{
		 String html =	//"<a id='facebook' href='http://www.facebook.com/sharer.php?u=http://127.0.0.1:8888/Diary.html?gwt.codesvr=127.0.0.1:9997' target='_blank'  >"
	    		    //+  "<img src='http://www.simplesharebuttons.com/images/somacro/facebook.png' alt='Facebook' /></a>"
	    
	                  "<a id='twitter' href='http://twitter.com/share?text=&url=' target='_blank'  >"
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
		this.logoutButton.setVisible(true);
		this.logoutButton.setEnabled(true);
		this.logoutButton.setFocus(true);
		this.editButton.setVisible(false);
		this.deleteButton.setVisible(false);
	}
	
public void createHolderForm(){
		
		uppertextForVPanel.setHTML("<h3>Add property right informations!</h3>");
		vPanel.add(uppertextForVPanel);
		
		this.holderNameLabel.setText("Holder name :");
		vPanel.add(holderNameLabel);
		vPanel.add(holderName);
		
		this.holderSurnameLabel.setText("Holder surname : ");
		vPanel.add(holderSurnameLabel);
		vPanel.add(holderSurname);
		
		this.holderEmailLabel.setText("Holder email : ");
		vPanel.add(holderEmailLabel);
		vPanel.add(holderEmail);
		
		this.holderPhoneLabel.setText("Holder phone : ");
		vPanel.add(holderPhoneLabel);
		vPanel.add(holderPhone);
		
		
		this.propertyrightListBox.addItem("Modify");
		this.propertyrightListBox.addItem("Copy");
		this.propertyrightListBox.addItem("View");
		vPanel.add(propertyrightListBox);
		
		
		RootPanel.get().add(vPanel);
		RootPanel.get().add(addButton);
		RootPanel.get().add(closeButtonForHolder);
		
		RootPanel.get().setWidgetPosition(vPanel, 600, 130);
		RootPanel.get().setWidgetPosition(addButton,600, 430);
		RootPanel.get().setWidgetPosition(closeButtonForHolder, 720, 430);
		
		this.vPanel.setVisible(true);
		this.addButton.setVisible(true);
		this.addButton.setEnabled(true);
		this.addButton.setFocus(true);
		this.closeButtonForHolder.setVisible(true);
		this.closeButtonForHolder.setEnabled(true);
		this.closeButtonForHolder.setFocus(true);
	}


	public void returnbackFromHolderForm()
	{
		this.vPanel.setVisible(false);
		this.addButton.setVisible(false);
		this.addButton.setFocus(false);
		this.addButton.setEnabled(false);
		this.closeButtonForHolder.setVisible(false);
		this.closeButtonForHolder.setEnabled(false);
		this.closeButtonForHolder.setFocus(false);
		this.area.setVisible(true);
		this.area.setHTML("");
		this.toolbar.setVisible(true);
		this.saveButton.setVisible(true);
		this.saveButton.setFocus(true);
		this.saveButton.setEnabled(true);
		this.viewButton.setVisible(true);
		this.viewButton.setFocus(true);
		this.viewButton.setEnabled(true);
		this.title.setVisible(true);
		this.panel.setVisible(true);
		this.logoutButton.setVisible(true);
		this.logoutButton.setEnabled(true);
		this.logoutButton.setFocus(true);
		this.editButton.setVisible(false);
		this.deleteButton.setVisible(false);
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

	public Button getCloseButton() {
		return this.closeButton;
	}

	public TextBox getTitle() {
		return title;
	}

	public void setTitle(TextBox title) {
		this.title = title;
	}

	public TextBox getHolderName() {
		return holderName;
	}

	public void setHolderName(TextBox holderName) {
		this.holderName = holderName;
	}

	public TextBox getHolderSurname() {
		return holderSurname;
	}

	public void setHolderSurname(TextBox holderSurname) {
		this.holderSurname = holderSurname;
	}

	public TextBox getHolderEmail() {
		return holderEmail;
	}

	public void setHolderEmail(TextBox holderEmail) {
		this.holderEmail = holderEmail;
	}

	public TextBox getHolderPhone() {
		return holderPhone;
	}

	public void setHolderPhone(TextBox holderPhone) {
		this.holderPhone = holderPhone;
	}


	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	public ListBox getPropertyrightListBox() {
		return propertyrightListBox;
	}

	public void setPropertyrightListBox(ListBox propertyrightListBox) {
		this.propertyrightListBox = propertyrightListBox;
	}

	public Button getCloseButtonForHolder() {
		return closeButtonForHolder;
	}

	public void setCloseButtonForHolder(Button closeButtonForHolder) {
		this.closeButtonForHolder = closeButtonForHolder;
	}

	public VerticalPanel getvPanel() {
		return vPanel;
	}

	public void setvPanel(VerticalPanel vPanel) {
		this.vPanel = vPanel;
	}


	public HTMLPanel getPanel() {
		return panel;
	}


	public void setPanel(HTMLPanel panel) {
		this.panel = panel;
	}


	public Button getLogoutButton() {
		return logoutButton;
	}


	public void setLogoutButton(Button logoutButton) {
		this.logoutButton = logoutButton;
	}



	public Button getEditButton() {
		return editButton;
	}



	public void setEditButton(Button editButton) {
		this.editButton = editButton;
	}



	public Button getDeleteButton() {
		return deleteButton;
	}



	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	
}
