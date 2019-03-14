import java.io.*;
import java.net.*;


public class SModule2 implements Runnable {
	Socket s;
	Server sv;
	InputStream is;
	BufferedReader br;
	OutputStream os;
	PrintWriter pw;
	String id;
	int i;
	
	public SModule2(Server sv,String id){
		this.sv=sv;
		this.id=id;
	}
	public int init() {
		try {
			is=s.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			os=s.getOutputStream();
			pw=new PrintWriter(os, true);
		}catch(IOException ie) {
			try {
				br.close();
				pw.close();
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return -1;
		}
		return 1;
	}
	public void run() {
		listen();
	}
	public void listen() {
		try{
			while(true) {
				String msg = br.readLine();
				if(msg!=null) broadcast(this.id+"¹ø"+ msg);
			}
		}catch(IOException ie){
			sv.list2.remove(this);
			broadcast(id + "GameOut");
		}
	}
	public void broadcast(String msg){
		for(SModule2 sm2 : sv.list2){
			sm2.pw.println(msg);
		}
	}
	public void sendMsg(String msg){
		pw.println(msg);
	}
}