<%@page import="java.util.List"%>
<%@page import="com.cyj.notice.NoticeDAO"%>
<%@page import="com.cyj.notice.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String board = (String)request.getAttribute("board");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Bootstrap Theme Company Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../../../temp/bootStrap.jsp"></jsp:include>
<script src="https://cdn.ckeditor.com/4.10.1/full/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		
		CKEDITOR.replace("contents");
		
		$("#btn").click(function() {
			var title = $("#title").val();
			if(title != '') { //or (title.length==0)
				$("#frm").submit();
			}else {
				alert("Title을 입력하세요");
			}
		});
		
		var count=1;
		var index=0;
		$("#add").click(function() {
			if(count<6){
				var r = '<div class="form-group" id="f'+index+'">';
				r = r+'<label for="file">File:</label>';
				r = r+'<input type="file" class="form-control" id="file" name="f'+index+'1">';
				r = r+'<span clss="remove" title="'+index+'">X</span>';
				r = r+'</div>';
				var n=5;
					$("#file").append(r);
				count++;
				index++;
			}else{
				alert("파일은 5개까지 업로드 가능");
			}
		});
		
		$("#file").on("click", ".remove", function() {
			var t = $(this).attr("title");
			$("#f"+t).remove();
			count--;
		});
		
	});
</script>

</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<jsp:include page="../../../temp/header.jsp"></jsp:include>



<div class="container-fluid">
	<div class="row">
		<form id="frm" action="./${board}Write.do" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="title">Title:</label>
				<input type="text" class="form-control" id="title" placeholder="Enter Title" name="title">
			</div>
			<div class="form-group">
				<label for="writer">Writer:</label>
				<input type="text" value="${member.id}" readonly="readonly" class="form-control" id="writer" placeholder="Enter Writer" name="writer">
			</div>
			<div class="form-group">
				<label for="contents">Contents:</label>
				<textarea rows="25" cols="" class="form-control" name="contents"></textarea>
			</div>
    
 			<!-- <div class="form-group">
				<label for="file">File:</label>
				<input type="file" class="form-control" id="file" name="f1">
			</div> -->
			
			<input type="button" id="add" value="File Add">
			<div class="files" id="file">
				
			</div>
			
    
			<input type="button" id="btn" value="Write" class="btn btn-default">
		</form>
	</div>
</div>



<jsp:include page="../../../temp/footer.jsp"></jsp:include>

</body>
</html>
