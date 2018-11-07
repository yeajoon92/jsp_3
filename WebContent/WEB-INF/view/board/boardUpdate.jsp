<%@page import="java.util.List"%>
<%@page import="com.cyj.notice.NoticeDAO"%>
<%@page import="com.cyj.notice.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Bootstrap Theme Company Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="../../../temp/bootStrap.jsp" />

<script type="text/javascript">
	$(function() {
		$(".del").click(function() {
			// /file/fileDelete.do
			var fNum = $(this).attr("id");
			var fName = $(this).attr("title");
			//alert(fNum); 확인해보기
			$.post("../file/fileDelete.do", {fNum:fNum, fName:fName}, function() {
				data = data.trim();
				if(data=='1'){
					alert("Success");
					$("#"+fNum).parent().remove();
					//$("#p"+fNum).remove();
				}else{
					aler("Fail");
				}
			});
		});
		
		
		
		
	});
	
	
</script>

</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<c:import url="../../../temp/header.jsp" />


<div class="container-fluid">
	<div class="row"></div>
		<h1>${board} Update</h1>
	<div class="row">
		<form action="./${board}Update.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="num" value="${dto.num}">
			<div class="form-group">
				<label for="title">Title:</label>
				<input type="text" value="${dto.title}" class="form-control" id="title" placeholder="Enter Title" name="title">
			</div>
			<div class="form-group">
				<label for="writer">Writer:</label>
				<input type="text" class="form-control" disabled="disabled" value="${dto.writer}" id="writer" placeholder="Enter Writer" name="writer">
			</div>
			<div class="form-group">
				<label for="contents">Contents:</label>
				<textarea rows="25" cols="" class="form-control" name="contents">${dto.contents}</textarea>
			</div>

			<c:forEach items="${files}" var="file" varStatus="i">
				<div class="form-group" id="p${file.fNum}">
					<%-- <label for="file">File:</label>
					<input type="file" value="${file.oName}" class="form-control" id="file" name="f${i.count}"> --%>
					<span>${file.oName}</span>
					<span class="del" id="${file.fNum} title="${file.fName}">X</span>
				</div>
			</c:forEach>

    
			<button type="submit" class="btn btn-default">Write</button>
		</form>
	</div>
</div>



<c:import url="../../../temp/footer.jsp" />
</body>
</html>
