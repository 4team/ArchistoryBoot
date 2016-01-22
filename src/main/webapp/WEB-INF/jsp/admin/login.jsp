<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> = ADMIN LOGIN =</title>
</head>
	<style>
	body{
	background-color: #000000;
	color:#ffffff;
	}
	h2{
	text-align: center;
	}
	#wrapper{
	position:absolute;
	width:100%;
	height:700px;
	}
	
	#contents{
	top:30%;
	position:relative;
	left:50%;
	margin-left:-150px;
	width:300px;
	text-align: center;
	}
	
	#button{
	text-align: center;
	}
	
	form{
	background-color: #444444;
	}
	input{
	margin : 4px;
	}
	</style>
<body>
<div id="wrapper">
<div id="contents">
<form name="form1" method="post" action="/admin/login">
<h2>로그인 </h2>
<table>
    <tr height="40px">
        <td>유저 Email</td>
        <td><input type="text" name="email"></td>
    </tr>
    <tr height="40px">
        <td>패스워드</td>
        <td><input type="password" name="password"></td>
    </tr>
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
</table>
    <div id="button">
     <input type="submit" value="로그인">
     <input type="reset" value="리셋">
    </div>
</form>
</div>
</div>
</body>
</html>