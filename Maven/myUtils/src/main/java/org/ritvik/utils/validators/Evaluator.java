package org.ritvik.utils.validators;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ritvik.utils.common.Errors;
import org.ritvik.utils.exceptions.BlankStringException;

public class Evaluator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Evaluator() {
		
	}

	public boolean validate(String sValue, String sExpression) {

		Pattern nPattern = null;
		Matcher nMatcher = null;

		try {
			nPattern = Pattern.compile(sExpression);
			nMatcher = nPattern.matcher(sValue);
			return nMatcher.find();
		} finally {
			nPattern = null;
			nMatcher = null;
		}
		
	}

	public String checkString(String sValue) {
		return (sValue == null ? "" : sValue);
	}

	public BigDecimal checkBigDecimal(BigDecimal decimal) {
		return (decimal == null || decimal.equals("") ? new BigDecimal(0)
				: decimal);
	}

	public BigDecimal checkBigDecimal(String sValue) {

		return (checkString(sValue).equals("") ? new BigDecimal(0)
				: new BigDecimal(sValue));
	}

	public int checkInt(String sValue) {
		return (checkString(sValue).equals("") ? 0 : Integer.parseInt(sValue));
	}

	public int checkInt(Integer sValue) {
		return (sValue == null ? 0 : sValue);
	}

	public boolean checkBlankString(String string) throws BlankStringException{
		
		if(string != null){
			
			if(string.length() > 0){
				return true;
			} else {
				throw new BlankStringException(Errors.ISD_00002);
			}
			
		} else {
			throw new NullPointerException(Errors.ISD_00001);
		}
		
	}
	
}
