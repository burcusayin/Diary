package com.google.gwt.diary.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Person;

import com.google.gwt.diary.db.ConnectionManager;

public class PersonDAOImpl implements PersonDAO {
	
	Connection connection ;
	PreparedStatement ps ;
	
	@Override
	public int insertPerson(Person newPerson) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		try {
			connection = getConnection();
			String query = "insert into DIARY.PERSON (NAME, SURNAME, PHONE, EMAIL, ADDRESS, USERNAME, PASSWORD) values (?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(query);
	
			ps.setString(1, newPerson.getName());
			ps.setString(2, newPerson.getSurname());
			ps.setString(3, newPerson.getPhone());
			ps.setString(4, newPerson.getEmail());
			ps.setString(5, newPerson.getAddress());
			ps.setString(6, newPerson.getUsername());
			ps.setString(7, newPerson.getPassword());

			
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
	public int deletePerson(String uname) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		try {
			connection = getConnection();
			String query = "delete from DIARY.PERSON where USERNAME=?";
			ps = connection.prepareStatement(query);
			
			ps.setString(1, uname);

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
	public Person getPersonByUsername(String uname) {
		// TODO Auto-generated method stub
		
		String username, password, name, surname, phone, email, address = null;
		Person person = null;
		ResultSet rs;
	
		try {
			connection = getConnection();
			String query = "select NAME, SURNAME, PHONE, EMAIL, ADDRESS, USERNAME, PASSWORD from DIARY.PERSON where USERNAME=?";
			ps = connection.prepareStatement(query);
		
			ps.setString(1, uname);
		
			rs = ps.executeQuery();
		
		
			while (rs.next()) {
				name = rs.getString("NAME");
				surname = rs.getString("SURNAME");
				phone = rs.getString("PHONE");
				email = rs.getString("EMAIL");
				address = rs.getString("ADDRESS");
				username = rs.getString("USERNAME");
				password = rs.getString("PASSWORD");

			
				person = new  Person();
				person.setName(name);
				person.setSurname(surname);
				person.setPhone(phone);
				person.setEmail(email);
				person.setAddress(address);
				person.setUsername(username);
				person.setPassword(password);
				
			}
			
			return person;
		
		} catch (SQLException e) {
			System.out.println("Can not get Connection");
			e.printStackTrace();
			return person;
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
