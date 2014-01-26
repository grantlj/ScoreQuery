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
import bean.ConfigFile;
import bean.Student;
import bean.Subject;

public class ViewDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ViewDo() {
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
		//request.getSession().setAttribute("userPrivilege", "admin");
	    //request.getSession().setAttribute("loginUser", u);
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
				ClassInfo cib=GeneralFunc.getClassInfo();
				ArrayList<Subject> subjectList=GeneralFunc.getSubjectList(cib.getSubjectCount());
				ArrayList<Student> studentList=GeneralFunc.getStudentList(cib.getStudentCount());
				request.setAttribute("cib",cib);
				request.setAttribute("sublb", subjectList);
				request.setAttribute("stulb", studentList);
				request.getRequestDispatcher("view.jsp").forward(request, response);
			    
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
