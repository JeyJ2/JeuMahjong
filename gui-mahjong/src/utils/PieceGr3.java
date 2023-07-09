package utils;


import javax.swing.ImageIcon;
import afficheImg.*;

public class PieceGr3 extends Piece {
	private ImageIcon image;
	private String[] nomsImgFleur= {"FLEUR_BAMBOU.png","FLEUR_CHRYSANTHEME.png","FLEUR_ORCHIDEE.png","FLEUR_PRUNIER.png"};
	private String[] nomsImgSaison= {"SAISON_HIVER.png","SAISON_PRINTEMPS.png","SAISON_ETE.png","SAISON_AUTOMNE.png"};


	public PieceGr3(String nom, char lettre, int indice, int num) {
		super(nom, lettre, indice, num);
		String nomImage="";
		if(lettre=='F') {
			nomImage=nomsImgFleur[num-1];
		}
		else if(lettre=='P') {
			nomImage=nomsImgSaison[num-1];
		}
		image= new ImageIcon("images/"+nomImage);
	}
	
	public ImageIcon getImage() {
		return image;
	}
	
}
