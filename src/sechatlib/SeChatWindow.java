package sechatlib;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;

import java.sql.*;

public class SeChatWindow implements SeChatPanelManager, ActionListener{
	private LoginPanelController lPC = new LoginPanelController();
	private SystemPanelController sPC = new SystemPanelController();
	private CreateAccountPanelController cAPC = new CreateAccountPanelController();
	private MainPanelController mPC;
	private ComponentMover cm;
	
	public static void main(String []args){
		SeChatWindow tpo = new SeChatWindow();		
	}
	public SeChatWindow(){
		seChatWindow.setSize(800, 432);
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
	
	
	
	public void addListeners(){
		exitButton.addActionListener(this);
		createAccountButton.addActionListener(this);
		createButton.addActionListener(this);
		backButton.addActionListener(this);
		logInButton.addActionListener(this);
		logOutButton.addActionListener(this);
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
		if(e.getSource() == backButton){
			removeFromWindow(createAccountPanel);
			addToWindow(loginPanel);
			backStack[0] = null;
		}
		if(e.getSource() == createAccountButton){
			backStack[0] = loginPanel;
			removeFromWindow(loginPanel);
			addToWindow(createAccountPanel);			
		}
		if(e.getSource() == createButton){
			try{
				if(cAPC.checkCreateCredentials()){
					removeFromWindow(createAccountPanel);
					addToWindow(backStack[0]);
				}
			}catch(NoSuchAlgorithmException ex){
				System.out.println("Message: " + ex.getMessage());
			}
			
		}
		if(e.getSource() == logInButton){
			try{
				if(lPC.checkLogInCredentials()){
					logOutButton.setVisible(true);
					removeFromWindow(loginPanel);
					mPC = new MainPanelController(lPC.getSSLConnection());
					addToWindow(mainPanel);
					seChatWindow.validate();
					seChatWindow.repaint();
				}
				else{
					System.out.println("Log In Incorrect.");
				}
			}catch(NoSuchAlgorithmException ex){
				System.out.println("Error login from Main window: " + ex.getMessage());
			}
		}
		if(e.getSource() == logOutButton){
			removeFromWindow(mainPanel);
			addToWindow(loginPanel);
			logOutButton.setVisible(false);
		}
	}
}
