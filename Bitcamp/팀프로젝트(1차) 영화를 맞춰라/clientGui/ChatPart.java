import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;

public class ChatPart extends JPanel {
	JButton chatB;
	JList chatList;
	JScrollPane chatScroll;
	JTextArea chatArea;
	JTextField chatField;
	JComboBox <String>comboBox;
	
	public ChatPart() {
		setUI();
	}
	public void setUI() {
		setSize(363, 239);
		setLayout(null);
		
		chatB = new JButton("→");
		chatB.setBounds(316, 199, 47, 39);
		add(chatB);
		
		/*chatList = new JList();
		chatList.setBounds(0, 199, 70, 39);
		add(chatList);*/
		
		chatScroll = new JScrollPane();
		chatScroll.setBounds(0, 0, 373, 199);
		add(chatScroll);
		
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatScroll.setViewportView(chatArea);
		
		chatField = new JTextField("메시지를 입력하시오...");
		chatField.setFont(new Font("굴림", Font.PLAIN, 18));
		chatField.setBounds(71, 199, 245, 39);
		add(chatField);
		chatField.setColumns(30);
		
		String strs[]= {"전체채팅","귓속말"};
		comboBox = new JComboBox<String>(strs);
		comboBox.setBounds(0, 199, 70, 39);
		add(comboBox);
	}
}
