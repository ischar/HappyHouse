package com.ssafy.house.board.comment.service;

import java.util.List;

import com.ssafy.house.board.comment.model.CommentDto;

public interface CommentService {

	public boolean writeComment(CommentDto commentDto) throws Exception;
	public List<CommentDto> listComment(int articleno);
	public boolean updateHit(int articleno) throws Exception;
	public boolean deleteComment(int commentno);
	public boolean modifyComment(CommentDto commentDto);


}
