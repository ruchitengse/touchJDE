package cse6324.uta.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name="ControllerServlet", urlPatterns={"*.action"})
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if(uri.contains("save.action")) {
			doGetSaveAction(request, response);
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if(uri.contains("create.action")) {
			doPostCreateAction(request, response);
		}
		if(uri.contains("insert.action")) {
			doPostInsertAction(request, response);
		}
	}
	
	private void doGetSaveAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String saveClsName = request.getParameter("classname"); 
		System.out.println(saveClsName);
		request.setAttribute("created", saveClsName);
		request.getRequestDispatcher("openedclass.jsp").forward(request,
				response);
		System.out.println("open: "+request.getAttribute("created"));
	}

	private void doPostInsertAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String insertCls = request.getParameter("create"); 
		String cls = request.getParameter("cls");
		System.out.println("insert: "+insertCls);
		System.out.println("insert: "+cls);
		DbConnection db = new DbConnection();
		db.insert(cls, insertCls);
		RequestDispatcher rd = request.getRequestDispatcher("newclass.jsp");
		rd.forward(request, response);
	}
	
	private void doPostCreateAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String clsName = request.getParameter("clsName");
		String pkgName = request.getParameter("pkgName");
		System.out.println(clsName+pkgName);
		request.setAttribute("create", "package "+pkgName+";\n"+"public class "+clsName+" {");
		request.setAttribute("cls", clsName);
		request.getRequestDispatcher("newclass.jsp").forward(request,
				response);
		System.out.println("create: "+request.getAttribute("create"));
		//out.print("Here");
	}

}
