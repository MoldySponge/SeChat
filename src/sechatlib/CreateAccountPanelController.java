package sechatlib;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.border.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class CreateAccountPanelController implements SeChatPanelManager{

	private CredentialChecker cc = new CredentialChecker();
	private PasswordControl pc;
	private GridBagConstraints c = new GridBagConstraints();
	private JTextField usernameTextField = new JTextField(20);
	private JPasswordField userPasswordField = new JPasswordField(20);
	private JPasswordField userRetypePasswordField = new JPasswordField(20);
	
	private JLabel headingMessage = new JLabel();
	private String welcomeMessage = "Welcome, please create an account.";
	private String badUserName = "Please select a different user name.";
	private String badPassword = "Your passwords do not match.";
	public CreateAccountPanelController(){
		setupPanel();
	}
	
	public void setupPanel(){
		createAccountPanel.setBackground(Color.WHITE);
		createAccountPanel.setLayout(new GridBagLayout());
		createAccountPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		headingMessage.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		headingMessage.setText(welcomeMessage);
		headingMessage.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountPanel.add(headingMessage,c);
		c.gridwidth = 1;
		c.gridy = 1;
		createAccountPanel.add(new JLabel("Desired Username: "), c);
		c.gridx = 1;
		createAccountPanel.add(usernameTextField, c);
		c.gridx = 0;
		c.gridy = 2;
		createAccountPanel.add(new JLabel("Password: "), c);
		c.gridx = 1;
		createAccountPanel.add(userPasswordField, c);
		c.gridx = 0;
		c.gridy = 3;
		createAccountPanel.add(new JLabel("Retype Password: "), c);
		c.gridx = 1;
		createAccountPanel.add(userRetypePasswordField, c);
		c.gridx = 0;
		c.gridy = 4;
		createAccountPanel.add(backButton, c);
		c.gridx = 1;
		createAccountPanel.add(createButton, c);
		
		createAccountPanel.setVisible(false);
	}
	
	public boolean checkCreateCredentials() throws NoSuchAlgorithmException{
		String desiredUsername = usernameTextField.getText();
		if(!cc.checkForUserNameConflict(desiredUsername)){
			headingMessage.setText(badUserName);
			return false;
		}
		try{
			pc = new PasswordControl();
			if(!pc.comparePasswords(pc.getHash(Arrays.toString(userPasswordField.getPassword())),
					pc.getHash(Arrays.toString(userRetypePasswordField.getPassword())))){
				headingMessage.setText(badPassword);
				return false;
			}
		}catch(UnsupportedEncodingException ex){
			System.out.println("Message: " + ex.getMessage());
			return false;
		}
		cc.sendPasswordHash(pc.getHash(Arrays.toString(userPasswordField.getPassword())));
		System.out.println(Arrays.toString(pc.getHash(Arrays.toString(userPasswordField.getPassword()))));
		return true;
	}
}
