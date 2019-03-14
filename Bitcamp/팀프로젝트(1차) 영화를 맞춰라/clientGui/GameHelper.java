import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GameHelper implements Runnable {
	PlayUI pu;
	String id, ip;
	Socket s;
	InputStream is;
	BufferedReader br;
	OutputStream os;
	PrintWriter pw;
	int port;
	String ids[]=new String[4];
	
	int Qsec, Tsec;
	long period = 1000;
	long delay = 0;
	int count=1;
	int i=0, mi=0, i2=0;
	
	Music m, m2;
	
	public GameHelper(PlayUI pu) {
		this.pu=pu;
		this.id=pu.id;
		this.ip=pu.ip;
		this.port=pu.port2;
	}
	public int init() {
		try {
			s=new Socket(ip, port);
			is=s.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			os=s.getOutputStream();
			pw=new PrintWriter(os, true);
		}catch (UnknownHostException e1) {
			return -1;
		}catch (IOException e1) {
			return -2;
		}
		return 1;
	}
	public void run() {
		listen();
	}
	public void listen() {
		try {
			while(true) {
				String msg=br.readLine();
				if(msg !=null) {
					if(msg.contains("번")) {
						int idx=msg.indexOf("번");
						for(int i=0; i<pu.player.length; i++) {
							if(pu.player[i].playerID.getText().equals(msg.substring(0, idx))) {
								pu.player[i].gameField.setText(msg.substring(idx+1));
							}
						}
					}else if(msg.equals("게임참여")) {
						pu.readyB.setText("대 기");
						pu.readyB.setEnabled(false);
						pu.gameField.setEditable(true);
					}else if(msg.equals("관전모드")) {
						pu.readyB.setText("대 기");
						pu.readyB.setEnabled(false);
					}else if(msg.equals("Game Start")) {
						timerQuiz();
					}
				}
			}
		}catch(IOException ie) {
			
		}
	}
	
	void timerQuiz() {// 수정 필요
		Timer timerQuiz = new Timer();
		Qsec = 60;
		changeImg();
		timerQuiz.schedule(new TimerTask() {
			
			public void run() {
				pu.quiz.timer.setText("" + Qsec + "s");
				--Qsec;
				if(Qsec == 0) {
					Qsec=60;
					pu.quiz.timer.setText("Next Stage");
					timerQuiz.cancel();
					try {
						Thread.sleep(100);
						pu.quiz.timer.setText("Ready");
						//Thread.sleep(100);
					}catch(InterruptedException ie){}
					if(pu.quiz.timer.getText().equals("Ready")) {
						if((i%3)==0) {
							
							count++;
							mi++;
						}
						if(count>7) {
							pu.quiz.stage.setText("The End");
							pu.quiz.timer.setText("Stop");
							pu.display.setVisible(false);
							m.close();
							return;
						}					
						timerQuiz();
						m2=new Music(pu.qd.scenesTip, false);
						m2.start();
					}
				}
			}
		}, delay, period);
	}
	
	void changeImg() {// 수정 필요
		if((i%3)==0) {
			if(count<=7) {
				pu.qd.make(count-1);
				if(m!=null) m.close();
				m=new Music(pu.qd.ost, true);
				m.start();
			}
		}
		if((i%2)==0)
		pu.quiz.stage.setText("stage "+ count);
		Graphics gameG=pu.display.getGraphics();
		Image img=new ImageIcon(pu.qd.posters[i++]).getImage();
		gameG.drawImage(img,0,0,null);
	}
//	void timerTip() {
//		Timer timerT = new Timer();
//		Tsec = 20;
//		timerT.schedule(new TimerTask(){
//			public void run(){
//				pu.timer.setText("" + Tsec + "s");
//				--Tsec;
//				if(Tsec == 0){
//					pu.timer.setText("Next Tip");
//					timerT.cancel();
//				}
//			}
//		}, delay, period);
//	}
}
