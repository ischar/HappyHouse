package com.ssafy.house.board.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.board.comment.model.CommentDto;

@Mapper
public interface CommentMapper {

	int writeComment(CommentDto commentDto);
	List<CommentDto> listComment(int articleno);
	int updateHit(int commentno);
	int deleteComment(int commentno);
	int modifyComment(CommentDto commentDto);



}
