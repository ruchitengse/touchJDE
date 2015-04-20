package com.web.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import com.properties.ReadProperties;


public class JavaCompile {
	
	private static String javaFileLocation = ReadProperties.getInstance().getValue("FILE_STORAGE_DIRECTORY");
	private static String javaCompilerPath = ReadProperties.getInstance().getValue("JAVA_HOME") + System.getProperty("file.separator") + "bin" + System.getProperty("file.separator") + "javac";
	private static String javaRunPath = ReadProperties.getInstance().getValue("JAVA_HOME") + System.getProperty("file.separator") + "bin" + System.getProperty("file.separator") + "java";
	
	
	public static String compileAndRunProgram(String javaCode, String pkgName, String className){
		
		String newPackageName = "";
		if(!"".equals(pkgName)){
			newPackageName = pkgName.replace(".", System.getProperty("file.separator"));
		}
		
		boolean fileCreated = createFile(javaCode, newPackageName, className);
		if(fileCreated){
			String response = compileProgram(javaCode, newPackageName, className);
			if("".equals(response)){
				response = "The code compiled successfully. <br>";
				String runResponse = runProgram(javaCode, pkgName, className);
				if("".equals(runResponse)){
					response += "No output to display. <br>";
				} else {
					response += "\n Output of program: <br>";
					response += runResponse;
				}
			} else {
				response = "The file could not compile <br>" + response;
			}
			return response;
		} else {
			return "An error occured. Could not create file. <br>";
		}
	}
	
	private static boolean createFile(String javaCode, String pkgName, String className){
		
		String javaFilePath = javaFileLocation + pkgName;
		boolean fileCreated = false;
		File f = new File(javaFilePath);
		boolean dirCreated = false;
		if(!f.exists()){
			dirCreated = f.mkdirs();
		} else {
			dirCreated = true;
		}
		if(dirCreated){
			try {
				String filePath = javaFilePath + System.getProperty("file.separator") + className + ".java";
				f = new File(filePath);
				FileWriter fw = new FileWriter(f);
				fw.write(javaCode);
				fw.close();
				fileCreated = true;
			} catch(IOException e){
				e.printStackTrace();
				fileCreated = false;
			}
		}
		return fileCreated;
	}

	private static String runProgram(String javaCode, String pkgName, String className) {
		
		StringBuilder response = new StringBuilder();
		BufferedReader stdInput = null, stdError = null;
		String javaFileCommand = "";
		if(!"".equals(pkgName)){
			javaFileCommand = pkgName + ".";
		}
		javaFileCommand += className;
		try {
			String command = "\"" + javaRunPath + "\"" + " " + "\"" + javaFileCommand + "\"";
			ProcessBuilder p = new ProcessBuilder(command);
			p.directory(new File(javaFileLocation));
            Process proc = p.start();
            stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String s;
	        while ((s = stdInput.readLine()) != null) {
                response.append(s);
                response.append("<br>");
            }
            while((s = stdError.readLine()) != null){
                response.append(s);
                response.append("<br>");
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
		return response.toString();
	}

	public static String compileProgram(String javaCode, String pkgName, String className) {
		
		StringBuilder response = new StringBuilder();
		BufferedReader stdInput = null, stdError = null;
		String classPath = javaFileLocation + pkgName + System.getProperty("file.separator") + className + ".java";
		try {
			String command = "\"" + javaCompilerPath + "\"" + " " + "\"" + classPath + "\"";
            Process p = new ProcessBuilder(command).start();
            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String s;
	        while ((s = stdInput.readLine()) != null) {
                response.append(s);
                response.append("<br>");
            }
            while((s = stdError.readLine()) != null){
                response.append(s);
                response.append("<br>");
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
		return response.toString();
	}
}
