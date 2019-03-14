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
			pw.println("�߸��� ���̵��Դϴ�. ���̵� �ٽ� �Է����ֽʽÿ�!!");
			return -1;
		}
		if(sv.list.size()!=0) {
			for(SModule sm: sv.list) {
				if(sm.id.equals(name)) {
					pw.println("���� ���̵� ���� �մϴ�. ���̵� �ٽ� �Է����ֽʽÿ�!!");
					return -1;
				}
			}
		}
		return 1;
	}
	public void listen() {
		try{
			if(sv.list.size()<=3) {	
				pw.println("���� ����");
				String id=br.readLine();
				id=id.trim();
				System.out.println("id�ޱ� ����");
				while(nameCheck(id)!=1){
					id=br.readLine();
				}
				this.id=id.trim();
				sv.list.add(this);
				//i=sv.list.indexOf(this);
				pw.println(this.id);
				broadcast(this.id + "�� ����!!(���� " +sv.list.size()+"��)");
				sendID();
				System.out.println(this.id + "�� ����!!(���� " +sv.list.size()+"��)");
				while(true) {
					String msg = br.readLine();
					if(msg.equals("!?1")) {
						if(sv.count==0) {
							System.out.println("���ο� ������ ����ϴ�.");
							sv.sv2=new Server2(sv);
						}
						SModule2 sm2=new SModule2(sv, this.id);
						sv.sv2.makeNewServer(sm2);
						if(sv.sv2.playing) {
							sm2.sendMsg("��������");
							broadcast(this.id +  "�� �غ�Ϸ�");
						}else {
							sm2.sendMsg("�������");
							sendMsg("�������");
						}
						
						continue;
					}
					int idx=msg.indexOf("�ӼӸ�!?");
					if(idx==-1)	broadcast("[��üä��]" +this.id + " >> " + msg);
					else {
						whisper(msg);
					}
				}
			}
			pw.println("Ŭ���̾�Ʈ�� ��ã���ϴ�... ������ȸ��");
		}catch(IOException ie){
			int idx = sv.list.indexOf(this);
			if(idx!=-1) {
				sv.list.remove(this);
				broadcast(id + "�� ����!!(���� " + sv.list.size()+"��)");
				sendID();
				System.out.println(id + "�� ����!!(���� " + sv.list.size()+"��)");
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
			if(md.id.equals(id)) md.pw.println("[�ӼӸ�]" +this.id + " >> " + msg);
		}
	}
}