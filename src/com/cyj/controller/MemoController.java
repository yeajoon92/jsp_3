package com.cyj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyj.action.ActionForward;
import com.cyj.memo.MemoService;

/**
 * Servlet implementation class MemoController
 */
@WebServlet("/MemoController")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemoService memoService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoController() {
        super();
        memoService = new MemoService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getPathInfo();
		ActionForward actionForward = null;
		if(command.equals("/memoList.do")) {
			actionForward = memoService.selectList(request, response);
		}else if(command.equals("/memoMore.do")) {
			actionForward = memoService.selectList(request, response);
		}else if(command.equals("/memoWrite.do")) {
			actionForward = memoService.insert(request, response);
		}else if(command.equals("/memoDelete.do")) {
			actionForward = memoService.delete(request, response);
		}
		
		if(actionForward.isCheck()) {
			RequestDispatcher view = request.getRequestDispatcher(actionForward.getPath());
			view.forward(request, response);
		}else {
			response.sendRedirect(actionForward.getPath());
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
