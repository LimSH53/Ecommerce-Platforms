package com.naedam.admin.board.model.vo;

import java.io.Serializable;

import com.naedam.admin.member.model.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int boardNo;			//board 테이블 primary 키
	private String boardTitle; 		//게시판 제목
	private String boardSubTitle;   //게시판 부제목
	private String boardContents;	//게시판 내용
	private Member boardMemberNo;	//회원 데이터
	private int count;
	private String locale;
	
	
	
	
}
