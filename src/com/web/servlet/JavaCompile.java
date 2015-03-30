package com.web.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.properties.ReadProperties;


public class JavaCompile {
	
	private static String javaFileLocation = ReadProperties.getInstance().getValue("FILE_STORAGE_DIRECTORY");
	private static String javaCompilerPath = ReadProperties.getInstance().getValue("JAVA_HOME") + System.getProperty("file.separator") + "bin" + System.getProperty("file.separator") + "javac";
	private static String javaRunPath = ReadProperties.getInstance().getValue("JAVA_HOME") + System.getProperty("file.separator") + "bin" + System.getProperty("file.separator") + "java";

	public static String compileProgram(String javaCode, String pkgName, String className) {
		
		StringBuilder response = new StringBuilder();
		BufferedReader stdInput = null, stdError = null;
		String javaFilePath = javaFileLocation;
		if(!"".equals(pkgName)){
			pkgName = pkgName.replaceAll("\\.", System.getProperty("file.separator"));
			javaFilePath += pkgName;
		}
		String compileClassName = className + ".java";
		File f = new File(javaFilePath);
		boolean dirCreated = false;
		if(!f.exists()){
			dirCreated = f.mkdir();
		} else {
			dirCreated = true;
		}
		if(dirCreated){
			String classPath = javaFilePath + System.getProperty("file.separator") + compileClassName;
			f = new File(classPath);
			try {
				FileWriter fw = new FileWriter(f);
				fw.write(javaCode);
				fw.close();				
				String command = "\"" + javaCompilerPath + "\"" + " " + "\"" + classPath + "\"";
	            Process p = new ProcessBuilder(command).start();
	            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	            String s;
		        while ((s = stdInput.readLine()) != null) {
	                response.append(s);
	                response.append("\n");
	            }
	            while((s = stdError.readLine()) != null){
	                response.append(s);
	                response.append("\n");
	            }
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(stdInput != null){
					try {
						stdInput.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(stdError != null){
					try {
						stdError.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return response.toString();
	}
}
