package com.test.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDateFormat {

	public static void main(String[] args) {
//		2017-06-29T01:47:45.555930444Z
		String s = "2017-06-29T01:47:45.555930444Z";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		try {
			Date date = df.parse(s);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			System.out.println(calendar.get(Calendar.MONTH));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
