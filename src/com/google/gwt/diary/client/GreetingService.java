package com.google.gwt.diary.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	public String takeDiary(String input) throws IllegalArgumentException;
	public ArrayList<String> takeLogin(String input1, String input2) throws IllegalArgumentException;
	public String takeNewAccount(ArrayList<String> str) throws IllegalArgumentException;
	public ArrayList<ArrayList<String>> viewDiary() throws IllegalArgumentException;
	public String showDiaryContent(int id) throws IllegalArgumentException;
}
