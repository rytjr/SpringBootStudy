package com.metabuild.board.service;

import com.metabuild.board.domain.BoardDTO;
import com.metabuild.board.domain.PagingDTO;
import com.metabuild.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;   //생성자 인젝션

    @Override
    public int insertBoard(BoardDTO board) {
        log.info("글쓰기 전 글번호 : {}",board.getId());
        int n = boardMapper.insertBorad(board);
        log.info("글ㅆ고 난후 글번호 : {}",board.getId());
        return n;
    }

    @Override
    public List<BoardDTO> listBoard(PagingDTO paging) {

        return boardMapper.listBoard(paging);
    }

    @Override
    public int getTotalCount(PagingDTO paging) {
        return boardMapper.getTotalCount(paging);
    }

    @Override
    public BoardDTO findById(int id) {
        return boardMapper.findById(id);
    }

    @Override
    public int updateReadNum(int id) {   //조회수 증가처리
        return boardMapper.updateReadNum(id);
    }

    @Override
    public int deleteBoard(int id) {
        return boardMapper.deleteBoard(id);
    }

    @Override
    public int updateBoard(BoardDTO board) {
        return boardMapper.updateBoard(board);
    }
}
