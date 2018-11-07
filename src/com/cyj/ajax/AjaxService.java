package com.cyj.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cyj.action.ActionForward;
import com.cyj.board.BoardDTO;
import com.cyj.member.MemberDAO;
import com.cyj.member.MemberDTO;
import com.cyj.notice.NoticeDAO;
import com.cyj.page.RowNumber;
import com.cyj.page.Search;

public class AjaxService {
	
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		NoticeDAO nDAO = new NoticeDAO();
		RowNumber rowNumber = new RowNumber();
		rowNumber.setStartRow(1);
		rowNumber.setLastRow(10);
		rowNumber.setSearch(new Search());
		List<BoardDTO> ar = null;
		try {
			ar = nDAO.selectList(rowNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jr = new JSONArray(); //JSONObject를 모으는 배열
		//ar에서 DTO를 꺼내와서 JSONObject로
		for(BoardDTO bDTO : ar) {
			JSONObject js = new JSONObject();
			js.put("num", bDTO.getNum());
			js.put("title", bDTO.getTitle());
			js.put("writer", bDTO.getWriter());
			jr.add(js);
		}
		request.setAttribute("message", jr.toJSONString());
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionForward;
	}
	
	public ActionForward memberInfo(HttpServletRequest request, HttpServletResponse response) {
		//id, pw, kind
		//memberDAO login 메서드 호출
		//id, name, email JSON으로 바꿔서 출력
		ActionForward actionForward = new ActionForward();

		MemberDTO mDTO = new MemberDTO();
		mDTO.setId(request.getParameter("id"));
		mDTO.setPw(request.getParameter("pw"));
		mDTO.setKind(request.getParameter("kind"));
		MemberDAO mDAO = new MemberDAO();
		try {
			mDTO = mDAO.login(mDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*String j = "{\"id\":\""+mDTO.getId()+"\",";
		j=j+ "\"name\":\""+mDTO.getName()+"\",";
		j=j+ "\"email\":\""+mDTO.getEmail()+"\"}";*/
		
		JSONObject js = new JSONObject();
		js.put("id", mDTO.getId());
		js.put("name", mDTO.getName());
		js.put("email", mDTO.getEmail());
		
		request.setAttribute("message", js.toJSONString());
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionForward;
	}
	
	public ActionForward checkId2(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		MemberDAO mDAO = new MemberDAO();
		String id = request.getParameter("id");
		boolean result = true;
		try {
			result = mDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message="1"; //불가능
		if(!result) {
			message="2"; //가능
		}
		
		//String j = "{\"result\":\""+message+"\", \"m\":\"v\"}";
		
		//use this one instead
		JSONObject js = new JSONObject();	//{}
		js.put("result", message);			//{"result":"1"}
		js.put("m", "v");					//{"result":"1", "m":"v"}
		
		request.setAttribute("message", js.toJSONString()); //{"result":"1", "m":"v"}
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		
		return actionForward;
	}
	
	public ActionForward checkId(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		MemberDAO mDAO = new MemberDAO();
		String id = request.getParameter("id");
		boolean result = true;
		try {
			result = mDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message="1"; //불가능
		if(!result) {
			message="2"; //가능
		}
		
		/*if(result) {
			//불가능 1
		}else {
			//가능 2
		}*/
		
		request.setAttribute("message", message);
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		
		return actionForward;
	}
	
}
