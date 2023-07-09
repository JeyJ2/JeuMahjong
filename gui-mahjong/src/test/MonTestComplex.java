package test;

import utils.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import afficheImg.*;

import mahjong.gui.IGMahjong;
import mahjong.gui.MahjongControler;

public class MonTestComplex {
	IGMahjong jeu;
	MahjongControler controler;
	Plateau plateau;
	public MonTestComplex() throws Exception {
		Sac monSac = new Sac();
		monSac.constructionPieces();
		//début interface graphique
		jeu = new IGMahjong(false);

		//PseudoTuile pst;
		//ImageIcon image;
		//ArrayList<ImageIcon> images = getShuffledImages();
		ArrayList<int[]> positions = readFile("default");
		System.out.println(positions.size());
		plateau = new Plateau(15,28,positions, jeu);
		jeu.setPlateau(plateau);
		plateau.arrangePieces(monSac);
		controler = new PseudoControler(jeu,plateau);
	    
	
		/*
		for (int[] tab : positions) {
			Piece p = monSac.tireAlea();
			//ImageIcon image = p.getImage();
			PseudoTuile pst = new PseudoTuile(p,tab[0], tab[1], tab[2]);
			jeu.ajouterTuile(pst);
		}
		*/

		jeu.repaint();
		jeu.setControler(controler);
		jeu.ajouterBouton("RETIRER","");
		jeu.ajouterBouton("ABONDONNER","rouge");
		jeu.setMessage("Cliquez sur des tuiles et activez les boutons");
	}
	

	
	public static void main(String[] args) throws Exception{
		new MonTestComplex();
	}
	
	private ArrayList<int[]> readFile(String layoutName) throws FileNotFoundException {
        File file = new File("layouts/" + layoutName);
        Scanner scan = new Scanner(file);
        ArrayList<int[]> res = new ArrayList<int[]>();
        String line;
        String[] lineContent;
        int[] triplet = {0,0,0};
        try {
        	do {
        		line = scan.nextLine().trim();
        		if (line.charAt(0)!='#') {
        			lineContent = line.split("\\s+");
        			//System.out.println("nb cases: " + lineContent.length);
        			for (int i=0; i<lineContent.length; i++) {
        				triplet[i]=Integer.parseInt(lineContent[i].trim());
        			}        		
        			res.add(Arrays.copyOf(triplet, 3));
        		}        	
        	}while(true);
        }catch(NoSuchElementException ex) {}
		return res;
	}


}
