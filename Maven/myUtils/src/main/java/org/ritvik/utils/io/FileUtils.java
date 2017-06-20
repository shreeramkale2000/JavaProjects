package org.ritvik.utils.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.MagicNumberFileFilter;
import org.apache.log4j.Logger;

import org.ritvik.utils.common.Constants;
import org.ritvik.utils.exceptions.InvalidFileFormatException;
import org.ritvik.utils.validators.Evaluator;

public class FileUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Evaluator eval = new Evaluator();
	/**
	 * 
	 */
	private String sExpression = "(select\\s+\\*\\s+from)|(drop table)|alert\\(|truncate table|(update\\s+)\\w(set\\s+)*";

	private String sFileFormat;

	private final Logger logger = Logger.getLogger(getClass());

	public FileUtils() {

	}

	public boolean checkFileFormat(String file, String format)
			throws IOException, InvalidFileFormatException {

		this.sFileFormat = format;

		return FormatChecker(new File(file));
	}

	public boolean checkFileFormat(File file, String format) throws IOException, InvalidFileFormatException {

		this.sFileFormat = format;

		return FormatChecker(file);
	}

	public boolean checkFileFormat(String file) throws IOException, InvalidFileFormatException {

		return FormatChecker(new File(file));
	}

	public boolean checkFileFormat(File file) throws IOException, InvalidFileFormatException {

		return FormatChecker(file);
	}

	public boolean FormatChecker(File file) throws IOException, InvalidFileFormatException {

		boolean check = false;
		String fileName = file.getName();
		String ext;
		MagicNumberFileFilter magic = null;

		// System.out.println("fileName = " + fileName);

		if (fileName.contains(".")) {
			ext = fileName.substring(fileName.lastIndexOf(".") + 1,
					fileName.length());

			// System.out.println("ext = " + ext);

			if (this.sFileFormat != null) {

				if(this.sFileFormat.equalsIgnoreCase(FileTypes.XLS_FORMAT)
						|| this.sFileFormat.equalsIgnoreCase(FileTypes.CSV_FORMAT)
						|| this.sFileFormat.equalsIgnoreCase(FileTypes.TXT_FORMAT)){
					
					if(!ext.equalsIgnoreCase(this.sFileFormat)){
						throw new InvalidFileFormatException("The extension of the file uploaded and extension provided do not match.");
					} 
					
				} else {
					throw new InvalidFileFormatException("Only xls, csv, txt files can be uploaded.");					
				}
				
			} else {

				if (ext.equalsIgnoreCase(FileTypes.XLS_FORMAT)
						|| ext.equalsIgnoreCase(FileTypes.CSV_FORMAT)
						|| ext.equalsIgnoreCase(FileTypes.TXT_FORMAT)) {

				} else {
					throw new InvalidFileFormatException("Only xls, csv, txt files can be uploaded.");
				}
			}
		} else {
			throw new InvalidFileFormatException("Only xls, csv, txt files can be uploaded. Please make sure you have provided a file with proper extension");
		}

		if (sFileFormat == null
				|| sFileFormat.equalsIgnoreCase(FileTypes.XLS_FORMAT)) {

			magic = new MagicNumberFileFilter(FileTypes.XLS);

			check = magic.accept(file);

			magic = null;

		} else {
			check = false;
		}
		// System.out.println("check 1 = " + check);

		if (sFileFormat == null
				|| sFileFormat.equalsIgnoreCase(FileTypes.CSV_FORMAT)
				|| sFileFormat.equalsIgnoreCase(FileTypes.TXT_FORMAT)) {

			if (!check) {
				check = isAsciiText(file);

				if (check) {

					String line;
					BufferedReader bfr = new BufferedReader(
							new FileReader(file));

					try {

						while ((line = bfr.readLine()) != null) {

							check = eval.validate(line, sExpression);
							// System.out.println(check + " > check 10 = " +
							// line);
							if (check) {
								check = false;
								break;
							} else {
								check = true;
							}
						}
					} finally {
						if (bfr != null) {
							bfr.close();
							bfr = null;
						}
					}

				} else {
					throw new InvalidFileFormatException("The uploaded file is not a csv or a txt file");
				}

				// System.out.println("check 2 = " + check);
			}
		}

		if(!check){
			throw new InvalidFileFormatException("The file you are trying to upload contains restricted phrases such as select * from, drop table, truncate table, update set. Please make necessary changes");
		}
		
		// System.out.println("check 3 = " + check);

		if (file != null) {
			file = null;
		}

		return check;
	}

	private boolean isAsciiText(File file) throws IOException {

		byte[] bytes = new byte[Integer.parseInt(String.valueOf(file.length()))];

		BufferedInputStream stream = new BufferedInputStream(
				new FileInputStream(file));

		stream.read(bytes, 0, bytes.length);

		for (byte thisByte : bytes) {
			
			if (thisByte >= 32 && thisByte < 127) {
			} else {
				if (!(thisByte == 13 || thisByte == 10 || thisByte == 9
						|| thisByte == 0 || thisByte == -42)) {
					return Constants.FAILED;
				}
			}
		}
		
		if (stream != null) {
			stream.close();
			stream = null;
		}
		return Constants.SUCCESS;

	}

	public boolean isFileExists(String file) {

		return isFileExists(new File(file));
	}

	public boolean isFileExists(File file) {

		try {
			if (file.exists()) {
				if (file.isFile()) {
					return Constants.SUCCESS;
				} else {
					return Constants.FAILED;
				}
			} else {
				return Constants.FAILED;
			}
		} finally {
			file = null;
		}
	}

	public boolean isFolderExists(String folder) {

		return isFolderExists(new File(folder));

	}

	public boolean isFolderExists(File folder) {

		if (folder.exists()) {
			if (folder.isDirectory()) {
				return Constants.SUCCESS;
			} else {
				return Constants.FAILED;
			}
		} else {
			return Constants.FAILED;
		}

	}

	public boolean createFolder(String folder) {

		return createFolder(new File(folder));
	}

	public boolean createFolder(File folder) {

		if (!isFolderExists(folder)) {
			if (folder.mkdirs()) {
				return Constants.SUCCESS;
			} else {
				return Constants.FAILED;
			}
		} else {
			return Constants.SUCCESS;
		}
	}

	public boolean moveFile(String source, String destination) {

		return moveFile(new File(source), new File(destination));
	}

	public boolean moveFile(File source, File destination) {

		if (isFileExists(source) && !isFileExists(destination)) {
			if (source.renameTo(destination)) {
				return Constants.SUCCESS;
			} else {
				return Constants.FAILED;
			}
		} else {
			return Constants.FAILED;
		}
	}

	public boolean deleteFile(String file) {

		return deleteFile(new File(file));
	}

	public boolean deleteFile(File file) {

		if (isFileExists(file)) {
			if (file.delete()) {
				return Constants.SUCCESS;
			} else {
				return Constants.FAILED;
			}
		} else {
			return Constants.FAILED;
		}
	}

	public boolean deleteFolder(String folder) {

		return deleteFolder(new File(folder));
	}

	public boolean deleteFolder(File folder) {

		if (isFolderExists(folder)) {
			if (folder.delete()) {
				return Constants.SUCCESS;
			} else {
				return Constants.FAILED;
			}
		} else {
			return Constants.FAILED;
		}
	}

	public boolean deleteFilesFromFolder(List<String> folders,
			boolean deleteFilesFromSubFolders) {

		File file = null;
		String[] list = null;
		List<String> subFolders = null;
		StringBuffer sbr = null;
		String temp;

		if (folders != null && folders.size() > 0) {
			sbr = new StringBuffer();

			if (deleteFilesFromSubFolders) {
				subFolders = new ArrayList<String>();
			}

			logger.debug("Directories to search " + folders);

			for (String folder : folders) {

				logger.debug("checking folder [" + folder + "]");

				file = new File(folder);

				if (isFolderExists(file)) {

					logger.debug("folder [" + folder + "] exists");

					list = doFolderListing(file);

					logger.debug("contents of folder [" + folder + "]");

					for (String item : list) {
						temp = sbr.append(folder).append(item).toString();
						sbr.delete(0, sbr.length());

						if (!isFileExists(temp)) {

							if (deleteFilesFromSubFolders) {
								if (temp.contains("\\")) {
									temp = (temp.endsWith("\\") ? temp : temp
											.concat("\\"));
								} else {
									temp = (temp.endsWith("/") ? temp : temp
											.concat("/"));
								}
								logger.debug("Folder " + temp);
								subFolders.add(temp);
							}

						} else {
							logger.debug("File " + temp);
							deleteFile(temp);
						}
						temp = null;
					}
					list = null;

				}
				file = null;
			}

			if (subFolders != null && subFolders.size() > 0
					&& deleteFilesFromSubFolders) {
				deleteFilesFromFolder(subFolders, deleteFilesFromSubFolders);
			}
		}

		return true;
	}

	public String[] doFolderListing(File folder) {

		return folder.list();

	}
}
