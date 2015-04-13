package sechatlib;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class LoginPanelController implements SeChatPanelManager{

	public LoginPanelController(){
		setupPanel();
	}
	public void setupPanel(){
		loginPanel.setLayout(new GridLayout(3, 2));
		loginPanel.add(new JLabel("User Name: "));
		loginPanel.add(new JTextField(20));
		loginPanel.add(new JLabel("Password: "));
		loginPanel.add(new JTextField(20));
		loginPanel.add(createAccountButton);
		loginPanel.add(logInButton);
		loginPanel.setVisible(false);
	}
	public void addToWindow(){
		loginPanel.setVisible(true);
	}
	
	public void removeFromWindow(){
		
	}
}
