package com.cyj.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyj.action.ActionForward;
import com.cyj.board.BoardDTO;
import com.cyj.file.FileDAO;
import com.cyj.file.FileDTO;
import com.cyj.page.MakePager;
import com.cyj.page.Pager;
import com.cyj.page.RowNumber;

public class NoticeService {
	
	private NoticeDAO nDAO;
	
	public NoticeService() {
		nDAO = new NoticeDAO();
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
			actionForward.setPath("../WEB-INF/notice/noticeList.jsp");
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
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/notice/noticeSelectOne.jsp");
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
