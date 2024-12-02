select * from member;

drop table board;

create table board(
    id number(8) constraint board_id_pk primary key,
    userId varchar2(30) not null,
    title varchar2(200) not null,
    content varchar2(2000),
    wdate timestamp default systimestamp,
    pwd varchar2(20) not null,   --글 비밀번호
    fileName varchar2(100),  -- 물리적 파일명 (랜덤한uuid_파일명.확장자)
    originFileName varchar2(100),   --원본 파일명  (파일명.확장자)
    fileSize number(8),   -- 첨부파일 크기
    readNum number(8)   --조회수 저장
);

drop sequence board_seq;

create sequence board_seq nocache;