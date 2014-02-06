<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="修改密码">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script>
    function checker(arg0,arg1,arg2,arg3)
    {
       //window.alert(arg0+" "+arg1+" "+arg2+" "+arg3);
       if (arg0=="") 
        {
          window.alert("用户名不能为空！");
          return false;
        }
       
       if (arg1=="")
         {
           window.alert("原密码不能为空！");
           return false;
         }
    
      if (arg2!=arg3)
      {
        window.alert("两次输入的新密码不相同！");
        return false;
      }
      
      return confirm("确认修改密码？");
        
    }
  
  </script>
  </head>
  
  <body>
  <center>
    <h1>修改密码</h1>
    <hr/>
    <form id="form1" action="student/StudentChangePwdDo" method="post" name="mainf" onSubmit="return checker(mainf.username.value,mainf.orgpwd.value,mainf.pwd1.value,mainf.pwd2.value)">
                用户名：<input type="text" name="username"><br/>
               原密码：<input type="password" name="orgpwd"><br/>
               新密码：<input type="password" name="pwd1"><br/>
     &nbsp; 确认：<input type="password" name="pwd2"><br/>          
    <input type="submit" value="修改密码" >
    <input type="reset" value="重填">
    </form>
   </center>
  </body>
</html>
