package com.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class CompileProgram
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder javaClassCode = new StringBuilder();
		String classCode = request.getParameter("create");
		javaClassCode.append(classCode);
		String pkgName = "";
		if(javaClassCode.toString().startsWith("package")){
			pkgName = javaClassCode.toString().split(";")[0].replaceAll("package ", "");
		}
		String res = JavaCompile.compileAndRunProgram(javaClassCode.toString(), pkgName, request.getParameter("className"));
		String json = new Gson().toJson(res);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
