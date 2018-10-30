package com.cyj.qna;

import java.util.List;

import com.cyj.board.BoardDAO;
import com.cyj.board.BoardDTO;
import com.cyj.board.BoardReply;
import com.cyj.board.BoardReplyDTO;
import com.cyj.page.RowNumber;
import com.cyj.page.Search;

public class QnaDAO implements BoardDAO, BoardReply {

	@Override
	public List<BoardDTO> selectList(RowNumber rowNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
