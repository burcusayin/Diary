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
	void showDiaryContent(int id, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}

