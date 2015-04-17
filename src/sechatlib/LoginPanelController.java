package sechatlib;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
public class LoginPanelController implements SeChatPanelManager{

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
		loginPanel.add(new JLabel("User Name: "), c);
		c.gridx = 1;
		loginPanel.add(new JTextField(20), c);
		c.gridx = 0;
		c.gridy = 1;
		loginPanel.add(new JLabel("Password: "), c);
		c.gridx = 1;
		loginPanel.add(new JPasswordField(20), c);
		c.gridx = 0;
		c.gridy = 2;
		loginPanel.add(createAccountButton, c);
		c.gridx = 1;
		loginPanel.add(logInButton, c);		
		
		loginPanel.setVisible(false);
	}	
}
