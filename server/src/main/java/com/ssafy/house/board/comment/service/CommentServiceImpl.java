package com.ssafy.house.board.comment.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.board.comment.mapper.CommentMapper;
import com.ssafy.house.board.comment.model.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeComment(CommentDto commentDto) throws Exception {
		if(commentDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(CommentMapper.class).writeComment(commentDto) == 1;
	}

	@Override
	public List<CommentDto> listComment(int articleno) {
		return sqlSession.getMapper(CommentMapper.class).listComment(articleno);
	}
	
	@Override
	public boolean updateHit(int commentno) throws Exception {
		return sqlSession.getMapper(CommentMapper.class).updateHit(commentno) == 1;
	}

	@Override
	public boolean deleteComment(int commentno) {
		return sqlSession.getMapper(CommentMapper.class).deleteComment(commentno) == 1;
	}

	@Override
	public boolean modifyComment(CommentDto commentDto) {
		return sqlSession.getMapper(CommentMapper.class).modifyComment(commentDto) == 1;
	}

}
