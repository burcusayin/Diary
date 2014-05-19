package com.google.gwt.diary.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import model.Diary;
import model.Person;

import com.google.gwt.diary.db.ConnectionManager;

public class DiaryDAOImpl implements DiaryDAO {
	
	Connection connection ;
	PreparedStatement ps ;
	
	@Override
	public int insertDiary(Diary newDiary) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		try {
			connection = getConnection();
			String query = "insert into DIARY.DIARY (DIARY_ID, TITLE, CONTENT, DIARY_DATE, DIARY_TIME, USERNAME) values (?,?,?,?,?,?)";
			ps = connection.prepareStatement(query);
			
			ps.setLong(1, newDiary.getDiaryId());
			ps.setString(2, newDiary.getTitle());
			ps.setString(3, newDiary.getContent());
			
			java.sql.Date sqlDate = new java.sql.Date(newDiary.getDiaryDate().getTime());
			ps.setDate(4, sqlDate);
			
			ps.setTimestamp(5, newDiary.getDiaryTime());
			ps.setString(6, newDiary.getPerson().getUsername());
			
			queryResult = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Can not get Connection");
			e.printStackTrace();
		} finally {
			try {
				if(ps != null)
					ps.clearParameters();
					ps.close();
				if(connection != null)
					connection.close();
		    } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		return queryResult;
	}



	@Override
	public int deleteDiary(long id) {
		// TODO Auto-generated method stub
		
		int queryResult = 0;
		try {
			connection = getConnection();
			String query = "delete from DIARY.DIARY where DIARY_ID=?";
			ps = connection.prepareStatement(query);
			
			ps.setLong(1, id);

			queryResult = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Can not get Connection");
			e.printStackTrace();
		} finally {
			try {
				if(ps != null)
					ps.clearParameters();
					ps.close();
				if(connection != null)
					connection.close();
		    } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		return queryResult;
	}
	

	@Override
	public Diary getDiaryById (long id) {
		// TODO Auto-generated method stub
		
		long diary_id = 0;
		String title = null;
		String content = null;
		String username = null;
		Date diary_date = null;
		Timestamp diary_time = null;
		Diary diary = null;
		Person person = null;
		ResultSet rs;
	
		try {
			connection = getConnection();
			String query = "select DIARY_ID, TITLE, CONTENT, DIARY_DATE, DIARY_TIME, USERNAME from DIARY.DIARY where DIARY_ID=?";
			ps = connection.prepareStatement(query);
		
			ps.setLong(1, id);
		
			rs = ps.executeQuery();
		
		
			while (rs.next()) {
				
				diary_id = rs.getLong("DIARY_ID");
				title = rs.getString("TITLE");
				content = rs.getString("CONTENT");
				diary_date = rs.getDate("DIARY_DATE");
				diary_time = rs.getTimestamp("DIARY_DATE");
				username = rs.getString("USERNAME");
			
				diary = new  Diary();
				diary.setDiaryId(diary_id);
				diary.setTitle(title);
				diary.setContent(content);
				diary.setDiaryDate(diary_date);
				diary.setDiaryTime(diary_time);
				
				PersonDAO pDao = PersonDAOFactory.getPersonDAO();
				person = pDao.getPersonByUsername(username);
				
				diary.setPerson(person);
				
			}
			
			return diary;
		
		} catch (SQLException e) {
			System.out.println("Can not get Connection");
			e.printStackTrace();
			return diary;
		} finally {
			try {
				if(ps != null)
					ps.clearParameters();
					ps.close();
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	@Override
	public ArrayList<Diary> getDiaryByUsername(String uname) {
		// TODO Auto-generated method stub
		
		long diary_id = 0;
		String title = null;
		String content = null;
		String username = null;
		Date diary_date = null;
		Timestamp diary_time = null;
		Diary diary = null;
		Person person = null;
		ResultSet rs;
		
		ArrayList<Diary> dList = new ArrayList<Diary>();
	
		try {
			connection = getConnection();
			String query = "select DIARY_ID, TITLE, CONTENT, DIARY_DATE, DIARY_TIME, USERNAME from DIARY.DIARY where USERNAME = ?";
			ps = connection.prepareStatement(query);
		
			ps.setString(1, uname);;
		
			rs = ps.executeQuery();
		
		
			while (rs.next()) {
				
				diary_id = rs.getLong("DIARY_ID");
				title = rs.getString("TITLE");
				content = rs.getString("CONTENT");
				diary_date = rs.getDate("DIARY_DATE");
				diary_time = rs.getTimestamp("DIARY_DATE");
				username = rs.getString("USERNAME");
			
				diary = new  Diary();
				diary.setDiaryId(diary_id);
				diary.setTitle(title);
				diary.setContent(content);
				diary.setDiaryDate(diary_date);
				diary.setDiaryTime(diary_time);
				
				PersonDAO pDao = PersonDAOFactory.getPersonDAO();
				person = pDao.getPersonByUsername(username);
				
				diary.setPerson(person);
				dList.add(diary);
			}
			
			return dList;
		
		} catch (SQLException e) {
			System.out.println("Can not get Connection");
			e.printStackTrace();
			return dList;
		} finally {
			try {
				if(ps != null)
					ps.clearParameters();
					ps.close();
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	public long getMaxDiaryId(){
			// TODO Auto-generated method stub
			
			long diary_id = 0;
			ResultSet rs;
		
			try {
				connection = getConnection();
				String query = "select MAX(DIARY_ID) AS MAX_DIARY_ID from DIARY.DIARY";
				ps = connection.prepareStatement(query);
					
				rs = ps.executeQuery();

				while (rs.next()) {
					diary_id = rs.getLong("MAX_DIARY_ID");
				}
				
				return diary_id;
			
			} catch (SQLException e) {
				System.out.println("Can not get Connection");
				e.printStackTrace();
				return diary_id;
			} finally {
				try {
					if(ps != null)
						ps.clearParameters();
						ps.close();
					if(connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
	}
	
	private Connection getConnection() throws SQLException{
        Connection conn;
        conn = ConnectionManager.getInstance().getConnection();
        return conn;
	}
}
