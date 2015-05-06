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
		systemPanel.setBackground(new Color(50, 200, 150));
		systemPanel.add(logOutButton, BorderLayout.EAST);
		logOutButton.setVisible(true);
		systemPanel.add(exitButton, BorderLayout.EAST);
	}
}
