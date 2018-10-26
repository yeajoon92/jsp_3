package com.cyj.board;

public interface BoardReply {
	
	//reply
	public int reply(BoardReplyDTO brDTO) throws Exception;
	
	//replyUpdate
	public int replyUpdate(BoardReplyDTO brDTO) throws Exception;
	
	
}
