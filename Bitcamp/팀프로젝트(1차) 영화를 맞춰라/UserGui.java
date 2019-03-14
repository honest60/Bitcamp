import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.imageio.ImageIO;
import java.io.*;

public class UserGui {
	JFrame login;
	Container cp;
	JPanel panel;
	JTextPane textPane;
	JScrollPane scrollPane;
	JTextField textField;
	JButton button;
	JLabel label;
	BufferedImage img;
	Image resizeImage;

	void init(){
		setFrame();
		setUI();
	}

	void setFrame(){
		try{
			UIManager.setLookAndFeel(
				UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception e){}
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
	}
	void setUI(){
		login=new JFrame();
		loadImage();
		cp=login.getContentPane();
		panel=new JPanel(){
			public void paintComponent(Graphics g){
				int x=resizeImage.getWidth(login)/2;
				int y=resizeImage.getHeight(login)/2;
				int x2 = login.getWidth()/2 - x;
				int y2 = login.getHeight()/2 - y;
				g.drawImage(resizeImage, x2, y2, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setSize(500,500);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		panel1.setBackground(new Color(255, 0, 0, 0));
		panel1.setLayout(new GridLayout(1,1));
		//panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		//panel1.setAlignmentX(0.5f);
		//panel1.setAlignmentY(0.5f);
		JPanel panel1_1=new JPanel();
		JPanel panel1_2=new JPanel();
		panel1_1.setBackground(new Color(255, 0, 0, 0));
		panel1_2.setBackground(new Color(255, 0, 0, 0));
		JLabel title=new JLabel("영화 맞추기 게임!!!",SwingConstants.CENTER);
		//title.setHorizontalTextPositon(Component.CENTER_ALIGNMENT);
		title.setFont(new Font("Dialog", Font.BOLD, 36));
		title.setForeground(Color.GREEN);
		title.setBorder(new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED),new EtchedBorder(EtchedBorder.RAISED)));
		//panel1.add(panel1_1);
		panel1.add(title);
		//panel1.add(panel1_2);
		
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10)); 
		panel2.setBackground(new Color(255, 0, 0, 0));
		panel3.setBackground(new Color(255, 0, 0, 0));
		label=new JLabel("IP  :  ");
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setForeground(Color.GREEN);
		textField=new JTextField("IP를 입력하시오...",15);
		textField.setFont(new Font("Dialog", Font.PLAIN, 15));
		textField.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		button=new JButton(" 접 속 ");
		panel2.add(label); panel2.add(textField); panel2.add(button);
		
		textPane= new JTextPane();
		textPane.setBackground(new Color(255, 0, 0, 0));
		textPane.setForeground(Color.GREEN);
		textPane.setFont(new Font("SansSerif", Font.BOLD+Font.ITALIC, 18));
		textPane.setText("만든이들!!\n\n박참\n김은수\n기휘도\n이강준\n과연 게임의 승자는 어디에...\n나중에 자동흐르기 할꺼임");
		textPane.setEditable(false);
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		scrollPane = new JScrollPane(textPane);
		scrollPane.setBackground(new Color(255, 0, 0, 0));
		scrollPane.setBorder(new EmptyBorder(scrollPane.getInsets()));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel3.add(scrollPane);

		panel.add(panel1);
		panel.add(panel3);
		panel.add(panel2);
		cp.add(panel);
		
		always();
	}
	void loadImage(){
		try{
			img=ImageIO.read(new File("a.jpg"));
			resizeImage = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		}catch(IOException ie){}
	}
	void always(){
		login.setTitle("login page~~~");
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setResizable(false);
		login.setSize(500,500);
		login.setLocation(500, 50);
		login.setVisible(true);
	}
	public static void main(String[] args){
		UserGui user=new UserGui();
		user.init();
	}
}