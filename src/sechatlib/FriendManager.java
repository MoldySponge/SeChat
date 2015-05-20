package sechatlib;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FriendManager implements ActionListener{

	private JPanel friendManager;
	private JButton acceptRequest;
	private JButton rejectRequest;
	private JButton sendRequest;
	private JComboBox friendRequestsBox;
	private JTextField requestFriend;
	private String[] friendRequests = {"test", "test2"};
	
	public FriendManager(){
		setupFriendManagerButtons();
		setupFriendManagerPanel();
	}
	
	public void setupFriendManagerButtons(){
		acceptRequest = new JButton("Accept Request");
		rejectRequest = new JButton("Reject Request");
		sendRequest = new JButton("Send Request");
		friendRequestsBox = new JComboBox(friendRequests);
		requestFriend = new JTextField(10);
	}
	public void setupFriendManagerPanel(){
		friendManager = new JPanel();
		friendManager.setLayout(new BoxLayout(friendManager, BoxLayout.PAGE_AXIS));
		friendManager.setPreferredSize(new Dimension(600, 400));
		friendManager.setMaximumSize(new Dimension(600, 400));
		friendManager.setBorder(BorderFactory.createEtchedBorder());
		friendManager.setBackground(Color.WHITE);
		
		friendRequestsBox.setPreferredSize(new Dimension(600,25));
		friendRequestsBox.setMaximumSize(new Dimension(600,25));
		friendManager.add(friendRequestsBox);		
		
		JPanel acceptRejectPanel = new JPanel();
		acceptRejectPanel.setLayout(new BoxLayout(acceptRejectPanel, BoxLayout.LINE_AXIS));	
		acceptRejectPanel.setPreferredSize(new Dimension(600, 40));
		acceptRejectPanel.setMaximumSize(new Dimension(600, 40));		
		acceptRejectPanel.setBackground(Color.WHITE);
		acceptRequest.setAlignmentX(Component.LEFT_ALIGNMENT);
		acceptRejectPanel.add(acceptRequest);
		rejectRequest.setAlignmentX(Component.RIGHT_ALIGNMENT);
		acceptRejectPanel.add(rejectRequest);
		friendManager.add(acceptRejectPanel);
		
		JPanel sendRequestPanel = new JPanel();
		sendRequestPanel.setLayout(new BoxLayout(sendRequestPanel, BoxLayout.LINE_AXIS));
		sendRequestPanel.setPreferredSize(new Dimension(600, 40));
		sendRequestPanel.setMaximumSize(new Dimension(600, 40));		
		sendRequestPanel.setBackground(Color.WHITE);
		sendRequest.setAlignmentX(Component.LEFT_ALIGNMENT);	
		sendRequestPanel.add(sendRequest);
		requestFriend.setAlignmentX(Component.RIGHT_ALIGNMENT);
		requestFriend.setPreferredSize(new Dimension(600,25));
		requestFriend.setMaximumSize(new Dimension(600,25));
		sendRequestPanel.add(requestFriend);
		friendManager.add(sendRequestPanel);
		
		addListeners();
	}
	
	public void setFriendRequestList(String friendRequests){
		
	}
	
	public JPanel getFriendManager(){
		return friendManager;
	}
	
	public void addListeners(){
		acceptRequest.addActionListener(this);
		rejectRequest.addActionListener(this);
		sendRequest.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == acceptRequest){
			System.out.println(friendRequestsBox.getSelectedIndex());
		}
		else if(e.getSource() == rejectRequest){
			System.out.println(friendRequestsBox.getSelectedIndex());
		}
		else if(e.getSource() == sendRequest){
			System.out.println(requestFriend.getText());
			requestFriend.setText("");
		}
	}
}
