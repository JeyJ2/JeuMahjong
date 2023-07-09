package test;

import mahjong.gui.*;
import utils.Plateau;

import java.util.ArrayList;

public class PseudoControler implements MahjongControler {
	
	private IGMahjong jeu;
	private Plateau plateau;
	private ArrayList<IGMTuile> highlighted;
	private int compteurAbondon = 0;
	
	public PseudoControler(IGMahjong igm, Plateau p) {
		jeu = igm ;
		plateau = p;
		highlighted = new ArrayList<IGMTuile>(); //les tuiles selectionnées 
	}
	

	@Override
	public void clic(int x, int y, int z) {
		compteurAbondon=0;
		System.out.println("La méthode clic a été appelée ("+x+","
				+y+","+z+")");
		IGMTuile tuile = jeu.tuile(x, y, z);
		if(highlighted.contains(tuile)) {
			jeu.unhighlight(x, y, z);
			highlighted.remove(tuile);
		}else {
			if(highlighted.size()<2 && plateau.estJouable(tuile.getPiece())) {
				jeu.highlight(x, y, z);
				highlighted.add(tuile);
			}
		}
		
	}

	@Override
	public void buttonPressed(String identifier){
		if(identifier.equals("RETIRER")) {
			compteurAbondon=0;
			if(highlighted.size()<2) {
				jeu.setMessage("Veuillez sélectionner 2 tuiles");
			}else {
				IGMTuile t1 = highlighted.get(0);
				IGMTuile t2 = highlighted.get(1);
				if(t1.getPiece().appariable(t2.getPiece())) {
					jeu.retirerPaire(t1, t2);
					plateau.retirer(t1.getX(), t1.getY(), t2.getX(), t2.getY());
					highlighted.clear();
					if(!plateau.coupPossible()) {
						jeu.arretBoutons();
						jeu.setControler(null);
						jeu.setMessage("PARTIE TERMINEE il n'y a pas de coup possible ! ");
					}
					
					 //intéréssant pour tester
				/*
					else {
						jeu.solutionAbondon();
						plateau.afficheJouable();
					}
		
				*/
				}else {
					jeu.setMessage("Attention les deux tuiles ne correspondent pas" );
					highlighted.clear();
					jeu.unhighlight();
				}
			}
		}
		
		if(identifier.equals("ABONDONNER")) {
			compteurAbondon++; 
			highlighted.clear();
			jeu.unhighlight();
			
			if(compteurAbondon==2) {
				jeu.solutionAbondon();
				jeu.arretBoutons();
				jeu.setControler(null);
				jeu.setMessage("PARTIE TERMINEE SUITE A ABONDON \n Voici une solution possible");
			
			}else {
				jeu.setMessage("Voulez vous vraiment arrêter le jeu ?? si oui cliquez encore une fois sur ABONDONNER ");
			}
		}
		System.out.println("Le bouton " + identifier + " a été activé");
	}
	
	

}
