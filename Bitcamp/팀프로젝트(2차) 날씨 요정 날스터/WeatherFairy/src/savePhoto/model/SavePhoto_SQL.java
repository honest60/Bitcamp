package savePhoto.model;

public class SavePhoto_SQL {
// 코디정보
// 기온, 풍속, 특성, 날짜, 시군구명, 회원번호, 옷(상의), 옷(하의), 옷(겉옷), 옷(기타), 느낌, 사진이름
/*
 insert into CLOTHING_MATCH values(20, 5, '비', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'서울시 서초구', 4, '민소매', '반바지', '자켓', '손풍기',
'딱 좋았어요', 'good1.jpg');	
 */
// 옷 ( 분류, 이름 ) => 상의 하의 등 분류해서 저장한 값 코디정보에 대입	

	final static String insert = "insert into CLOTHING_MATCH values( ?, ?, ?,TO_CHAR(SYSDATE, 'YYYYMMDD'), ?, ?, ?, ?, ?, ?, ?, ? )";		
//		insert into CLOTHING_MATCH values( -5, 1, 'Clear', TO_CHAR(SYSDATE, 'YYYYMMDD'), '서울', 6, '긴팔 셔츠', '청바지', '간절기 야상', '우산', '너무 더웠어요', ' 20181121143946_ips.txt');
}
