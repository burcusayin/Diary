package com.google.gwt.diary.db.dao;

import model.Propertyright;


public interface PropertyrightDAO {
	public int insertPropertyright(Propertyright propertyright);
	public int deletePropertyright(long id);
	public Propertyright getPropertyrightById(long id);
}
