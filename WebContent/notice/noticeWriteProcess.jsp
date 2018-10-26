<%@page import="com.cyj.file.FileDAO"%>
<%@page import="com.cyj.file.FileDTO"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.cyj.notice.NoticeDAO"%>
<%@page import="com.cyj.notice.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String path = request.getServletContext().getRealPath("upload");
	System.out.println(path);
	int max = 1024*1024*10; //단위:byte
	
	MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
	//path 경로에 파일 업로드 끝
	NoticeDTO nDTO = new NoticeDTO();
	nDTO.setTitle(multi.getParameter("title"));
	nDTO.setWriter(multi.getParameter("writer"));
	nDTO.setContents(multi.getParameter("contents"));
	
	//파일의 정보
	FileDTO f1 = new FileDTO();
	f1.setfName(multi.getFilesystemName("f1")); //파일의 파라미터의 이름을 매개변수로 넣기
	f1.setoName(multi.getOriginalFileName("f1"));
	FileDTO f2 = new FileDTO();
	f2.setfName(multi.getFilesystemName("f2"));
	f2.setoName(multi.getOriginalFileName("f2"));
	/*System.out.println("fName : "+fName); //서버에 저장되는 이름
	System.out.println("oName : "+oName); //클라이언트가 저장한 파일 이름(original name)*/
	/*File f = multi.getFile("f1");
	Enumeration e =  multi.getFileNames(); //파라미터 이름들을 열거형(Enumeration)으로 집어넣기*/
	
	
	NoticeDAO nDAO = new NoticeDAO();
	int num = nDAO.getNum();
	nDTO.setNum(num);
	int result = nDAO.insert(nDTO);
	
	f1.setNum(num);
	f2.setNum(num);
	f1.setKind("N");
	f2.setKind("N");
	
	FileDAO fDAO = new FileDAO();
	fDAO.insert(f1);
	fDAO.insert(f2);
	
	String s = "Write Fail";
	if(result>0){
		s="Write Success";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert('<%=s%>');
	location.href="./noticeList.jsp";
</script>
</head>
<body>
	
</body>
</html>