package com.web.service;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.web.domain.Board;
import com.web.domain.Inquery;
import com.web.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	// 게시글 목록 
	@Override
	public void trading_board(Model model, Board board, Pageable pageable) {
		Page<Board> boardList = boardRepo.findAll(pageable);
		int nowPage = boardList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, boardList.getTotalPages());

		model.addAttribute("boardList", boardList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}

	// 게시글 글 쓰기 
	@Override
	public void board_write(Board board) {
		boardRepo.save(board);
	}

	// 게시글 상세페이지 
	@Override
	public void trading_board_view(Model model, Long boardSeq) {
		Board board = boardRepo.findById(boardSeq).get();
		board.setBoardViews(board.getBoardViews()+1);
		boardRepo.save(board);
		model.addAttribute("board",board);
		
	}
	
	// 게시글 글 수정 시 정보데이터 & seq 같이 보내기 + 페이징처리 
	@Override
	public void getBoard(Long boardSeq,Model model) {
		Board board = boardRepo.findById(boardSeq).get();
		model.addAttribute("board", board);
	}

	// 게시글 글 수정 
	@Override
	public void board_update(Board board) {
		Board findBoard = boardRepo.findById(board.getBoardSeq()).get();
		
		findBoard.setBoardTitle(board.getBoardTitle());
		findBoard.setBoardContents(board.getBoardContents());
		boardRepo.save(board);
	}
	
	// 게시글 글 삭제 
	@Override
	public void board_delete(Board board) {
		boardRepo.deleteById(board.getBoardSeq());
	}
	
	
}

















