package utils;

import java.util.ArrayList;

public class TuilesJouables {
	
	private ArrayList<Piece> liste = new ArrayList<Piece>();
	
	public void add(Piece p) {
		liste.add(p);
	}
	
	public void remove(Piece p) {
		liste.remove(p);
	}
	
	public boolean coupPossible() {
		for(int i=0; i<liste.size()-1; i++) {
			for(int j=i+1; j<liste.size(); j++) {
				if(liste.get(i).appariable(liste.get(j))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int size() {
		return liste.size();
	}
	
	public Piece get(int i) {
		return liste.get(i);
	}
	
	public boolean contains(Piece p) {
		return liste.contains(p);
	}
	
	
	public Piece [] uneSolution() {
		Piece [] res = new Piece[2];
		for(int i=0; i<liste.size()-1; i++) {
			for(int j=i+1; j<liste.size(); j++) {
				if(liste.get(i).appariable(liste.get(j))) {
					 res[0]=liste.get(i);
					 res[1]=liste.get(j);
					 return res;
				}
			}
		}
		return res;
	}
	
}
