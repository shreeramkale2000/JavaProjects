package org.ritvik.utils.io;

import java.io.Serializable;

public class FileTypes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Valid formats
	protected static final byte[] XLS = {(byte) 0xD0, (byte) 0xCF,(byte) 0x11, (byte) 0xE0};
	
	public static final String XLS_FORMAT = "XLS";
	
	public static final String CSV_FORMAT = "CSV";
	
	public static final String TXT_FORMAT = "TXT";
	
	//Invalid Formats
	protected static final byte[] JAVA_CLASS = { (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE };
	
	protected static final byte[] PDF = { (byte) 0x25, (byte) 0x50, (byte) 0x44, (byte) 0x46 };
	
	protected static final byte[] JPG = { (byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xe0 };
	
	protected static final byte[] PNG = { (byte) 0x89, (byte) 0x50, (byte) 0x4e, (byte) 0x47 };
	
	protected static final byte[] TIFF = { 0x49, 0x49 };
	
	protected static final byte[] BMP = { 0x42, 0x4d };
	
	protected static final byte[] RAR = { 0x52, 0x61, 0x72, 0x21};
	
	protected static final byte[] ZIP = { 0x50, 0x4b };
	
	
}
