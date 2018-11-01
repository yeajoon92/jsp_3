package com.cyj.notice;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyj.action.ActionForward;
import com.cyj.board.BoardDTO;
import com.cyj.board.BoardService;
import com.cyj.file.FileDAO;
import com.cyj.file.FileDTO;
import com.cyj.page.MakePager;
import com.cyj.page.Pager;
import com.cyj.page.RowNumber;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeService implements BoardService {
	
	private NoticeDAO nDAO;
	
	public NoticeService() {
		nDAO = new NoticeDAO();
	}
	
	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			String message = "Fail";
			String path = "./noticeList.do";
			
			//file의 크기 (단위: byte)
			int maxSize = 1024*1024*10;
			
			//file의 저장공간
			String save = request.getServletContext().getRealPath("upload"); //upload폴더에 집어넣기
			
			System.out.println(save);
			File file = new File(save);
			if(!file.exists()) {//해당파일이 없으면
				file.mkdirs();//이 공간에 이 경로로 디렉터리를 만들자 (mkdirs => make directories)
			}
			
			try {
				MultipartRequest multi = new MultipartRequest(request, save, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				NoticeDTO nDTO = new NoticeDTO();
				nDTO.setTitle(multi.getParameter("title"));
				nDTO.setWriter(multi.getParameter("writer"));
				nDTO.setContents(multi.getParameter("contents"));
				nDTO.setNum(nDAO.getNum());
				int result = nDAO.insert(nDTO);
				if(result>0) {
					FileDAO fDAO = new FileDAO();
					//파일의 파라미터 이름을 모은 것
					Enumeration<Object> e = multi.getFileNames();
					while(e.hasMoreElements()) { //몇 개 있는지 모를 때
						String p = (String)e.nextElement();
						FileDTO fDTO = new FileDTO();
						fDTO.setKind("N");
						fDTO.setNum(nDTO.getNum());
						fDTO.setfName(multi.getFilesystemName(p));
						fDTO.setoName(multi.getOriginalFileName(p));
						fDAO.insert(fDTO);
					}
					
					message = "Success";
					actionForward.setCheck(true);
					actionForward.setPath("../WEB-INF/view/common/result.jsp");
					
				}else {
					actionForward.setCheck(true);
					actionForward.setPath("../WEB-INF/view/common/result.jsp");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("message", message);
			request.setAttribute("path", path);
			
			
		}else {
			request.setAttribute("board", "notice");
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/board/boardList.jsp");
		}
		return actionForward;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		String method = request.getMethod();
		
		if(method.equals("POST")) {
			//DB Update
			int max=1024*1024*10;
			String path = request.getServletContext().getRealPath("upload");
			File file = new File(path);
			if(!file.exists()) { //file이 존재하지 않으면
				file.mkdirs(); //file객체를 만들자
			}
			
			try {
				MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
				NoticeDTO nDTO = new NoticeDTO();
				nDTO.setNum(Integer.parseInt(multi.getParameter("num")));
				nDTO.setTitle(multi.getParameter("title"));
				nDTO.setContents(multi.getParameter("contents"));
				
				//update
				int result = nDAO.update(nDTO); //bDTO넣는건데 nDTO자체가 bDTO이기 때문에 nDTO 넣으면 됨
				if(result>0) { //Update Success
					Enumeration<Object> e = multi.getFileNames();
					FileDAO fDAO = new FileDAO();
					while(e.hasMoreElements()) {
						String key = (String)e.nextElement();
						FileDTO fDTO = new FileDTO();
						fDTO.setoName(multi.getOriginalFileName(key));
						fDTO.setfName(multi.getFilesystemName(key));
						fDTO.setKind("N");
						fDTO.setNum(nDTO.getNum());
						fDAO.insert(fDTO);
					}//while 끝
					
					request.setAttribute("message", "Update Success");
					request.setAttribute("path", "./noticeList.do");
					
				}else {
					//Update Fail
					request.setAttribute("message", "Update Fail");
					request.setAttribute("path", "./noticeList.do");
				}
				
				
			} catch (Exception e) { //Exception발생했을때도 Fail
				request.setAttribute("message", "Update Fail");
				request.setAttribute("path", "./noticeList.do");
				e.printStackTrace();
			}
			
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/common/result.jsp");
			
			
		}else {
			//Form
			try {
				int num = Integer.parseInt(request.getParameter("num"));
				BoardDTO bDTO = nDAO.selectOne(num);
				FileDAO fDAO = new FileDAO();
				FileDTO fDTO = new FileDTO();
				fDTO.setNum(num);
				fDTO.setKind("N");
				List<FileDTO> ar = fDAO.selectList(fDTO);
				request.setAttribute("dto", bDTO);
				request.setAttribute("files", ar);
				request.setAttribute("board", "notice");
				actionForward.setCheck(true);
				actionForward.setPath("../WEB-INF/view/board/boardUpdate.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return actionForward;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			
			FileDAO fDAO = new FileDAO();
			fDAO.deleteAll(num);
			
			num = nDAO.delete(num);
			
			
			if(num>0) {
				request.setAttribute("message", "Delete Success");
				request.setAttribute("path", "./noticeList.do");
			}else {
				request.setAttribute("message", "Delete Fail");
				request.setAttribute("path", "./noticeList.do");
			}
		} catch (Exception e) {
			request.setAttribute("message", "Delete Fail");
			request.setAttribute("path", "./noticeList.do");
			e.printStackTrace();
		}
		
		actionForward.setCheck(true); //forward로 보내야되니깐
		actionForward.setPath("../WEB-INF/view/common/result.jsp");
		
		return actionForward;
	}

	//selectList
	public ActionForward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		int curPage=1;
		try {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}catch(Exception e) {
			
		}
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		
		MakePager mk = new MakePager(curPage, search, kind);
		RowNumber rowNumber = mk.makeRow();
		
		try {
			List<BoardDTO> ar = nDAO.selectList(rowNumber);
			int totalCount = nDAO.getCount(rowNumber.getSearch());
			Pager pager = mk.makePage(totalCount);
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			request.setAttribute("board", "notice");
			actionForward.setPath("../WEB-INF/view/board/boardList.jsp");
		} catch (Exception e) {
			request.setAttribute("message", "Fail");
			request.setAttribute("path", "../index.jsp");
			actionForward.setPath("../WEB-INF/common/result.jsp");
			e.printStackTrace();
		}
		
		actionForward.setCheck(true);
		
		return actionForward;
	}
	
	//selectOne
	public ActionForward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		BoardDTO bDTO = null;
		int num = Integer.parseInt(request.getParameter("num"));
		
		try {
			bDTO = nDAO.selectOne(num);
			FileDAO fDAO = new FileDAO();
			FileDTO fDTO = new FileDTO();
			fDTO.setNum(num);
			fDTO.setKind("N");
			List<FileDTO> ar = fDAO.selectList(fDTO);
			request.setAttribute("dto", bDTO);
			request.setAttribute("files", ar);
			request.setAttribute("board", "notice");
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/board/boardSelectOne.jsp");
		} catch (Exception e) {
			actionForward.setCheck(false);
			actionForward.setPath("./notice/noticeList.do"); //Servlet 거쳐가려면 .do	*forward는 .jsp로 끝남
			e.printStackTrace();
		}
		
		if(bDTO == null) {
			actionForward.setCheck(false);
			actionForward.setPath("./notice/noticeList.do");
		}
		
		return actionForward;
	}
	
}
