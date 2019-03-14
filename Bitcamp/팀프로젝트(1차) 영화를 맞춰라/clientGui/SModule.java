import java.io.*;
import java.net.*;

public class SModule implements Runnable {
	Socket s;
	Server sv;
	InputStream is;
	BufferedReader br;
	OutputStream os;
	PrintWriter pw;
	String id;
	
	public SModule(Server sv, Socket s){
		this.sv=sv;
		this.s=s;
		try {
			is=s.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			os=s.getOutputStream();
			pw=new PrintWriter(os, true);
		}catch(IOException ie) {}
	}
	@Override
	public void run() {
		listen();
	}
	public int nameCheck(String name){
		if(name.length()==0||name==null) {
			pw.println("잘못된 아이디입니다. 아이디를 다시 입력해주십시오!!");
			return -1;
		}
		if(sv.list.size()!=0) {
			for(SModule sm: sv.list) {
				if(sm.id.equals(name)) {
					pw.println("동일 아이디가 존재 합니다. 아이디를 다시 입력해주십시오!!");
					return -1;
				}
			}
		}
		return 1;
	}
	public void listen() {
		try{
			if(sv.list.size()<=3) {	
				pw.println("연결 성공");
				String id=br.readLine();
				id=id.trim();
				System.out.println("id받기 성공");
				while(nameCheck(id)!=1){
					id=br.readLine();
				}
				this.id=id.trim();
				sv.list.add(this);
				//i=sv.list.indexOf(this);
				pw.println(this.id);
				broadcast(this.id + "님 입장!!(현재 " +sv.list.size()+"명)");
				sendID();
				System.out.println(this.id + "님 입장!!(현재 " +sv.list.size()+"명)");
				while(true) {
					String msg = br.readLine();
					if(msg.equals("!?1")) {
						if(sv.count==0) {
							System.out.println("새로운 서버를 만듭니다.");
							sv.sv2=new Server2(sv);
						}
						SModule2 sm2=new SModule2(sv, this.id);
						sv.sv2.makeNewServer(sm2);
						if(sv.sv2.playing) {
							sm2.sendMsg("게임참여");
							broadcast(this.id +  "님 준비완료");
						}else {
							sm2.sendMsg("관전모드");
							sendMsg("관전모드");
						}
						
						continue;
					}
					int idx=msg.indexOf("귓속말!?");
					if(idx==-1)	broadcast("[전체채팅]" +this.id + " >> " + msg);
					else {
						whisper(msg);
					}
				}
			}
			pw.println("클라이언트가 꽉찾습니다... 다음기회에");
		}catch(IOException ie){
			int idx = sv.list.indexOf(this);
			if(idx!=-1) {
				sv.list.remove(this);
				broadcast(id + "님 퇴장!!(현재 " + sv.list.size()+"명)");
				sendID();
				System.out.println(id + "님 퇴장!!(현재 " + sv.list.size()+"명)");
			}
		}/*finally {
			try {
				br.close();
				pw.close();
				is.close();
				os.close();
				s.close();
			}catch(IOException ie2) {}
		}*/
	}
	public void sendID() {
		int i=0;
		for(SModule sm: sv.list) {
			broadcast(i++ +"!?@"+sm.id);
		}
	}
	public void sendMsg(String msg){
		pw.println(msg);
	}
	public void broadcast(String msg){
		for(SModule sm : sv.list){
			sm.pw.println(msg);
		}
	}
	public void whisper(String msg) {
		int idx1=msg.indexOf("?");
		int idx=msg.indexOf(">");
		String id=msg.substring(idx1+1, idx);
		msg=msg.substring(idx+1);
		for(SModule md : sv.list){
			if(md.id.equals(id)) md.pw.println("[귓속말]" +this.id + " >> " + msg);
		}
	}
}