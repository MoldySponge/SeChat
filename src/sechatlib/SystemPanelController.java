package sechatlib;

import java.awt.BorderLayout;
public class SystemPanelController implements SeChatPanelManager{
	public SystemPanelController(){
		setupPanel();
	}
	public void setupPanel(){
		systemPanel.setLayout(new BorderLayout());
		systemPanel.add(logOutButton, BorderLayout.EAST);
		logOutButton.setVisible(false);
		systemPanel.add(exitButton, BorderLayout.EAST);
	}
}
