import java.io.*;
import java.net.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.*;

public class ClientLoginUI extends JFrame {
	
	Container cp;
	
	JPanel screen ;
	Image background = Images.Loginbg;
	ImageIcon exitButtonImage = Images.ExitButtonImage;
	ImageIcon exitButtonEnteredImage = Images.ExitButtonEnteredImage;
	ImageIcon startButtonImage = Images.StartButtonImage;
	ImageIcon startButtonEnteredImage = Images.StartButtonEnteredImage;
	
	//Button
	JButton exitButton = new JButton(exitButtonImage);
	JButton startButton = new JButton(startButtonImage);
	
	//IP
	JLabel ipLabel = new JLabel("IP : ");
	JTextField ipField=new JTextField("IP를 입력하시오...",15);
	
	//network
	Socket s;
	InputStream is;
	OutputStream os;
	BufferedReader br;
	PrintWriter pw;
	String ip, id;
	int port=7879, port2=7880;
	
	Music buttonEnteredMusic, introMusic;
	boolean isMainScreen = false;
	
	PlayActionHandler listener;
	KeyListener key;
	public ClientLoginUI() {
		setUI();
	}
	public void setUI(){
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(" 영화를 맞춰라 Ver 1.0 ");
		setSize(1280,700);
		setVisible(true);
		setLocationRelativeTo(null);
		cp = getContentPane();
		getContentPane().setLayout(null);
		
		introMusic = new Music("The Avengers Theme Song Different Remake.mp3", true);
		introMusic.start();
		
		screen = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(background, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		screen.setBounds(0, 0, 1280,700);
		cp.add(screen);
		screen.setBackground(new Color(0, 0, 0, 0));
		screen.setLayout(null);
		
		//exitButton part
		exitButton.setBounds(1237, 0, 32, 32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		screen.add(exitButton);
		/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
		
		//startButton part
		startButton.setBounds(840, 504, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		screen.add(startButton);
		
		ipLabel.setBounds(339,504,94,75);
		ipLabel.setBorder(new EmptyBorder(ipLabel.getInsets()));
		ipLabel.setFont(new Font("Dialog", Font.PLAIN, 42));
		ipLabel.setForeground(Color.GREEN);
		
		ipField.setBounds(426,504,400,75);
		ipField.setBorder(new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED),new EmptyBorder(ipField.getInsets())));
		ipField.setFont(new Font("Dialog", Font.PLAIN, 42));	
		
		screen.add(ipLabel);		
		screen.add(ipField);
		
		//XXXlistener
		listener = new PlayActionHandler(this);
		startButton.addActionListener(listener);
		
		KeyListener key = new UIKeyListener(this);
		ipField.addKeyListener(key);
		
		MouseListener mouse = new MouseHandler(this);
		exitButton.addMouseListener(mouse);
		startButton.addMouseListener(mouse);
		
	}
	public void closeAll() {
		try {
			if(s!=null){
				if(br != null) { br.close(); br=null;}
				if(pw != null) { pw.close(); pw=null;}
				if(is != null) { is.close(); is=null;}
				if(br != null) { os.close(); os=null;}
				s.close();
				s=null;
			}
		}catch (IOException e) {
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLoginUI frame = new ClientLoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
