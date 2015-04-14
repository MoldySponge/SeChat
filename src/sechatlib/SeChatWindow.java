package sechatlib;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;

public class SeChatWindow implements SeChatPanelManager, ActionListener{
	private LoginPanelController lPC = new LoginPanelController();
	private SystemPanelController sPC = new SystemPanelController();
	private CreateAccountPanelController cAPC = new CreateAccountPanelController();
	private ComponentMover cm;
	public SeChatWindow(){
		seChatWindow.setSize(800, 400);
		seChatWindow.setLocation((int)(dim.getWidth()/2-seChatWindow.getWidth()/2), 
				(int)(dim.getHeight()/2-seChatWindow.getHeight()/2));
		seChatWindow.setTitle("SeChat Alpha");
		seChatWindow.setUndecorated(true);
		seChatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		seChatWindow.setLayout(new BorderLayout());	
		seChatWindow.add(systemPanel, BorderLayout.NORTH);
		addListeners();
		seChatWindow.getContentPane().setBackground(Color.WHITE);
		cm = new ComponentMover(seChatWindow,systemPanel);
		seChatWindow.setVisible(true);
		addToWindow(loginPanel);
	}	
	
	public static void main(String []args){
		SeChatWindow tpo = new SeChatWindow();		
	}
	
	public void addListeners(){
		exitButton.addActionListener(this);
		createAccountButton.addActionListener(this);
		createButton.addActionListener(this);
	}
	
	public void addToWindow(JPanel panelToAdd){
		seChatWindow.add(panelToAdd, BorderLayout.CENTER);
		panelToAdd.setVisible(true);
		seChatWindow.repaint();
	}
	
	public void removeFromWindow(JPanel panelToRemove){
		seChatWindow.remove(panelToRemove);
		panelToRemove.setVisible(false);
		seChatWindow.repaint();
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == exitButton){
			System.exit(0);
		}
		if(e.getSource() == createAccountButton){
			System.out.println("Clicked Create Account Button");
			removeFromWindow(loginPanel);
			addToWindow(createAccountPanel);			
		}
		if(e.getSource() == createButton){
			cAPC.checkCreateCredentials();
		}
	}
}
