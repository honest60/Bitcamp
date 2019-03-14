import java.io.*;
import java.net.*;
import java.util.*;

public class Server{
	ServerSocket ss;
	Server2 sv2;
	int port=7879;
	ArrayList <SModule> list= new ArrayList<>();
	ArrayList <SModule2> list2= new ArrayList<>();
	int count;
	
	public Server(){
		init();
	}
	public void init() {
		try {
			ss=new ServerSocket(port);
			System.out.println("������ 7879�� ��Ʈ���� Ŭ���̾�Ʈ�� ��ٸ������Դϴ�...");
			while(true) {
				Socket s=ss.accept();
				SModule md=new SModule(this, s);
				new Thread(md).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void broadcast(String msg){
		for(SModule sm : list){
			sm.pw.println(msg);
		}
	}
	public static void main(String[] args) {
		new Server();
	}
}