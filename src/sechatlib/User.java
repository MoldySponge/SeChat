package sechatlib;
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
	private Friend[] friends = new Friend[10]; //holds each friend conversation and data
	private JLabel[] friendLabels = new JLabel[10]; //the selectable label for the friend in that conversation
	private MainPanelController mPM;
	
	private int clickedHighlight = 0;
	
	public User(MainPanelController curMainPanelController){
		mPM = curMainPanelController;
		//get my name
		//gets friends
		//hashes names out into array
		//places friends into friend array
		for(int i = 0; i < friends.length; i++){
			friends[i] = new Friend("Friend" + i);
		}
		prepareFriendLabels();
	}
	
	public void prepareFriendLabels(){
		for(int i = 0; i < friendLabels.length;i++){
			friendLabels[i] = new JLabel(friends[i].getFriendUserName());
			friendLabels[i].setOpaque(true);
			friendLabels[i].setBackground(Color.white);
			friendLabels[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.blue));
			friendLabels[i].setPreferredSize(new Dimension(190, 30));
			friendLabels[i].setMaximumSize(new Dimension(190,30));
			friendLabels[i].setMinimumSize(new Dimension(190,30));
			friendLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			friendLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			addFriendLabelListeners(i);
			friendLabels[0].setBackground(new Color(50, 200, 150));
			
		}		
		
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
				mPM.setScrollPaneChatPanel(friends[i].getScrollPane(), friends[i].getChatPanel());
			}
		});
	}
}
