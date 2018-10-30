package com.cyj.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyj.action.ActionForward;
import com.cyj.board.BoardDTO;
import com.cyj.page.MakePager;
import com.cyj.page.Pager;
import com.cyj.page.RowNumber;

public class QnaService {
	
	private QnaDAO qDAO;
	
	public QnaService() {
		qDAO = new QnaDAO();
	}
	
	//selectList
	public ActionForward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		int curPage=1;
		curPage = Integer.parseInt(request.getParameter("curPage"));
		
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		
		MakePager mk = new MakePager(curPage, search, kind);
		RowNumber rowNumber = mk.makeRow();
		
		
		try {
			List<BoardDTO> ar = qDAO.selectList(rowNumber);
			int totalCount = qDAO.getCount(rowNumber.getSearch());
			Pager pager = mk.makePage(totalCount);
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			actionForward.setPath("../WEB-INF/qna/qnaList.jsp");
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
		actionForward.setCheck(false);
		actionForward.setPath("./qnaList.do");
		BoardDTO bDTO = null;
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			bDTO = qDAO.selectOne(num);
			
			request.setAttribute("dto", bDTO);
			actionForward.setPath("../WEB-INF/qna/qnaSelectOne.jsp");
			actionForward.setCheck(true);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(bDTO == null) {
			actionForward.setCheck(false);
			actionForward.setPath("./qnaList.do");
		}
		
		return actionForward;
	}

}
