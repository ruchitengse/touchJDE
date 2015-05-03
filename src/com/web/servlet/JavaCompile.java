package com.web.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import com.properties.ReadProperties;


public class JavaCompile {
	
	//Properties needed from touchjde.properties for compiling and running
	private static String javaFileLocation = ReadProperties.getInstance().getValue("FILE_STORAGE_DIRECTORY");
	private static String javaCompilerPath = ReadProperties.getInstance().getValue("JAVA_HOME") + System.getProperty("file.separator") + "bin" + System.getProperty("file.separator") + "javac";
	private static String javaRunPath = ReadProperties.getInstance().getValue("JAVA_HOME") + System.getProperty("file.separator") + "bin" + System.getProperty("file.separator") + "java";
	
	
	/**
	 * Function which creates a java class file, compiles the code, and runs it with the help of standard java commands javac and java
	 * @param javaCode: The java code written in text area
	 * @param pkgName: The package where the java code is to be stored
	 * @param className: The name of  the class
	 * @return The response (Output of running the program/Compile error/Exception while running)
	 */
	public static String compileAndRunProgram(String javaCode, String pkgName, String className){
		
		String newPackageName = "";
		if(!"".equals(pkgName)){
			newPackageName = pkgName.replace(".", System.getProperty("file.separator"));
		}
		
		//Call createFile function, if function returns true file is created, else file is not created
		boolean fileCreated = createFile(javaCode, newPackageName, className);
		
		if(fileCreated){
			//If file is created, compile the java code
			String response = compileProgram(javaCode, newPackageName, className);
			
			//Compile program returns empty string file compiled successfully
			if("".equals(response)){
				//If file compiled successfully, add "Success" to response
				response = "The code compiled successfully. <br>";
				
				//Run the file now to get the output
				String runResponse = runProgram(javaCode, pkgName, className);
				
				//If the file which is run has no output to display, add the appropriate response to "response" string, else append the output
				if("".equals(runResponse)){
					response += "No output to display. <br>";
				} else {
					response += "\n Output of program: <br>";
					response += runResponse;
				}
			} else {
				//If the file could not compile, specify appropriate message
				response = "The file could not compile <br>" + response;
			}
			
			//return the response back to servlet
			return response;
		} else {
			return "An error occured. Could not create file. Check if the file already exists<br>";
		}
	}
	
	/**
	 * Function to create the specified java file
	 * @param javaCode: The java code to be written to file
	 * @param pkgName: The package i.e directories to be created for a file
	 * @param className: The name the file
	 * @return
	 */
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
				if(f.exists()){
					fileCreated = false;
				} else {
					FileWriter fw = new FileWriter(f);
					fw.write(javaCode);
					fw.close();
					fileCreated = true;
				}
			} catch(IOException e){
				e.printStackTrace();
				fileCreated = false;
			}
		}
		return fileCreated;
	}

	/**
	 * Run the program with the "java <CLASSNAME>" command
	 * @param javaCode: The java code to be written
	 * @param pkgName: The package name
	 * @param className: The classname of the code
	 * @return The output of run program, empty if no output
	 */
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
			
			//Use processbuilder to run "java command", it emulates the dos command prompt
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

	/**
	 * Run the program with the "java <CLASSNAME>" command
	 * @param javaCode: The java code to be written
	 * @param pkgName: The package name
	 * @param className: The classname of the code
	 * @return The output of compile program, empty if no error, else return the error
	 */
	public static String compileProgram(String javaCode, String pkgName, String className) {
		
		StringBuilder response = new StringBuilder();
		BufferedReader stdInput = null, stdError = null;
		String classPath = javaFileLocation + pkgName + System.getProperty("file.separator") + className + ".java";
		try {
			String command = "\"" + javaCompilerPath + "\"" + " " + "\"" + classPath + "\"";
			
			//Use processbuilder to run "java command", it emulates the dos command prompt
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
