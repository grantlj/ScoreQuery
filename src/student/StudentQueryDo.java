package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassInfo;
import bean.Student;
import bean.Subject;

import util.GeneralFunc;

public class StudentQueryDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StudentQueryDo() {
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
    Student stub=GeneralFunc.getStudentScore(u,p);
    
    if (stub==null)
    {
    	response.setContentType("text/html");  
        request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");  
        PrintWriter out = response.getWriter();  
  
        out.println("<html>");  
        out.println("<head>");  
        out.println("<title>Servlet upload</title>");
        out.println("<meta http-equiv=\"refresh\" content=3;url=studentquery.jsp");
        out.println("</head>");  
        out.println("<body>");  
        out.println("用户名或密码不正确，请核对！");
        out.println("3秒后自动跳转。。。");
  
        out.println("</body>");  
        out.println("</html>");
    
    
    }
    
    else
    {
    	//valid!
    	ClassInfo cib=GeneralFunc.getClassInfo();
		ArrayList<Subject> subjectList=GeneralFunc.getSubjectList(cib.getSubjectCount());
		request.setAttribute("sublb", subjectList);
		request.setAttribute("cib", cib);
		request.setAttribute("stub", stub);
		
		request.getRequestDispatcher("result.jsp").forward(request, response);
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
