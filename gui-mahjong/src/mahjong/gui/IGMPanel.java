package mahjong.gui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class IGMPanel extends JPanel{
	private IGMTuile[][][] tank;
	private ImageIcon highlight;

	public IGMPanel(IGMTuile[][][] tank) {
		this.tank = tank;
		highlight = new ImageIcon("images/trucii.png");
		
	}
	
	private int getPosX(int x, int z) {
		return x*27+50-z*8;
	}

	private int getPosY(int y, int z) {
		return y*35+90-z*8;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int z=0; z<15; z++)
			for (int y=0; y<100; y++)
				for (int x =0; x<100; x++)
					if (tank[x][y][z]!=null) {
						tank[x][y][z].getImage().paintIcon(this,g,
								this.getPosX(y,z),
								this.getPosY(x,z));
						if (tank[x][y][z].isHighlighted())
							highlight.paintIcon(this,g,
									this.getPosX(y,z),
									this.getPosY(x,z));
					}
	}
}
