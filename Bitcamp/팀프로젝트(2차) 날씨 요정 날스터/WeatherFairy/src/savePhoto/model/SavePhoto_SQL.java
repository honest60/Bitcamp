package savePhoto.model;

public class SavePhoto_SQL {
// �ڵ�����
// ���, ǳ��, Ư��, ��¥, �ñ�����, ȸ����ȣ, ��(����), ��(����), ��(�ѿ�), ��(��Ÿ), ����, �����̸�
/*
 insert into CLOTHING_MATCH values(20, 5, '��', TO_CHAR(SYSDATE, 'YYYYMMDD'),
'����� ���ʱ�', 4, '�μҸ�', '�ݹ���', '����', '��ǳ��',
'�� ���Ҿ��', 'good1.jpg');	
 */
// �� ( �з�, �̸� ) => ���� ���� �� �з��ؼ� ������ �� �ڵ������� ����	

	final static String insert = "insert into CLOTHING_MATCH values( ?, ?, ?,TO_CHAR(SYSDATE, 'YYYYMMDD'), ?, ?, ?, ?, ?, ?, ?, ? )";		
//		insert into CLOTHING_MATCH values( -5, 1, 'Clear', TO_CHAR(SYSDATE, 'YYYYMMDD'), '����', 6, '���� ����', 'û����', '������ �߻�', '���', '�ʹ� �������', ' 20181121143946_ips.txt');
}
