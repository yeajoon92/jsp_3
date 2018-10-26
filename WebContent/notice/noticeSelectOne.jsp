<%@page import="java.util.List"%>
<%@page import="com.cyj.file.FileDTO"%>
<%@page import="com.cyj.file.FileDAO"%>
<%@page import="com.cyj.board.BoardDTO"%>
<%@page import="com.cyj.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	int num = Integer.parseInt(request.getParameter("num")); //this (int)num
	NoticeDAO nDAO = new NoticeDAO();
	BoardDTO bDTO = nDAO.selectOne(num);
	FileDAO fDAO = new FileDAO();
	FileDTO fDTO = new FileDTO();
	fDTO.setNum(num); //comes here
	fDTO.setKind("N");
	fDAO.selectList(fDTO);
	List<FileDTO> ar = fDAO.selectList(fDTO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../temp/bootStrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../temp/header.jsp"></jsp:include>
	
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
		<a href="./noticeList.jsp">List로 돌아가기</a>
		<a href="./noticeUpdateForm.jsp">Update</a>
		<a href="./noticeDelete.jsp">Delete</a>
	</div>
	
<jsp:include page="../temp/footer.jsp"></jsp:include>
</body>
</html>