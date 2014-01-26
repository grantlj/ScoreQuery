package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.GeneralFunc;
import bean.ClassInfo;
import bean.Student;
import bean.Subject;

public class DeleteDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteDo() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String userPri,loginUser;
		userPri=(String)request.getSession().getAttribute("userPrivilege");
		loginUser=(String) request.getSession().getAttribute("loginUser");
		if ((userPri==null || loginUser==null) || !(userPri.equals("admin")))
			response.sendRedirect("login.jsp");
		else
		{
			if (!GeneralFunc.checkScoreExists())
				response.sendRedirect("admin.jsp");
			else
			{
	           request.getSession().setAttribute("existed", "no");
	           GeneralFunc.deleteAll();
	           response.sendRedirect("admin.jsp");
			    
			}
		
		}
			

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
