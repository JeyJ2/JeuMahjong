package mahjong.gui;

import javax.swing.ImageIcon;

import utils.Piece;

public interface IGMTuile {
	ImageIcon getImage();
	int getX();
	int getY();
	int getZ();
	boolean isHighlighted();
	void setHighlighted(boolean b);
	Piece getPiece();
}
