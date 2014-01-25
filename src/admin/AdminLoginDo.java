package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import util.*;

public class AdminLoginDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminLoginDo() {
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
       String u,p;
       u=(String) request.getParameter("username");
       p=(String) request.getParameter("userpwd");
       
       try {
		if (GeneralFunc.validAdmin(u,p))
		   {
			  request.getSession().setAttribute("userPrivilege", "admin");
			  request.getSession().setAttribute("loginUser", u);
			  
			  if (!GeneralFunc.checkScoreExists())
				  request.getSession().setAttribute("existed","no");
			      
				  
			  else
			  {
				  request.getSession().setAttribute("existed","yes");
				  //Set beans.
				  
			  }
			  response.sendRedirect("admin.jsp");
		   }
		   
		   else
			 response.sendRedirect("login.jsp");
		   
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
