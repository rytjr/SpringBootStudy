package com.metabuild.board.service;

import com.metabuild.board.domain.BoardDTO;
import com.metabuild.board.domain.PagingDTO;

import java.util.List;

public interface BoardService {

    //글쓰기
    int insertBoard(BoardDTO board);
    //글 목록
    List<BoardDTO> listBoard(PagingDTO paging);
    // 총 게시글 수 or 검색한 게시글 수
    int getTotalCount(PagingDTO paging);
    //id(Pk)로 글내용 가져오기
    BoardDTO findById(int id);
    //조회수 증가
    int updateReadNum(int id);
    //글 번호로 삭제
    int deleteBoard(int id);
    //글수정
    int updateBoard(BoardDTO board);

}
