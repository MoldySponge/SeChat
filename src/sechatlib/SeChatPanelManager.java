package sechatlib;

import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public interface SeChatPanelManager {
	/*
	 * get user screen information
	 */
	public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	//the window
	public JFrame seChatWindow = new JFrame();
	/*	 
	 * Panels used in the program
	 */
	public JPanel loginPanel = new JPanel();
	public JPanel createAccountPanel = new JPanel();
	public JPanel mainPanel = new JPanel();
	public JPanel systemPanel = new JPanel();
	/*
	 * Buttons used in the program
	 * These might be changed to different labels in the future
	 * in order to improve upon the look of the program
	 * Default ways of doing this are pretty poor and labels can 
	 * be utilized just as well as buttons
	 */
	public JButton backButton = new JButton("Back");
	public JButton createAccountButton = new JButton("Create Account");
	public JButton createButton = new JButton("Create");
	public JButton logInButton = new JButton("Log In");
	public JButton logOutButton = new JButton("Log Out");
	public JButton exitButton = new JButton("Exit");
	public JButton sendButton = new JButton("send");
	public JButton saveChat = new JButton("Save Chat");
	
}
