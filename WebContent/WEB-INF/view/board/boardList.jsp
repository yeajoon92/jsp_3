<%@page import="com.cyj.qna.QnaDTO"%>
<%@page import="com.cyj.page.Pager"%>
<%@page import="com.cyj.board.BoardDAO"%>
<%@page import="com.cyj.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.cyj.page.MakePager"%>
<%@page import="com.cyj.page.RowNumber"%>
<%@page import="com.cyj.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../../../temp/bootStrap.jsp" %>
</head>
<body>
<c:import url="../../../temp/header.jsp"/>
	<div class="container-fluid">
		<div class="row">
			<h1>${board}</h1>
			
			<div>
				<form class="form-inline" action="./${board}List.do">
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
				
				<c:forEach items="${list}" var="bDTO">
				
				<tr>
					<td>${bDTO.num}</td>
					<td>
					<a href="./${board}SelectOne.do?num=${bDTO.num}">
					<c:catch>
						<c:forEach begin="1" end="${bDTO.depth}">
							--
						</c:forEach>
					</c:catch>
					
					${bDTO.title}</a></td>
					<td>${bDTO.writer}</td>
					<td>${bDTO.reg_date}</td>
					<td>${bDTO.hit}</td>
				</tr>
				</c:forEach>
			</table>
		
	</div>
</div>



<div class="container-fluid">
	<div class="row">
		<ul class="pagination">
			<li><a href="./${board}List.do?curPage=1"><span class="glyphicon glyphicon-backward"></span></a></li>
				
				<c:if test="${pager.curBlock gt 1}">
					<li><a href="./${board}List.do?curPage=${pager.startNum-1}"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
				</c:if>
				
				<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
					<li><a href="./${board}List.do?curPage=${i}">${i}</a></li>
				</c:forEach>
				
				<c:if test="${pager.curBlock lt pager.totalBlock}">
					<a href="./${board}List.do?curPage=${pager.lastNum+1}"><span class="glyphicon glyphicon-chevron-right"></span></a>
				</c:if>
				
			<li><a href="./${board}List.do?curPage=${pager.totalPage}"><span class="glyphicon glyphicon-forward"></span></a></li>
		</ul>
	</div>
</div>
			
	
		<c:if test="${not empty member and member.kind eq 'T'}">
			<c:import url="../../../temp/writeButton.jsp"></c:import>
		</c:if>
		
	
	
	
<jsp:include page="../../../temp/footer.jsp"></jsp:include>
</body>
</html>