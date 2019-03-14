import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Server2 extends Thread{
	ServerSocket ss;
	Server sv;
	int i, gameCount;
	int Qsec, Tsec;
	long period = 1000;
	long delay = 0;
	ArrayList <Integer> intlist;
	boolean playing=true;
	
	int port2=7880;
	
	public Server2(Server sv) {
		this.sv=sv;
	}
	public void makeNewServer(SModule2 sm2) {
		try {
			if(sv.list2.size()<=4) {
				sv.list2.add(sm2);
				if(sv.count==0) {
					ss=new ServerSocket(port2);
					sv.count++;
					this.start();
				}
				if(sv.list2.size()>0) {
					if(sm2.s == null) {
						try {
							sm2.s=ss.accept();
							sm2.init();
							new Thread(sm2).start();
						}catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void run() {
		try {
			sv.broadcast("40초후에 게임을 시작하겠습니다.");
			//Thread.sleep(35000);
			Thread.sleep(3500);
			for(int i=1; i<=5; i++) {
				sv.broadcast("  "+i);
				Thread.sleep(1000);
			}
			playing=false;
			sv.broadcast("Game Start");
			broadcast("Game Start");
		}catch(InterruptedException ie2) {}
	}
	public void broadcast(String msg){
		for(SModule2 sm2 : sv.list2){
			sm2.pw.println(msg);
		}
	}
	/*public void gameplaying() {
		try {
			
		}catch(IOException ie) {
		
		}
	}
	
	
	void timerQuiz() {
		Timer timerQuiz = new Timer();
		Qsec = 60;
		timerQuiz.schedule(new TimerTask() {
			
			public void run() {
				broadcast("!?timer"+ Qsec + "s");
				--Qsec;
				if(Qsec == 0) {
					Qsec=60;
					broadcast("!?timer"+"Next Stage");
					timerQuiz.cancel();
					try {
						Thread.sleep(100);
						broadcast("!?timer"+"Ready");
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
							return;
						}					
						timerQuiz();
					}
				}
			}
		}, delay, period);
	}*/
	
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
