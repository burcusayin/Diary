package com.google.gwt.diary.db.dao;

import model.Propertyrightholder;


public interface PropertyrightholderDAO {
	public int insertPropertyrightholder(Propertyrightholder newPropertyrightholder);
	public int deletePropertyrightholder(long id);
	public Propertyrightholder getPropertyrightholderById(long id);
	public long getMaxPropertyrightholderId();
}
