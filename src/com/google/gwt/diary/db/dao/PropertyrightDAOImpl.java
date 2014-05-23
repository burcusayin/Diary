package com.google.gwt.diary.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.Diary;
import model.Propertyright;

import com.google.gwt.diary.db.ConnectionManager;


public class PropertyrightDAOImpl implements PropertyrightDAO {
	
	Connection connection ;
	PreparedStatement ps ;
	
	@Override
	public int insertPropertyright(Propertyright newPropertyright) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		try {
			connection = getConnection();
			String query = "insert into DIARY.PROPERTYRIGHT (PROPERTYRIGHT_ID, PROPERTYRIGHT_DATE, TYPE, DIARY_ID) values (?,?,?,?)";
			ps = connection.prepareStatement(query);
			
			ps.setLong(1, newPropertyright.getPropertyrightId());
			
			java.sql.Date sqlDate = new java.sql.Date(newPropertyright.getPropertyrightDate().getTime());
			ps.setDate(2, sqlDate);
			
			ps.setString(3, newPropertyright.getType());
			ps.setLong(4, newPropertyright.getDiary().getDiaryId());
			
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
	public int deletePropertyright(long id) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		try {
			connection = getConnection();
			String query = "delete from DIARY.PROPERTYRIGHT where PROPERTYRIGHT_ID =?";
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
	public Propertyright getPropertyrightById (long id) {
		// TODO Auto-generated method stub
		
		long propertyright_id, diary_id = 0;
		Date propertyright_date = null;
		String type = null;
		Propertyright propertyright = null;
		Diary diary = null;
		ResultSet rs;
	
		try {
			connection = getConnection();
			String query = "select PROPERTYRIGHT_ID, PROPERTYRIGHT_DATE, TYPE, DIARY_ID from DIARY.PROPERTYRIGHT where PROPERTYRIGHT_ID=?";
			ps = connection.prepareStatement(query);
		
			ps.setLong(1, id);
		
			rs = ps.executeQuery();
		
		
			while (rs.next()) {
				propertyright_id = rs.getLong("PROPERTYRIGHT_ID");
				propertyright_date = rs.getDate("PROPERTYRIGHT_DATE");
				type = rs.getString("TYPE");
				diary_id = rs.getLong("DIARY_ID");

			
				propertyright = new  Propertyright();
				propertyright.setPropertyrightId(propertyright_id);
				propertyright.setPropertyrightDate(propertyright_date);
				propertyright.setType(type);
				
				DiaryDAO dDao = DiaryDAOFactory.getDiaryDAO();
				diary = dDao.getDiaryById(diary_id);
				
				propertyright.setDiary(diary);

			}
			
			return propertyright;
		
		} catch (SQLException e) {
			System.out.println("Can not get Connection");
			e.printStackTrace();
			return propertyright;
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

	public long getMaxPropertyrightId(){
		// TODO Auto-generated method stub
		
		long propertyright_id = 0;
		ResultSet rs;
	
		try {
			connection = getConnection();
			String query = "select MAX(PROPERTYRIGHT_ID ) AS MAX_PROPERTYRIGHT_ID  from DIARY.PROPERTYRIGHT";
			ps = connection.prepareStatement(query);
				
			rs = ps.executeQuery();

			while (rs.next()) {
				propertyright_id = rs.getLong("MAX_PROPERTYRIGHT_ID");
			}
			
			return propertyright_id;
		
		} catch (SQLException e) {
			System.out.println("Can not get Connection");
			e.printStackTrace();
			return propertyright_id;
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
