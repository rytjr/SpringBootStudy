drop table member;

create table member(
    no number(8) primary key,     --회원번호
    name varchar(50) not null,    --이름
    userId varchar2(20) not null unique,     --아이디
    passwd varchar2(100) not null,      --비밀번호
    hp1 char(3),    --핸드폰 앞자리
    hp2 char(4),    --중간자리
    hp3 char(4),     --끝자리
    indate date default sysdate   --가입일
);

drop sequence member_seq;

create sequence member_seq
start with 1
increment by 1
nocache;



insert into member(no,name,userId,passwd,hp1,hp2,hp3)
values(member_seq.nextval,'김관리','admin','111','010','2323','5555');
commit;

select * from member;

select count(*) from member;

select no from member where userid='admin';