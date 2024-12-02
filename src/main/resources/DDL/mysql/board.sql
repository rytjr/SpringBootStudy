DROP TABLE IF EXISTS board;

CREATE TABLE IF NOT EXISTS  board (
    id INT(8) PRIMARY KEY AUTO_INCREMENT,
    userId VARCHAR(30) NOT NULL,
    title VARCHAR(200) NOT NULL,
    content VARCHAR(2000),
    wdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pwd VARCHAR(20) NOT NULL, -- 글 비밀번호
    fileName VARCHAR(100), -- 물리적 파일명 (랜덤한 uuid_파일명.확장자)
    originFileName VARCHAR(100), -- 원본 파일명 (파일명.확장자)
    fileSize INT(8), -- 첨부파일 크기
    readNum INT(8) -- 조회수
);

-- MySQL에서는 AUTO_INCREMENT를 사용하므로 별도의 시퀀스가 필요하지 않습니다.