DROP TABLE FILES cascade constraints;
DROP TABLE MEMBER cascade constraints;
DROP TABLE BOARD cascade constraints;
DROP TABLE MUTE cascade constraints;
DROP TABLE AUTHORITY cascade constraints;
DROP TABLE LIKES cascade constraints;
DROP TABLE BLOCK cascade constraints;
DROP TABLE NOTE cascade constraints;
DROP TABLE FOLLOWING cascade constraints;
DROP TABLE FOLLOWER cascade constraints;

DROP SEQUENCE MEMBER_SEQ;
DROP SEQUENCE BOARD_SEQ;
DROP SEQUENCE NOTE_SEQ;
DROP SEQUENCE FILES_SEQ;

CREATE TABLE FILES(
	F_NO NUMBER(15) CONSTRAINT F_NO_PK primary key NOT NULL, 
	F_NAME VARCHAR2(50) NOT NULL,
	F_SAVENAME VARCHAR2(60) CONSTRAINT F_SAVENAME_UNI UNIQUE NOT NULL, 
	F_TYPE VARCHAR2(10) NOT NULL,
	F_SIZE NUMBER(8) NOT NULL
);

CREATE TABLE MEMBER( 
	M_NO NUMBER(11) CONSTRAINT M_NO_PK primary key NOT NULL, 
	M_ID VARCHAR2(30) CONSTRAINT M_ID_UNI UNIQUE NOT NULL, 
	M_NAME VARCHAR2(15) NOT NULL, 
	M_EMAIL VARCHAR2(30) CONSTRAINT M_EMAIL_UNI UNIQUE, 
	M_PHONE NUMBER(11) CONSTRAINT M_PHONE_UNI UNIQUE, 
	M_PASSWORD VARCHAR2(15) NOT NULL, 
	M_PROFILE CONSTRAINT MEM_F_NO_FK references FILES(F_NO) on delete cascade,
    M_HEADER CONSTRAINT MEM_F_NO_FK2 references FILES(F_NO) on delete cascade,
	M_DELETE NUMBER(2), 
	M_RDATE DATE NOT NULL
); 

CREATE TABLE AUTHORITY(
	M_NO CONSTRAINT AUTH_M_NO_PK primary key CONSTRAINT AUTH_M_NO_FK  
		references MEMBER(M_NO) NOT NULL,
	A_ENABLED CHAR(1) NOT NULL,
	A_AUTH VARCHAR2(15) NOT NULL
);

CREATE TABLE BOARD( 
	B_NO NUMBER(15) CONSTRAINT B_NO_PK primary key NOT NULL, 
	M_NO CONSTRAINT B_M_NO_FK 
		references MEMBER(M_NO) on delete cascade NOT NULL, 
	F_NO CONSTRAINT B_F_NO_FK references FILES(F_NO) on delete cascade,
	B_CONTENT VARCHAR2(420) NOT NULL, 
	B_REBUZ NUMBER(11) NOT NULL, 
	B_LIKE NUMBER(11) NOT NULL,
	B_REF NUMBER(15) NOT NULL, 
	B_RDATE DATE NOT NULL
); 

CREATE TABLE MUTE(    
	M_NO CONSTRAINT MU_M_NO_FK references MEMBER(M_NO) on delete cascade  NOT NULL,
	B_NO CONSTRAINT MU_B_NO_FK references BOARD(B_NO) on delete cascade  NOT NULL,
    CONSTRAINT MU_NO_PK PRIMARY KEY (M_NO,B_NO)    
);

CREATE TABLE LIKES(
	B_NO CONSTRAINT L_B_NO_FK
		references BOARD(B_NO) on delete cascade NOT NULL,
	M_NO CONSTRAINT L_M_NO_FK 
		references MEMBER(M_NO) on delete cascade NOT NULL,
	CONSTRAINT LK_M_NO_PK primary key (M_NO, B_NO)  
);

CREATE TABLE BLOCK(
	M_NO CONSTRAINT BL_M_NO_FK references MEMBER(M_NO) on delete cascade NOT NULL,
	M_NO2 CONSTRAINT BL_M_NO2_FK references MEMBER(M_NO) on delete cascade NOT NULL,
    CONSTRAINT BL_M_NO_PK primary key (M_NO, M_NO2)        
);

CREATE TABLE NOTE(
	N_NO NUMBER(15) CONSTRAINT N_NO_PK primary key NOT NULL,
	M_NO CONSTRAINT N_M_NO_FK
		references MEMBER(M_NO) on delete cascade NOT NULL,
	M_NO2 CONSTRAINT N_M_NO2_FK
		references MEMBER(M_NO) on delete cascade NOT NULL,
	F_NO CONSTRAINT N_F_NO_FK references FILES(F_NO) on delete cascade,
	N_CONTENT VARCHAR2(1000) NOT NULL,
	N_RDATE DATE NOT NULL
);

CREATE TABLE FOLLOWING(
	M_NO CONSTRAINT FG_M_NO_FK references MEMBER(M_NO) on delete cascade NOT NULL,
	M_NO2 CONSTRAINT FG_M_NO2_FK references MEMBER(M_NO) on delete cascade NOT NULL,
	MUTE VARCHAR2(1) NOT NULL,
    CONSTRAINT FG_M_NO_PK primary key (M_NO, M_NO2)
);

CREATE TABLE FOLLOWER(
	M_NO CONSTRAINT FR_M_NO_FK references MEMBER(M_NO) on delete cascade NOT NULL,
	M_NO2 CONSTRAINT FR_M_NO2_FK references MEMBER(M_NO) on delete cascade NOT NULL,
    CONSTRAINT FR_M_NO_PK primary key (M_NO, M_NO2)
);

CREATE SEQUENCE	MEMBER_SEQ START WITH 0 
INCREMENT BY 1 MINVALUE 0 NOCACHE NOCYCLE;   
CREATE SEQUENCE	BOARD_SEQ START WITH 0 
INCREMENT BY 1 MINVALUE 0 NOCACHE NOCYCLE; 
CREATE SEQUENCE	NOTE_SEQ START WITH 0 
INCREMENT BY 1 MINVALUE 0 NOCACHE NOCYCLE; 
CREATE SEQUENCE	FILES_SEQ START WITH 0 
INCREMENT BY 1 MINVALUE 0 NOCACHE NOCYCLE; 

insert into FILES values(FILES_SEQ.nextval, 'mb.png', 'mp.png', 'mp.png', 1);
insert into FILES values(FILES_SEQ.nextval, 'b', 'b', 'b', 1);
insert into FILES values(FILES_SEQ.nextval, 'c', 'c', 'c', 1);
insert into FILES values(FILES_SEQ.nextval, 'd', 'd', 'd', 1);

insert into MEMBER values(MEMBER_SEQ.nextval, 'admin', '全辨悼', 'aa@aa.aa', 01012344321, '{noop}admin', 1, 1, null, SYSDATE);
insert into MEMBER values(MEMBER_SEQ.nextval, 'user1', '全辨辑', 'bb@aa.aa', 01012344322, '{noop}1234', 2, 2, null, SYSDATE);
insert into MEMBER values(MEMBER_SEQ.nextval, 'user2', '全辨巢', 'cc@aa.aa', 01012344323, '{noop}1234', 3, 3, null, SYSDATE);
insert into MEMBER values(MEMBER_SEQ.nextval, 'user3', '全辨合', 'dd@aa.aa', 01012344324, '{noop}1234', 4, 4, null, SYSDATE);

insert into AUTHORITY values(1, 1, 'ROLE_ADMIN');
insert into AUTHORITY values(2, 1, 'ROLE_USER');
insert into AUTHORITY values(3, 1, 'ROLE_USER');
insert into AUTHORITY values(4, 1, 'ROLE_USER');

insert into FOLLOWER values(1, 2);
insert into FOLLOWING values(2, 1, 'N');
insert into FOLLOWING values(1, 3, 'N');
insert into FOLLOWER values(3, 1);

commit;

desc FILES;
desc MEMBER;
desc BOARD;
desc MUTE;
desc AUTHORITY;
desc LIKES;
desc BLOCK;
desc NOTE;
desc FOLLOWING;
desc FOLLOWER;

select * from ALL_CONSTRAINTS where TABLE_NAME='FILES';
select * from ALL_CONSTRAINTS where TABLE_NAME='MEMBER';
select * from ALL_CONSTRAINTS where TABLE_NAME='BOARD';
select * from ALL_CONSTRAINTS where TABLE_NAME='MUTE';
select * from ALL_CONSTRAINTS where TABLE_NAME='AUTHORITY';
select * from ALL_CONSTRAINTS where TABLE_NAME='LIKES';
select * from ALL_CONSTRAINTS where TABLE_NAME='BLOCK';
select * from ALL_CONSTRAINTS where TABLE_NAME='NOTE';
select * from ALL_CONSTRAINTS where TABLE_NAME='FOLLOWING';
select * from ALL_CONSTRAINTS where TABLE_NAME='FOLLOWER';