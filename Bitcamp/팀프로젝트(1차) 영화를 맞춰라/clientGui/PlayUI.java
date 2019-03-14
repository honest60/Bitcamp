import java.net.*;
import java.io.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PlayUI extends JFrame implements Runnable{
	Socket s;
	String ip, id;
	InputStream is;
	BufferedReader br;
	OutputStream os;
	PrintWriter pw;
	int port=7879, port2=7880;
	//int i;
	
	Container cp;
	
	JPanel screen ;
	Image background = Images.Gamebg;
	Image profilePh= Images.Profile;
	ImageIcon exitButtonImage =Images.ExitButtonImage;
	ImageIcon exitButtonEnteredImage =Images.ExitButtonEnteredImage;
	
	//buttonPanel
	JPanel buttonPanel;
	JButton readyB, ansB;
	
	//chatting
	ChatPart chatPanel;
	
	//player
	Players[] player=new Players[4];
	JPanel panelEast, panelWest;
	
	//game
	GameHelper help;
	JPanel gamePanel;
	JLabel gameDisplay, display;
	Quizs quiz;
	QuizData qd = new QuizData();
	
	JTextField gameField;
	
	//exitButton
	JButton exitButton;
	Music gameMusic ,buttonEnteredMusic;
	
	public PlayUI() {
		setUI();
	}
	
	public PlayUI(Socket s,String ip, String id,InputStream is,OutputStream os,BufferedReader br,PrintWriter pw){
		this.s=s;
		this.ip=ip;
		this.id=id;
		this.is=is;
		this.os=os;
		this.br=br;
		this.pw=pw;
		setUI();
		new Thread(this).start();
	}
	public void setUI(){
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(" 영화를 맞춰라 Ver 1.0 ");
		setSize(1280,733);
		
		setLocationRelativeTo(null);
		cp = getContentPane();
		cp.setLayout(null);
		
		screen = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(background, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		screen.setBounds(0, 0, 1280,733);
		screen.setBackground(new Color(0, 0, 0, 0));
		screen.setLayout(null);
		cp.add(screen);
		/*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
		
		//panelEast part
		panelEast = new JPanel();
		panelEast.setBounds(14, 36, 363, 437);
		screen.add(panelEast);
		panelEast.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelWest = new JPanel();
		panelWest.setBounds(902, 36, 363, 437);
		screen.add(panelWest);
		panelWest.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		for(int i=0; i<=3; i++) {
			player[i]=new Players();
			if(i<2){
				panelEast.add(player[i]);
			}else {
				panelWest.add(player[i]);
			}
			player[i].setVisible(true);
		}
		
		//buttonPanel part
		buttonPanel= new JPanel();
		buttonPanel.setBounds(941, 509, 275, 191);
		screen.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel readyPanel = new JPanel();
		buttonPanel.add(readyPanel);
		readyPanel.setLayout(null);
		
		readyB = new JButton("준 비");
		readyB.setBounds(50, 47, 185, 36);
		readyPanel.add(readyB);
		
		JPanel startPane = new JPanel();
		buttonPanel.add(startPane);
		
		ansB = new JButton("정 답");
		ansB.setBounds(51, 12, 185, 36);
		//
		ansB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		startPane.setLayout(null);
		startPane.add(ansB);
		/*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
		
		
		//Chatting part
		chatPanel = new ChatPart();
		chatPanel.setBounds(11, 480, 363, 239);
		screen.add(chatPanel);
		
		/*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
		
		//gamePanel part
		gamePanel = new JPanel();
		gamePanel.setBounds(377, 12, 525, 711);
		gamePanel.setBackground(new Color(254,0,0,0));
		screen.add(gamePanel);
		gamePanel.setLayout(null);
		
		quiz = new Quizs();
		quiz.setBounds(0, 0, 525, 635);
		gamePanel.add(quiz);
		gameDisplay = new JLabel() {
			public void paintComponent(Graphics g) {
				Image img=new ImageIcon(PlayUI.class.getResource("image/슬1.png")).getImage();
				g.drawImage(img,0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		gameDisplay.setBounds(49, 35, 420, 600);
		display = new JLabel();
		display.setBounds(0, 0, 420, 600);
		gameDisplay.add(display);
		
		quiz.add(gameDisplay);
		quiz.setVisible(true);

		gameField = new JTextField();
		gameField.setHorizontalAlignment(SwingConstants.LEFT);
		gameField.setFont(new Font("굴림", Font.PLAIN, 24));
		gameField.setBounds(140, 647, 289, 42);
		gamePanel.add(gameField);
		gameField.setColumns(10);
		gameField.setEditable(false);
		
		JLabel ansLabel = new JLabel("정답란 : ");
		ansLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ansLabel.setBounds(40, 647, 103, 42);
		ansLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
		ansLabel.setForeground(Color.GREEN);
		gamePanel.add(ansLabel);
		/*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
		
		//exitButton part
		exitButton = new JButton(exitButtonImage);
		exitButton.setBounds(1248, 0, 32, 32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		
		//XXXlistener
		ActionListener listener = new PlayActionHandler(this);
		readyB.addActionListener(listener);
		chatPanel.chatB.addActionListener(listener);
		ansB.addActionListener(listener);
		
		KeyListener key = new UIKeyListener(this);
		chatPanel.chatField.addKeyListener(key);
		gameField.addKeyListener(key);
		
		MouseListener mouse = new MouseHandler(this);
		exitButton.addMouseListener(mouse);
		
		screen.add(exitButton);
		setVisible(true);
	}
	@Override
	public void run() {
		listen();
	}
	public void listen() {
		try {
			while(true) {
				String chatmsg=br.readLine();
				if(chatmsg!=null) {
					if(chatmsg.contains("!?@")) {
						int idx1=chatmsg.indexOf("!?@");
						int count=Integer.parseInt(chatmsg.substring(0,idx1));
						String id=chatmsg.substring(idx1+3);
						if(id.equals(this.id))player[count].playerID.setText("당신");
						else player[count].playerID.setText(id);
						if(player[count+1].playerID.getText().equals(id)) {
							player[count+1].playerID.setText("없음");
						}
						for(int i=0; i<player.length; i++) {
							player[i].gameField.setText("");
						}
						continue;
					}
					int idx3=chatmsg.indexOf("]");
					int idx4=chatmsg.indexOf(">>");
					if(idx4!=-1) {
						String id=chatmsg.substring(idx3+1, idx4-1);
						if(id.equals(this.id)) {
							chatmsg=chatmsg.substring(idx4+3);
							chatPanel.chatArea.append("[전체채팅] 나 >> "+chatmsg+"\n");
							continue;
						}
					}
					chatPanel.chatArea.append(chatmsg+"\n");
				}
			}
		}catch(IOException ie) {
			JOptionPane.showMessageDialog(null,"서버와의 연결이 끊겼습니다.\n종료하시겠습니까?", "확인창",  JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		Socket s=null;
		PlayUI pu=new PlayUI();		
	}

	
}
