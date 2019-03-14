import java.io.*;
import java.net.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PlayActionHandler implements ActionListener{
	
	PlayUI pu;
	ClientLoginUI clu;

	public PlayActionHandler(PlayUI pu){
		this.pu = pu;
	}
	
	public PlayActionHandler(ClientLoginUI clu){
		this.clu = clu;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(pu != null) {
			if(obj == pu.readyB) {
				pu.buttonEnteredMusic = new Music("exit_press.mp3", false);
				pu.buttonEnteredMusic.start();
				try{
					Thread.sleep(700);
				}catch(InterruptedException ex){
					ex.printStackTrace();
				}
				pu.buttonEnteredMusic.close();
				pu.pw.println("!?1");
				pu.help=new GameHelper(pu);
				int hp=pu.help.init();
				if(hp==-1) {
					JOptionPane.showMessageDialog(null, "게임 호스트를 찾지못했습니다.다시 누르십시오.", "확인창",  JOptionPane.INFORMATION_MESSAGE);
					return;
				}else if(hp==-2) {
					JOptionPane.showMessageDialog(null, "잘못된 서버입니다.다시 누르십시오", "확인창",  JOptionPane.INFORMATION_MESSAGE);
					return;
				}/*else if(hp==1) {
					new Thread() {   
			            public void run() {   
			                for (int i = 0; i < 3; i++) {   
			                    try {   
			                        Thread.sleep(2000);   
			                    } catch (InterruptedException e) {   
			                    }   
			                    JOptionPane.getRootFrame().dispose();   
			                }   
			            }   
			        }.start();   
					JOptionPane.showMessageDialog(null, "정원이 모두 찾습니다.\n시작하십시오.", "확인창",  JOptionPane.INFORMATION_MESSAGE);
				}*/
				new Thread(pu.help).start();
				
				/*try {
					Thread.sleep(1000);
				}catch(InterruptedException iee) {}
				new Thread(pu.help).start();
				timerQuiz();
				pu.gameField.setEditable(true);
				/*pu.quiz.stage.setText("stage "+ ++count);
				Graphics gameG=pu.quiz.gameDisplay.getGraphics();
				Image img=new ImageIcon(postersTipList.get(i)).getImage();
				gameG.drawImage(img,0,0,null);*/
			}else if(obj == pu.chatPanel.chatB) {
				if(pu.chatPanel.chatField.getText().contains("메시지를 입력하시오...")) {
					pu.chatPanel.chatField.setText("메시지를 입력하시오...");
					return;
				}
				String msg=pu.chatPanel.chatField.getText();
                pu.pw.println(msg);
                pu.chatPanel.chatField.setText("메시지를 입력하시오...");	
			}
		}else if(clu != null) {
			if (obj == clu.startButton) {
				login();
			}
		}
	}
	void login() {
		clu.buttonEnteredMusic = new Music("exit_press.mp3", false);
		clu.buttonEnteredMusic.start();
		try{
			Thread.sleep(700);
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		clu.buttonEnteredMusic.close();
		if(clu.ipLabel.getText().equals("IP : ")){
			if(!clu.ipField.getText().equals("")||!clu.ipField.getText().equals("IP를 입력하시오...")) {
				clu.ip=clu.ipField.getText();
				try {
					clu.s=new Socket(clu.ip, clu.port);
					clu.is=clu.s.getInputStream();
					clu.br=new BufferedReader(new InputStreamReader(clu.is));
					clu.os=clu.s.getOutputStream();
					clu.pw=new PrintWriter(clu.os, true);
					String ip=clu.br.readLine();
					if(ip.equals("클라이언트가 꽉찾습니다... 다음기회에")) {
						JOptionPane.showMessageDialog(null, ip, "인원이 꽉참", JOptionPane.QUESTION_MESSAGE);
						clu.ipField.setText("IP를 입력하시오...");
						return;
					}else JOptionPane.showMessageDialog(null, ip, "확인창",  JOptionPane.INFORMATION_MESSAGE);
					clu.ipLabel.setText("ID : ");
					clu.ipField.setText("ID를 입력하시오...");
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(null, "호스트를 찾지못했습니다.다시 입력하시오", "확인창",  JOptionPane.INFORMATION_MESSAGE);
					clu.ipField.setText("IP를 입력하시오...");
					clu.closeAll();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "잘못된 서버입니다.다시 입력하시오", "확인창",  JOptionPane.INFORMATION_MESSAGE);
					clu.ipField.setText("IP를 입력하시오...");
					clu.closeAll();
				}
				return;
			}
			clu.ipField.setText("IP를 입력하시오...");
		}else if(clu.ipLabel.getText().equals("ID : ")) {
			if(!clu.ipField.getText().equals("")&&!clu.ipField.getText().equals("ID를 입력하시오...")) {
				clu.id=clu.ipField.getText();
				clu.pw.println(clu.id);
			    try {
					clu.id=clu.br.readLine();
					if(clu.id.equals("잘못된 아이디입니다. 아이디를 다시 입력해주십시오!!")||clu.id.equals("동일 아이디가 존재 합니다. 아이디를 다시 입력해주십시오!!")) {
						JOptionPane.showMessageDialog(null,clu.id, "확인창",  JOptionPane.INFORMATION_MESSAGE);
						clu.ipField.setText("ID를 입력하시오...");
						return;
					}
					JOptionPane.showMessageDialog(null,"입장하겠습니다.", "확인창",  JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"서버와 끊겼습니다...", "확인창",  JOptionPane.INFORMATION_MESSAGE);
					clu.ipField.setText("IP를 입력하시오...");
					clu.ipLabel.setText("IP : ");
					clu.closeAll();
					return;
				}
				//next Frame here
				clu.setVisible(false);
				clu.introMusic.close();
				new PlayUI(clu.s,clu.ip,clu.id,clu.is, clu.os, clu.br, clu.pw).setVisible(true);
			}
			clu.ipField.setText("ID를 입력하시오...");
		}
	}

}
