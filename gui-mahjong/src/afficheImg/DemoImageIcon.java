package afficheImg;

import javax.swing.ImageIcon; 
import javax.swing.JFrame;
public class DemoImageIcon {
	public static void main(String[] args) {
		ImageIcon imic = new ImageIcon("images/BAMBOU_4.png");
		DemoFrame demof = new DemoFrame(imic);
	}
}
