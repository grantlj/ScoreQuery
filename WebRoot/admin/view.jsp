<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>成绩单查看</title>
    
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
       
       DecimalFormat subPFormat = new DecimalFormat("#.#");
       DecimalFormat gpaFormat = new DecimalFormat("###.##");
       
       ClassInfo cib=(ClassInfo) request.getAttribute("cib");
       String chartTitle=cib.getChartTitle();
	   int stuCount=cib.getStudentCount();
	   int subjectCount=cib.getSubjectCount();
	   double avgGpa=cib.getAvgGpa();
	   
	   ArrayList<Subject> sublb=(ArrayList<Subject>) request.getAttribute("sublb");
	   ArrayList<Student> stulb=(ArrayList<Student>) request.getAttribute("stulb");
	   
   %>
  
  <body>
     <center>
       <!-- 成绩单抬头 -->
       <h2>成绩单查看</h2>
       <hr>
     <table border="1">
      <tr>
       <th>成绩单名称</th><th>学生人数</th><th>科目总数</th><th>平均绩点</th>
      </tr>
      <tr>
        <td><%=chartTitle%></td>
        <td><%=stuCount%></td>
        <td><%=subjectCount%></td>
        <td><%=gpaFormat.format(avgGpa)%></td>
      </tr>
    </table>
    <hr>
    
    <table border="3">
    <!-- 分数表抬头 -->
    <tr>
    <th rowspan="2">姓名</th>
    <th rowspan="2">学号</th>
    <%
      for (int i=0;i<subjectCount;i++)
        out.println("<th align=\"center\">"+sublb.get(i).getSubjectName()+"</th>");
     %>
    <th rowspan="2">GPA</th>
    </tr>
    
    <tr>

    <%
      for (int i=0;i<subjectCount;i++)
        out.println("<td align=\"center\">"+subPFormat.format(sublb.get(i).getSubjectPoint())+"</td>");
    %>
    
    </tr>
    <!-- 分数表抬头结束 ,成绩单开始-->
    
    <% 
    for (int i=0;i<stuCount;i++)
    {
      Student ts=stulb.get(i);
      String name=ts.getName();
      String id=ts.getStuId();
      String[] args=ts.getScoreList().split(",");
      double gpa=ts.getGpa();
      
      out.println("<tr>");
      out.println("<td align=\"center\">"+name+"</td>"+"<td align=\"center\">"+id+"</td>");
      
      for (int j=0;j<subjectCount;j++)
      {
        String t;
        if (!(args[j].equals("32767") || args[j].equals("-32767")))
          t=args[j];
        else if (args[j].equals("32767"))
          t="-";
          else
          t="成绩取消";
        
        out.println("<td align=\"center\">"+t+"</td>");   
      }
      
      out.println("<td align=\"center\">"+subPFormat.format(gpa)+"</td>");
      out.println("</tr>");
    %>
    
    
    
    <%
      };
     %>
     </table>
         <a href="javascript:window.print();">打印</a>
     </center>
  </body> 
</html>
