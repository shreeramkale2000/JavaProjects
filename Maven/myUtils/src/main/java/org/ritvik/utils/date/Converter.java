package org.ritvik.utils.date;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Converter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getJulianDate(String dt) {

		int sum = 0;
		int date = Integer.parseInt(dt.substring(0, 2));
		//System.out.println("date is" + date);
		int mon = Integer.parseInt(dt.substring(3, 5));
		//System.out.println("Month is" + mon);
		int year = Integer.parseInt(dt.substring(6, 10));
		//System.out.println("Year is" + year);
		int i;
		if (mon <= 6) {

			for (i = 1; i < mon; i++) {
				if (i % 2 == 0) {
					if (i == 2) {
						if (year % 4 == 0) {
							sum = sum + 29;
						} else {
							sum = sum + 28;
						}
					} else {
						sum = sum + 30;
					}
				} else {
					sum = sum + 31;
				}
			}
		} else {
			// int mn = mon;
			for (i = 1; i <= 6; i++) {
				if (i % 2 == 0) {
					if (i == 2) {
						if (year % 4 == 0) {
							sum = sum + 29;
						} else {
							sum = sum + 28;
						}
					} else {
						sum = sum + 30;
					}
				} else {
					sum = sum + 31;
				}
			}
			for (int j = 7; j < mon; j++) {
				if (j == 7) {
					sum = sum + 31;
				} else {
					if (j % 2 == 0) {
						sum = sum + 31;
					} else {
						sum = sum + 30;
					}
				}
			}
		}

		sum = sum + date;
		//System.out.println("Computed No. Of Days are" + sum);
		int year1 = year * 1000;
		int juliandate = year1 + sum;
		//System.out.println("Julian Date is=" + juliandate);

		return juliandate;

	}

	public int getJulianDate(String sDate, String sFormat) throws ParseException{
		int iSumOfDays = 0;
		int iYear = 0;
		int iNoOfDays = 0;
		GregorianCalendar calendar = convertToGregorianCalendar(sDate, sFormat);
		iYear = calendar.get(Calendar.YEAR);
		iNoOfDays = calendar.get(GregorianCalendar.DAY_OF_YEAR);
		
		iSumOfDays = (iYear * 1000) + iNoOfDays;
		
		return iSumOfDays;
	}

	public Calendar convertToCalender(String sDate, String sFormat)
			throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stringToDate(sFormat, sDate));

		return calendar;
	}

	public GregorianCalendar convertToGregorianCalendar(String sDate,
			String sFormat) throws ParseException {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(stringToDate(sFormat, sDate));

		return calendar;
	}

	public GregorianCalendar convertToGregorianCalendar(java.util.Date date) {
		GregorianCalendar calendar = new GregorianCalendar();

		calendar.setTime(date);

		return calendar;
	}
	
	public Calendar convertToCalender(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar;
	}

	public long getTimeInMilliseconds(java.util.Date date) {

		return convertToCalender(date).getTimeInMillis();
	}

	public java.util.Date convertDateType(java.sql.Date sqldate) {
		return new java.util.Date(sqldate.getTime());
	}

	public java.sql.Date convertDateType(java.util.Date utildate) {
		return new java.sql.Date(utildate.getTime());
	}

	public java.sql.Timestamp convertDateType(java.util.Date utildate, int i) {
		return new java.sql.Timestamp(utildate.getTime());
	}

	public java.util.Date stringToDate(String format, String date)
			throws ParseException {
		java.util.Date today = null;

		DateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		today = (java.util.Date) df.parse(date);

		return today;

	}
}
