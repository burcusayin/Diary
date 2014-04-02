package com.google.gwt.diary;

import static org.junit.Assert.*;

import com.google.gwt.diary.client.*;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;

import org.junit.Test;

public class CheckDiary {
	
	Diary diary = new Diary();
	final Label label = new Label();
	final static RichTextArea area = new RichTextArea();
	
	public static void main(String [ ] args)
	{
		area.setText("Burcu");
	}
	
	
	
	@Test
	public void test() {
		assertEquals(true, diary.isItEmpty(area, label));
	}

}
