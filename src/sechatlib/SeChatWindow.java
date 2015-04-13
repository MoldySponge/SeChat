package sechatlib;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class SeChatWindow implements SeChatPanelManager{
	private LoginPanelController lPC = new LoginPanelController();
	
	public SeChatWindow(){
		seChatWindow.setSize(800, 400);
		seChatWindow.setLocation((int)(dim.getWidth()/2-seChatWindow.getWidth()/2), 
				(int)(dim.getHeight()/2-seChatWindow.getHeight()/2));
		seChatWindow.setTitle("SeChat Alpha");
		seChatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		seChatWindow.setLayout(new BorderLayout());
		seChatWindow.setVisible(true);
		addToWindow();
	}	
	
	public static void main(String []args){
		SeChatWindow tpo = new SeChatWindow();		
	}
	
	public void addToWindow(){
		seChatWindow.add(loginPanel, BorderLayout.CENTER);
		lPC.addToWindow();
	}
	
	public void removeFromWindow(){
		
	}
}
