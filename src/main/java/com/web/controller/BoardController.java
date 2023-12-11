package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.domain.Board;
import com.web.service.BoardService;

@RequestMapping("board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 자유 게시판 
	@GetMapping("/free_board")
	public void free_board() {
		
	}
	
	//----------------------------------------------------------
	
	// 거래 게사판 
	@GetMapping("/trading_board")
	public String trading_board(Model model, Board board,
			@PageableDefault(page = 0, size = 3, sort = "boardSeq",direction = Sort.Direction.DESC) Pageable pageable) {
		boardService.trading_board(model, board, pageable);
		return "board/trading_board";
	}
	
	//----------------------------------------------------------
	
	// 상세 페이지  
	@GetMapping("/trading_board_view")
	public String trading_board_view(Model model, Long boardSeq) {
		boardService.trading_board_view(model, boardSeq);
		return "board/trading_board_view";
	}
	
	//----------------------------------------------------------
	
	// 팁 & 노하우 
	@GetMapping("/tip_know-how")
	public void tip_know() {
	
	}
	
	//----------------------------------------------------------
	
	// 글 쓰기 
	@GetMapping("/board_write")
	public String board_write() {
		return "board/board_write";
	}
	
	// 작성한 글 등록 
	@PostMapping("/board_write_form")
	public String board_write(Board board) {
		boardService.board_write(board);
		return "redirect:trading_board";
	}
	
	//----------------------------------------------------------

	// **글 수정 시 정보 데아터 & seq 저장 + 페이징처리 
	@GetMapping("/board_update")
	public String board_update_form(Model model,Long boardSeq){
		boardService.getBoard(boardSeq,model);
		return "board/board_update";
	}
	// **글 수정
	@PostMapping("/boardUpdate")
	public String boardUpdate(Board board) {
		boardService.board_update(board);
		return "redirect:trading_board";
	}
	
	//----------------------------------------------------------

	// 글 삭제 
	@GetMapping("/board_delete")
	public String board_delete(Board board) {
		boardService.board_delete(board);
		return "forward:trading_board";
	}
	//----------------------------------------------------------


	
	//----------------------------------------------------------


	


	// 추천수
	
	
	
	
	
	
	
	
}
