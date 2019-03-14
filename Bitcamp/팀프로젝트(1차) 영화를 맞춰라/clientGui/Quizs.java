import java.awt.*;
import javax.swing.*;

public class Quizs extends JPanel{
	JLabel timer, gameDisplay, stage;
	
	public Quizs() {
		setUI();
	}
	public void setUI() {
		setBackground(new Color(254,0,0,0));
		setSize(525, 635);
		setLayout(null);
		
		timer = new JLabel("Timer");
		timer.setHorizontalAlignment(SwingConstants.CENTER);
		timer.setBounds(179, 0, 93, 29);
		timer.setFont(new Font("Serif", Font.BOLD, 20));
		timer.setForeground(Color.GREEN);
		add(timer);		
		
		//gameDisplay = new JLayeredPane();
		
		
		stage = new JLabel("stage");
		stage.setFont(new Font("Serif", Font.BOLD, 20));
		stage.setHorizontalAlignment(SwingConstants.CENTER);
		stage.setBounds(32, 0, 75, 29);
		stage.setForeground(Color.GREEN);
		add(stage);
	}
	
	
}
