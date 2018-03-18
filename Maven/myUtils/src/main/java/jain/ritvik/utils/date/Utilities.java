package jain.ritvik.utils.date;

import java.io.Serializable;
import java.util.Date;

public class Utilities implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Formatter formatter = null;
	Converter converter = null;

	public Utilities() {

	}

	public long getDifferenceInDates(Date fromDate, Date toDate, String sDiffIn) {

		long min = 0;
		try {
			converter = new Converter();
			long milliseconds1 = converter.getTimeInMilliseconds(fromDate);
			long milliseconds2 = converter.getTimeInMilliseconds(toDate);
			long diff = milliseconds2 - milliseconds1;

			if (sDiffIn.equalsIgnoreCase("M")) {
				min = diff / (60 * 1000);
			} else if (sDiffIn.equalsIgnoreCase("H")) {
				min = diff / (60 * 60 * 1000);
			} else if (sDiffIn.equalsIgnoreCase("D")) {
				min = diff / (24 * 60 * 60 * 1000);
			}
		} finally {
			converter = null;
		}
		return min;
	}

	public boolean isDateAfter(java.util.Date check, java.util.Date against) {
		return check.after(against);
	}

	public boolean isDateBefore(java.util.Date check, java.util.Date against) {
		return check.before(against);
	}

	public String getDatePlusMinusDays(String format, java.util.Date date,
			int iDays) {

		String sValue = null;
		try {
		formatter = new Formatter();
		int iDaysInMillis = Math.abs(iDays * (1000 * 24 * 60 * 60));
		sValue = formatter.getFormattedDate(format, (iDays > 0 ? date.getTime()
				+ iDaysInMillis : date.getTime() - iDaysInMillis)); 
		} finally {
			formatter = null;
		}
		return sValue;
	}

	public String getDatePlusMinusDays(String format, java.sql.Date date,
			int iDays) {

		String sValue = null;
		try {
			formatter = new Formatter();
			int iDaysInMillis = Math.abs(iDays * (1000 * 24 * 60 * 60));
			sValue = formatter.getFormattedDate(format, (iDays > 0 ? date
					.getTime()
					+ iDaysInMillis : date.getTime() - iDaysInMillis));
		} finally {
			formatter = null;
		}
		return sValue;
	}

	public java.util.Date getDatePlusMinusDays(java.util.Date date, int iDays) {

		int iDaysInMillis = Math.abs(iDays * (1000 * 24 * 60 * 60));

		return new java.util.Date((iDays > 0 ? date.getTime() + iDaysInMillis
				: date.getTime() - iDaysInMillis));
	}

	public java.sql.Date getDatePlusMinusDays(java.sql.Date date, int iDays) {

		int iDaysInMillis = Math.abs(iDays * (1000 * 24 * 60 * 60));

		return new java.sql.Date((iDays > 0 ? date.getTime() + iDaysInMillis
				: date.getTime() - iDaysInMillis));
	}

}
