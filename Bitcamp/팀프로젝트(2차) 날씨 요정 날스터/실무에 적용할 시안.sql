--ȸ������
insert into MEMBERT values(MEMBERT_SEQ.NEXTVAL,?,?,?,?,?,?,?);
--�α��� üũ
select pwd from MEMBERT where email=?;
--��������
insert into REGION_LALO values(?, ?, ?, ?, ?, ?);
--����-������ȸ->REGION�� insert (����/����)
insert into REGION values(?,?,?,?,AM, TO_CHAR(SYSDATE, 'YYYYMMDD'));
insert into REGION values(?,?,?,?,PM, TO_CHAR(SYSDATE, 'YYYYMMDD'));
--CLOTHING MATCH���� ������ ��������(����/����)
select * from CLOTHING_MATCH where DEGREEC <= ?+1 and DEGREEC >= ?-1 and MEM_NUM =?;
select * from CLOTHING_MATCH where DEGREEC < =?+1 and DEGREEC >= ?-1 and MEM_NUM !=?;
--�Խ��� �۾���
insert into CHATBOARD values(TO_CHAR(SYSDATE, 'YYYYMMDD'), ?,?, ?);
--�Խ��� �ҷ�����
select COMMENTC from CHATBOARD where REGION=? and TDATE = TO_CHAR(SYSDATE, 'YYYYMMDD');
--SYSDATE���� �����ϸ� ������ ��
--���� �� �����
select * from FEELING_TABLE;
--���� �ڵ� ����
insert into CLOTHING_MATCH values(?,?,?,?,TO_CHAR(SYSDATE, 'YYYYMMDD'),?,?,?,?,?);




/*
�׽���
--ȸ������
insert into MEMBERT values(MEMBERT_SEQ.NEXTVAL,'���� ���빮��', 20, 'nnn@gmail.com', '����', 'nana', '��', '������ �� Ž');

--�α��� üũ
select PWD from MEMBERT where EMAIL='nnn@gmail.com';
--��������
insert into REGION_LALO values('���� ���빮��', 1, 1, '���빮��', 100, 200);
insert into REGION_LALO values('���� ��걸', 2, 2, '���� ��걸', 200, 300);
--����-������ȸ->REGION�� insert (����/����)
insert into REGION values(23, 5, '�ϱ��� ŭ', '���� ���빮��', 'AM', TO_CHAR(SYSDATE, 'YYYYMMDD'));
insert into REGION values(22, 4, '���ῡ ���', '���� ��걸', 'PM', TO_CHAR(SYSDATE, 'YYYYMMDD'));
--�Խ��� �۾���
insert into CHATBOARD values(TO_CHAR(SYSDATE, 'YYYYMMDD'),2, '���� ���빮��','���� �����׾��');
--���� �ڵ� ����
insert into CLOTHING_MATCH values(23, 5, '�ϱ��� ŭ',TO_CHAR(SYSDATE, 'YYYYMMDD'),'���� ���빮��',2,'��Ʈ', '���ǽ�', '�����', '����', '�ణ ������');
--CLOTHING MATCH���� ������ ��������(����/����)
select * from CLOTHING_MATCH where DEGREEC <= 23+1 and DEGREEC>=23-1 and MEM_NUM =2; 

--�Խ��� �ҷ�����

select COMMENTC from CHATBOARD where REGION='���� ���빮��' and TDATE=TO_CHAR(SYSDATE, 'YYYYMMDD');
--���� �� �����
select * from FEELING_TABLE;
///////////////////////////////////////
*/