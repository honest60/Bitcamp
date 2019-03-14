import javax.swing.*; // JFrame Jbutton ImageIcon ����
import javax.swing.border.*;
import java.awt.*; // Image, Graphics ���� ��
import java.awt.event.*; //MouseAdapter ��

class ClientUI2 extends JFrame {
	private Container cp;
	private Image screenImage;
	private Graphics screenGraphic; 
	// ���� ���۸� ���α׷��� ����� ���ؼ� ���, ���� ��ü ȭ�鿡 �°� �ż��� �����ؼ�
	// �̹����� ����ִ� �༮��
	// (��� ���ϸ� ���� ������ ���� ������??)
	private Image background = new ImageIcon(
		ClientUI2.class.getResource("image/introBackground.jpg")).getImage(); 
	private Image stageImage = new ImageIcon(
		ClientUI2.class.getResource("image/���˵��� ����.jpg")).getImage(); 
		// ���ȭ���� ���� �� �ִ� �ϳ��� ��ü		
		//ClientUIŬ������ �������� get.image�� �̹����� �ν��Ͻ�ȭ �ؼ� �ҷ���
	private ImageIcon exitButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/exit.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(ClientUI2.class.getResource("icon/exit_enter.png"));
	private ImageIcon startButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/start_button.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(ClientUI2.class.getResource("icon/start_button_enter.png"));
	private ImageIcon readyButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/���� ����.png"));
	private ImageIcon replayButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/���� ����.png"));
	private ImageIcon answerButtonImage = new ImageIcon(ClientUI2.class.getResource("icon/���� ����.png"));

	private JLabel menuBar = new JLabel(new ImageIcon(ClientUI2.class.getResource("icon/menuBar.png")));  
	private JButton exitButton = new JButton(exitButtonImage);
	private JButton startButton = new JButton(startButtonImage);
	private JButton readyButton = new JButton(readyButtonImage);
	private JButton replayButton = new JButton(replayButtonImage);
	private JButton answerButton = new JButton(answerButtonImage);

	private JLabel ipLabel = new JLabel("IP  :  ");
	private JTextField ipField=new JTextField("IP�� �Է��Ͻÿ�...",15);
	private Music buttonEnteredMusic, introMusic;

	private int mouseX, mouseY; //�޴�â�� �̵� ��Ű�µ� �ʿ�
	// ��ǻ�� �� ���콺�� ��ǥ
	
	private boolean isMainScreen = false;
	
	ClientUI2(){
		setUI();
	}

	void setUI(){
		cp=this.getContentPane();
		setUndecorated(true);//������ �ڹ� �޴�â���� �ȳ����� ����
		setTitle(" ��ȭ�� ����� Ver 1.0 ");
		setSize(1280,700);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0));//paintComponents(g)������
		//����� ������ ��
		setLayout(null);//��ư�̳� JLabel�� �־����� �ش� ��ġ�� �߰Բ� ��.

		ipLabel.setBounds(280,430,120,90);
		ipLabel.setFont(new Font("Dialog", Font.PLAIN, 48));
		ipField.setBounds(390,440,400,75);
		ipField.setBorder(new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED),new EmptyBorder(ipField.getInsets())));
		ipField.setFont(new Font("Dialog", Font.PLAIN, 42));
		ipField.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
				if(ipField.getText().equals("IP�� �Է��Ͻÿ�..."))
				ipField.setText("");
				if(ipField.getText().equals("ID�� �Է��Ͻÿ�..."))
				ipField.setText("");
			}
			public void keyReleased(KeyEvent e){
				
			}
			public void keyTyped(KeyEvent e){
			
			}
		});
		add(ipLabel);		
		add(ipField);
		exitButton.setBounds(1237, 0, 32, 32);//������ġ�� ������ ������
		//������ �ʵ�� �׵θ� �����ϴ� �༮��
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);

		//MouseAdapter�� MouseListener�� ������ �༮��
		//�׳��� �����ϸ鼭 �������̵�

		//������ ��ư ����
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
		
		
		//���۹�ư ����
		startButton.setBounds(840, 425, 400, 100);//������ġ�� ������ ������
		//������ �ʵ�� �׵θ� �����ϴ� �༮��
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		//MouseAdapter�� MouseListener�� ������ �༮��
		//�׳��� �����ϸ鼭 �������̵�
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
					if(!ipField.getText().equals("")&&!ipField.getText().equals("IP�� �Է��Ͻÿ�...")){
						ipLabel.setText("ID : ");
						ipField.setText("ID�� �Է��Ͻÿ�...");
						return;
					}
					ipField.setText("IP�� �Է��Ͻÿ�...");
				}else if(ipLabel.getText().equals("ID : ")){
					if(!ipField.getText().equals("")&&!ipField.getText().equals("ID�� �Է��Ͻÿ�...")){
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
					ipField.setText("ID�� �Է��Ͻÿ�...");
				}
			}
		});		
		add(startButton);

		//�غ� ��ư ����
		readyButton.setVisible(false);
		readyButton.setBounds(700, 480, 100, 53);//������ġ�� ������ ������
		//������ �ʵ�� �׵θ� �����ϴ� �༮��
		readyButton.setBorderPainted(false);
		readyButton.setContentAreaFilled(false);
		readyButton.setFocusPainted(false);
		//MouseAdapter�� MouseListener�� ������ �༮��
		//�׳��� �����ϸ鼭 �������̵�
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
				// �غ� ��ư �̺�Ʈ
			}
		});		
		add(readyButton);

		//�ٽõ�� ��ư ����
		replayButton.setVisible(false);
		replayButton.setBounds(900, 480, 100, 53);//������ġ�� ������ ������
		//������ �ʵ�� �׵θ� �����ϴ� �༮��
		replayButton.setBorderPainted(false);
		replayButton.setContentAreaFilled(false);
		replayButton.setFocusPainted(false);
		//MouseAdapter�� MouseListener�� ������ �༮��
		//�׳��� �����ϸ鼭 �������̵�
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
				// �ٽõ�� ��ư �̺�Ʈ
			}
		});		
		add(replayButton);

		//�غ� ��ư ����
		answerButton.setVisible(false);
		answerButton.setBounds(1100, 480, 100, 53);//������ġ�� ������ ������
		//������ �ʵ�� �׵θ� �����ϴ� �༮��
		answerButton.setBorderPainted(false);
		answerButton.setContentAreaFilled(false);
		answerButton.setFocusPainted(false);
		//MouseAdapter�� MouseListener�� ������ �༮��
		//�׳��� �����ϸ鼭 �������̵�
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
				// ���� ��ư �̺�Ʈ
			}
		});		
		add(answerButton);



		menuBar.setBounds(0, 0, 1280, 32);
		//���콺�� �������� �Ͼ ȿ�� ����.
		menuBar.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				//���� ��ũ���� ��ǥ���� ������
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar); //JFrame�� �߰���Ŵ		
		
		introMusic = new Music("The Avengers Theme Song Different Remake.mp3", true);
		introMusic.start();
	}

	// paint�� JFrame�� ��ӹ��� �̷��� GUI ���ӿ��� ���� ù������ ȭ���� �׷��ִ�
	// �޼�����. ������ ��Ģ��
	public void paint(Graphics g){ //JFrame�� �ִ� �ϳ��� ��ӵ� �޼���
		screenImage = createImage(1280, 700);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic); // screenGraphic�� �׸��� �׷��ִ� ���� �� �༮
		g.drawImage(screenImage, 0, 0, null);
	} 
	
	//ȭ���� ��� �ݺ��Ǵ� ����
	public void screenDraw(Graphics g){
		g.drawImage(background, 0, 0, null);
		//�ܼ��ϰ� �̹����� �ҷ��ͼ� �׸��� ���/
		if(isMainScreen){
			g.drawImage(stageImage, 40, 32, null);//��ũ�� �̹����� �׷���
		}
		paintComponents(g); // �׻� �����ϴ� �̹���, �����ȰŶ� ��� ����ؼ�
		//���������ӿ� �߰��� �� ������. add�� �Ǽ� ����� �͵��� ��༮�� �о��
		// �׷���. (JLabel�̿�)
		this.repaint(); //�ٽ� ����Ʈ�� �ҷ���. ��� �ݺ���.
	}
	public static void main(String[] args) {
		new ClientUI2();
	}
}
