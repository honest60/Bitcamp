/*위도 경도 테이블 정보
reg_name	시군구명	p.k	nn	VARCHAR2	30
reg_num	순번		null	NUMBER	15
reg_code	시군구코드		null	NUMBER	30
reg_ename	시군구명영어		null	VARCHAR2	30
longitude	경도		nn	NUMBER	20
latitude	위도		nn	NUMBER	20  
*/

insert into REGION_LALO values( '도봉구', REGION_LALO_SEQ.NEXTVAL, 11320, 'Dobong-gu', 127.0317674, 37.6658609 );
insert into REGION_LALO values( '은평구', REGION_LALO_SEQ.NEXTVAL, 11380, 'Eunpyeong-gu', 126.9227004, 37.6176125 );
insert into REGION_LALO values( '동대문구', REGION_LALO_SEQ.NEXTVAL, 11230, 'Dongdaemun-gu', 127.0507003, 37.5838012 );
insert into REGION_LALO values( '동작구', REGION_LALO_SEQ.NEXTVAL, 11590, 'Dongjak-gu', 126.9443073,  37.4965037);
insert into REGION_LALO values( '금천구', REGION_LALO_SEQ.NEXTVAL, 11545, 'Geumcheon-gu', 126.9001546,  37.4600969);
insert into REGION_LALO values( '구로구', REGION_LALO_SEQ.NEXTVAL, 11530, 'Guro-gu', 126.858121,  37.4954856);
insert into REGION_LALO values( '종로구', REGION_LALO_SEQ.NEXTVAL, 11110, 'Jongno-gu', 126.9861493,  37.5990998);
insert into REGION_LALO values( '강북구', REGION_LALO_SEQ.NEXTVAL, 11305, 'Gangbuk-gu', 127.0147158,  6469954);
insert into REGION_LALO values( '중랑구', REGION_LALO_SEQ.NEXTVAL, 11260, 'Jungnang-gu', 127.0939669,  5953795);
insert into REGION_LALO values( '강남구', REGION_LALO_SEQ.NEXTVAL, 11680, 'Gangnam-gu', 127.0664091,  37.4959854);
insert into REGION_LALO values( '강서구', REGION_LALO_SEQ.NEXTVAL, 11500, 'Gangseo-gu', 126.8226561,  37.5657617);
insert into REGION_LALO values( '중구', REGION_LALO_SEQ.NEXTVAL, 11140, 'Jung-gu', 126.9941904, 37.5579452);
insert into REGION_LALO values( '강동구', REGION_LALO_SEQ.NEXTVAL, 11740, 'Gangdong-gu', 127.1464824,  37.5492077);
insert into REGION_LALO values( '광진구', REGION_LALO_SEQ.NEXTVAL, 11215, 'Gwangjin-gu', 127.0857528,  37.5481445);
insert into REGION_LALO values( '마포구', REGION_LALO_SEQ.NEXTVAL, 11440, 'Mapo-gu', 126.9087803,  37.5622906);
insert into REGION_LALO values( '서초구', REGION_LALO_SEQ.NEXTVAL, 11650, 'Seocho-gu', 127.0378103,  37.4769528);
insert into REGION_LALO values( '성북구', REGION_LALO_SEQ.NEXTVAL, 11290, 'Seongbuk-gu', 127.0232185,  37.606991);
insert into REGION_LALO values( '노원구', REGION_LALO_SEQ.NEXTVAL, 11350, 'Nowon-gu', 127.0771201,  37.655264);
insert into REGION_LALO values( '송파구', REGION_LALO_SEQ.NEXTVAL, 11710, 'Songpa-gu', 127.1144822,  37.5048534);
insert into REGION_LALO values( '서대문구', REGION_LALO_SEQ.NEXTVAL, 11410, 'Seodaemun-gu', 126.9356665,  37.5820369);
insert into REGION_LALO values( '양천구', REGION_LALO_SEQ.NEXTVAL, 11470, 'Yangcheon-gu', 126.8561534,  37.5270616);
insert into REGION_LALO values( '영등포구', REGION_LALO_SEQ.NEXTVAL, 11560, 'Yeongdeungpo-gu', 126.9139242,  37.520641);
insert into REGION_LALO values( '관악구', REGION_LALO_SEQ.NEXTVAL, 11620, 'Gwanak-gu', 126.9438071,  37.4653993);
insert into REGION_LALO values( '성동구', REGION_LALO_SEQ.NEXTVAL, 11200, 'Seongdong-gu', 127.0409622,  37.5506753);
insert into REGION_LALO values( '용산구', REGION_LALO_SEQ.NEXTVAL, 11170, 'Yongsan-gu', 126.9810742,  37.5311008);


//옷 테이블 정보 ( 분류, 이름 )
insert into CLOTHING values( '상의', '민소매');
insert into CLOTHING values( '상의', '반팔');
insert into CLOTHING values( '상의', '긴팔');
insert into CLOTHING values( '상의', '셔츠');
insert into CLOTHING values( '상의', '블라우스');
insert into CLOTHING values( '상의', '남방');
insert into CLOTHING values( '상의', '조끼');
insert into CLOTHING values( '상의', '니트');
insert into CLOTHING values( '상의', '가디건');
insert into CLOTHING values( '상의', '원피스');
insert into CLOTHING values( '상의', '후드');
insert into CLOTHING values( '상의', '목폴라');
insert into CLOTHING values( '상의', '히트텍');

insert into CLOTHING values( '하의', '반바지');
insert into CLOTHING values( '하의', '면바지');
insert into CLOTHING values( '하의', '청바지');
insert into CLOTHING values( '하의', '기모바지');
insert into CLOTHING values( '하의', '슬랙스');
insert into CLOTHING values( '하의', '살색스타킹');
insert into CLOTHING values( '하의', '기모스타킹');
insert into CLOTHING values( '하의', '히트텍');
insert into CLOTHING values( '하의', '치마');
insert into CLOTHING values( '하의', '트레이닝복');

insert into CLOTHING values( '아우터', '자켓');
insert into CLOTHING values( '아우터', '후드집업');
insert into CLOTHING values( '아우터', '간절기 야상');
insert into CLOTHING values( '아우터', '트랜치코트');
insert into CLOTHING values( '아우터', '라이더자켓');
insert into CLOTHING values( '아우터', '후리스');
insert into CLOTHING values( '아우터', '야구잠바');
insert into CLOTHING values( '아우터', '야상');
insert into CLOTHING values( '아우터', '무스탕');
insert into CLOTHING values( '아우터', '숏패딩');
insert into CLOTHING values( '아우터', '코트');
insert into CLOTHING values( '아우터', '롱패딩');

insert into CLOTHING values( 'ETC', '손풍기');
insert into CLOTHING values( 'ETC', '부채');
insert into CLOTHING values( 'ETC', '양산');
insert into CLOTHING values( 'ETC', '우산');
insert into CLOTHING values( 'ETC', '캡모자');
insert into CLOTHING values( 'ETC', '목도리');
insert into CLOTHING values( 'ETC', '장갑');
insert into CLOTHING values( 'ETC', '핫팩');
insert into CLOTHING values( 'ETC', '귀도리');
insert into CLOTHING values( 'ETC', '털모자');
insert into CLOTHING values( 'ETC', '수면양말');

insert into FEELING_TABLE values( '딱 좋았어요' );
insert into FEELING_TABLE values( '조금 더웠어요' );
insert into FEELING_TABLE values( '너무 더웠어요' );
insert into FEELING_TABLE values( '조금 추웠어요' );
insert into FEELING_TABLE values( '너무 추웠어요' );



/*  회원정보 [실무에선 회훤번호 MEM_NUM.nextval] */
insert into MEMBERT values(4, '서울시 구로구', 35, 'jjang123@naver.com', 
'짱짱맨', '1234', '남', '더위를 잘 탐');
insert into MEMBERT values(5, '서울시 도봉구', 26, 'mmm123@naver.com', 
'귀요미', '2345', '남', '추위를 잘 탐');
insert into MEMBERT values(6, '서울시 종로구', 44, 'nam123@naver.com', 
'멋쟁이', '3456', '남', '땀이 많음');

/* 코디정보 */
insert into CLOTHING_MATCH values(20, 5, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 4, '민소매', '반바지', '자켓', '손풍기',
'딱 좋았어요', 'good1.jpg');
 insert into CLOTHING_MATCH values(20, 15, '눈', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 용산구', 4, '반팔', '면바지', '후드집업', '부채',
'조금 더웠어요', 'good2.jpg');
insert into CLOTHING_MATCH values(10, 5, '황사', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 4, '긴팔', '청바지', '간절기 야상', '양산',
'너무 더웠어요', 'good3.jpg');
insert into CLOTHING_MATCH values(20, 15, '미세먼지', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 관악구', 4, '셔츠', '기모바지', '트랜치코트', '우산',
'딱 좋았어요', 'good4.jpg');
insert into CLOTHING_MATCH values(16, 5, '맑음', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 송파구', 4, '블라우스', '슬렉스', '라이더자켓', '캡모자',
'조금 더웠어요', 'good5.jpg');
insert into CLOTHING_MATCH values(19, 20, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 4, '남방', '살색스타킹', '후리스', '목도리',
'너무 더웠어요', 'good6.jpg');
insert into CLOTHING_MATCH values(5, 5, '눈', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 종로구', 4, '조끼', '기모스타킹', '야구잠바', '장갑',
'조금 추웠어요', 'good7.jpg');
insert into CLOTHING_MATCH values(0, 0, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 마포구', 4, '니트', '히트텍', '야상', '핫팩',
'너무 추웠어요', 'good8.jpg');

insert into CLOTHING_MATCH values(20, 5, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 5, '민소매', '반바지', '자켓', '손풍기',
'딱 좋았어요', 'nam1.jpg');
 insert into CLOTHING_MATCH values(20, 15, '눈', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 용산구', 5, '반팔', '면바지', '후드집업', '부채',
'조금 더웠어요', 'nam2.jpg');
insert into CLOTHING_MATCH values(10, 5, '황사', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 5, '긴팔', '청바지', '간절기 야상', '양산',
'너무 더웠어요', 'nam3.jpg');
insert into CLOTHING_MATCH values(20, 15, '미세먼지', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 관악구', 5, '셔츠', '기모바지', '트랜치코트', '우산',
'딱 좋았어요', 'nam4.jpg');
insert into CLOTHING_MATCH values(16, 5, '맑음', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 송파구', 5, '블라우스', '슬렉스', '라이더자켓', '캡모자',
'조금 더웠어요', 'nam5.jpg');
insert into CLOTHING_MATCH values(19, 20, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 5, '남방', '살색스타킹', '후리스', '목도리',
'너무 더웠어요', 'nam6.jpg');
insert into CLOTHING_MATCH values(5, 5, '눈', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 종로구', 5, '조끼', '기모스타킹', '야구잠바', '장갑',
'조금 추웠어요', 'nam7.jpg');
insert into CLOTHING_MATCH values(0, 0, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 마포구', 5, '니트', '히트텍', '야상', '핫팩',
'너무 추웠어요', 'nam8.jpg');

insert into CLOTHING_MATCH values(20, 5, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 6, '민소매', '반바지', '자켓', '손풍기',
'딱 좋았어요', 'mm1.jpg');
 insert into CLOTHING_MATCH values(20, 15, '눈', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 용산구', 6, '반팔', '면바지', '후드집업', '부채',
'조금 더웠어요', 'mm2.jpg');
insert into CLOTHING_MATCH values(10, 5, '황사', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 6, '긴팔', '청바지', '간절기 야상', '양산',
'너무 더웠어요', 'mm3.jpg');
insert into CLOTHING_MATCH values(20, 15, '미세먼지', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 관악구', 6, '셔츠', '기모바지', '트랜치코트', '우산',
'딱 좋았어요', 'mm4.jpg');
insert into CLOTHING_MATCH values(16, 5, '맑음', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 송파구', 6, '블라우스', '슬렉스', '라이더자켓', '캡모자',
'조금 더웠어요', 'mm5.jpg');
insert into CLOTHING_MATCH values(19, 20, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 6, '남방', '살색스타킹', '후리스', '목도리',
'너무 더웠어요', 'mm6.jpg');
insert into CLOTHING_MATCH values(5, 5, '눈', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 종로구', 6, '조끼', '기모스타킹', '야구잠바', '장갑',
'조금 추웠어요', 'mm7.jpg');
insert into CLOTHING_MATCH values(0, 0, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 마포구', 6, '니트', '히트텍', '야상', '핫팩',
'너무 추웠어요', 'mm8.jpg');


//회원 insert문 예시
//insert into MEMBERT values(MEMBERT_SEQ.NEXTVAL,'서울 서대문구', 20, 'nnn@gmail.com', '나나', 'nana', '여', '더위를 잘 탐');
//insert into MEMBERT values(MEMBERT_SEQ.NEXTVAL,'광주 광산구', 30, 'devil@gmail.com', '구구', '9toNine', '여', '추위를 잘 탐');
//insert into MEMBERT values(MEMBERT_SEQ.NEXTVAL,'김포 장기동', 22, 'good@gmail.com', 's2나비', 'Btfly', '여', '보통');

//나나 봄
insert into CLOTHING_MATCH values(23, 3, '일교차 큼',TO_DATE('20180402','YYYY-MM-DD'),'서울 서대문구',1,'니트', '원피스', '가디건', '없음', '적당함',  '1_180402.jpg');
insert into CLOTHING_MATCH values(24, 3, '따스함',TO_DATE('20180403','YYYY-MM-DD'),'서울 서대문구',1,'맨투맨', '청바지', '가디건', '없음', '약간 더웠음',  '1_180403.jpg');

//나나 여름
insert into CLOTHING_MATCH values(31, 2, '습함',TO_DATE('20180403','YYYY-MM-DD'),20180808,'서울 서대문구',1,'민소매', '반바지', '없음', '손풍기', '너무 더웠음', '1_180808.jpg');
insert into CLOTHING_MATCH values(30, 3, '습함',TO_DATE('20180403','YYYY-MM-DD'),20180809,'서울 서대문구',1, '없음', '원피스', '없음', '손풍기', '약간 더웠음', '1_180809.jpg');

//나나 가을
insert into CLOTHING_MATCH values(21, 5, '일교차 큼',TO_DATE('20180403','YYYY-MM-DD'),20181011,'서울 서대문구',1,'맨투맨', '반바지', '청자켓', '없음', '낮에 약간 더움', '1_181011.jpg');
insert into CLOTHING_MATCH values(20, 4, '건조함',TO_DATE('20180403','YYYY-MM-DD'),20181012,'서울 서대문구',1,'셔츠', '슬랙스', '가디건', '없음', '낮에 약간 더움', '1_181012.jpg');

//나나 겨울
insert into CLOTHING_MATCH values(11, 1, '건조함',TO_DATE('20180403','YYYY-MM-DD'),20181203,'서울 서대문구',1,'니트', '스키니진', '패딩', '없음', '낮에 추웠음', '1_181203.jpg');
insert into CLOTHING_MATCH values(10, 1, '건조함',TO_DATE('20180403','YYYY-MM-DD'),20181204,'서울 서대문구',1,'니트', '스키니진', '패딩', '없음', '낮에 추웠음', '1_181204.jpg');

//구구 봄
insert into CLOTHING_MATCH values(23, 3, '일교차 큼',TO_DATE('20180403','YYYY-MM-DD'),20180402,'광주 광산구',2,'블라우스', '청바지', '가디건', '없음', '약간 더웠음',  '2_180402.jpg');
insert into CLOTHING_MATCH values(25, 3, '따스함',TO_DATE('20180403','YYYY-MM-DD'),20180403,'광주 광산구',2,'없음', '원피스', '자켓', '없음', '적당함',  '2_180403.jpg');
-------------밑에부터 하기
//구구 여름
insert into CLOTHING_MATCH values(31, 1, '습함',TO_DATE('20180403','YYYY-MM-DD'),20180808,'광주 광산구',2,'반팔', '반바지', '없음', '손풍기', '땀이 많이 남', '2_180808.jpg');
insert into CLOTHING_MATCH values(28, 3, '습함',TO_DATE('20180403','YYYY-MM-DD'),20180809,'광주 광산구',2, '반팔', '긴 치마', '없음', '손풍기', '비와서 괜찮았음', '2_180809.jpg');

//구구 가을
insert into CLOTHING_MATCH values(20, 3, '일교차 큼',TO_DATE('20180403','YYYY-MM-DD'),20181011,'광주 광산구',2,'니트', '슬랙스', '트렌치코트', '없음', '적당함', '2_181011.jpg');
insert into CLOTHING_MATCH values(21, 3, '건조함',TO_DATE('20180403','YYYY-MM-DD'),20181012,'광주 광산구',2,'긴팔', '슬랙스', '가디건', '없음', '낮에 약간 더움', '2_181012.jpg');

//구구 겨울
insert into CLOTHING_MATCH values(11, 4, '바람이 세게 붊',TO_DATE('20181203','YYYY-MM-DD'),'광주 광산구',2,'니트', '니트치마', '롱패딩', '없음', '적당함', '2_181203.jpg');
insert into CLOTHING_MATCH values(10, 1, '건조함',TO_DATE('20181204','YYYY-MM-DD'),'광주 광산구',2,'맨투맨', '트레이닝복', '숏패딩', '없음', '낮에 추웠음', '2_181204.jpg');

//s2나비 봄
insert into CLOTHING_MATCH values(21, 2, '일교차 큼',TO_DATE('20180402','YYYY-MM-DD'),'김포 장기동',3,'남방', '슬랙스', '자켓', '없음', '적당함',  '3_180402.jpg');
insert into CLOTHING_MATCH values(22, 2, '따스함',TO_DATE('20180403','YYYY-MM-DD'),'김포 장기동',3,'긴팔, 조끼', '청바지', '가디건', '없음', '약간 더웠음',  '3_180403.jpg');

//s2나비 여름
insert into CLOTHING_MATCH values(31, 2, '습함',TO_DATE('20180808','YYYY-MM-DD'),'김포 장기동',3,'민소매', '반바지', '없음', '손풍기', '너무 더웠음', '3_180808.jpg');
insert into CLOTHING_MATCH values(30, 3, '습함',TO_DATE('20180809','YYYY-MM-DD'),'김포 장기동',3, '반팔', '원피스', '없음', '손풍기', '괜히 겹쳐입음', '3_180809.jpg');

//s2나비 가을
insert into CLOTHING_MATCH values(21, 5, '일교차 큼',TO_DATE('20181011','YYYY-MM-DD'),'김포 장기동',3,'맨투맨', '반바지', '야상', '없음', '낮에 약간 더움', '3_181011.jpg');
insert into CLOTHING_MATCH values(20, 4, '건조함',TO_DATE('20181012','YYYY-MM-DD'),'김포 장기동',3,'셔츠', '슬랙스', '라이더자켓', '없음', '따뜻함', '3_181012.jpg');

//s2나비 겨울
insert into CLOTHING_MATCH values(11, 1, '건조함',TO_DATE('20181203','YYYY-MM-DD'),'김포 장기동',3,'목폴라, 맨투맨', '스키니진', '무스탕', '없음', '낮에 추웠음', 
'3_181203.jpg');
insert into CLOTHING_MATCH values(10, 1, '건조함',TO_DATE('20181204','YYYY-MM-DD'),'김포 장기동',3,'히트텍, 남방', '면바지', '패딩', '없음', '낮에 추웠음', '3_181204.jpg');