import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class MouseHandler implements MouseListener{
	
	PlayUI pu;
	ClientLoginUI clu;
	
	public MouseHandler(PlayUI pu){
		this.pu = pu;
	}
	public MouseHandler(ClientLoginUI clu){
		this.clu = clu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		if(pu != null) {
			if(obj == pu.exitButton) {
				pu.buttonEnteredMusic = new Music("exit_press.mp3", false);
				pu.buttonEnteredMusic.start();
				try{
					Thread.sleep(700);
				}catch(InterruptedException ex){
					ex.printStackTrace();
				}
				System.exit(0);
			}
		}else if(clu != null) {
			if(obj == clu.exitButton) {
				clu.buttonEnteredMusic = new Music("exit_press.mp3", false);
				clu.buttonEnteredMusic.start();
				try{
					Thread.sleep(700);
				}catch(InterruptedException ex){
					ex.printStackTrace();
				}
				System.exit(0);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();
		if(pu != null) {
			pu.exitButton.setIcon(pu.exitButtonEnteredImage);
			pu.exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			pu.buttonEnteredMusic = new Music("exit_entered.mp3", false);
			pu.buttonEnteredMusic.start();
		}else if(clu != null) {
			if(obj == clu.exitButton) {
				clu.exitButton.setIcon(clu.exitButtonEnteredImage);
				clu.exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				clu.buttonEnteredMusic = new Music("exit_entered.mp3", false);
				clu.buttonEnteredMusic.start();
			}else if(obj == clu.startButton) {
				clu.startButton.setIcon(clu.startButtonEnteredImage);
				clu.startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				clu.buttonEnteredMusic = new Music("exit_entered.mp3", false);
				clu.buttonEnteredMusic.start();
			}
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		Object obj = e.getSource();
		if(pu!=null) {
			pu.exitButton.setIcon(pu.exitButtonImage);
			pu.exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}else if(clu!=null) {
			if(obj == clu.exitButton) {
				clu.exitButton.setIcon(clu.exitButtonImage);
				clu.exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}else if(obj == clu.startButton) {
				clu.startButton.setIcon(clu.startButtonImage);
				clu.startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
}



