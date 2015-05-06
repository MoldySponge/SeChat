package sechatlib;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class MainPanelController implements ActionListener,SeChatPanelManager{
 
	private User loggedInUser;
	
	private JPanel mainContainer = new JPanel();
	private JPanel chatAndMessageContainer = new JPanel();
	private JPanel friendContainer = new JPanel();
	
	private JScrollPane chatScrollPane;
	private JScrollPane friendScrollPane;
	private JPanel messagePanel = new JPanel();
	private JPanel friendPanel = new JPanel();
	private JPanel chatPanel;
	
	private JScrollBar vertical;

	public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField messageField = new JTextField(40);
	private JButton sendText = new JButton("SEND");
	private int i = 0;
	
	public MainPanelController(){
		initialUserSetup();
		sendListener();
		setupPanel();
		setupContainers();
		populateFriends();
	}
	public void initialUserSetup(){
		loggedInUser = new User(this);
		chatScrollPane = loggedInUser.getFriendScrollPane(1);
		chatPanel = loggedInUser.getFriendChatPanel(1);
		vertical = chatScrollPane.getVerticalScrollBar();
	}
	
	public void setupContainers(){
		//setup the two big containers
		chatAndMessageContainer.setLayout(new BoxLayout(chatAndMessageContainer, BoxLayout.PAGE_AXIS));
		chatAndMessageContainer.setPreferredSize(new Dimension(600, 400));
		chatAndMessageContainer.setMaximumSize(new Dimension(600, 400));
		chatAndMessageContainer.setBorder(BorderFactory.createEtchedBorder());
		chatAndMessageContainer.setBackground(Color.WHITE);
		
		friendContainer.setLayout(new BoxLayout(friendContainer, BoxLayout.PAGE_AXIS));
		friendContainer.setPreferredSize(new Dimension(200,400));
		friendContainer.setMaximumSize(new Dimension(200, 400));
		friendContainer.setBorder(BorderFactory.createEtchedBorder());
		friendContainer.setBackground(Color.WHITE);
		//setup the minor containers
		
		
		
		chatAndMessageContainer.add(chatScrollPane);
		chatAndMessageContainer.add(messagePanel);
		
		mainPanel.validate();
		mainPanel.repaint();	
		
	}
	public void setupPanel(){
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.LINE_AXIS));
		mainContainer.add(chatAndMessageContainer);
		mainContainer.add(friendContainer);
		mainPanel.add(mainContainer);

		messagePanel.setBackground(Color.white);
		messagePanel.setPreferredSize(new Dimension(590,30));
		messagePanel.setMaximumSize(new Dimension(590, 30));
		messagePanel.add(messageField);
		messagePanel.add(sendText);
		
		friendScrollPane = new JScrollPane(friendPanel);
		friendScrollPane.setPreferredSize(new Dimension(200,390));
		friendScrollPane.setMaximumSize(new Dimension(200,390));
		
		friendPanel.setBackground(Color.white);
		friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.PAGE_AXIS));
		friendPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		friendPanel.setPreferredSize(new Dimension(190,380));
		friendPanel.setMaximumSize(new Dimension(190,390));
		friendContainer.add(friendScrollPane);		

	}

	
	public void populateFriends(){
		for(int i = 0; i < loggedInUser.getFriendLabels().length;i++){
			friendPanel.add(loggedInUser.getFriendLabels()[i]);
		}
	}
	
	public void setScrollPaneChatPanel(JScrollPane curScrollPane, JPanel curChatPanel){
		chatAndMessageContainer.remove(chatScrollPane);
		chatAndMessageContainer.remove(messagePanel);
		chatScrollPane = curScrollPane;
		chatPanel = curChatPanel;
		chatAndMessageContainer.add(chatScrollPane);
		chatAndMessageContainer.add(messagePanel);
		vertical = chatScrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		mainPanel.validate();
		mainPanel.repaint();
	}
	
	public void sendListener(){
		sendText.addActionListener(this);
	}	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == sendText){
			if(messageField.getText().compareTo("") != 0){
				JLabel text = new JLabel("<html><p style=\"width:175px\">" + messageField.getText() + "</p></html>");
				text.setOpaque(true);
				text.setBorder(BorderFactory.createRaisedSoftBevelBorder());
				text.setPreferredSize(new Dimension(180, 30));
				if(i % 2 == 0){
					text.setBackground(new Color(255,125,200));
					text.setAlignmentX(Component.LEFT_ALIGNMENT);
					chatPanel.add(text);
				}
				else
				{
					text.setBackground(new Color(125,255,200));
					text.setAlignmentX(Component.RIGHT_ALIGNMENT);
					chatPanel.add(text);
				}
				mainPanel.validate();
				mainPanel.repaint();
				i++;
			}
			messageField.setText("");
			vertical.setValue(vertical.getMaximum());
		}
	}
}
