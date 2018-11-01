package com.cyj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyj.action.ActionForward;
import com.cyj.member.MemberService;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
        memberService = new MemberService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String command = request.getPathInfo();
		
		//forward, redirect
		ActionForward actionForward = null;
		
		if(command.equals("/memberJoin.do")) {
			actionForward = memberService.join(request, response);
		}else if(command.equals("/memberLogin.do")) {
			actionForward = memberService.login(request, response);
		}else if(command.equals("/memberMypage.do")) {
			actionForward = memberService.myPage(request, response);
		}else if(command.equals("/memberUpdate.do")) {
			actionForward = memberService.update(request, response);
		}else if(command.equals("/memberDelete.do")) {
			actionForward = memberService.delete(request, response);
		}else if(command.equals("/memberLogout.do")) {
			actionForward = memberService.logout(request, response);
		}else { //else if(command.equals("/memberList.do"))
			actionForward = new ActionForward();
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/member/memberList.jsp");
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
