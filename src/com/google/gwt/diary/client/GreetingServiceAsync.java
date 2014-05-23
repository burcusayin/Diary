package com.google.gwt.diary.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void takeDiary(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void takeLogin(String input1, String input2,AsyncCallback<ArrayList<String>> callback)
			throws IllegalArgumentException;
	void takeNewAccount(ArrayList<String> str,AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void viewDiary(AsyncCallback<ArrayList<ArrayList<String>>> callback)
			throws IllegalArgumentException;
	void showDiaryContent(Long id, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void takeNewHolder(ArrayList<String> str,AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void logout(AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void editDiary(Long diary_id, String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void deleteDiary(Long diary_id, AsyncCallback<String> callback) 
			throws IllegalArgumentException;
	
}

