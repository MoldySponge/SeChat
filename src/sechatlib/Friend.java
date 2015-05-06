package sechatlib;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Friend {
	
	private String friendUserName;
	private JScrollPane chatScrollPane;
	private JPanel chatPanel = new JPanel();
	
	public Friend(String newFriendUserName){
		friendUserName = newFriendUserName;
		prepareChatScrollPane();
		prepareChatPanel();
	}
	
	public void prepareChatScrollPane(){
		chatScrollPane = new JScrollPane(chatPanel);
		chatScrollPane.setBackground(Color.white);
		chatScrollPane.setPreferredSize(new Dimension(590,370));
		chatScrollPane.setMaximumSize(new Dimension(590, 370));
	}
	
	public void prepareChatPanel(){
		chatPanel.setBackground(Color.white);
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.PAGE_AXIS));
	}
	
	public String getFriendUserName(){
		return friendUserName;
	}
	
	public JScrollPane getScrollPane(){
		return chatScrollPane;
	}	
	
	public JPanel getChatPanel(){
		return chatPanel;
	}
	
}
