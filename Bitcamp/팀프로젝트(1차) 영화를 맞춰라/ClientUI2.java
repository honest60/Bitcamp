import javax.swing.*; // JFrame Jbutton ImageIcon 포함
import javax.swing.border.*;
import java.awt.*; // Image, Graphics 포함 등
import java.awt.event.*; //MouseAdapter 등

class ClientUI2 extends JFrame {
	private Container cp;
	private Image screenImage;
	private Graphics screenGraphic; 
	// 더블 버퍼링 프로그래밍 기법을 위해서 사용, 현재 전체 화면에 맞게 매순간 생성해서
	// 이미지를 담아주는 녀석들
	// (사용 안하면 게임 동작이 많이 느리다??)
	private Image background = new ImageIcon(
		ClientUI2.class.getResource("image/introBackground.jpg")).getImage(); 
	private Image stageImage = new ImageIcon(
		ClientUI2.class.getResource("image/범죄도시 연습.jpg")).getImage(); 
		// 배경화면을 담을 수 있는 하나의 객체		
		//ClientUI클래스를 기준으로 get.image는 이미지를 인스턴스화 해서 불러옴
	private ImageIcon exitButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/exit.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(ClientUI2.class.getResource("icon/exit_enter.png"));
	private ImageIcon startButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/start_button.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(ClientUI2.class.getResource("icon/start_button_enter.png"));
	private ImageIcon readyButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/연습 복사.png"));
	private ImageIcon replayButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/연습 복사.png"));
	private ImageIcon answerButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/연습 복사.png"));

	private JLabel menuBar = new JLabel(new ImageIcon(ClientUI2.class.getResource("icon/menuBar.png")));  
	private JButton exitButton = new JButton(exitButtonImage);
	private JButton startButton = new JButton(startButtonImage);
	private JButton readyButton = new JButton(readyButtonImage);
	private JButton replayButton = new JButton(replayButtonImage);
	private JButton answerButton = new JButton(answerButtonImage);

	private JLabel ipLabel = new JLabel("IP  :  ");
	private JTextField ipField=new JTextField("IP를 입력하시오...",15);
	private Music buttonEnteredMusic, introMusic;

	private int mouseX, mouseY; //메뉴창을 이동 시키는데 필요
	// 컴퓨터 내 마우스의 좌표
	
	private boolean isMainScreen = false;
	
	ClientUI2(){
		setUI();
	}

	void setUI(){
		cp=this.getContentPane();
		setUndecorated(true);//기존의 자바 메뉴창으로 안나오게 해줌
		setTitle(" 영화를 맞춰라 Ver 1.0 ");
		setSize(1280,700);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0));//paintComponents(g)했을때
		//사용할 색깔이 됨
		setLayout(null);//버튼이나 JLabel을 넣었을때 해당 위치에 뜨게끔 함.

		ipLabel.setBounds(280,430,120,90);
		ipLabel.setFont(new Font("Dialog", Font.PLAIN, 48));
		ipField.setBounds(390,440,400,75);
		ipField.setBorder(new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED),new EmptyBorder(ipField.getInsets())));
		ipField.setFont(new Font("Dialog", Font.PLAIN, 42));
		ipField.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
				if(ipField.getText().equals("IP를 입력하시오..."))
				ipField.setText("");
				if(ipField.getText().equals("ID를 입력하시오..."))
				ipField.setText("");
			}
			public void keyReleased(KeyEvent e){
				
			}
			public void keyTyped(KeyEvent e){
			
			}
		});
		add(ipLabel);		
		add(ipField);
		exitButton.setBounds(1237, 0, 32, 32);//시작위치와 아이콘 사이즈
		//아이콘 필드와 테두리 제거하는 녀석들
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);

		//MouseAdapter는 MouseListener를 구현한 녀석임
		//그놈을 생성하면서 오버라이딩

		//나가기 버튼 생성
		exitButton.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonEnteredMusic = 
					new Music("exit_entered.mp3", false);
				buttonEnteredMusic.start();
			}
			public void mouseExited(MouseEvent e){
				exitButton.setIcon(exitButtonImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e){
				buttonEnteredMusic = 
					new Music("exit_press.mp3", false);
				buttonEnteredMusic.start();
				try{
					Thread.sleep(700);
				}catch(InterruptedException ex){
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});		
		add(exitButton);
		
		
		//시작버튼 생성
		startButton.setBounds(840, 425, 400, 100);//시작위치와 아이콘 사이즈
		//아이콘 필드와 테두리 제거하는 녀석들
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		//MouseAdapter는 MouseListener를 구현한 녀석임
		//그놈을 생성하면서 오버라이딩
		startButton.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonEnteredMusic = 
					new Music("exit_entered.mp3", false);
				buttonEnteredMusic.start();
			}
			public void mouseExited(MouseEvent e){
				startButton.setIcon(startButtonImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e){
				if(ipLabel.getText().equals("IP  :  ")){
					if(!ipField.getText().equals("")&&!ipField.getText().equals("IP를 입력하시오...")){
						ipLabel.setText("ID : ");
						ipField.setText("ID를 입력하시오...");
						return;
					}
					ipField.setText("IP를 입력하시오...");
				}else if(ipLabel.getText().equals("ID : ")){
					if(!ipField.getText().equals("")&&!ipField.getText().equals("ID를 입력하시오...")){
						buttonEnteredMusic = 
							new Music("exit_press.mp3", false);
						buttonEnteredMusic.start();
						try{
							Thread.sleep(700);
						}catch(InterruptedException ex){
							ex.printStackTrace();
						}
						buttonEnteredMusic.close();	
						introMusic.close();
						ipLabel.setVisible(false);
						ipField.setVisible(false);
						startButton.setVisible(false);
						readyButton.setVisible(true);
						replayButton.setVisible(true);
						answerButton.setVisible(true);
						background = new ImageIcon(ClientUI.class.getResource(
							"image/mainBackground.jpg")).getImage();
						isMainScreen = true;
						return;
					}
					ipField.setText("ID를 입력하시오...");
				}
			}
		});		
		add(startButton);

		//준비 버튼 생성
		readyButton.setVisible(false);
		readyButton.setBounds(700, 480, 100, 53);//시작위치와 아이콘 사이즈
		//아이콘 필드와 테두리 제거하는 녀석들
		readyButton.setBorderPainted(false);
		readyButton.setContentAreaFilled(false);
		readyButton.setFocusPainted(false);
		//MouseAdapter는 MouseListener를 구현한 녀석임
		//그놈을 생성하면서 오버라이딩
		readyButton.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				readyButton.setIcon(readyButtonImage);
				readyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonEnteredMusic = 
					new Music("exit_entered.mp3", false);
				buttonEnteredMusic.start();
			}
			public void mouseExited(MouseEvent e){
				readyButton.setIcon(readyButtonImage);
				readyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e){
				buttonEnteredMusic = new Music("exit_press.mp3", false);
				buttonEnteredMusic.start();
				// 준비 버튼 이벤트
			}
		});		
		add(readyButton);

		//다시듣기 버튼 생성
		replayButton.setVisible(false);
		replayButton.setBounds(900, 480, 100, 53);//시작위치와 아이콘 사이즈
		//아이콘 필드와 테두리 제거하는 녀석들
		replayButton.setBorderPainted(false);
		replayButton.setContentAreaFilled(false);
		replayButton.setFocusPainted(false);
		//MouseAdapter는 MouseListener를 구현한 녀석임
		//그놈을 생성하면서 오버라이딩
		replayButton.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				replayButton.setIcon(replayButtonImage);
				replayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonEnteredMusic = 
					new Music("exit_entered.mp3", false);
				buttonEnteredMusic.start();
			}
			public void mouseExited(MouseEvent e){
				replayButton.setIcon(replayButtonImage);
				replayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e){
				buttonEnteredMusic = 
					new Music("/music/exit_press.mp3", false);
				buttonEnteredMusic.start();
				// 다시듣기 버튼 이벤트
			}
		});		
		add(replayButton);

		//준비 버튼 생성
		answerButton.setVisible(false);
		answerButton.setBounds(1100, 480, 100, 53);//시작위치와 아이콘 사이즈
		//아이콘 필드와 테두리 제거하는 녀석들
		answerButton.setBorderPainted(false);
		answerButton.setContentAreaFilled(false);
		answerButton.setFocusPainted(false);
		//MouseAdapter는 MouseListener를 구현한 녀석임
		//그놈을 생성하면서 오버라이딩
		answerButton.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				answerButton.setIcon(answerButtonImage);
				answerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonEnteredMusic = 
					new Music("exit_entered.mp3", false);
				buttonEnteredMusic.start();
			}
			public void mouseExited(MouseEvent e){
				answerButton.setIcon(answerButtonImage);
				answerButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e){
				buttonEnteredMusic = 
					new Music("exit_press.mp3", false);
				buttonEnteredMusic.start();
				// 정답 버튼 이벤트
			}
		});		
		add(answerButton);



		menuBar.setBounds(0, 0, 1280, 32);
		//마우스를 눌렀을때 일어날 효과 저장.
		menuBar.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				//현재 스크린의 좌표들을 가져옴
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar); //JFrame에 추가시킴		
		
		introMusic = new Music("The Avengers Theme Song Different Remake.mp3", true);
		introMusic.start();
	}

	// paint는 JFrame을 상속받은 이러한 GUI 게임에서 가장 첫번쨰로 화면을 그려주는
	// 메서드임. 정해진 규칙임
	public void paint(Graphics g){ //JFrame에 있는 하나의 약속된 메서드
		screenImage = createImage(1280, 700);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic); // screenGraphic에 그림을 그려주는 일을 할 녀석
		g.drawImage(screenImage, 0, 0, null);
	} 
	
	//화면이 계속 반복되는 구간
	public void screenDraw(Graphics g){
		g.drawImage(background, 0, 0, null);
		//단순하게 이미지를 불러와서 그릴때 사용/
		if(isMainScreen){
			g.drawImage(stageImage, 40, 32, null);//스크린 이미지에 그려줌
		}
		paintComponents(g); // 항상 존재하는 이미지, 고정된거라 요걸 사용해서
		//메인프레임에 추가된 걸 보여줌. add가 되서 저장된 것들을 요녀석이 읽어옴
		// 그려줌. (JLabel이용)
		this.repaint(); //다시 페인트를 불러옴. 계속 반복됨.
	}
	public static void main(String[] args) {
		new ClientUI2();
	}
}
