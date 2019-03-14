import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends Thread{
	Socket s;
	String ip="127.0.0.1";
	String id;
	int port=7879, port2=7880;
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	InputStream is;
	OutputStream os;
	BufferedReader chatbr;
	PrintWriter pw;	
	
	Client(){
		init();		
	}
	public void init() {
		try {
			input();
			s=new Socket(ip,port);
			System.out.println("���� ���� �Ϸ�..");
			is=s.getInputStream();
			os=s.getOutputStream();
			chatbr=new BufferedReader(new InputStreamReader(is));
			pw=new PrintWriter(os, true);
			inputID();
			pw.println(id);
			String msg=chatbr.readLine();
			while(msg.equals("�߸��� ���̵��Դϴ�. ���̵� �ٽ� �Է����ֽʽÿ�!!")||msg.equals("���� ���̵� ���� �մϴ�. ���̵� �ٽ� �Է����ֽʽÿ�!!")){
				System.out.println(msg);
				inputID();
				//id=br.readLine();
				pw.println(id);
				msg=chatbr.readLine();
			}
			id=msg;
			this.start();
			speak();
		}catch(UnknownHostException uhe){
			System.out.println("�߸��� ���� �Դϴ�.");
			init();
		}catch(IOException ie){
			System.out.println("�ش� ������ ã�� ����");
		}
	}
	public void input(){
		try {
			System.out.print("�����Ϸ��� ���� IP�Է� : ");
			String ip=br.readLine();
			ip=ip.trim();
			if(!ip.equals("")||ip!=null) {
				this.ip=ip;
			}
		}catch(IOException ie){}
	}
	public void inputID() {
		try {
			System.out.print("ID�� �Է� : ");
			String id=br.readLine();
			id=id.trim();
			if(id.equals("")||id==null) {
				inputID();
				return;
			}
			this.id=id;
		}catch(IOException ie){}
	}
	public void run() {
		listen();
	}
	public void listen() {
		try {
			while(true) {
				String chatmsg=chatbr.readLine();
				System.out.println(chatmsg);
			}
		}catch (IOException ie){
			System.out.println("������ �������ϴ�... 2���Ŀ� �����ϰڽ��ϴ�.");
			try {
				Thread.sleep(1000);
				System.out.println(1);
				Thread.sleep(1000);
				System.out.println(2);
			}catch(InterruptedException ie2) {}
			System.exit(0);
		}
	}
	public void speak() {
		
		try{
			while(true) {
				String msg=br.readLine();
				if(msg!=null) pw.println(msg);
			}
		}catch(IOException ie) {}
	}
	
	public static void main(String[] args) {
		new Client();
	}
}
