CREATE DATABASE BOOKSHOP;
USE BOOKSHOP;


CREATE TABLE MANAGER(
	MANAGERID VARCHAR(50),
    MANAGERPW VARCHAR(16)
);

INSERT INTO MANAGER (MANAGERID, MANAGERPW) VALUES('ADMIN@SHOP.COM', '1111');
SELECT * FROM MANAGER;


CREATE TABLE BOOK(
	BOOK_ID 		INT,
    BOOK_KIND 		VARCHAR(3),
    BOOK_TITLE 		VARCHAR(100),
    BOOK_PRICE 		INT,
    BOOK_COUNT 		INT,
    AUTHOR 			VARCHAR(40),
    PUBLISHING_COM  VARCHAR(30),
    PUBLISHING_DATE VARCHAR(15),
    BOOK_IMAGE 		VARCHAR(16),			
    BOOK_CONTENT 	VARCHAR(500),
    DISCOUNT_RATE 	INT,				-- DEFAULT 10
    REG_DATE 		VARCHAR(50)
);

INSERT INTO BOOK VALUES(1, '100', '나미야 잡화점의 기적', 15000, 100, '히가시노 게이고', '현대문학', '2019-01-11', '01.jpg', '일본을 대표하는 소설가 히가시노 게이고의 신작', 10, now());
INSERT INTO BOOK VALUES(2, '100', '연필로 쓰기', 18000, 80, '김훈', '문학동네', '2018-02-14', '02.jpg', '70대의 김훈이 연필로 꾹꾹 눌러쓴 산문의 진경', 5, now());
INSERT INTO BOOK VALUES(3, '100', '인간 실격', 20000, 50, '다자이 오사무', '민음사', '2016-11-20', '03.jpg', '2019 상반기 종합 베스트셀러', 15, now());
INSERT INTO BOOK VALUES(4, '100', '기묘한 신혼여행', 12000, 30, '정태원', '문학의 문학', '2017-04-20', '04.jpg', '13편의 추리문학 걸작선', 20, now());
INSERT INTO BOOK VALUES(5, '200', '외국어 공부의 감각', 15000, 100, '아키야마 요헤이', '월북', '2011-03-11', '05.jpg', '나는 어떻게 10개 국어를 말하게 되었나?', 25, now());
INSERT INTO BOOK VALUES(6, '200', 'EBS 수능완성 고등 제2외국어', 28000, 200, 'EBS 편집부', 'EBS교육방송', '2014-08-14', '06.jpg', '2014 학년도 수능 연계교재', 10, now());
INSERT INTO BOOK VALUES(7, '200', '외국어 전파담', 23000, 70, '로버트 파우저', '혜화', '2012-10-03', '07.jpg', '외국어는 어디에서 어디로', 10, now());
INSERT INTO BOOK VALUES(8, '200', '아무튼,외국어', 22000, 50, '조지영', '위고', '2019-04-20', '08.jpg', '모든 나라에는 철수와 영희가 있다', 10, now());
INSERT INTO BOOK VALUES(9, '300', '컴퓨터 비전과 딥러닝', 35000, 700, '라쟈링가파 샨무갸마니', '에이콘', '2018-02-18', '09.jpg', '텐서플로와 케라스를 사용한 전문 가이드', 5, now());
INSERT INTO BOOK VALUES(10, '300', '쉽게 배우는 데이터 통신', 19000, 100, '박기현', '한빛아카데미', '2015-03-10', '10.jpg', '데이터 통신과 컴퓨터 네트워크', 10, now());
INSERT INTO BOOK VALUES(11, '300', '컴퓨터활용능력', 38000, 200, '이주희', '배움', '2012-09-24', '11.jpg', '컴퓨터 자격증', 20, now());
INSERT INTO BOOK VALUES(12, '300', '초등학생이 알아야할 숫자', 22000, 0, '엘리스 제임스', '어스본코리아', '2013-06-12', '12.jpg', '컴퓨터와 코디 100가지', 10, now());
SELECT * FROM BOOK;


CREATE TABLE BANK(
	ACCOUNT VARCHAR(30),
    BANK 	VARCHAR(10),
    NAME 	VARCHAR(10)
);

INSERT INTO BANK (account, bank, name) VALUES('11111-111-11111', '우리은행', '(주)코리아');
INSERT INTO BANK (account, bank, name) VALUES('22222-222-22222', '신한은행', '(주)코리아');
SELECT * FROM BANK;


CREATE TABLE CART (
	CART_ID 	INT AUTO_INCREMENT PRIMARY KEY,
    BUYER 		VARCHAR(50),
    BOOK_ID 	INT,
    BOOK_TITLE  VARCHAR(100),
    BUY_PRICE 	INT,
    BUY_COUNT 	INT,
    BOOK_IMAGE 	VARCHAR(16)			
);


CREATE TABLE BUY(
	BUY_ID 		   BIGINT ,
    BUYER 		   VARCHAR(50),
    BOOK_ID 	   VARCHAR(12),
    BOOK_TITLE     VARCHAR(100),
    BUY_PRICE      INT,
    BUY_COUNT      INT,
    BOOK_IMAGE 	   VARCHAR(16),			
    BUY_DATE 	   VARCHAR(50),
    ACCOUNT 	   VARCHAR(50),
    DELIVERYNAME   VARCHAR(10),
    DELIVERYTEL    VARCHAR(20),
    DELIVERYADRESS VARCHAR(10),
    SANCTION 	   VARCHAR(10)			
);


CREATE TABLE MEMBER(
	ID 		 VARCHAR(20),
    PASSWD 	 VARCHAR(16),
    NAME 	 VARCHAR(20),
    REG_DATE VARCHAR(30),
    ADDRESS  VARCHAR(100),
    TEL 	 VARCHAR(20)
);

INSERT INTO MEMBER VALUES('qwer@shop.com', '1111', '홍길동', now(), '서울특별시 강남대로 5-37 2층', '010-1234-5678');
SELECT * FROM MEMBER;


CREATE TABLE QNA(
	QNA_ID 		INT AUTO_INCREMENT PRIMARY KEY,
    BOOK_ID 	INT,
    BOOK_TITLE  VARCHAR(40),
    QNA_WRITER  VARCHAR(20),
    QNA_CONTENT VARCHAR(500),
    GROUP_ID 	INT,
    QORA 		INT,
    REPLY 		INT,
    REG_DATE 	DATETIME
);
