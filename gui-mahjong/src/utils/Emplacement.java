package utils;

public class Emplacement {
    private int tailleMax; // Taille maximale de la pile
    private int indTete; // Index du sommet de la pile
    private Piece[] tabPieces; // Tableau pour stocker les éléments de la pile
    private int hauteur;  // important uniquement dans le cas complexe

    public Emplacement(int taille, int h) {
    	tailleMax = taille;    	
        indTete = -1; // Initialiser le sommet de la pile à -1 pour indiquer qu'il est vide
        tabPieces = new Piece[tailleMax]; // Créer un tableau avec la taille maximale spécifiée
        this.hauteur = h; 
    }
    
    public int getIndTete() {
    	return indTete;
    }
    
    public int getHauteur() {
    	return hauteur; 
    }
    
    

    // Méthode pour ajouter un élément à la pile
    public void ajout(Piece piece) {
        if (indTete < tailleMax - 1) {
            indTete++;
            tabPieces[indTete] = piece;
        } else {
            System.out.println("La pile est pleine, impossible d'ajouter l'element " + piece.toString());
        }
    }

    // Méthode pour retirer le dernier élément de la pile
    public void retire() {
        if (indTete >= 0) {
            Piece piece = tabPieces[indTete];
            indTete--;
            System.out.println("La piece suivante a ete retiree : "+piece.toString());
        } else {
            System.out.println("La pile est vide, impossible de retirer un element");
        }
    }

    // Méthode pour obtenir le dernier élément de la pile sans le retirer
    public Piece sommet() {
        if (indTete >= 0) {
            Piece piece = tabPieces[indTete];
            return piece;
        } else {
            //System.out.println("La pile est vide, aucun élément à retourner");
            return null;
        }
    }

    // Méthode pour vérifier si la pile est vide
    public boolean estVide() {
        return (indTete == -1);
    }

    // Méthode pour vérifier si la pile est pleine
    public boolean estPleine() {
        return (indTete == tailleMax - 1);
    }

}
