<%@page import="java.util.List"%>
<%@page import="com.cyj.file.FileDTO"%>
<%@page import="com.cyj.file.FileDAO"%>
<%@page import="com.cyj.board.BoardDTO"%>
<%@page import="com.cyj.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDTO bDTO = (BoardDTO)request.getAttribute("dto");
	List<FileDTO> ar = (List<FileDTO>)request.getAttribute("files");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../../temp/bootStrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../../temp/header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
			<h1>TITLE : <%=bDTO.getTitle() %></h1>
			<h1>WRITER : <%=bDTO.getWriter() %></h1>
			<h1>CONTENTS : <%=bDTO.getContents() %></h1>
			<%for(FileDTO fileDTO : ar){ %>
				<h3><a href="../upload/<%=fileDTO.getfName()%>"><%=fileDTO.getoName() %></a></h3>
				<!-- 꺼내올때는 프로젝트 내의 경로를 따라가면 됨 -->
			<%} %>
		</div>
	</div>
	
	<div>
		<a href="./noticeList.do">List로 돌아가기</a>
		<a href="./noticeUpdateForm.do">Update</a>
		<a href="./noticeDelete.do">Delete</a>
	</div>
	
<jsp:include page="../../temp/footer.jsp"></jsp:include>
</body>
</html>