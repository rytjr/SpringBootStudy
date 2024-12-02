-----------------------------------------------------
-- 카테고리
DROP TABLE Category;

-- 카테고리
CREATE TABLE Category (
   cg_code   number(8)  primary key, -- 카테고리 코드   
   cg_name varchar2(30) NOT NULL  -- 카테고리명
);
DROP sequence category_seq;
CREATE sequence category_seq nocache;

INSERT INTO category VALUES(category_seq.nextval,'컴퓨터/디지털가전');
INSERT INTO category VALUES(category_seq.nextval,'생필품');
INSERT INTO category VALUES(category_seq.nextval,'의류');
commit;

select * from category;
------------------------------------------------------------        
-- 상품
DROP TABLE Product;

-- 상품
CREATE TABLE Product (
   pnum        Number(8)      primary key, -- 상품번호
   pname       varchar2(30)   NOT NULL, -- 상품명
   pimage1     varchar2(100)  NULL,     -- 이미지1
   pimage2     varchar2(100)  NULL,     -- 이미지2
   pimage3     varchar2(100)  NULL,     -- 이미지3
   price       number(8)      NOT NULL, -- 상품 정가
   saleprice   number(8)      NOT NULL, -- 상품 판매가
   pqty        number(8)      NOT NULL, -- 상품 수량
   point       number(8)      NULL,     -- 지급 포인트
   pspec       varchar2(20)   NULL,     -- 스펙(HIT,NEW,BEST)
   pcontents   varchar2(1000) NULL,     -- 상품설명
   pcompany    varchar2(50)   NULL,     -- 제조사
   pdate       DATE           NULL,     -- 등록일
   cg_code   number(8)      NULL  -- 카테고리 코드
);



-- 상품
ALTER TABLE Product
   ADD
      CONSTRAINT FK_Category_TO_Products -- 하위 카테고리 -> 상품
      FOREIGN KEY (
         cg_code   -- 카테고리 코드
      )
      REFERENCES Category ( 
         cg_code   -- 카테고리 코드
      );
-- 상품
DROP sequence Product_seq;
CREATE sequence Product_seq nocache;

insert into product values(Product_seq.nextval,'갤럭시북2','note1.png','note2.png','note3.png',
1000000,800000,500,5000,'HIT','성능 좋은 노트북','삼성',SYSDATE,1);
insert into product values(Product_seq.nextval,'게이밍데스크탑','pc1.png','pc2.png','pc3.png',
1200000,600000,100,3000,'HIT','성능 좋은 데스크탑','HP',SYSDATE,1);

insert into product values(Product_seq.nextval,'주방세제','a1.png','a2.png','a3.png',
80000,80000,100,300,'HIT','깨끗이 닦이는 주방세제','퐁퐁',SYSDATE,2);
COMMIT;
SELECT * FROM PRODUCT;

-------------------------------
-- cart
drop table cart;
create table cart(
   cnum number(8) primary key,    --장바구니 번호
   userId varchar2(20) not null references member(userId) on delete cascade,  --회원 아이디(FK)
   pnum number(8) not null references product(pnum) on delete cascade,   --상품번호
   pqty number(8) check (pqty >0 and pqty<51),   --장바구니에 담긴 수량
   cdate date default sysdate   --장바구니 담은 날짜
);

drop sequence cart_seq;
create sequence cart_seq nocache;