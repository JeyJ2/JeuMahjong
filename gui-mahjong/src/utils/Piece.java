package utils;

import javax.swing.ImageIcon;

import test.PseudoTuile;

public class Piece extends Type{
	private String identifiant;
	private int numero;
	private PseudoTuile tuileAsso = null;
	
	public Piece(String nom, char lettre, int chiffre , int num){
		super(nom,lettre,chiffre);
		this.numero=num;
		this.identifiant=lettre+""+chiffre+""+num;
	}
	
	public String identifiant() {
		return this.identifiant;
	}
	
	public int numeroPiece() {
		return numero;
	}
	
	public boolean appariable (Piece p) {
		if(this.type().equals(p.type())) return true;
		else return false;
	}
	
	public String toString() {
		return "Je suis de categorie : "+nom+" de Type : "+type+" numeroPiece: "+numero+" id: "+identifiant;
	}
	
	public ImageIcon getImage() {
		return null;
	}
	
	public void setTuileAssociee(PseudoTuile tuile) {
		tuileAsso =tuile;
	}
	public PseudoTuile getTuileAssociee() {
		return tuileAsso; 
	}
}
