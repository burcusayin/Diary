package com.google.gwt.diary.db.dao;

import java.util.ArrayList;

import model.Diary;


public interface DiaryDAO {
	public int insertDiary(Diary newDiary);
	public int deleteDiary(long diary_id);
	public Diary getDiaryById(long id);
	public long getMaxDiaryId();
	public ArrayList<Diary> getDiaryByUsername(String uname);
}