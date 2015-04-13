package sechatlib;

import javax.swing.JFrame;

public class SeChatWindow implements SeChatPanelManager{
	
	
	public static void main(String []args){
		JFrame seChatWindow = new JFrame();
		seChatWindow.setSize(800, 400);
		seChatWindow.setTitle("SeChat Alpha");
		seChatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		seChatWindow.setVisible(true);
		
	}
	
	public void addToWindow(){
		
	}
	
	public void removeFromWindow(){
		
	}

}
