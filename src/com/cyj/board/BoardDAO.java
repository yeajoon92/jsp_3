package com.cyj.board;

import java.util.List;

import com.cyj.page.RowNumber;

public interface BoardDAO { //methods 팀장 asks us to use
	
	//selectList
	public List<BoardDTO> selectList(RowNumber rowNumber) throws Exception;
	//use RowNumber instead of (int startRow, int lastRow, String kind, String search)
	
	//selectOne
	public BoardDTO selectOne(int num) throws Exception;
	
	//insert
	public int insert(BoardDTO bDTO) throws Exception;
	
	//update
	public int update(BoardDTO bDTO) throws Exception;
	
	//delete
	public int delete(int num) throws Exception;
	
	//getCount
	public int getCount(String kind, String search) throws Exception;

}
