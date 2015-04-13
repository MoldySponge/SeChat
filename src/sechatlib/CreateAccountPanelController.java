package sechatlib;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class CreateAccountPanelController implements SeChatPanelManager{

	public CreateAccountPanelController(){
		setupPanel();
	}
	
	public void setupPanel(){
		createAccountPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		createAccountPanel.add(new JLabel("Desired Username: "), c);
		c.gridx = 1;
		createAccountPanel.add(new JTextField(20), c);
		c.gridx = 0;
		c.gridy = 1;
		createAccountPanel.add(new JLabel("Password: "), c);
		c.gridx = 1;
		createAccountPanel.add(new JPasswordField(20), c);
		c.gridx = 0;
		c.gridy = 2;
		createAccountPanel.add(new JLabel("Retype Password: "), c);
		c.gridx = 1;
		createAccountPanel.add(new JPasswordField(20), c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		createAccountPanel.add(createButton, c);
		
		createAccountPanel.setVisible(false);
	}
}
