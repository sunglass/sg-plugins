package com.sg.utils;

import java.io.*;
import java.awt.FileDialog;
import java.awt.Frame;

public class FileIO {

	public static Boolean VERBOSE = false;

	/**
	 * 
	 * 
	 */
	public static File saveFile(final String prompt) {
		Frame parent = new Frame();
		FileDialog fileDialog = new FileDialog(parent, prompt, FileDialog.SAVE);
		fileDialog.setVisible(true);
		String directory = fileDialog.getDirectory();
		String filename = fileDialog.getFile();
		return (filename == null) ? null : new File(directory, filename);
	}

	/**
	 * 
	 * 
	 */
	public static File selectFile(final String prompt) {
		Frame parent = new Frame();
		FileDialog fileDialog = new FileDialog(parent, prompt, FileDialog.LOAD);
		fileDialog.setVisible(true);
		String directory = fileDialog.getDirectory();
		String filename = fileDialog.getFile();
		return (filename == null) ? null : new File(directory, filename);
	}

	/**
	 * 
	 * 
	 */
	public static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 * 
		 * (c) public domain:
		 * http://senior.ceng.metu.edu.tr/2009/praeda/2009/01/
		 * 11/a-simple-restful-client-at-android/
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
				if (VERBOSE) {
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * OBJ Loader
	 * 
	 */
	public static File convertStreamToFile(InputStream is, File f) {
		try {
			OutputStream out = new FileOutputStream(f);
			byte buf[] = new byte[1024];
			int len;

			while ((len = is.read(buf)) > 0)
				out.write(buf, 0, len);
			out.close();
			return f;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				System.out.println("[FileIO/ConvertStreamToFile] --Stream written to " + f.getPath() + " ("+ f.length() +")"); 
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}