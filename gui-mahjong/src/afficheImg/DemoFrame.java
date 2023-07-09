package afficheImg;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DemoFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon icon;
	public DemoFrame(ImageIcon imic) throws HeadlessException {
		icon = imic;
		this.setPreferredSize(new Dimension(400,400));
		this.pack();
		this.setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		icon.paintIcon(this, g, 200, 200);
	}
} 
