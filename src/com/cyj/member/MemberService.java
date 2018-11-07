package com.cyj.member;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cyj.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberService {
	
	private MemberDAO mDAO;
	
	public MemberService() {
		mDAO = new MemberDAO();
	}
	
	//checkId
	public ActionForward checkId(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		String id = request.getParameter("id");
		boolean check=true;
		String result="1"; //1:사용가능, 2:사용불가능
		try {
			check = mDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check) {
			result="2";
		}
		request.setAttribute("result", result);
		
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/member/memberCheckId.jsp");
		
		return actionForward;
	}
	
	//join
	public ActionForward join(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			int max = 1024*1024*10;
			String path = request.getServletContext().getRealPath("upload");
			//System.out.println(path);
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			try {
				//파일저장
				MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
				//Member Data
				MemberDTO mDTO = new MemberDTO();
				mDTO.setId(multi.getParameter("id"));
				mDTO.setPw(multi.getParameter("pw"));
				mDTO.setName(multi.getParameter("name"));
				mDTO.setEmail(multi.getParameter("email"));
				mDTO.setKind(multi.getParameter("kind"));
				mDTO.setClassMate(multi.getParameter("classMate"));
				mDTO.setfName(multi.getFilesystemName("f1"));
				mDTO.setoName(multi.getOriginalFileName("f1"));
				/*
				 *	파일의 정보를 DTO에 추가
				 * 
				 */
				int result = mDAO.join(mDTO);
				if(result>0) {
					request.setAttribute("message", "Join Success");
					request.setAttribute("path", "../index.jsp");
				}else {
					request.setAttribute("message", "Join Fail");
					request.setAttribute("path", "./memberJoin.do");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/common/result.jsp");
		}else {
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		}
		
		return actionForward;
	}
	
	//login
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		String method = request.getMethod();
		
		if(method.equals("POST")) {
			MemberDTO mDTO = new MemberDTO();
			mDTO.setId(request.getParameter("id"));
			mDTO.setPw(request.getParameter("pw"));
			mDTO.setKind(request.getParameter("kind"));
			try {
				mDTO = mDAO.login(mDTO);
			} catch (Exception e) {
				mDTO = null;
				e.printStackTrace();
			}
			
			if(mDTO != null) {
				HttpSession session = request.getSession();
				//jsp에서는 session객체 있는데 servlet에는 없음
				//내장객체로 접근할수 있는게 request기 때문에 request.getSession()해서 session불러오기
				session.setAttribute("member", mDTO);
				
				actionForward.setCheck(false);
				actionForward.setPath("../index.jsp");
			}else {
				request.setAttribute("message", "Login Fail");
				actionForward.setCheck(true);
				actionForward.setPath("../WEB-INF/view/member/memberLogin.jsp");
			}
		}else {
			//GET
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/member/memberLogin.jsp");	
		}
		
		return actionForward;
	}
	
	//myPage
	public ActionForward myPage(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		actionForward.setCheck(true); //jsp로 forwarding
		actionForward.setPath("../WEB-INF/view/member/memberMypage.jsp");
		
		return actionForward;
	}
	
	//update
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		if(method.equals("POST")) {
		//POST
			int max = 1024*1024*10;
			String path = request.getServletContext().getRealPath("upload");
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			String message = "Update Fail";
			
			try {
				MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
				MemberDTO mDTO = new MemberDTO();
				MemberDTO sessionDTO = (MemberDTO)request.getSession().getAttribute("member");
				mDTO.setId(multi.getParameter("id"));
				mDTO.setPw(multi.getParameter("pw"));
				mDTO.setName(multi.getParameter("name"));
				mDTO.setEmail(multi.getParameter("email"));
				mDTO.setfName(sessionDTO.getfName()); //이거랑
				mDTO.setoName(sessionDTO.getoName()); //이거, else{}안에 넣는 대신 미리 셋팅함
				mDTO.setKind(sessionDTO.getKind());
				mDTO.setClassMate(sessionDTO.getClassMate());
				file = multi.getFile("f1");
				//System.out.println(file.exists()); //파일이 넘어왔는지 확인해보기
				if(file != null) { //file 성공했을 때
					file = new File(path, mDTO.getfName()); //same as sessionDTO.getfName()
					file.delete();
					mDTO.setfName(multi.getFilesystemName("f1"));
					mDTO.setoName(multi.getOriginalFileName("f1"));
				}
				
				int result = mDAO.update(mDTO);
				
				if(result>0) { //update 성공했을 때
					request.getSession().setAttribute("member", mDTO);
					message="Update Success";
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("message", message);
			request.setAttribute("path", "./memberMypage.do");
			
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/common/result.jsp");
			
		}else {
		//GET
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/member/memberUpdate.jsp");
		}
		
		return actionForward;
	}
	
	//delete
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		MemberDTO mDTO = null;
		HttpSession session = request.getSession();
		mDTO = (MemberDTO)session.getAttribute("member");
		String message = "Delete Fail";
		int result;
		try {
			result = mDAO.delete(mDTO);
			if(result>0) {
				message = "Delete Success";
				String path = session.getServletContext().getRealPath("upload"); //session대신에 request써도 됨
				File file = new File(path, mDTO.getfName());
				file.delete(); //file도 지워짐
				session.invalidate(); //DB에서만 지워짐
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("message", message);
		request.setAttribute("path", "../index.jsp");
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/result.jsp");
		
		return actionForward;
	}
	
	//logout
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		HttpSession session = request.getSession();
		session.invalidate(); //session종료하기
		
		actionForward.setCheck(false); //forward말고 redirect로 보내면 됨
		actionForward.setPath("../index.jsp"); //redirect
		
		return actionForward;
	}
	
	
}
