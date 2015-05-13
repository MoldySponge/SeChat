package sechatlib;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.net.ssl.SSLSocket;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class LoginPanelController implements SeChatPanelManager{
	private CredentialChecker cc = new CredentialChecker();
	private PasswordControl pc;
    private JLabel welcomeMessage = new JLabel();
    private JTextField userNameTextField = new JTextField(20);
    private JPasswordField userPasswordField = new JPasswordField(20);
    
    private String defaultMessage = "Welcome to SeChat";
	public LoginPanelController(){
		setupPanel();
	}
	public void setupPanel(){
		loginPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		welcomeMessage.setText(defaultMessage);
		welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMessage.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		loginPanel.add(welcomeMessage, c);
		c.gridwidth = 1;
		c.gridy = 1;
		loginPanel.add(new JLabel("User Name: "), c);
		c.gridx = 1;
		loginPanel.add(userNameTextField, c);
		c.gridx = 0;
		c.gridy = 2;
		loginPanel.add(new JLabel("Password: "), c);
		c.gridx = 1;
		loginPanel.add(userPasswordField, c);
		c.gridx = 0;
		c.gridy = 3;
		loginPanel.add(createAccountButton, c);
		c.gridx = 1;
		loginPanel.add(logInButton, c);		
		
		loginPanel.setVisible(false);
	}
	public boolean checkLogInCredentials() throws NoSuchAlgorithmException{
		//return true;
		// commented for testing purposes of adding main panel
		try{
			pc = new PasswordControl();
			String userName = userNameTextField.getText();
			byte[] userPassword = pc.getHash(Arrays.toString(userPasswordField.getPassword()));
			System.out.println(Arrays.toString(userPassword));
			if(cc.checkLogIn(userName, userPassword)){
				return true;
			}
			return false;
		}catch(UnsupportedEncodingException ex){
		      System.out.println("Problem with password Encoding: " + ex.getMessage());
		      return false;
		}
		
	}
	
	public SSLSocket getSSLConnection(){
		return cc.getSSLConnection();
	}
}
