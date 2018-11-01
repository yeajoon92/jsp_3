package com.cyj.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cyj.board.BoardDAO;
import com.cyj.board.BoardDTO;
import com.cyj.page.RowNumber;
import com.cyj.page.Search;
import com.cyj.util.DBConnector;
import com.oreilly.servlet.MultipartRequest;

public class NoticeDAO implements BoardDAO {

	@Override
	public List<BoardDTO> selectList(RowNumber rowNumber) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from "
				+ "(select rownum R, N.* from "
				+ "(select num, title, writer, reg_date, hit from notice "
				+ "where "+rowNumber.getSearch().getKind()+" like ? "
				+ "order by num desc) N) "
				+ "where R between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, "%"+rowNumber.getSearch().getSearch()+"%");
		st.setInt(2, rowNumber.getStartRow());
		st.setInt(3, rowNumber.getLastRow());
		
		ResultSet rs = st.executeQuery();
		List<BoardDTO> ar = new ArrayList<>();
		NoticeDTO nDTO = null;
		
		while(rs.next()) {
			nDTO = new NoticeDTO();
			nDTO.setNum(rs.getInt("num"));
			nDTO.setTitle(rs.getString("title"));
			nDTO.setWriter(rs.getString("writer"));
			nDTO.setReg_date(rs.getDate("reg_date"));
			nDTO.setHit(rs.getInt("hit"));
			ar.add(nDTO);
		}
		
		DBConnector.disConnect(rs, st, con);
		return ar;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from notice where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);		
		NoticeDTO nDTO = null;
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			nDTO = new NoticeDTO();
			nDTO.setNum(rs.getInt("num"));
			nDTO.setTitle(rs.getString("title"));
			nDTO.setContents(rs.getString("contents"));
			nDTO.setWriter(rs.getString("writer"));
			nDTO.setReg_date(rs.getDate("reg_date"));
			nDTO.setHit(rs.getInt("hit"));
		}
		
		DBConnector.disConnect(rs, st, con);
		return nDTO;
	}
	
	//sequence 가져오기
	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select notice_seq.nextval from dual";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		DBConnector.disConnect(rs, st, con);
		return num;
	}
	
	@Override
	public int insert(BoardDTO bDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="insert into notice values (?, ?, ?, ?, sysdate, 0)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, bDTO.getNum());
		st.setString(2, bDTO.getTitle());
		st.setString(3, bDTO.getContents());
		st.setString(4, bDTO.getWriter());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public int update(BoardDTO bDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "update notice set title=?, contents=? where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, bDTO.getTitle());
		st.setString(2, bDTO.getContents());
		st.setInt(3, bDTO.getNum());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public int delete(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "delete notice where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public int getCount(Search search) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from notice "
				+ "where "+search.getKind()+" like ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search.getSearch()+"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		DBConnector.disConnect(rs, st, con);
		return result;
	}
	
	

}
