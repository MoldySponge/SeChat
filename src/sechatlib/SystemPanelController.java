package sechatlib;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.border.*;
public class SystemPanelController implements SeChatPanelManager{
	public SystemPanelController(){
		setupPanel();
	}
	public void setupPanel(){
		systemPanel.setLayout(new BorderLayout());
		systemPanel.setBorder(BorderFactory.createEtchedBorder());
		systemPanel.setBackground(Color.WHITE);
		systemPanel.add(logOutButton, BorderLayout.EAST);
		logOutButton.setVisible(false);
		systemPanel.add(exitButton, BorderLayout.EAST);
	}
}
