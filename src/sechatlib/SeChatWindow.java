package sechatlib;

import javax.swing.JFrame;

public class SeChatWindow implements SeChatPanelManager{
	
	private JFrame seChatWindow;
	public SeChatWindow(){
		seChatWindow = new JFrame();
		seChatWindow.setSize(800, 400);
		seChatWindow.setTitle("SeChat Alpha");
		seChatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		seChatWindow.setVisible(true);
	}	
	
	public static void main(String []args){
		SeChatWindow tpo = new SeChatWindow();		
	}
	
	public void addToWindow(){
		
	}
	
	public void removeFromWindow(){
		
	}
}
