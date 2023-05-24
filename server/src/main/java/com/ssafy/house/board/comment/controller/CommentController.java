package com.ssafy.house.board.comment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.board.comment.model.CommentDto;
import com.ssafy.house.board.comment.service.CommentService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/comment")
@Api("코멘트 컨트롤러  API V1")
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CommentService commentService;

	@ApiOperation(value = "댓글 작성", notes = "게시글에 댓글을 입력한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeComment(@RequestBody @ApiParam(value = "댓글 정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("writeComment - 호출");
		if (commentService.writeComment(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 리스트", notes = "글에 있는 댓글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{articleno}")
	public ResponseEntity<List<CommentDto>> listComment(@PathVariable("articleno") @ApiParam(value = "게시글 번호", required = true) int articleno) throws Exception {
		logger.info("listComment - 호출" + articleno);
		return new ResponseEntity<List<CommentDto>>(commentService.listComment(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 수정", notes = "수정할 댓글 번호를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyComment(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("modifyArticle - 호출 {}", commentDto);
		
		if (commentService.modifyComment(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 삭제", notes = "댓글 번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{commentno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("commentno") @ApiParam(value = "삭제할 댓글의 글번호", required = true) int commentno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (commentService.deleteComment(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}