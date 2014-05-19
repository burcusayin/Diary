package com.google.gwt.diary.client; 

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.diary.client.ui.DiaryUI;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

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
	
	private int rowIndex;
	private int dID;
	
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
						new AsyncCallback<ArrayList<String>>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
								ui.getSubmitButton().setEnabled(true);
								ui.getSubmitButton().setFocus(true);
							}

							public void onSuccess(ArrayList<String> result) {
								Window.alert("Result is: " + result);
								System.out.println("Result is: " + result);
								String res = "OK";
								if(result.get(0).equalsIgnoreCase(res))
								{
									if(result.get(1).equalsIgnoreCase("Wrong username or password!"))
									{
										Window.alert("Wrong username or password!");
										ui.getUsername().setText("");
										ui.getPassword().setText("");
										ui.getSubmitButton().setEnabled(true);
									}
									else
									{
										ui.getDialogBox().setText("Remote Procedure Call");
										ui.getServerResponseLabel().removeStyleName("serverResponseLabelError");
										ui.getServerResponseLabel().setHTML(result.get(0));
										ui.getDialogBox().center();
										ui.getCloseButton().setFocus(true);
										
										ui.getUsername().setVisible(false);
										ui.getPassword().setVisible(false);
										ui.getSubmitButton().setVisible(false);
									    ui.getArea().setVisible(true);
									    ui.getToolbar().setVisible(true);
									    ui.getSaveButton().setVisible(true);
									    ui.getViewButton().setVisible(true);
									    ui.getAddNewMember().setVisible(false);
									    ui.createPlugins();
									}
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
				ui.getViewButton().setEnabled(true);
				ui.getViewButton().setFocus(true);
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
				ui.getViewButton().setEnabled(false);
				ui.getTextToServerLabel().setText(textToServer);
				ui.getServerResponseLabel().setText("");
				greetingService.takeDiary(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
								ui.getSaveButton().setEnabled(true);
								ui.getViewButton().setEnabled(true);
							}

							public void onSuccess(String result) {
								String res = "OK";
								String res2 = "NoUser";
								Window.alert("Result is: " + result);
								if(result.equalsIgnoreCase(res))
								{
									ui.getDialogBox().setText("Remote Procedure Call");
									ui.getServerResponseLabel().removeStyleName("serverResponseLabelError");
									ui.getServerResponseLabel().setHTML(result);
									ui.getDialogBox().center();
									ui.getCloseButton().setFocus(true);
								}
								else if(res2.equalsIgnoreCase(result))
								{
									Window.alert("You should be logged in!");
								}
								else
								{
									Window.alert("You should write something for your diary!");
									ui.getSaveButton().setEnabled(true);
									ui.getSaveButton().setFocus(true);
									ui.getViewButton().setEnabled(true);
									ui.getViewButton().setFocus(true);
								}
							}
						});
				}
			}	
			
		ui.getAddNewMember().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ui.getDialogBox().hide();
				ui.getSubmitButton().setEnabled(false);
				ui.getSubmitButton().setFocus(false);
				ui.getSubmitButton().setVisible(false);
				ui.getUsername().setEnabled(false);
				ui.getUsername().setFocus(false);
				ui.getUsername().setVisible(false);
				ui.getPassword().setEnabled(false);
				ui.getPassword().setFocus(false);
				ui.getPassword().setVisible(false);
				ui.getAddNewMember().setVisible(false);
				ui.createForm();
				
			}
		});
		
		class MyHandlerRegister implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */

			public void onClick(ClickEvent event) {
				sendNewAccountToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNewAccountToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNewAccountToServer() {
				// First, we validate the input.
				ui.getErrorLabel().setText("");
				if (    ui.getName().getText().length() == 0 ||
						ui.getSurname().getText().length() == 0 ||
						ui.getPhone().getText().length() == 0 ||
						ui.getAddress().getText().length() == 0 ||
			        	ui.getEmail().getText().length() == 0 ||
			        	ui.getUsernameNew().getText().length() == 0 ||
			        	ui.getPasswordNew().getText().length() == 0)
			    {
			        	Window.alert("The text boxes must not be empty");
			    }
				else
				{
					ArrayList<String> dataToServer = new ArrayList<>();
					dataToServer.add(ui.getUsernameNew().getText());
					dataToServer.add(ui.getPasswordNew().getText());
					dataToServer.add(ui.getName().getText());
					dataToServer.add(ui.getSurname().getText());
					dataToServer.add(ui.getPhone().getText());
					dataToServer.add(ui.getEmail().getText());
					dataToServer.add(ui.getAddress().getText());
					// Then, we send the input to the server.
					ui.getSaveButton().setEnabled(false);
					ui.getTextToServerLabel().setText(dataToServer.get(0) + dataToServer.get(1) + dataToServer.get(2) + dataToServer.get(3) + dataToServer.get(4) + dataToServer.get(5) + dataToServer.get(6));
					ui.getServerResponseLabel().setText("");
					greetingService.takeNewAccount(dataToServer,
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
									ui.returnback();
								}
								else
								{
									Window.alert("Invalid username!");
									ui.getRegisterButton().setEnabled(true);
									ui.getRegisterButton().setFocus(true);
								}
							}
						});
					}
				}
			}
		
		// ---------------------------------------------------------------------
		
		// Create a handler for the viewButton
		class MyHandlerView implements ClickHandler, KeyUpHandler {
					/**
					 * Fired when the user clicks on the sendButton.
					 */

					public void onClick(ClickEvent event) {
						viewDiaries();
						ui.getToolbar().setVisible(false);
						ui.getArea().setVisible(false);
						ui.getSaveButton().setVisible(false);
						ui.getViewButton().setVisible(false);
						ui.createDiaryTable();
						ui.getFlexTable().setVisible(true);
						ui.getBackButton().setVisible(true);
						RootPanel.get("plugin").setVisible(false);
					}

					/**
					 * Fired when the user types in the nameField.
					 */
					public void onKeyUp(KeyUpEvent event) {
						if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
							viewDiaries();
						}
					}

					/**
					 * Send the name from the nameField to the server and wait for a response.
					 */
					private void viewDiaries() {
						// First, we validate the input.
						ui.getErrorLabel().setText("");

						ui.getTextToServerLabel().setText("");
						ui.getServerResponseLabel().setText("");
						greetingService.viewDiary(new AsyncCallback<ArrayList<ArrayList<String>>>() {
									public void onFailure(Throwable caught) {
										Window.alert("RPC call fail! \n" + SERVER_ERROR);
										ui.getSaveButton().setEnabled(true);
										ui.getSaveButton().setVisible(true);
										ui.getSaveButton().setFocus(true);
										ui.getViewButton().setEnabled(true);
										ui.getViewButton().setVisible(true);
										ui.getViewButton().setFocus(true);
										ui.getToolbar().setVisible(true);
										ui.getArea().setVisible(true);
										ui.getFlexTable().setVisible(false);
										ui.getBackButton().setVisible(false);
										RootPanel.get("plugin").setVisible(true);
									}

									public void onSuccess(ArrayList<ArrayList<String>> result) {
										if(result.isEmpty())
										{
											Window.alert("You have not written a diary, yet!");
											ui.getSaveButton().setEnabled(true);
											ui.getSaveButton().setFocus(true);
											ui.getSaveButton().setVisible(true);
											ui.getViewButton().setEnabled(true);
											ui.getViewButton().setFocus(true);
											ui.getViewButton().setVisible(true);
											ui.getToolbar().setVisible(true);
											ui.getArea().setVisible(true);
											ui.getFlexTable().setVisible(false);
											ui.getBackButton().setVisible(false);
											RootPanel.get("plugin").setVisible(true);
										}
										else
										{
											int i = 0;
											ui.getDialogBox().setText("Remote Procedure Call");
											ui.getServerResponseLabel().removeStyleName("serverResponseLabelError");
											ui.getServerResponseLabel().setHTML("Viewed");
											ui.getDialogBox().center();
											ui.getCloseButton().setFocus(true);
											int rows = ui.getFlexTable().getRowCount();
											if(rows > 1)
											{
												while(ui.getFlexTable().getRowCount() != 1)
												{
													ui.getFlexTable().removeRow(rows - 1);
												}
											}
											int l = result.size();
											while(i != l)
											{
												
												String id = result.get(i).get(0);
												String title = result.get(i).get(1);
												String date = result.get(i).get(2);
												String time = result.get(i).get(3);
												String uname = result.get(i).get(4);
												
												int numRows = ui.getFlexTable().getRowCount();
									
												ui.getFlexTable().setText(numRows, 0, id);
												ui.getFlexTable().setText(numRows, 1, title);
												ui.getFlexTable().setText(numRows, 2, date);
												ui.getFlexTable().setText(numRows, 3, time);
												ui.getFlexTable().setText(numRows, 4, uname);
												
												i++;
											}
											
											ui.getFlexTable().setVisible(true);
										}
									}
								});
						}
					}	
		
		ui.getBackButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				ui.getFlexTable().setVisible(false);
				ui.getBackButton().setVisible(false);
				ui.getToolbar().setVisible(true);
				ui.getArea().setVisible(true);
				ui.getSaveButton().setVisible(true);
				ui.getAddNewMember().setVisible(true);
				RootPanel.get("plugin").setVisible(true);
			}
		});
		
		// ////////////////////////////////////////////////////////////////////
		
		class MyHandlerTable implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */

			public void onClick(ClickEvent event) {
				rowIndex = ui.getFlexTable().getCellForEvent(event).getRowIndex();
				System.out.println("Týklanýlan index: " + rowIndex + "\n");
				dID = Integer.parseInt(ui.getFlexTable().getText(rowIndex, 0));
				System.out.println("Týklanýlan dID: " + dID + "\n");
				ui.getToolbar().setVisible(true);
				ui.getArea().setVisible(true);
				ui.getSaveButton().setVisible(true);
				ui.getViewButton().setVisible(true);
				ui.getFlexTable().setVisible(false);
				ui.getBackButton().setVisible(false);
				RootPanel.get("plugin").setVisible(true);
				showDiaryContent();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					showDiaryContent();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void showDiaryContent() {
				// First, we validate the input.
				ui.getErrorLabel().setText("");

				ui.getTextToServerLabel().setText("");
				ui.getServerResponseLabel().setText("");
				greetingService.showDiaryContent(dID,new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
								ui.getToolbar().setVisible(false);
								ui.getArea().setVisible(false);
								ui.getSaveButton().setVisible(false);
								ui.getViewButton().setVisible(false);
								ui.createDiaryTable();
								ui.getFlexTable().setVisible(true);
								ui.getBackButton().setVisible(true);
								RootPanel.get("plugin").setVisible(false);
							}

							public void onSuccess(String result) {
								
								ui.getDialogBox().setText("Remote Procedure Call");
								ui.getServerResponseLabel().removeStyleName("serverResponseLabelError");
								ui.getServerResponseLabel().setHTML("Viewed");
								ui.getDialogBox().center();
								ui.getCloseButton().setFocus(true);
								System.out.println("Týklanýnca gelen result: " + result + "\n");
								
								ui.getArea().setText(result);
								
								ui.getArea().setVisible(true);
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
		
		MyHandlerRegister register = new MyHandlerRegister();
		ui.getRegisterButton().addClickHandler(register);
		ui.getName().addKeyUpHandler(register);
		ui.getSurname().addKeyUpHandler(register);
		ui.getPhone().addKeyUpHandler(register);
		ui.getEmail().addKeyUpHandler(register);
		ui.getAddress().addKeyUpHandler(register);
		ui.getUsernameNew().addKeyUpHandler(register);
		ui.getPasswordNew().addKeyUpHandler(register);
		
		MyHandlerView viewHandler = new  MyHandlerView();
		ui.getViewButton().addClickHandler(viewHandler);
		
		MyHandlerTable tableHandler = new MyHandlerTable();
		ui.getFlexTable().addClickHandler(tableHandler);
	}
}
