package test;

import java.util.Scanner;

import javax.swing.ImageIcon;

import mahjong.gui.IGMahjong;

public class TestIGMahjong {

	public static void main(String[] args) {
		IGMahjong igm = new IGMahjong(true);
		ImageIcon[] tab = new ImageIcon[9];
		Scanner scan = new Scanner(System.in);
		for (int i=1; i<10; i++)
			tab[i-1]=new ImageIcon("images/CARACTERE_"+i+".png");
		for (int i=0; i<9; i++) {
			igm.ajouterTuile(tab[i], i/3, i%3, 0);
			if (i==5) {
				System.out.println("ajout de trois tuiles\nappuyez sur <entrée>");
				scan.nextLine();
			}
		}
		System.out.println("suppression de 2 tuiles\nappuyez sur <entrée>");
		scan.nextLine();
		igm.retirerPaire(0, 0, 0, 2, 2, 0);
		System.out.println("affichage d'un message\nappuyez sur <entrée>");
		scan.nextLine();
		igm.setMessage("ceci est un message affiché");
		System.out.println("highlight d'une tuile\nappuyez sur <entrée>");
		scan.nextLine();
		igm.highlight(1, 1, 0);
		System.out.println("highlight d'une autre tuile\nappuyez sur <entrée>");
		scan.nextLine();
		igm.highlight(0, 1, 0);
		System.out.println("unhighlight\nappuyez sur <entrée>");
		scan.nextLine();
		igm.unhighlight();
		System.out.println("empilement de 3 tuiles\nappuyez sur <entrée>");
		scan.nextLine();
		for (int i=0; i<3; i++)
			igm.ajouterTuile(tab[i], 1, i, 1);
		System.out.println("empilement de 2 tuiles\nappuyez sur <entrée>");
		scan.nextLine();
		for (int i=0; i<2; i++)
			igm.ajouterTuile(tab[i], 1, i, 2);
		System.out.println("ajout d'un bouton\nappuyez sur <entrée>");
		scan.nextLine();
		igm.ajouterBouton("Congolexicomatisation","");
		
	}

}
