<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootStrap.jsp"></c:import>
<script type="text/javascript">
	$(function() {
		$("#btn").click(function() {
			//opener.document.getElementById("id").value = '${param.id}';
			opener.document.frm.id.value='${param.id}';
			self.close();
		});
	});
	
	/*	ID 중복확인 issue 해결 방법들
	
		1. opener.document.frm.id.value 와 ${param.id} 가 일치하는지 확인해서
			일치하지 않으면 alert창으로 사용할 수 없는 ID라고 띄우기
		2. opener.document.frm.id.value 와 ${param.id} 가 일치하는지 확인해서
			일치하지 않으면 ID입력창 밑에 message로 사용할 수 없는 ID라고 띄우기
		3. ID입력창에 입력할 수 없게 막아놓고, 무조건 중복확인 버튼 눌러서 memberSearch창에서만
			ID를 입력할 수 있게 하기
	*/
		
		
</script>
</head>
<body>
	<h1>Member Check ID</h1>
	<div>
		<c:if test="${result eq '1'}">
			<h3>${param.id} 사용 가능한 ID입니다.</h3>
			<button id="btn">사용하기</button>
		</c:if>
		<c:if test="${result eq '2'}">
			<h3>${param.id} 사용 불가능한 ID입니다.</h3>
		</c:if>
	</div>
	
	<div>
		<form action="./memberCheckId.do">
			<input type="text" name="id">
			<button>중복확인</button>
		</form>
	</div>
	

	
</body>
</html>