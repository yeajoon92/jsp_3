package com.cyj.memo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyj.action.ActionForward;
import com.cyj.page.MakePager;
import com.cyj.page.RowNumber;

public class MemoService {
	private MemoDAO memoDAO;
	
	public MemoService() {
		memoDAO = new MemoDAO();
	}
	
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String [] nums = request.getParameterValues("num");
		
		// 여러개일 때
		int num = 0;
		MemoDAO memoDAO = new MemoDAO();
		for(String s : nums) {
			try {
				num = Integer.parseInt(s);
				num = memoDAO.delete(num); //num재활용~
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		request.setAttribute("message", num);
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionForward;
	}
	
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		MemoDTO memoDTO = new MemoDTO();
		
		memoDTO.setContents(request.getParameter("contents"));
		memoDTO.setWriter(request.getParameter("writer"));
		try {
			int result = memoDAO.insert(memoDTO);
			String message = "Fail";
			if(result>0) {
				message = "Success";
			}
			request.setAttribute("message", message); //성공이든 실패든 message를 담음
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionForward;
	}
	
	public ActionForward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		//1. curPage
		
		//2. kind, search
		int curPage = 1;
		
		try {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		MakePager makePager = new MakePager(curPage, "", "");
		RowNumber rowNumber = makePager.makeRow();
		try {
			List<MemoDTO> ar = memoDAO.selectList(rowNumber);
			request.setAttribute("list", ar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path=request.getPathInfo(); // /memoList.do
		// memoList.do -> path : memoList.jsp
		// memoMore.do -> path : memoMore.jsp
		
		/* 1번 방법
		path = path.substring(0, path.lastIndexOf("."));
		path = path+".jsp";*/
		
		// 2번 방법
		path = path.replace(".do", ".jsp");
		
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/memo"+path);
		
		return actionForward;
	}
	
	
	
}
