package com.web.service;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import com.web.domain.Board;

public interface BoardService {

	// 게시글 목록 
	public void trading_board(Model model, Board board, Pageable pageable);

	// 게시글 글 쓰기 
	public void board_write(Board board);
	
	// 게시글 상세페이지 
	public void trading_board_view(Model model, Long boardSeq);
	
	// 게시글 글 수정 시 정보데이터 & seq 같이 보내기 + 페이징처리 
	public void getBoard(Long boardSeq,Model model);
	
	// 게시글 글 수정 
	public void board_update(Board board);
	
	// 게시글 글 삭제 
	public void board_delete(Board board);
	
	// 조회수 
	
}
