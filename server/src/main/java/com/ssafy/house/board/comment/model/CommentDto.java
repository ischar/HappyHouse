package com.ssafy.house.board.comment.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentDto : 게시글정보" , description ="댓글의 상세 정보를 나타낸다." )
public class CommentDto {
	@ApiModelProperty(value = "글번호")
	private int articleno;
	@ApiModelProperty(value = "글번호")
	private int commentno;
	@ApiModelProperty(value = "글번호")
	private String userid;
	@ApiModelProperty(value = "글번호")
	private String content;
	@ApiModelProperty(value = "글번호")
	private int hit;
	@ApiModelProperty(value = "글번호")
	private String regtime;
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public int getCommentno() {
		return commentno;
	}
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "CommentDto [articleno=" + articleno + ", commentno=" + commentno + ", userid=" + userid + ", content="
				+ content + ", hit=" + hit + ", regtime=" + regtime + "]";
	}
	
	
}
