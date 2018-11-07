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
		var curPage=1;
		
		$("del").click(function() {
			
			var p="?";
			
			$(".del").each(function() {
				if($(this).prop("checked")){
					p=p+"num="+$(this).val()+"&"; //다 한꺼번에 붙여서 한꺼번에 삭제하기위해 씀
					// $.get("./memoDelete.do?num="+$(this).val()); 이거 쓰면 한개 보내고 삭제하고를 반복
				}
			});
			$.get("./memoDelete.do"+p, function() {
				$.get("./memoMore.do", function(data) { //delete 성공했을 때 실행
					$("#result").html(data.trim()); //ajax로 받아오는건 공백이 있을 수 있기 때문에 trim으로 공백 제거
				});				
			});
		});
		
		$("#more").click(function() {
			curPage++;
			$.get("./memoMore.do?curPage="+curPage, function(data) {
				$("#result").append(data.trim());
			});
		});
		
		$(writer).click(function() {
			var writer=$("#writer").val();
			var contents=$("$contents").val();
			$.post("./memoWrite.do",{writer:writer,contents:contents}, function(data) {
				$.get("./memoMore.do", function(data) {
					$("#result").html(data.trim());
				});
			});
		});
	});
</script>
</head>
<body>
<c:import url="../../../temp/header.jsp"></c:import>

<div class="container-fluid">
	<div class="row">
		<div class="form-group">
			<label for="writer">Writer:</label>
			<input type="text" class="form-control" id="writer" placeholder="Enter Writer" name="writer">
		</div>
		<div class="form-group">
			<label for="contents">Contents:</label>
			<textarea rows="25" cols="" class="form-control" id="contents" name="contents"></textarea>
		</div>
		<input type="button" id="write" value="Write">
	
	</div>
			
	<div class="row">
		<table class="table table-hover">
			<tr>
				<td></td>
				<td>NUM</td>
				<td>CONTENTS</td>
				<td>WRITER</td>
				<td>DATE</td>
			</tr>
			<tbody id="result">
				<c:forEach items="${list}" var="m">
					<tr>
						<td><input type="checkbox" name="del" class="del" value="${m.num}" id="${m.num}"></td>
						<td>${m.num}</td>
						<td>${m.contents}</td>
						<td>${m.writer}</td>
						<td>${m.reg_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<button id="more">더보기</button>
			
	</div>
	
	<div class="row">
		<button id="del">DEL</button>
	</div>
	
</div>

<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>