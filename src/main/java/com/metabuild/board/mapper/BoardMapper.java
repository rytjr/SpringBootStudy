package com.metabuild.board.mapper;

import com.metabuild.board.domain.BoardDTO;
import com.metabuild.board.domain.PagingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int insertBorad(BoardDTO board);
    List<BoardDTO> listBoard(PagingDTO paging);
    int getTotalCount(PagingDTO paging);

    int updateReadNum(int id);

    BoardDTO findById(int id);

    int updateBoard(BoardDTO board);

    int deleteBoard(int id);
}
