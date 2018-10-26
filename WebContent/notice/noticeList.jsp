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
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	int curPage=1; //Exception 발생할 수 있기 때문에 1로 설정
	try{
		curPage = Integer.parseInt(request.getParameter("curPage"));
	}catch(Exception e){
		
	}
	
	String kind = request.getParameter("kind");
	if(kind == null || kind.equals("")){
		kind = "title";
	}
	
	String search = request.getParameter("search");
	if(search == null){
		search = "";
	}
	
	BoardDAO bDAO = new NoticeDAO();
	MakePager mk = new MakePager(curPage, search, kind);
	List<BoardDTO> ar =	bDAO.selectList(mk.makeRow());
	
	int totalCount = bDAO.getCount(kind, search);
	//page
	Pager pager = mk.makePage(totalCount);
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../temp/bootStrap.jsp" %>
</head>
<body>
<jsp:include page="../temp/header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
			<h1>Notice</h1>
		</div>
		<div class="row">
			
			
		<div>
			<form class="form-inline" action="./noticeList.jsp">
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
					<td><a href="./noticeSelectOne.jsp?num=<%=bDTO.getNum()%>"><%=bDTO.getTitle() %></a></td>
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
			<li><a href="./noticeList.jsp?curPage=<%=1%>"><span class="glyphicon glyphicon-backward"></span></a></li>
				<%if(pager.getCurBlock()>1){ %>
					<li><a href="./noticeList.jsp?curPage=<%=pager.getStartNum()-1%>"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
				<%} %>
			    <%for(int i=pager.getStartNum();i<=pager.getLastNum();i++){ %>
					<li><a href="./noticeList.jsp?curPage=<%=i%>>"><%=i%></a></li>
			    <%} %>
			    <%if(pager.getCurBlock() != pager.getTotalBlock()){ %>
					<li><a href="./noticeList.jsp?curPage=<%=pager.getLastNum()+1%>"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
			    <%} %>
			<li><a href="./noticeList.jsp?curPage=<%=pager.getTotalPage()%>"><span class="glyphicon glyphicon-forward"></span></a></li>
		</ul>
	</div>
</div>
			
		
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-1">
				<a href="./noticeWriteForm.jsp" class="btn btn-primary">WRITE</a>
			</div>
		</div>
	</div>
			
			
			
		</div>
	</div>
	
<jsp:include page="../temp/footer.jsp"></jsp:include>
</body>
</html>
</head>
<body>
	
</body>
</html>