<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootStrap.jsp"></c:import>
</head>
<body>
<c:import url="../../../temp/header.jsp"></c:import>

	<h2>Member Join</h2>
	
	<div class="container-fluid">
		<div class="row">
			<h3>${message}</h3>
		</div>
		<div class="row">
			<form action="./memberLogin.do" method="post">
				<div class="form-group">
					<label for="id">Id : </label>
					<input type="text" class="form-control" id="id" placeholder="Enter Id" name="id">
				</div>
				<div class="form-group">
					<label for="password">Password : </label>
					<input type="password" class="form-control" id="pw1" placeholder="Enter Password" name="pw">
				</div>
				<div class="form-group">
					<label for="kind">Kind : </label>
					<input type="text" class="form-control" id="kind" placeholder="Enter S or T" name="kind">
				</div>
				
				<button type="submit" class="btn btn-default">Log in</button>
				
			</form>
		</div>
	</div>
	
	
	
<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>