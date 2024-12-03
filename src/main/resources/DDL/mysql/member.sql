drop table member;

create table member(
    no int primary key auto_increment,
    name varchar(50) not null,
    userId varchar(20) not null unique,
    passwd varchar(100) not null,
    hp1 char(3),
    hp2 char(4),
    hp3 char(4),
    indate date default (current_date)
);




insert into member(no,name,userId,passwd,hp1,hp2,hp3)
values(member_seq.nextval,'김관리','admin','111','010','2323','5555');
commit;

select * from member;

select count(*) from member;

select no from member where userid='admin';