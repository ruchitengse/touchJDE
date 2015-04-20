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
				/*response = runProgram(javaCode, pkgName, className);
				if("".equals(response)){
					response = "No output to display.";
				}*/
				response = "The code compiled successfully";
			} else {
				response = "The file could not compile" + response;
			}
			return response;
		} else {
			return "An error occured. Could not create file.";
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
		String javaFileCommand = pkgName + "." + className;
		String pattern = Pattern.quote(System.getProperty("file.separator"));
		try {
			String driveLetter = javaFileLocation.split(pattern)[0];
			String javaPath = javaFileLocation.split(pattern)[1];
			String openDirectoryCommand = "cmd /c " + driveLetter;
			Process p = new ProcessBuilder(openDirectoryCommand).start();
			openDirectoryCommand = "cd " + javaPath;
			p = new ProcessBuilder(openDirectoryCommand).start();
			String command = "\"" + javaRunPath + "\"" + " " + "\"" + javaFileCommand + "\"";
            p = new ProcessBuilder(command).start();
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
		return response.toString();
	}
}
