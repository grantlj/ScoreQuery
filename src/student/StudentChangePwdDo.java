package student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.GeneralFunc;

public class StudentChangePwdDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentChangePwdDo() {
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
	 * The doDelete method of the servlet. <br>
	 *
	 * This method is called when a HTTP delete request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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
      String username=(String) request.getParameter("username");
      String orgpwd=(String) request.getParameter("orgpwd");
      String pwd1=(String) request.getParameter("pwd1");
      String pwd2=(String) request.getParameter("pwd2");
      
      if (!GeneralFunc.validStudent(username,orgpwd))
      {
    	  response.setContentType("text/html");  
          request.setCharacterEncoding("utf-8");  
          response.setCharacterEncoding("utf-8");  
          PrintWriter out = response.getWriter();  
          
          out.println("<html>");  
          out.println("<head>");  
          out.println("<title>修改失败</title>");
          out.println("<meta http-equiv=\"refresh\" content=3;url=studentpwd.jsp");
          out.println("</head>");  
          out.println("<body>");  
          out.println("用户名或密码不正确，请核对！");
          out.println("3秒后自动跳转。。。");
    
          out.println("</body>");  
          out.println("</html>");
      }
      
      else 
      {
    	  //Do Change Password.
    	  GeneralFunc.changeStudentPwd(username,orgpwd,pwd1);
    	  

      	  response.setContentType("text/html");  
          request.setCharacterEncoding("utf-8");  
          response.setCharacterEncoding("utf-8");  
          PrintWriter out = response.getWriter();  
    
          out.println("<html>");  
          out.println("<head>");  
          out.println("<title>修改成功</title>");
          out.println("<meta http-equiv=\"refresh\" content=3;url=studentpwd.jsp");
          out.println("</head>");  
          out.println("<body>");  
          out.println("修改密码成功！");
          out.println("3秒后自动跳转。。。");
    
          out.println("</body>");  
          out.println("</html>");
    	  
      }
    	  
      
      //System.out.println(username+" "+orgpwd+" "+pwd1+" "+pwd2);
   }

	/**
	 * The doPut method of the servlet. <br>
	 *
	 * This method is called when a HTTP put request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Put your code here
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
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
