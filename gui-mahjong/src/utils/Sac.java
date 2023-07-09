package utils;

import java.util.ArrayList;
import java.util.Random;

public class Sac{
	
	private ArrayList<Piece> liste;
	
	private static Random random = new Random();
	
	
	public Sac(){
		this.liste= new ArrayList<Piece>();
	}
	
	public void add(Piece p) {
		liste.add(p);
	}
	
	public void remove(Piece p) {
		liste.remove(p);
	}
	
	public int taille() {
		return liste.size();
	}
	
	public Piece get(int ind) {
		return liste.get(ind);
	}
	
	public Piece tireAlea() {
		int indicePiece = (int) (random.nextDouble()*this.taille());
		Piece p = this.get(indicePiece);
		this.remove(p);
		return p;
	}
	
	public void constructionPieces() {
		char [] lettres = {'C','B','K'};
		
		for(char lettre : lettres) {
			String categorie="";
			switch(lettre){
			case 'C':
				categorie = "CERCLE";
				break;
			case 'B':
				categorie = "BAMBOU";
				break;
			case 'K':
				categorie = "CARACTERE";
				break;
			}
			for(int i=0; i<9 ; i++) {
				for(int j=0; j<4; j++) {
					Piece truc = new PieceGr1(categorie,lettre,i+1, j+1);
					liste.add(truc);
				}
			}
		}
		
		char [] autreLettres= {'S','N','O','E','V','R','W','F','P'};
		
		for(char lettre : autreLettres) {
			String categorie="";
			switch(lettre) {
			case 'S':
				categorie="VENT_SUD";
				break;
			case 'N':
				categorie="VENT_NORD";
				break;
			case 'O':
				categorie="VENT_OUEST";
				break;
			case 'E':
				categorie="VENT_EST";
				break;
			case 'V':
				categorie="DRAGON_VERT";
				break;
			case 'R':
				categorie="DRAGON_ROUGE";
				break;
			case 'W':
				categorie="DRAGON_BLANC";
				break;
			case 'F':
				categorie="FLEUR";
				break;
			case 'P':
				categorie="SAISON";
				break;
			}
			for(int i=0; i<4;i++) {
				Piece truc;
				if(lettre=='F'||lettre=='P') {
					truc = new PieceGr3(categorie, lettre,1, i+1);
				}else {
					truc = new PieceGr2(categorie, lettre,1, i+1);
				}
				liste.add(truc);
			}	
		}
	}
	
	
	
}
