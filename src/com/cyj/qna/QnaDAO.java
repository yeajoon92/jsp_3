package com.cyj.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cyj.board.BoardDAO;
import com.cyj.board.BoardDTO;
import com.cyj.board.BoardReply;
import com.cyj.board.BoardReplyDTO;
import com.cyj.notice.NoticeDTO;
import com.cyj.page.RowNumber;
import com.cyj.page.Search;
import com.cyj.util.DBConnector;

public class QnaDAO implements BoardDAO, BoardReply {

	@Override
	public List<BoardDTO> selectList(RowNumber rowNumber) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from "
				+ "(select rownum R, N.* from "
				+ "(select num, title, writer, reg_date, hit from qna "
				+ "where "+rowNumber.getSearch().getKind()+" like ? "
				+ "order by ref desc, step asc) N) "
				+ "where R between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, "%"+rowNumber.getSearch().getSearch()+"%");
		st.setInt(2, rowNumber.getStartRow());
		st.setInt(3, rowNumber.getLastRow());
		
		ResultSet rs = st.executeQuery();
		List<BoardDTO> ar = new ArrayList<>();
		QnaDTO qDTO = null;
		
		while(rs.next()) {
			qDTO = new QnaDTO();
			qDTO.setNum(rs.getInt("num"));
			qDTO.setTitle(rs.getString("title"));
			qDTO.setWriter(rs.getString("writer"));
			qDTO.setReg_date(rs.getDate("reg_date"));
			qDTO.setHit(rs.getInt("hit"));
			qDTO.setDepth(rs.getInt("depth"));
			ar.add(qDTO);
		}
		
		DBConnector.disConnect(rs, st, con);
		return ar;
	}

	@Override
	public int reply(BoardReplyDTO brDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replyUpdate(BoardReplyDTO brDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from qna where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);		
		QnaDTO qDTO = null;
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			qDTO = new QnaDTO();
			qDTO.setNum(rs.getInt("num"));
			qDTO.setTitle(rs.getString("title"));
			qDTO.setContents(rs.getString("contents"));
			qDTO.setWriter(rs.getString("writer"));
			qDTO.setReg_date(rs.getDate("reg_date"));
			qDTO.setHit(rs.getInt("hit"));
		}
		
		DBConnector.disConnect(rs, st, con);
		return qDTO;
	}

	@Override
	public int insert(BoardDTO bDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardDTO bDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
