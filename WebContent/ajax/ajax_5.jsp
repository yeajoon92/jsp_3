<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootStrap.jsp"></c:import>
<script type="text/javascript">
	$(function() {
		$("#btn").click(function() {
			$.ajax({
				url:"../a/memberInfo.do",
				data:{
					id:"id",
					pw:"pw",
					kind:"T"
				},
				type:"GET",
				success:function(data){
					data=data.trim();
					data=JSON.parse(data);
					alert(data.id);
					alert(data.name);
					alert(data.email);
				}
			});
			
		});
		
		
		$("#id").blur(function() {
			var id = $(this).val();
			$.ajax({
				url:"../a/memberCheckId2.do",
				type:"POST",
				data:{
					id:id
				},
				success:function(data){
					data = data.trim();
					data = JSON.parse(data);
					alert(data.result);
					alert(data.m);
				},
				error:function(){
					$("#id").val('');
					alert("error 발생");
				}
				
			});
		});
	});
	
</script>
</head>
<body>
	<input type="text" id="id">
	<button id="btn">CLICK</button>
	<div id="result"></div>
	
</body>
</html>