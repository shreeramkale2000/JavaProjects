package org.ritvik.utils.date;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Formatter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getFormattedDate(String format, long timestamp) {
		return new SimpleDateFormat(format).format(timestamp);
	}

	public String getFormattedDate(String format, java.util.Date date) {
		return getFormattedDate(format, date.getTime());
	}

	public String getFormattedDate(String format, java.sql.Date date) {
		return getFormattedDate(format, date.getTime());
	}
	
}
