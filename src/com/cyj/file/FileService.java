package com.cyj.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyj.action.ActionForward;

public class FileService {
	private FileDAO fDAO;
	
	public FileService() {
		fDAO = new FileDAO();
	}
	
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		int fNum=0;
		try {
			fNum = Integer.parseInt(request.getParameter("fNum"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		String fName = request.getParameter("fName");
		
		try {
			fNum = fDAO.delete(fNum); //테이블에서 먼저 지우고
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(fNum>0) { //테이블에서 지우기 성공했으면
			String path = request.getServletContext().getRealPath("upload"); //파일이 있는 경로:upload폴더
			System.out.println(path);
			File file = new File(path, fName); //경로와 이름
			file.delete(); //실제경로인 upload폴더에서 파일 지우기
		}
		
		request.setAttribute("message", fNum);
		
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionForward;
	}
	
}
