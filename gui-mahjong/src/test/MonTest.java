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

public class MonTest {
	IGMahjong jeu;
	MahjongControler controler;
	Plateau plateau;
	public MonTest() throws Exception {
		Sac monSac = new Sac();
		monSac.constructionPieces();
		//début interface graphique
		jeu = new IGMahjong(true);
		ArrayList<int[]> positions = readFile("arena");
		System.out.println(positions.size());
		plateau = new Plateau(8,18,positions, jeu);
		jeu.setPlateau(plateau);
		plateau.arrangePieces(monSac);
		controler = new PseudoControler(jeu,plateau);
		
		jeu.repaint();
		jeu.setControler(controler);
		jeu.ajouterBouton("RETIRER","");
		jeu.ajouterBouton("ABONDONNER","rouge");
		jeu.setMessage("Cliquez sur des tuiles et activez les boutons");
	}
	
	/*
	
	private ArrayList<ImageIcon> getShuffledImages() {
		ArrayList<ImageIcon> res = new ArrayList<ImageIcon>();
		ImageIcon image;
		String[] anum = {"BAMBOU","CERCLE","CARACTERE","DRAGON_BLANC",
				"DRAGON_ROUGE", "DRAGON_VERT","VENT_SUD","VENT_EST",
				"VENT_OUEST","VENT_NORD"};
		String[] anum2 = {"SAISON_AUTOMNE", "SAISON_ETE",
				"SAISON_HIVER", "SAISON_PRINTEMPS", "FLEUR_PRUNIER",
				"FLEUR_ORCHIDEE", "FLEUR_BAMBOU", 
				"FLEUR_CHRYSANTHEME"};
		for (int i=0; i<3; i++) {
			for (int num=1; num<10; num++) {
				image = new ImageIcon("images/"+anum[i]+"_"+num+".png");
				for (int j=0; j<4; j++)
					res.add(image);
			}
		}
		for (int i=3; i<anum.length; i++) {
			image = new ImageIcon("images/"+anum[i]+".png");
			for (int j=0; j<4; j++)
				res.add(image);
		}
		for (int i=0; i<anum2.length; i++)
			res.add(new ImageIcon("images/"+anum2[i]+".png"));
		//Collections.shuffle(res);
		return res;
	}
	
	public void stopControler() {
		this.controler = null; 
	}
	
	*/
	
	
	public static void main(String[] args) throws Exception{
		new MonTest();
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
