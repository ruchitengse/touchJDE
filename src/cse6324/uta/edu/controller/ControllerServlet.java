package cse6324.uta.edu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cse6324.uta.edu.persistent.DbConnection;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name = "ControllerServlet", urlPatterns = { "*.action" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("logout.action")) {
			doGetLogOutAction(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();

		if (uri.contains("save.action")) {
			doGetSaveAction(request, response);
		}
		if (uri.contains("create.action")) {
			doPostCreateAction(request, response);
		}
		if (uri.contains("insert.action")) {
			doPostInsertAction(request, response);
		}
		if (uri.contains("login.action")) {
			System.out.println("here");
			doPostLoginAction(request, response);
		}
		if (uri.contains("register.action")) {
			doPostRegisterAction(request, response);
		}
	}

	//
	private void doGetSaveAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String saveClsName = request.getParameter("classname");
		System.out.println(saveClsName);
		request.setAttribute("created", saveClsName);
		request.getRequestDispatcher("openedclass.jsp").forward(request,
				response);
		System.out.println("open: " + request.getAttribute("created"));
	}

	private void doPostInsertAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String insertCls = request.getParameter("create");
		String cls = request.getParameter("cls");
		System.out.println("insert: " + insertCls);
		System.out.println("insert: " + cls);
		// DbConnection db = new DbConnection();
		DbConnection.insert(cls, insertCls);
		// request.getRequestDispatcher("newclass.jsp").forward(request,
		// response);
	}

	private void doPostCreateAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String clsName = request.getParameter("clsName");
		String projName = request.getParameter("projName");
		String projSelect = request.getParameter("projSelect");
		String pkgName = request.getParameter("pkgName");
		String pkgSelect = request.getParameter("pkgSelect");
		System.out
				.println(clsName + "|pkgname: " + pkgName + "|pkgS: "
						+ pkgSelect + "|projNam: " + projName + "|projS: "
						+ projSelect);
		if (pkgName != null) {
			request.setAttribute("create", "package " + pkgName + ";\n"
					+ "public class " + clsName + " {");
		} else if (pkgSelect != null) {
			request.setAttribute("create", "package " + pkgSelect + ";\n"
					+ "public class " + clsName + " {");
		}
		request.setAttribute("cls", clsName);
		request.getRequestDispatcher("newclass.jsp").forward(request, response);
		System.out.println("create: " + request.getAttribute("create"));
		// out.print("Here");
	}

	private void doPostLoginAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String username = request.getParameter("lusername");
		String password = request.getParameter("lpassword");
		String error = "Error on login";
		System.out.println(username + password);
		boolean login = DbConnection.login(username, password);
		if (login == true) {
			// Set some attribute values to the session
			// In this case user and password from the request and client
			session.setAttribute("username", username);
			//session.setAttribute("password", password);
			System.out.println("SESS:" + session.getAttribute("username"));
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("errorplace", error);
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}
	}

	private void doPostRegisterAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String error = "Username already exists";
		System.out.println(username + email + password);
		boolean register = DbConnection.register(username, email, password);
		if (register == true) {
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("regerror", error);
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
		}
	}

	//Logout for user
	private void doGetLogOutAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("login.jsp").forward(request,
				response);
	}

}
