package utils;

public class Type extends Categorie{
	protected String type;
	protected int indice;
	
	public Type(String nom, char lettre, int chiffre) {
		super(nom,lettre);
		this.indice=chiffre;
		this.type=lettre+""+chiffre;
	}
	

	public int indiceType() {
		return indice;
	}
	
	public String type() {
		return type;
	}
	
	
	public String toString() {
		return "Je suis de catégorie : "+nom+" de Type : "+type;
	}
	
}

