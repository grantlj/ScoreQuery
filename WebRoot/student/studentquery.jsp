<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>成绩查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <style>
    #foot
    {
       font-size:15;
       position:absolute;    
       float:right;
       bottom:0px;
    }
    
  
  </style>
  </head>
  
  <body>
     
     <div id="content">
      <center>
      <h2>成绩查询</h2>
      <form action="student/StudentQueryDo" method="post">
                  用户名（默认为学号）：<input type="text" name="username"/><br/>
                 密&nbsp;码（默认为学号）：&nbsp;<input type="password" name="userpwd"/><br/>
      <input type="submit" value="查询">
      <input type="reset" value="重填">
      </form>
      </center>
     </div>
    
    <div id="foot"><a href="student/studentpwd.jsp" target="_blank">修改密码</a></div>
  </body>
</html>
