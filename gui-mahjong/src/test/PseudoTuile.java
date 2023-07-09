package test;

import javax.swing.ImageIcon;

import mahjong.gui.IGMTuile;
import utils.Piece;

public class PseudoTuile implements IGMTuile {
	private boolean highlighted = false; // est-ce utile ??? 
	private int x, y, z;
	private Piece piece; //la pièce de plateau associée à la tuile de notre igm
	

	public PseudoTuile(Piece p ,int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		piece= p ;  
	}
	
	public Piece getPiece() {
		return piece;
	}

	@Override
	public ImageIcon getImage() {
		return piece.getImage();
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public boolean isHighlighted() {
		return highlighted;
	}

	@Override
	public void setHighlighted(boolean b) {
		highlighted = b;
	}
}
