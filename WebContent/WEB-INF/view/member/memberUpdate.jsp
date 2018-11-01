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
			<form action="./memberUpdate.do" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="id">Id : </label>
					<input type="text" value="${member.id}" readonly="readonly" class="form-control" id="id" placeholder="Enter Id" name="id">
				</div>
				<div class="form-group">
					<label for="password">Password : </label>
					<input type="password" value="${member.pw}" class="form-control" id="pw1" placeholder="Enter Password">
				</div>
 				<div class="form-group">
					<label for="password">Confirm Password : </label>
					<input type="password" class="form-control" id="pw2" placeholder="Confirm Password" name="pw">
				</div>
				<div class="form-group">
					<label for="name">Name : </label>
					<input type="text" value="${member.name}" class="form-control" id="name" placeholder="Enter Name" name="name">
				</div>
				<div class="form-group">
					<label for="email">Email : </label>
					<input type="email" value="${member.email}" class="form-control" id="email" placeholder="Enter Email" name="email">
				</div>
				<div class="form-group">
					<label for="photo">Photo : </label>
					<input type="file" class="form-control" id="photo" name="f1">
				</div>
				
				<button type="submit" class="btn btn-default">Update</button>
				
			</form>
		</div>
	</div>
	
	
	
<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>