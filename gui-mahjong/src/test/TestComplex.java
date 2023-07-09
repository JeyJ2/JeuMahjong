package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.ImageIcon;

import mahjong.gui.IGMahjong;

public class TestComplex {
	IGMahjong igm;
	public TestComplex() throws Exception {
		igm = new IGMahjong(false);
		ArrayList<ImageIcon> images = getShuffledImages();
		ArrayList<int[]> positions = readFile("bridge");	
		for (int[] tab : positions) {
			igm.ajouterTuile(images.remove(0), tab[0], tab[1], tab[2]);
		}
		igm.repaint();
	}

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
		Collections.shuffle(res);
		return res;
	}

	public static void main(String[] args) throws Exception{
		new TestComplex();

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
