package admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.GeneralFunc;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ScoreUploadDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ScoreUploadDo() {
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
	private String webTempPath=null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try
		{
		response.setContentType("text/plain");  
        request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");  
  
        // file limit size of 5 MB  
        webTempPath=GeneralFunc.getDefaultdir();
        MultipartRequest mpr = new MultipartRequest(request, webTempPath,  
                120 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());  
        // System.out.println("The file length  
        // is::"+mpr.getFile(webTempPath).length());  
        Enumeration params = mpr.getFileNames();  
         
        // mpr.getParameter("name");  
     
        response.setContentType("text/html");  
  
        PrintWriter out = response.getWriter();  
  
        out.println("<html>");  
        out.println("<head>");  
        out.println("<title>Servlet upload</title>");
        out.println("<meta http-equiv=\"refresh\" content=5;url=admin.jsp");
        out.println("</head>");  
        out.println("<body>");  
        for (int i = 1; params.hasMoreElements(); i++) {  
            String src = new String(mpr.getFilesystemName((String) params  
                    .nextElement()));  
            
            GeneralFunc.initialScoreFile(src);
            out.println("成绩文件："+src+" 已经上传！<br>");
            out.println("5秒后自动跳转。。。");
        }  
  
        out.println("</body>");  
        out.println("</html>");
		}
		catch (Exception e)
		{
			response.sendRedirect("admin.jsp");
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
