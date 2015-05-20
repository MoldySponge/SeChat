package sechatlib;

import javax.net.ssl.SSLSocket;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


public class User implements ActionListener,SeChatPanelManager{

	private String userName;
	private Friend[] friends; //holds each friend conversation and data
	private JLabel[] friendLabels; //the selectable label for the friend in that conversation
	
	private FriendManager fMP;
	private ClientRead reader;
	private ClientWrite writer;
	private MainPanelController mPM;
	
	private SSLSocket client;
	private int clickedHighlight = 0;
	private int setup = 0;
	
	public User(MainPanelController curMainPanelController, SSLSocket newSSLClient){
		mPM = curMainPanelController;
		client = newSSLClient;		
		reader = new ClientRead(client, this);
	}
	
	public void setUserName(String newUserName){
	    userName = newUserName;
	}
	
	public void prepareFriendLabels(){
		fMP = new FriendManager();
		friendLabels[0] = new JLabel("Friend Manager");
		friendLabels[0].setOpaque(true);
		friendLabels[0].setBackground(Color.white);
		friendLabels[0].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.blue));
		friendLabels[0].setPreferredSize(new Dimension(190, 30));
		friendLabels[0].setMaximumSize(new Dimension(190,30));
		friendLabels[0].setMinimumSize(new Dimension(190,30));
		friendLabels[0].setAlignmentX(Component.CENTER_ALIGNMENT);
		friendLabels[0].setHorizontalAlignment(SwingConstants.CENTER);
		addFriendLabelListeners(0);
		for(int i = 1; i < friendLabels.length;i++){
			friendLabels[i] = new JLabel(friends[i-1].getFriendUserName());
			friendLabels[i].setOpaque(true);
			friendLabels[i].setBackground(Color.white);
			friendLabels[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.blue));
			friendLabels[i].setPreferredSize(new Dimension(190, 30));
			friendLabels[i].setMaximumSize(new Dimension(190,30));
			friendLabels[i].setMinimumSize(new Dimension(190,30));
			friendLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			friendLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			addFriendLabelListeners(i);
			friendLabels[1].setBackground(new Color(50, 200, 150));			
		}
		mPM.continueSetup();
	}
	public JScrollPane getFriendScrollPane(int i){
		return friends[i].getScrollPane();
	}
	
	public JPanel getFriendChatPanel(int i){
		return friends[i].getChatPanel();
	}
	
	public JLabel[] getFriendLabels(){
		return friendLabels;
	}
	
	public void actionPerformed(ActionEvent e){
		
	}	

	public void setFriends(String []userFriends){
		friends = new Friend[userFriends.length];
		friendLabels = new JLabel[userFriends.length+1];		
		for(int i = 0; i < userFriends.length;i++){
			friends[i] = new Friend(userFriends[i]);
		}			
		prepareFriendLabels();
	}
	public void addFriendLabelListeners(final int i){
		friendLabels[i].addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				if(clickedHighlight != i)
					friendLabels[i].setBackground(Color.cyan);
			}
			
			public void mouseExited(MouseEvent e){
				if(clickedHighlight != i)
					friendLabels[i].setBackground(Color.white);
			}
			
			public void mouseClicked(MouseEvent e){
				friendLabels[clickedHighlight].setBackground(Color.white);
				clickedHighlight = i;
				friendLabels[i].setBackground(new Color(50, 200, 150));
				if(i != 0)
					mPM.setScrollPaneChatPanel(friends[i-1].getScrollPane(), friends[i-1].getChatPanel());
				else
					mPM.setFriendManagerPanel(fMP.getFriendManager());
			}
		});
	}
}
