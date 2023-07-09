package utils;

import javax.swing.ImageIcon;

import afficheImg.*;

public class PieceGr2 extends Piece {
	private ImageIcon image;

	public PieceGr2(String nom, char lettre, int indice, int num) {
		super(nom, lettre, indice, num);
		String nomImage=nom+".png";
		image = new ImageIcon("images/"+nomImage);
	}
	
	public ImageIcon getImage() {
		return image;
	}
	
}
