--회원가입
insert into MEMBERT values(MEMBERT_SEQ.NEXTVAL,?,?,?,?,?,?,?);
--로그인 체크
select pwd from MEMBERT where email=?;
--지역정보
insert into REGION_LALO values(?, ?, ?, ?, ?, ?);
--지역-날씨조회->REGION에 insert (오전/오후)
insert into REGION values(?,?,?,?,AM, TO_CHAR(SYSDATE, 'YYYYMMDD'));
insert into REGION values(?,?,?,?,PM, TO_CHAR(SYSDATE, 'YYYYMMDD'));
--CLOTHING MATCH에서 옷차림 가져오기(오전/오후)
select * from CLOTHING_MATCH where DEGREEC <= ?+1 and DEGREEC >= ?-1 and MEM_NUM =?;
select * from CLOTHING_MATCH where DEGREEC < =?+1 and DEGREEC >= ?-1 and MEM_NUM !=?;
--게시판 글쓰기
insert into CHATBOARD values(TO_CHAR(SYSDATE, 'YYYYMMDD'), ?,?, ?);
--게시판 불러오기
select COMMENTC from CHATBOARD where REGION=? and TDATE = TO_CHAR(SYSDATE, 'YYYYMMDD');
--SYSDATE에서 연월일만 꺼내는 법
--오늘 평가 물어보기
select * from FEELING_TABLE;
--오늘 코디 저장
insert into CLOTHING_MATCH values(?,?,?,?,TO_CHAR(SYSDATE, 'YYYYMMDD'),?,?,?,?,?);




/*
테스터
--회원가입
insert into MEMBERT values(MEMBERT_SEQ.NEXTVAL,'서울 서대문구', 20, 'nnn@gmail.com', '나나', 'nana', '여', '더위를 잘 탐');

--로그인 체크
select PWD from MEMBERT where EMAIL='nnn@gmail.com';
--지역정보
insert into REGION_LALO values('서울 서대문구', 1, 1, '서대문구', 100, 200);
insert into REGION_LALO values('서울 용산구', 2, 2, '서울 용산구', 200, 300);
--지역-날씨조회->REGION에 insert (오전/오후)
insert into REGION values(23, 5, '일교차 큼', '서울 서대문구', 'AM', TO_CHAR(SYSDATE, 'YYYYMMDD'));
insert into REGION values(22, 4, '저녁에 비옴', '서울 용산구', 'PM', TO_CHAR(SYSDATE, 'YYYYMMDD'));
--게시판 글쓰기
insert into CHATBOARD values(TO_CHAR(SYSDATE, 'YYYYMMDD'),2, '서울 서대문구','버스 더워죽어요');
--오늘 코디 저장
insert into CLOTHING_MATCH values(23, 5, '일교차 큼',TO_CHAR(SYSDATE, 'YYYYMMDD'),'서울 서대문구',2,'니트', '원피스', '가디건', '없음', '약간 더웠음');
--CLOTHING MATCH에서 옷차림 가져오기(오전/오후)
select * from CLOTHING_MATCH where DEGREEC <= 23+1 and DEGREEC>=23-1 and MEM_NUM =2; 

--게시판 불러오기

select COMMENTC from CHATBOARD where REGION='서울 서대문구' and TDATE=TO_CHAR(SYSDATE, 'YYYYMMDD');
--오늘 평가 물어보기
select * from FEELING_TABLE;
///////////////////////////////////////
*/