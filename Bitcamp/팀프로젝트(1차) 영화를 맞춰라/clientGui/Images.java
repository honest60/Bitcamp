import java.awt.Image;

import javax.swing.ImageIcon;

public class Images {
	public static Image Loginbg = new ImageIcon(
			Images.class.getResource("image/introBackground.jpg")).getImage();
	public static Image Gamebg = new ImageIcon(
			Images.class.getResource("image/mainBackground.jpg")).getImage();
	public static Image Profile=new ImageIcon(
			Images.class.getResource("image/player.jpg")).getImage();
	
	public static ImageIcon ExitButtonImage = new ImageIcon(Images.class.getResource("icon/exit.png"));
	public static ImageIcon ExitButtonEnteredImage = new ImageIcon(Images.class.getResource("icon/exit_enter.png"));
	public static ImageIcon StartButtonImage = new ImageIcon(Images.class.getResource("icon/start_button.png"));
	public static ImageIcon StartButtonEnteredImage = new ImageIcon(Images.class.getResource("icon/start_button_enter.png"));
	
}
