--drop sequence board_seq
--drop table board
--drop table userinfo


create table userinfo
(
	userid varchar2(50) not null,
	password varchar2(50) not null,
	name varchar2(50) not null,
	email varchar2(50) not null,
	primary key (userid)
)

insert into userinfo(userid, PASSWORD, name, email)
values(
'BBM','12345', '강신윤', 'bbm@bbm.com'
)

create table board
(
   num number not null,  --글번호
   userid varchar2(50) null,  -- 사용자id
   title varchar2(50) not null,  --제목
   regdate date null,  --작성일자
   content varchar2(500) not null,   --내용
   count number null,  -- 조회수
   filename varchar2(20) null,  --파일명
   ref number null,  -- 참조글
   re_step number default 0,  
   re_level number default 0,
   primary key (num),
   foreign key(userid) references userinfo(userid)
)



--시퀀스 설정
create sequence board_seq 
increment by 1
start with 1
maxvalue 10000;

insert into board(num, userid, title, regdate, 
content, count, filename, ref, re_step, re_level)
values(
board_seq.nextval,'dugohr89','제목입니다',sysdate,'내용입니다',0,'이승철.jpg',1,1,1
)

select * from board;
select * from userinfo;
delete from userinfo where userid like '%mail%';

select * from BOARDFILE


SELECT ROWNUM rnum, t1.*
  FROM (
        SELECT * FROM board
         ORDER BY num DESC
       ) t1
 WHERE ROWNUM BETWEEN 1 AND 10



INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);
INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);
INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);
INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);
INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);
INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);
INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);
INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);
INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);
INSERT INTO board(num, userid, title, regdate, content, count, filename, ref) 
VALUES(board_seq.nextval, 'BBM', 'testdate', sysdate,'testcontent', 0,null,board_seq.currval);




alter table userinfo
   add (role varchar2(20));


CREATE TABLE BOARDFILE
(
  NUM       NUMBER NOT NULL,
  FILENUM   NUMBER NOT NULL,
  FILENAME  VARCHAR2(100 BYTE)
)

ALTER TABLE BOARDFILE ADD (
  CONSTRAINT BOARDFILE_PK
 PRIMARY KEY
 (FILENUM, NUM)
);


SELECT NVL(MAX(filenum), 0) + 1
  FROM boardfile
 WHERE num = 0


