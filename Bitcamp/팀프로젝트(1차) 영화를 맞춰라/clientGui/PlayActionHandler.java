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
					JOptionPane.showMessageDialog(null, "���� ȣ��Ʈ�� ã�����߽��ϴ�.�ٽ� �����ʽÿ�.", "Ȯ��â",  JOptionPane.INFORMATION_MESSAGE);
					return;
				}else if(hp==-2) {
					JOptionPane.showMessageDialog(null, "�߸��� �����Դϴ�.�ٽ� �����ʽÿ�", "Ȯ��â",  JOptionPane.INFORMATION_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "������ ��� ã���ϴ�.\n�����Ͻʽÿ�.", "Ȯ��â",  JOptionPane.INFORMATION_MESSAGE);
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
				if(pu.chatPanel.chatField.getText().contains("�޽����� �Է��Ͻÿ�...")) {
					pu.chatPanel.chatField.setText("�޽����� �Է��Ͻÿ�...");
					return;
				}
				String msg=pu.chatPanel.chatField.getText();
                pu.pw.println(msg);
                pu.chatPanel.chatField.setText("�޽����� �Է��Ͻÿ�...");	
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
			if(!clu.ipField.getText().equals("")||!clu.ipField.getText().equals("IP�� �Է��Ͻÿ�...")) {
				clu.ip=clu.ipField.getText();
				try {
					clu.s=new Socket(clu.ip, clu.port);
					clu.is=clu.s.getInputStream();
					clu.br=new BufferedReader(new InputStreamReader(clu.is));
					clu.os=clu.s.getOutputStream();
					clu.pw=new PrintWriter(clu.os, true);
					String ip=clu.br.readLine();
					if(ip.equals("Ŭ���̾�Ʈ�� ��ã���ϴ�... ������ȸ��")) {
						JOptionPane.showMessageDialog(null, ip, "�ο��� ����", JOptionPane.QUESTION_MESSAGE);
						clu.ipField.setText("IP�� �Է��Ͻÿ�...");
						return;
					}else JOptionPane.showMessageDialog(null, ip, "Ȯ��â",  JOptionPane.INFORMATION_MESSAGE);
					clu.ipLabel.setText("ID : ");
					clu.ipField.setText("ID�� �Է��Ͻÿ�...");
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(null, "ȣ��Ʈ�� ã�����߽��ϴ�.�ٽ� �Է��Ͻÿ�", "Ȯ��â",  JOptionPane.INFORMATION_MESSAGE);
					clu.ipField.setText("IP�� �Է��Ͻÿ�...");
					clu.closeAll();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "�߸��� �����Դϴ�.�ٽ� �Է��Ͻÿ�", "Ȯ��â",  JOptionPane.INFORMATION_MESSAGE);
					clu.ipField.setText("IP�� �Է��Ͻÿ�...");
					clu.closeAll();
				}
				return;
			}
			clu.ipField.setText("IP�� �Է��Ͻÿ�...");
		}else if(clu.ipLabel.getText().equals("ID : ")) {
			if(!clu.ipField.getText().equals("")&&!clu.ipField.getText().equals("ID�� �Է��Ͻÿ�...")) {
				clu.id=clu.ipField.getText();
				clu.pw.println(clu.id);
			    try {
					clu.id=clu.br.readLine();
					if(clu.id.equals("�߸��� ���̵��Դϴ�. ���̵� �ٽ� �Է����ֽʽÿ�!!")||clu.id.equals("���� ���̵� ���� �մϴ�. ���̵� �ٽ� �Է����ֽʽÿ�!!")) {
						JOptionPane.showMessageDialog(null,clu.id, "Ȯ��â",  JOptionPane.INFORMATION_MESSAGE);
						clu.ipField.setText("ID�� �Է��Ͻÿ�...");
						return;
					}
					JOptionPane.showMessageDialog(null,"�����ϰڽ��ϴ�.", "Ȯ��â",  JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"������ ������ϴ�...", "Ȯ��â",  JOptionPane.INFORMATION_MESSAGE);
					clu.ipField.setText("IP�� �Է��Ͻÿ�...");
					clu.ipLabel.setText("IP : ");
					clu.closeAll();
					return;
				}
				//next Frame here
				clu.setVisible(false);
				clu.introMusic.close();
				new PlayUI(clu.s,clu.ip,clu.id,clu.is, clu.os, clu.br, clu.pw).setVisible(true);
			}
			clu.ipField.setText("ID�� �Է��Ͻÿ�...");
		}
	}

}
