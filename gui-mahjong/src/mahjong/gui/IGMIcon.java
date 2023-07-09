package mahjong.gui;

import javax.swing.ImageIcon;

import utils.Piece;

public class IGMIcon implements IGMTuile{
	private int x, y, z, posX, posY;
	private ImageIcon imag;
	boolean highlighted = false;
	public IGMIcon(ImageIcon imag, int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		posY = y*27+50-z*8;
		posX = x*35+90-z*8;
		this.imag = imag;
	}
	
	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getZ() {
		return z;
	}
	public ImageIcon getImage() {
		return imag;
	}
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public boolean covers(int a, int b) {
		return (a >= posX) && (a < posX+72) && (b >= posY) && (b < posY+56);
	}
	@Override
	public String toString() {
		return "("+x+","+y+","+z+")";
	}
	
	//ma méthode 
	
	public Piece getPiece() {
		return null;  // provisoire
	}
	
	


}
