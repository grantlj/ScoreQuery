<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="bean.ConfigFile" %>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%
     String pri=(String) request.getSession().getAttribute("userPrivilege");
     if (pri==null || (!pri.equals("admin")))
        response.sendRedirect("login.jsp");
     String existed=(String) request.getSession().getAttribute("existed");
     
  %>
  <body>
    <center>
      <h2>管理中心</h2>
      <hr>
      <h3>当前成绩文件</h3>
      
      <%
         if (existed==null || !existed.equals("yes"))
         {
            out.println("<p>当前无成绩录入！请导入EXCEL文件！</p>");
         // enctype="multipart/form-data"
      %>
     
      
    <form enctype="multipart/form-data" method="post" action="admin/ScoreUploadDo">
      <input type="file" name="xlsFile" size="100" maxlength="100"><br>
       <input type = "submit" value = "上传">
       <input type="reset" value="重填">
    </form>
    
    <%
      }
      
      else 
        {
         DecimalFormat gpaFormat = new DecimalFormat("###.##");
             
          ConfigFile cfb=(ConfigFile) request.getAttribute("cfb");
          String chartTitle=cfb.getChartTitle();
	      int stuCount=cfb.getStuCount();
	      int subjectCount=cfb.getSubjectCount();
	      double avgGpa=cfb.getAvgGpa();
	      String date=cfb.getDate();
	       
    %>  
   
    <table border="1">
      <tr>
        <th>成绩单名称</th><th>学生人数</th><th>科目总数</th><th>平均绩点</th><th>录入时间</th><th>操作</th>
      </tr>
      <tr>
        <td><%=chartTitle%></td>
        <td><%=stuCount%></td>
        <td><%=subjectCount%></td>
        <td><%=gpaFormat.format(avgGpa)%></td>
        <td><%=date%></td> 
        <td><a href="admin/ViewDo" target="blank">查看</a>&nbsp;<a href="admin/DeleteDo" target="blank">删除</a></td>
      </tr>
    </table>
    <hr>
      
    <%
       }
     %>
    </center>
   
  </body>
</html>
