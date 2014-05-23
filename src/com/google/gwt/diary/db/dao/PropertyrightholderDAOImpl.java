package com.google.gwt.diary.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Propertyrightholder;

import com.google.gwt.diary.db.ConnectionManager;

public class PropertyrightholderDAOImpl implements PropertyrightholderDAO {
	
	Connection connection ;
	PreparedStatement ps ;
	
	@Override
	public int insertPropertyrightholder(Propertyrightholder newPropertyrightholder) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		try {
			connection = getConnection();
			String query = "insert into DIARY.PROPERTYRIGHTHOLDER (HOLDER_ID, HOLDER_NAME, HOLDER_SURNAME, HOLDER_E_MAIL, HOLDER_PHONE) values (?,?,?,?,?)";
			ps = connection.prepareStatement(query);
			
			ps.setLong(1, newPropertyrightholder.getHolderId());
			ps.setString(2, newPropertyrightholder.getHolderName());
			ps.setString(3, newPropertyrightholder.getHolderSurname());
			ps.setString(4, newPropertyrightholder.getHolderEMail());
			ps.setString(5, newPropertyrightholder.getHolderPhone());
			
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
	public int deletePropertyrightholder(long id) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		try {
			connection = getConnection();
			String query = "delete from DIARY.PROPERTYRIGHTHOLDER where HOLDER_ID=?";
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
	public Propertyrightholder getPropertyrightholderById (long id) {
		// TODO Auto-generated method stub
		
		long holder_id = 0;
		String holder_name, holder_surname, holder_e_mail, holder_phone = null;
		Propertyrightholder propertyrightholder = null;
		ResultSet rs;
	
		try {
			connection = getConnection();
			String query = "select HOLDER_ID, HOLDER_NAME, HOLDER_SURNAME, HOLDER_E_MAIL, HOLDER_PHONE from DIARY.PROPERTYRIGHTHOLDER where HOLDER_ID=?";
			ps = connection.prepareStatement(query);
		
			ps.setLong(1, id);
		
			rs = ps.executeQuery();
		
		
			while (rs.next()) {
				holder_id = rs.getLong("HOLDER_ID");
				holder_name = rs.getString("HOLDER_NAME");
				holder_surname = rs.getString("HOLDER_SURNAME");
				holder_e_mail = rs.getString("HOLDER_E_MAIL");
				holder_phone = rs.getString("HOLDER_PHONE");

				propertyrightholder = new  Propertyrightholder();
				propertyrightholder.setHolderId(holder_id);
				propertyrightholder.setHolderName(holder_name);
				propertyrightholder.setHolderSurname(holder_surname);
				propertyrightholder.setHolderEMail(holder_e_mail);
				propertyrightholder.setHolderPhone(holder_phone);
			}
			
			return propertyrightholder;
		
		} catch (SQLException e) {
			System.out.println("Can not get Connection");
			e.printStackTrace();
			return propertyrightholder;
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
	
	public long getMaxPropertyrightholderId(){
		// TODO Auto-generated method stub
		
		long propertyrightholder_id = 0;
		ResultSet rs;
	
		try {
			connection = getConnection();
			String query = "select MAX(HOLDER_ID) AS MAX_HOLDER_ID from DIARY.PROPERTYRIGHTHOLDER";
			ps = connection.prepareStatement(query);
				
			rs = ps.executeQuery();

			while (rs.next()) {
				propertyrightholder_id = rs.getLong("MAX_HOLDER_ID");
			}
			
			return propertyrightholder_id;
		
		} catch (SQLException e) {
			System.out.println("Can not get Connection");
			e.printStackTrace();
			return propertyrightholder_id;
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
