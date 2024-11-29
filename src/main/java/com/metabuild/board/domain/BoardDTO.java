package com.metabuild.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString(exclude = {"pwd"})
public class BoardDTO {

    private int id;
    private String userId;
    private String title;
    private String content;
    private Timestamp wdate;
    private String pwd;
    private String fileName;
    private String originFileName;
    private long fileSize;
    private int readNum;

    //글쓰기(wirte), 글수정(edit)
    private String mode;

    private String oldFile; //수정 또는 삭제시 예전에 첨부했던 파일명
}
