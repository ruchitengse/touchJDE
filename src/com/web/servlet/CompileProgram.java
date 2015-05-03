package com.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * This servlet handles the request for compiling and running a program
 */

@WebServlet(name="CompileProgram", urlPatterns={"/CompileProgram"})
public class CompileProgram extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompileProgram() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
     * doGet not used since it is not 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder javaClassCode = new StringBuilder();
		String classCode = request.getParameter("create"); 				//The content present in the text area
		String res = "";
		
		//Check if text area is empty
		if("".equals(classCode.trim())){
			res = "No code found to compile"; 
		} else {
			
			//Append class code to StringBuilder
			javaClassCode.append(classCode);
			String pkgName = "";
			
			//Check if the class is present in a package
			if(javaClassCode.toString().startsWith("package")){
				pkgName = javaClassCode.toString().split(";")[0].replaceAll("package ", "");
			}
			
			/**Call compileAndRunProgram from the JavaCompile class
			 * Get the response of the function back
			 */
			res = JavaCompile.compileAndRunProgram(javaClassCode.toString(), pkgName, request.getParameter("className"));
		}
		
		/**
		 * Convert response to json and print on the web page
		 */
		String json = new Gson().toJson(res);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
