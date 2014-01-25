package test;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class TestUploadDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TestUploadDo() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
   private String webTempPath;  

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
		response.setContentType("text/plain");  
        request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");  
  
        // file limit size of 5 MB  
        webTempPath="D://TMP//";
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
        out.println("</head>");  
        out.println("<body>");  
        for (int i = 1; params.hasMoreElements(); i++) {  
            String src = new String(mpr.getFilesystemName((String) params  
                    .nextElement()));  
            // System.out.println(mpr.getFilesystemName((String)params.nextElement()).length());  
            File file = new File(webTempPath + "/" + src);  
            System.out.println("The file length is::" + file.length());  
            System.out.println(src);  
            out.println("The name of uploaded file " + i + " is: " + src  
                    + "<br><br>");  
        }  
  
        out.println("</body>");  
        out.println("</html>");  

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
