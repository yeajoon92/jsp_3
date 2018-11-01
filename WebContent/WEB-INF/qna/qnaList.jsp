<%@page import="com.cyj.page.Pager"%>
<%@page import="com.cyj.board.BoardDAO"%>
<%@page import="com.cyj.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.cyj.page.MakePager"%>
<%@page import="com.cyj.page.RowNumber"%>
<%@page import="com.cyj.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardDTO> ar = (List<BoardDTO>)request.getAttribute("list");
	Pager pager = (Pager)request.getAttribute("pager");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../../temp/bootStrap.jsp" %>
</head>
<body>
<jsp:include page="../../temp/header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
			<h1>Notice</h1>
		</div>
		<div class="row">
			
			
		<div>
			<form class="form-inline" action="./qnaList.do">
				<div class="form-group">
					<select class="form-control" id="sel1" name="kind">
						<option>Title</option>
						<option>Contents</option>
						<option>Writer</option>
					</select>
				    <input type="text" class="form-control" id="search" placeholder="Enter search" name="search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
			
			
			<table class="table table-hover">
				<tr>
					<td>NUM</td>
					<td>TITLE</td>
					<td>WRITER</td>
					<td>DATE</td>
					<td>HIT</td>
				</tr>
				
				<%for(BoardDTO bDTO : ar){ %>
				<tr>
					<td><%=bDTO.getNum() %></td>
					<td><a href="./qnaSelectOne.do?num=<%=bDTO.getNum()%>"><%=bDTO.getTitle() %></a></td>
					<td><%=bDTO.getWriter() %></td>
					<td><%=bDTO.getReg_date() %></td>
					<td><%=bDTO.getHit() %></td>
				</tr>
				<%} %>
			</table>
		
	</div>
</div>



<div class="container-fluid">
	<div class="row">
		<ul class="pagination">
			<li><a href="./qnaList.do?curPage=<%=1%>"><span class="glyphicon glyphicon-backward"></span></a></li>
				<%if(pager.getCurBlock()>1){ %>
					<li><a href="./qnaList.do?curPage=<%=pager.getStartNum()-1%>"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
				<%} %>
			    <%for(int i=pager.getStartNum();i<=pager.getLastNum();i++){ %>
					<li><a href="./qnaList.do?curPage=<%=i%>>"><%=i%></a></li>
			    <%} %>
			    <%if(pager.getCurBlock() != pager.getTotalBlock()){ %>
					<li><a href="./qnaList.do?curPage=<%=pager.getLastNum()+1%>"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
			    <%} %>
			<li><a href="./qnaList.do?curPage=<%=pager.getTotalPage()%>"><span class="glyphicon glyphicon-forward"></span></a></li>
		</ul>
	</div>
</div>
			
		
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-1">
				<a href="./qnaWriteForm.jsp" class="btn btn-primary">WRITE</a>
			</div>
		</div>
	</div>
			
			
			
		</div>
	</div>
	
<jsp:include page="../../temp/footer.jsp"></jsp:include>
</body>
</html>
</head>
<body>
	
</body>
</html>