<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../../../temp/bootStrap.jsp"></c:import>
<title>Insert title here</title>
</head>
<body>
<c:import url="../../../temp/header.jsp"></c:import>

	<div class="container-fluid">
		<div class="row">
			<h1>ID : ${member.id}</h1>
			<h1>Name : ${member.name}</h1>
			<h1>Email : ${member.email}</h1>
			<img src="../upload/${member.fName}">
		</div>
		
		<div class="row">
			<a href="./memberUpdate.do">Update</a>
			<a href="memberDelete.do">Delete</a> <!-- ./ 안 쓰면 현재위치라는 뜻 -->
		</div>
		
	</div>


<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>