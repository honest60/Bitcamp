import java.awt.event.*;

public class UIKeyListener implements KeyListener{
	
	ClientLoginUI clu;
	PlayUI pu;
	
	UIKeyListener(ClientLoginUI clu){
		this.clu = clu;
	}
	
	UIKeyListener(PlayUI pu){
		this.pu = pu;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object obj=e.getSource();
		if(clu!=null) {
			if(clu.ipField.getText().equals("IP를 입력하시오...")) clu.ipField.setText("");
			if(clu.ipField.getText().equals("ID를 입력하시오...")) clu.ipField.setText("");
		}else if(pu!=null) {
			if(obj==pu.chatPanel.chatField) {
				if(pu.chatPanel.chatField.getText().contains("메시지를 입력하시오...")) {
					pu.chatPanel.chatField.setText("");
				}
			}else if(obj==pu.gameField) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String msg=pu.gameField.getText();
					pu.help.pw.println(msg);
					pu.gameField.setText("");
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Object obj=e.getSource();
		if(clu!=null) {
			if(obj==clu.ipField) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)	clu.listener.login();
			}
		}else if(pu!=null) {
			if(obj==pu.chatPanel.chatField) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                String msg=pu.chatPanel.chatField.getText();
	                pu.pw.println(msg);
	                pu.chatPanel.chatField.setText("메시지를 입력하시오...");
	            }
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

