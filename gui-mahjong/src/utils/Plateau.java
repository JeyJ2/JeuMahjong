package utils;

import java.util.ArrayList;

import mahjong.gui.IGMahjong;
import test.PseudoTuile;

public class Plateau {
	int nbrLignes;
	int nbrColonnes;
	Emplacement [] [] cases; 
	TuilesJouables liste;
	IGMahjong igm;
	
	
	public Plateau(int l, int c, ArrayList<int[]> disp,	IGMahjong jeu ) {
		nbrLignes = l;
		nbrColonnes = c;
		cases = new Emplacement [l][c];
		igm = jeu;
		int sumTailleEmpl = 0; 
		for(int i=0; i<l ; i++) {
			for(int j=0; j<c ; j++) {
				int taille=0;
				int hauteur = 144 ;  //valeur arbitraire 
				for(int k=0; k<disp.size() ; k++) {
					if(igm.estSimple() && disp.get(k)[0]/2==i && disp.get(k)[1]/2==j) {  //division par 2 car jeu simple
						if(disp.get(k)[2]>=0) {
							taille++;
							if(disp.get(k)[2]<hauteur) {
								hauteur = disp.get(k)[2];
							}
						}
					}else if(!igm.estSimple() && disp.get(k)[0]==i && disp.get(k)[1]==j){
						if(disp.get(k)[2]>=0) {
							taille++;
							if(disp.get(k)[2]<hauteur) {
								hauteur = disp.get(k)[2];
							}
						}
					}
				}
				cases[i][j] = new Emplacement(taille, hauteur);
				if(hauteur!=144) System.out.println(i+"_"+j+"_"+hauteur);
			}
		}
		liste = new TuilesJouables();
	}
	
	
	
	
	
	public void arrangePieces(Sac monSac) {
		for(int i=0; i<nbrLignes; i++) {
			for(int j=0 ; j<nbrColonnes ; j++) {
				int z = cases[i][j].getHauteur();
				while(!cases[i][j].estPleine()) {
					Piece p = monSac.tireAlea();
					cases[i][j].ajout(p);
					PseudoTuile tuile = new PseudoTuile(p,i,j,z);
					p.setTuileAssociee(tuile);
					igm.ajouterTuile(tuile);
					z++;
				}
			}
		}
		igm.repaint();
		
		if(igm.estSimple()) {
			this.initJouable();
		}else {
			this.initJouableComplex();
		}
	}
	
	
	public void initJouable() {
		//partie ajout liste pieces jouables
		for(int i=0; i<nbrLignes; i++) {
			for(int j=0 ; j<nbrColonnes ; j++) {
				
				if(cases[i][j].sommet()!=null) {
					
					if(j==0||j==nbrColonnes-1) {
						liste.add(cases[i][j].sommet());
					}else {
						if(cases[i][j-1].getIndTete()<cases[i][j].getIndTete()||cases[i][j+1].getIndTete()<cases[i][j].getIndTete())
							liste.add(cases[i][j].sommet());
					}
				}
			}
		}
	}
	
	public void initJouableComplex() {
		//partie ajout liste pieces jouables
		for(int i=0; i<nbrLignes; i++) {
			for(int j=0 ; j<nbrColonnes ; j++) {
					//d'abord condition de supersposition 
					int hauteur = cases[i][j].getHauteur();
					if(cases[i][j].sommet()!=null && this.emplacementVide(i-1, j-1, hauteur)&&this.emplacementVide(i-1, j+1, hauteur)&&this.emplacementVide(i+1, j-1, hauteur)&&this.emplacementVide(i+1, j+1, hauteur)) {
						if(j==0||j==nbrColonnes-1||j==1||j==nbrColonnes-2) {
							if(!liste.contains(cases[i][j].sommet())) liste.add(cases[i][j].sommet());
						}else {
							int haut = cases[i][j].getIndTete(); 
							if(this.indTeteCase(i-1,j+2)< haut&& this.indTeteCase(i+1,j+2) <haut&& this.indTeteCase(i,j+2) <haut) {
								if(!liste.contains(cases[i][j].sommet())) liste.add(cases[i][j].sommet());
							}
							if(this.indTeteCase(i-1,j-2) < haut&&this.indTeteCase(i+1,j-2) <haut&&this.indTeteCase(i,j-2) <haut) {
								if(!liste.contains(cases[i][j].sommet())) liste.add(cases[i][j].sommet());
							}	
						}
					}
				
			}
		}
	}
	
	//pseudo fonctions pour éviter effet de bord
	public boolean emplacementVide(int i , int j, int hauteur ) {  
		if(i<0||i>nbrLignes-1||j<0||j>nbrColonnes-1) {
			return true;
		}else {
			if(cases[i][j].getHauteur()<hauteur) {
				return true;
			}
			return cases[i][j].estVide();
		}
	}
	
	public int indTeteCase(int i , int j ) {
		if(i<0||i>nbrLignes-1||j<0||j>nbrColonnes-1) {
			return -1;
		}else {
			return cases[i][j].getIndTete();
		}
	}
	
	
	
	
	
	public void retirer(int lig1, int col1, int lig2, int col2) {

			   liste.remove(cases[lig1][col1].sommet());
			   liste.remove(cases[lig2][col2].sommet());
			   cases[lig1][col1].retire();
			   cases[lig2][col2].retire();
			   System.out.println("Bravo les deux tuiles ont ete retirees ! ");
			   
			   
			   if(!igm.estSimple()) {
				   this.initJouableComplex();
			   }else {
			   
			   if(col1==0||col1==nbrColonnes-1) {
				   if(cases[lig1][col1].sommet()!=null) {
					   Piece p = cases[lig1][col1].sommet();
					   if(!liste.contains(p)) {
						   liste.add(p);
					   }  
				   }
				   //la partie influence pièces latérales
				   if(col1==0) {
					   if(cases[lig1][1].getIndTete()>cases[lig1][0].getIndTete()) {
						   Piece p = cases[lig1][1].sommet();
						   if(!liste.contains(p)){
							   liste.add(p);
						   }
					   }
				   }
				   if(col1==nbrColonnes-1) {
					   if(cases[lig1][nbrColonnes-2].getIndTete()>cases[lig1][nbrColonnes-1].getIndTete()) {
						   Piece p = cases[lig1][nbrColonnes-2].sommet();
						   if(!liste.contains(p)){
							   liste.add(p);
						   }
					   }
				   }
			   }else {
				   if(cases[lig1][col1].getIndTete()>cases[lig1][col1-1].getIndTete()||cases[lig1][col1].getIndTete()>cases[lig1][col1+1].getIndTete()) {
					   Piece p = cases[lig1][col1].sommet();
					   if(!liste.contains(p)){
						   liste.add(p);
					   }
				   }
				   if(cases[lig1][col1-1].getIndTete()>cases[lig1][col1].getIndTete()) {
					   Piece p = cases[lig1][col1-1].sommet();
					   if(!liste.contains(p)){
						   liste.add(p);
					   }
				   }
				   if(cases[lig1][col1+1].getIndTete()>cases[lig1][col1].getIndTete()) {
					   Piece p = cases[lig1][col1+1].sommet();
					   if(!liste.contains(p)){
						   liste.add(p);
					   }
				   }
			   }
			   
			   
			   //pour la deuxième tuile
			   
			   if(col2==0||col2==nbrColonnes-1) {
				   if(cases[lig2][col2].sommet()!=null) {
					   Piece p = cases[lig2][col2].sommet();
					   if(!liste.contains(p)){
						   liste.add(p);
					   }
				   }
				   //la partie influence pièces latérales
				   if(col2==0) {
					   if(cases[lig2][1].getIndTete()>cases[lig2][0].getIndTete()) {
						   Piece p = cases[lig2][1].sommet();
						   if(!liste.contains(p)){
							   liste.add(p);
						   }
					   }
				   }
				   if(col2==nbrColonnes-1) {
					   if(cases[lig2][nbrColonnes-2].getIndTete()>cases[lig2][nbrColonnes-1].getIndTete()) {
						   Piece p = cases[lig2][nbrColonnes-2].sommet();
						   if(!liste.contains(p)){
							   liste.add(p);
						   }
					   }
				   }
			   }else {
				   if(cases[lig2][col2].getIndTete()>cases[lig2][col2-1].getIndTete()||cases[lig2][col2].getIndTete()>cases[lig2][col2+1].getIndTete()) {
					   Piece p = cases[lig2][col2].sommet();
					   if(!liste.contains(p)){
						   liste.add(p);
					   }
				   }
				   //la partie influence pièces latérales
				   if(cases[lig2][col2-1].getIndTete()>cases[lig2][col2].getIndTete()) {
					   Piece p = cases[lig2][col2-1].sommet();
					   if(!liste.contains(p)){
						   liste.add(p);
					   }
				   }
				   if(cases[lig2][col2+1].getIndTete()>cases[lig2][col2].getIndTete()) {
					   Piece p = cases[lig2][col2+1].sommet();
					   if(!liste.contains(p)){
						   liste.add(p);
					   }
				   }
			   }
	
			   } // ferme else
	}
	
	public boolean coupPossible() {
		return liste.coupPossible(); // renvoie true si un coup suivant est possible 
	}

	
	/*
	public void affiche(){
		System.out.print(" ");
		for(int j=0; j< nbrColonnes; j++) {
			System.out.print("  "+(char)((int)'A'+j));
		}
		System.out.println();
		for (int i=0; i<nbrLignes ; i++) {
			System.out.print(i+" ");
			for(int j=0; j<nbrColonnes ; j++){
				if(j==0) {
					if(cases[i][0].sommet()!=null) {
						System.out.print("<"+cases[i][0].sommet().type());
					}else {
						System.out.print("   ");
					}
				}else if(j==nbrColonnes-1) {
					if(cases[i][j].sommet()!=null) {
						if(cases[i][j-1].getIndTete()>cases[i][j].getIndTete()){
							System.out.print(">"+cases[i][j].sommet().type()+">");
						}else if(cases[i][j-1].getIndTete()<cases[i][j].getIndTete()){
							System.out.print("<"+cases[i][j].sommet().type()+">");
						}else {
							System.out.print(" "+cases[i][j].sommet().type()+">");
						}
					}else {
						if(cases[i][j-1].getIndTete()>-1) {
							System.out.print(">  ");
						}else {
							System.out.print("   ");
						}
					}
				}else{
					if(cases[i][j].sommet()!=null) {
						if(cases[i][j-1].getIndTete()>cases[i][j].getIndTete()){
							System.out.print(">"+cases[i][j].sommet().type());
						}else if(cases[i][j-1].getIndTete()<cases[i][j].getIndTete()){
							System.out.print("<"+cases[i][j].sommet().type());
						}else {
							System.out.print(" "+cases[i][j].sommet().type());
						}
					}else {
						if(cases[i][j-1].getIndTete()>-1) {
							System.out.print(">  ");
						}else {
							System.out.print("   ");
						}
					}
				}
			}
			System.out.println();
		}
		
		for(int i=0; i<liste.size(); i++) {
			System.out.print(liste.get(i).type());
		}
		System.out.println();
	}
	
	*/
	
	public void afficheJouable() {

		for(int i=0; i<liste.size(); i++) {
			System.out.print(liste.get(i).type()+"|| ");
		}
	}
	
	public boolean estJouable(Piece piece) {
		return liste.contains(piece);
	}
	
	public Piece sommetCase(int lig, int col) {
		return cases[lig][col].sommet();
	}
	
	public Piece [] uneSolution() {
		return liste.uneSolution();  //interroge  liste de TuilesJouables
	}
	
	//une méthode qui renvoie un emplacement où la tuile placée au somment sera jouable
	
}
