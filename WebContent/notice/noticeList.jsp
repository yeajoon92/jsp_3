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
	
	request.setAttribute("list", ar);
	request.setAttribute("board", "notice");
	request.setAttribute("pager", pager);
	
	RequestDispatcher view = request.getRequestDispatcher("./board/boardList.jsp");
	view.forward(request, response);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>