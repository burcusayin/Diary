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

public class Diary implements EntryPoint {

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private int rowIndex;
	private Long dID;
	
	public void onModuleLoad() {
		
	    final DiaryUI ui = new DiaryUI();
	    ui.createUI();
	    
		ui.getSubmitButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			    ui.getSubmitButton().setEnabled(true);
			    ui.getSubmitButton().setFocus(true);
					
			}
		});
		
		class MyHandlerLogin implements ClickHandler, KeyUpHandler {

			public void onClick(ClickEvent event) {
				sendLoginInfosToServer();
			}

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

				greetingService.takeLogin(nameToServer,passwordToServer,
						new AsyncCallback<ArrayList<String>>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
								ui.getSubmitButton().setEnabled(true);
								ui.getSubmitButton().setFocus(true);
							}

							public void onSuccess(ArrayList<String> result) {
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
									    ui.getTitle().setVisible(true);
									    ui.getLogoutButton().setVisible(true);
									    ui.getEditButton().setVisible(false);
									    ui.getDeleteButton().setVisible(false);
									    
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
		
		ui.getCloseButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ui.getSaveButton().setEnabled(true);
				ui.getSaveButton().setFocus(true);
				ui.getViewButton().setEnabled(true);
				ui.getViewButton().setFocus(true);
				ui.getLogoutButton().setEnabled(true);
				ui.getLogoutButton().setFocus(true);
			}
		});

		class MyHandler implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				sendDiaryToServer();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendDiaryToServer();
				}
			}

			private void sendDiaryToServer() {

				ui.getErrorLabel().setText("");
				String textToServer = ui.getArea().getText();

				// Then, we send the input to the server.
				ui.getSaveButton().setEnabled(false);
				ui.getViewButton().setEnabled(false);
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
								
								if(result.equalsIgnoreCase(res))
								{	
									ui.getArea().setVisible(false);
									ui.getToolbar().setVisible(false);
									ui.getSaveButton().setVisible(false);
									ui.getViewButton().setVisible(false);
									ui.getTitle().setVisible(false);
									ui.getPanel().setVisible(false);
									ui.createHolderForm();
									ui.getTitle().setVisible(false);
									ui.getLogoutButton().setVisible(false);
									ui.getEditButton().setVisible(false);
									ui.getDeleteButton().setVisible(false);
									
								}
								else if(res2.equalsIgnoreCase(result))
								{
									Window.alert("You should be logged in!");
									ui.getSaveButton().setEnabled(true);
									ui.getSaveButton().setFocus(true);
									ui.getViewButton().setEnabled(true);
									ui.getViewButton().setFocus(true);
									ui.getLogoutButton().setEnabled(true);
									ui.getLogoutButton().setFocus(true);
								}
								else
								{
									Window.alert("You should write something for your diary!");
									ui.getSaveButton().setEnabled(true);
									ui.getSaveButton().setFocus(true);
									ui.getViewButton().setEnabled(true);
									ui.getViewButton().setFocus(true);
									ui.getLogoutButton().setEnabled(true);
									ui.getLogoutButton().setFocus(true);
								}
							}
						});
				}
			}	
			
		ui.getAddNewMember().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
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

			public void onClick(ClickEvent event) {
				sendNewAccountToServer();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNewAccountToServer();
				}
			}

			private void sendNewAccountToServer() {
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
					ui.getSaveButton().setEnabled(false);

					greetingService.takeNewAccount(dataToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
							}

							public void onSuccess(String result) {
								String res = "OK";
								if(result.equalsIgnoreCase(res))
								{
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
		
		ui.getCloseButtonForHolder().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ui.returnbackFromHolderForm();
			}
		});
		
		
		class MyHandlerRecordHolder implements ClickHandler, KeyUpHandler {
			
			public void onClick(ClickEvent event) {
				sendNewHolderToServer();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNewHolderToServer();
				}
			}

			private void sendNewHolderToServer() {
				ui.getErrorLabel().setText("");
				if (    ui.getHolderName().getText().length() == 0 ||
						ui.getHolderSurname().getText().length() == 0 ||
						ui.getHolderPhone().getText().length() == 0 ||
						ui.getHolderEmail().getText().length() == 0)
			    {
			        	Window.alert("The propert right text boxes must not be empty");
			    }
				else
				{
					ArrayList<String> dataToServer = new ArrayList<>();
					dataToServer.add(ui.getHolderName().getText());
					dataToServer.add(ui.getHolderSurname().getText());
					dataToServer.add(ui.getHolderEmail().getText());
					dataToServer.add(ui.getHolderPhone().getText());
					
					int index = ui.getPropertyrightListBox().getSelectedIndex();
					dataToServer.add(ui.getPropertyrightListBox().getValue(index));
					
					greetingService.takeNewHolder(dataToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
							}

							public void onSuccess(String result) {
								String res = "OK";
								if(result.equalsIgnoreCase(res))
								{
									ui.returnbackFromHolderForm();
								}
								else
								{
									Window.alert("Property right informations cannot be added!");
								}
							}
						});
					}
				}
			}
		
		class MyHandlerView implements ClickHandler, KeyUpHandler {

					public void onClick(ClickEvent event) {
						viewDiaries();
						ui.getToolbar().setVisible(false);
						ui.getArea().setVisible(false);
						ui.getSaveButton().setVisible(false);
						ui.getViewButton().setVisible(false);
						ui.createDiaryTable();
						ui.getFlexTable().setVisible(true);
						ui.getBackButton().setVisible(true);
						ui.getPanel().setVisible(false);
						ui.getTitle().setVisible(false);
						ui.getLogoutButton().setVisible(false);
						ui.getEditButton().setVisible(false);
						ui.getDeleteButton().setVisible(false);
					}

					public void onKeyUp(KeyUpEvent event) {
						if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
							viewDiaries();
						}
					}

					private void viewDiaries() {

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
										ui.getTitle().setVisible(true);
										ui.getLogoutButton().setEnabled(true);
										ui.getLogoutButton().setVisible(true);
										ui.getLogoutButton().setFocus(true);
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
											ui.getTitle().setVisible(true);
											ui.getLogoutButton().setEnabled(true);
											ui.getLogoutButton().setVisible(true);
											ui.getLogoutButton().setFocus(true);
											ui.getAddButton().setVisible(false);
										}
										else
										{
											int i = 0;
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
				ui.getFlexTable().setVisible(false);
				ui.getBackButton().setVisible(false);
				ui.getToolbar().setVisible(true);
				ui.getArea().setVisible(true);
				ui.getSaveButton().setVisible(true);
				ui.getViewButton().setVisible(true);
				ui.getPanel().setVisible(true);
				ui.getLogoutButton().setVisible(true);
				ui.getArea().setHTML("");
				ui.getPanel().setVisible(true);
				ui.getTitle().setVisible(true);
				ui.getEditButton().setVisible(false);
				ui.getDeleteButton().setVisible(false);
			}
		});
		
		
		class MyHandlerTable implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				rowIndex = ui.getFlexTable().getCellForEvent(event).getRowIndex();
				System.out.println("Týklanýlan index: " + rowIndex + "\n");
				dID = Long.parseLong(ui.getFlexTable().getText(rowIndex, 0));
				System.out.println("Týklanýlan dID: " + dID + "\n");
				ui.getToolbar().setVisible(true);
				ui.getArea().setVisible(true);
				ui.getSaveButton().setVisible(true);
				ui.getViewButton().setVisible(true);
				ui.getFlexTable().setVisible(false);
				ui.getBackButton().setVisible(false);
				ui.getPanel().setVisible(true);
				showDiaryContent();
				ui.getTitle().setVisible(true);
				ui.getLogoutButton().setVisible(true);
				ui.getEditButton().setVisible(true);
				ui.getEditButton().setEnabled(true);
				ui.getEditButton().setFocus(true);
				ui.getDeleteButton().setVisible(true);
				ui.getDeleteButton().setEnabled(true);
				ui.getDeleteButton().setFocus(true);
			    RootPanel.get().setWidgetPosition(ui.getEditButton(), 364, 477);
			    RootPanel.get().setWidgetPosition(ui.getDeleteButton(), 364, 513);
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
								ui.getTitle().setVisible(false);
								ui.getLogoutButton().setVisible(false);
								ui.getEditButton().setVisible(false);
								ui.getDeleteButton().setVisible(false);
							}

							public void onSuccess(String result) {
								ui.getCloseButton().setFocus(true);
								System.out.println("Týklanýnca gelen result: " + result + "\n");
								
								ui.getArea().setText(result);
								
								ui.getArea().setVisible(true);
						}
					});
				}
			}	
		
		class MyHandlerLogout implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */

			public void onClick(ClickEvent event) {
				logoutFromHomePage();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					logoutFromHomePage();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void logoutFromHomePage() {
				greetingService.logout(new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
							}

							public void onSuccess(String result) {
								
								String res = "OK";
								if(result.equalsIgnoreCase(res))
								{
									ui.getToolbar().setVisible(false);
									ui.getArea().setVisible(false);
									ui.getSaveButton().setVisible(false);
									ui.getViewButton().setVisible(false);
									ui.getPanel().setVisible(false);
									ui.getTitle().setVisible(false);
									ui.getLogoutButton().setVisible(false);
									ui.getSubmitButton().setVisible(true);
									ui.getSubmitButton().setEnabled(true);
									ui.getSubmitButton().setFocus(true);
									ui.getUsername().setVisible(true);
									ui.getUsername().setEnabled(true);
									ui.getUsername().setFocus(true);
									ui.getPassword().setVisible(true);
									ui.getPassword().setEnabled(true);
									ui.getPassword().setVisible(true);
									ui.getUsername().setText("Username");
									ui.getPassword().setText("Password");
									ui.getAddNewMember().setVisible(true);
									ui.getEditButton().setVisible(false);
									ui.getDeleteButton().setVisible(false);
								}
								else
								{
									Window.alert("You should be logged in!");
								}
							}
						});
							
				}
		}
		
		
		class MyHandlerEdit implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				editDiaryWithUpdate();
			}
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					editDiaryWithUpdate();
				}
			}
			private void editDiaryWithUpdate() {
				String textToServer = ui.getArea().getText();
				ui.getEditButton().setEnabled(false);

				greetingService.editDiary(dID, textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
								ui.getEditButton().setEnabled(false);
							}

							public void onSuccess(String result) {
								String res = "OK";
								String res2 = "NoDiary";
								if(result.equalsIgnoreCase(res))
								{
									ui.getArea().setVisible(true);
									ui.getToolbar().setVisible(true);
									ui.getSaveButton().setVisible(true);
									ui.getSaveButton().setEnabled(true);
									ui.getSaveButton().setFocus(true);
									ui.getViewButton().setVisible(true);
									ui.getViewButton().setEnabled(true);
									ui.getViewButton().setFocus(true);
									ui.getTitle().setVisible(true);
									ui.getPanel().setVisible(true);
									ui.getTitle().setVisible(true);
									ui.getLogoutButton().setVisible(true);
									ui.getLogoutButton().setEnabled(true);
									ui.getLogoutButton().setFocus(true);
									ui.getEditButton().setVisible(false);
									ui.getDeleteButton().setVisible(false);
									
								}
								else if(res2.equalsIgnoreCase(result))
								{
									Window.alert("There is no diary!");
									ui.getEditButton().setEnabled(true);
									ui.getEditButton().setFocus(true);
								}
								else
								{
									Window.alert("You should write something for your diary to edit!");
									ui.getEditButton().setEnabled(true);
									ui.getEditButton().setFocus(true);
								}
							}
						});
				}
			}
		
		class MyHandlerDelete implements ClickHandler, KeyUpHandler {

			public void onClick(ClickEvent event) {
				deleteSelectedDiary();
			}
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					deleteSelectedDiary();
				}
			}
			private void deleteSelectedDiary() {
				ui.getEditButton().setEnabled(false);
				greetingService.deleteDiary(dID,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								Window.alert("RPC call fail! \n" + SERVER_ERROR);
								ui.getDeleteButton().setEnabled(false);
							}

							public void onSuccess(String result) {
								String res = "OK";
								if(result.equalsIgnoreCase(res))
								{
									ui.getArea().setVisible(true);
									ui.getToolbar().setVisible(true);
									ui.getSaveButton().setVisible(true);
									ui.getSaveButton().setEnabled(true);
									ui.getSaveButton().setFocus(true);
									ui.getViewButton().setVisible(true);
									ui.getViewButton().setEnabled(true);
									ui.getViewButton().setFocus(true);
									ui.getTitle().setVisible(true);
									ui.getPanel().setVisible(true);
									ui.getTitle().setVisible(true);
									ui.getLogoutButton().setVisible(true);
									ui.getLogoutButton().setEnabled(true);
									ui.getLogoutButton().setFocus(true);
									ui.getEditButton().setVisible(false);
									ui.getDeleteButton().setVisible(false);
									
								}
								else
								{
									Window.alert("Diary cannot be deleted!");
									ui.getDeleteButton().setEnabled(true);
									ui.getDeleteButton().setFocus(true);
								}
							}
						});
				}
			}
		
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

		MyHandlerRecordHolder holder = new MyHandlerRecordHolder();
		ui.getAddButton().addClickHandler(holder);
		
		MyHandlerLogout logoutHandler = new MyHandlerLogout();
		ui.getLogoutButton().addClickHandler(logoutHandler);
		
		MyHandlerEdit editHandler = new MyHandlerEdit();
		ui.getEditButton().addClickHandler(editHandler);
		
		MyHandlerDelete deleteHandler = new MyHandlerDelete();
		ui.getDeleteButton().addClickHandler(deleteHandler);
		
	}
}
