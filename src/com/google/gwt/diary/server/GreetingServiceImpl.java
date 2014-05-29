package com.google.gwt.diary.server;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import model.Diary;
import model.Person;

import org.apache.commons.lang3.time.DateUtils;

import com.google.gwt.diary.client.GreetingService;
import com.google.gwt.diary.db.DatabaseController;
import com.google.gwt.diary.db.dao.DiaryDAO;
import com.google.gwt.diary.db.dao.DiaryDAOFactory;
import com.google.gwt.diary.db.dao.PersonDAO;
import com.google.gwt.diary.db.dao.PersonDAOFactory;
import com.google.gwt.diary.db.dao.PropertyrightDAO;
import com.google.gwt.diary.db.dao.PropertyrightDAOFactory;
import com.google.gwt.diary.db.dao.PropertyrightholderDAO;
import com.google.gwt.diary.db.dao.PropertyrightholderDAOFactory;
import com.google.gwt.diary.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.util.Calendar;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	DiaryDAO dDao = DiaryDAOFactory.getDiaryDAO();
	PropertyrightholderDAO prhDAO = PropertyrightholderDAOFactory.getPropertyrightholderDAO();
	PropertyrightDAO prDAO = PropertyrightDAOFactory.getPropertyRightDAO();
	
	private long diaryID;
	private long propertyrightholderID;
	private long propertyrightID;
	private String username;
	
	public GreetingServiceImpl(){
		this.username = null;
		this.diaryID = dDao.getMaxDiaryId();
		this.propertyrightholderID = prhDAO.getMaxPropertyrightholderId();
		this.propertyrightID = prDAO.getMaxPropertyrightId();
	}
	
	
	public String takeDiary(String input) throws IllegalArgumentException {
		PersonDAO pDao = PersonDAOFactory.getPersonDAO();
		Person person = new Person();
		String result = null;
		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);

		boolean res = isItValid(input);
		if(res)
		{
			Diary d = new Diary();
			d.setContent(input);
			System.out.println("username at takeDiary : " + this.username);
			String uname = this.username;
			if(uname != null)
			{
				person = pDao.getPersonByUsername(uname);
				d.setPerson(person);
				//date
				d.setDiaryDate(Calendar.getInstance().getTime()); 
				// time
				java.util.Date date= new java.util.Date(); 
				Date newDate = DateUtils.addHours(date, -9);  
				d.setDiaryTime(new Timestamp(newDate.getTime()));
				
				d.setTitle("Diary " + diaryID);
				this.diaryID = diaryID + 1;
				d.setDiaryId(diaryID);
				
				int resultForInsert = dDao.insertDiary(d);
				if (resultForInsert==1){
					System.out.println("diary added!!");
				}else{ 
					System.out.println("Can not be added");
				}	
				result = "OK";
			}
			else
			{
				result = "NoUser";
			}
		}
		else
		{
			result = "FAIL";
		}
		
		return result;
	}
	
	public ArrayList<ArrayList<String>> viewDiary() throws IllegalArgumentException 
	{
		ArrayList<Diary> dList = new ArrayList<Diary>();
		ArrayList<ArrayList<String>> listt = new ArrayList<ArrayList<String>>();
		int i = 0;
		ArrayList<String> strList;
		dList = dDao.getDiaryByUsername(this.username);
		
		if(!dList.isEmpty())
		{
			int l = dList.size();
			while(i != l)
			{
				strList = new ArrayList<String>();
				String id = "" + dList.get(i).getDiaryId();
				String title = "" + dList.get(i).getTitle();
				String date = "" + dList.get(i).getDiaryDate();
				String uname = "" + dList.get(i).getPerson().getUsername();
				
				strList.add(id);
				strList.add(title);
				strList.add(date);
				strList.add(uname);
				listt.add(strList);
				
				i++;
			}
		}
		return listt;
	}
	
	public String showDiaryContent(Long id) throws IllegalArgumentException {
		
		Diary d = dDao.getDiaryById(id);
		String content = d.getContent();
		System.out.println("Impl sýnýfýnda gelen content: " + content + "\n");
		return content;
		
	}
	
	public ArrayList<String> takeLogin(String input1, String input2) throws IllegalArgumentException {
		
		ArrayList<String> result = new ArrayList<>();
		// Escape data from the client to avoid cross-site script vulnerabilities.
		input1 = escapeHtml(input1);
		input2 = escapeHtml(input2);
		
		boolean res1 = isItValid(input1);
		boolean res2 = isItValid(input2);
		
		if( res1 && res2)
		{
			DatabaseController dbc = new DatabaseController();
			String data = dbc.verifyLoginEntries(input1, input2);
			result.add("OK");
			result.add(data);
			
			if(data.equalsIgnoreCase("Username and password are true ")){
				this.username = input1;
			}
		}
		else
		{
			result.add("FAIL");
		}
		
		return result;
	}
	
	public String takeNewAccount(ArrayList<String> list) throws IllegalArgumentException {
		
		DatabaseController dbc = new DatabaseController();
		String flag = null;
		String username = list.get(0);
		String password = list.get(1);
		String name = list.get(2);
		String surname = list.get(3);
		String phone = list.get(4);
		String email = list.get(5);
		String address = list.get(6);
		Boolean result = dbc.addNewAccount(username, password, name, surname, phone, email, address);
		
		if(result)
		{
			flag = "OK";
		}
		else
		{
			flag = "FAIL";
		}
		return flag;
	}
	
	public String takeNewHolder(ArrayList<String> list) throws IllegalArgumentException {
		
		DatabaseController dbc = new DatabaseController();
		String flag = null;
		String name = list.get(0);
		String surname = list.get(1);
		String email = list.get(2);
		String phone = list.get(3);
		String type = list.get(4);
		
		this.propertyrightholderID = propertyrightholderID + 1;
		this.propertyrightID = propertyrightID + 1;
		
		Boolean result1 = dbc.addNewHolder(propertyrightholderID, name, surname, email, phone);
		Boolean result2 = dbc.addNewPropertyright(propertyrightID, type);
		
		if(result1 && result2)
		{
			flag = "OK";
		}
		else
		{
			flag = "FAIL";
		}
		return flag;
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	public boolean isItValid(String text)
	{
		if (!FieldVerifier.isValidName(text)) 
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public String logout() throws IllegalArgumentException {
		String result = null;

		if(this.username != null)
		{
			this.username = null;
			result = "OK";
		}
		else
		{
			result = "FAIL";
		}
		
		return result;
	}
	
	public String editDiary(Long diary_id, String input) throws IllegalArgumentException {

		String result = null;
		input = escapeHtml(input);

		boolean res = isItValid(input);
		if(res)
		{
			Diary d = new Diary();
			d = dDao.getDiaryById(diary_id);
			if(d != null)
			{				
				int resultForUpdate = dDao.updateDiary(diary_id, input);
				if (resultForUpdate==1){
					System.out.println("diary edited!!");
				}else{ 
					System.out.println("Can not be edited");
				}	
				result = "OK";
			}
			else
			{
				result = "NoDiary";
			}
		}
		else
		{
			result = "FAIL";
		}
		
		return result;
	}
	
	public String deleteDiary(Long diary_id) throws IllegalArgumentException {

		String res = null;
		int result = dDao.deleteDiary(diary_id);

		if(result==1)
		{
			System.out.println("Diary deleted");	
			res = "OK";
		}
		else{
			System.out.println("Diary cannot be deleted!");
			res = "FAIL";
		}
		
		return res;
	}
}
