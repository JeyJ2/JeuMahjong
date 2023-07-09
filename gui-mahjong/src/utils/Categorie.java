package utils;

public class Categorie {
	protected String nom;
	protected char initial;
	
	public Categorie(String nom, char lettre) {
		this.nom=nom;
		this.initial=lettre;
	}
	public String nom() {
		return nom;
	}
	public char initial() {
		return initial;
	}
	public String toString() {
		return "Je suis une catégorie nom : "+nom+" initial: "+initial;
	}
}
