import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class Players extends JPanel {
	JLabel playerID, playerAbata, winPointLabel, scorePointLabel;
	JTextField gameField;
	
	public Players() {
		setUI();
	}
	public void setUI() {
		setSize(363,218);
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));
		setLayout(null);
		
		playerID = new JLabel("¾øÀ½");
		playerID.setBounds(24, 164, 62, 36);
		add(playerID);
		
		playerAbata = new JLabel() {
			public void paintComponent(Graphics g){
				g.drawImage(Images.Profile, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		playerAbata.setBounds(14, 12, 162, 147);
		add(playerAbata);
		
		winPointLabel = new JLabel("win");
		winPointLabel.setBounds(190, 12, 134, 36);
		add(winPointLabel);
		
		scorePointLabel = new JLabel("score");
		scorePointLabel.setBounds(190, 60, 134, 36);
		add(scorePointLabel);
		
		gameField = new JTextField();
		gameField.setBounds(72, 171, 209, 36);
		gameField.setColumns(10);
		gameField.setEditable(false);
		add(gameField);
		
	}
}
