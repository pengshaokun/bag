package com.zhskg.bag.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public final class DateUtil 
{
	public static String getStringDate(long times)
	{
		Date dt = new Date(times);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(dt);
	}
	
	public static String timeKey()
	{
		Date dt = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(dt)+ RandomStringUtils.randomNumeric(5);
	}

	public static String getStringSysTime(){
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
		return dateFormat.format(date);
	}

	public static Date getDateSysTime(){
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
		Date date1 =null;
		try {
			date1 = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}

	public static void main(String[] args) {
		getStringSysTime();
		getDateSysTime();
	}
}
