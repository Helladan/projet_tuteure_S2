package Control;

import Model.Jeu;
import View.*;

import java.awt.event.*;

/**
 * @author Corentin
 */

public class ControlMenu extends Control implements ActionListener {
	Fenetre fenetre;
	Jeu jeu;


	public ControlMenu(Jeu j, Fenetre fenetre) {
		this.fenetre = fenetre;
		jeu = j;
		this.fenetre.setControlMenu(this);
	}

	/**
	 * Gère le menu en accèdant aux fontions crées dans la fenêtre
	 *
	 * @param e
	 */

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source.equals(fenetre.getmItemNouvellePartie())) {
			fenetre.restart();
		}
		else if(source.equals(fenetre.getmItemScores())) {
			fenetre.afficherScores();
		}
		else if(source.equals(fenetre.getmItemQuitter())) {
			fenetre.quitter();
		}
		else if(source.equals(fenetre.getMenuItem10x10())) {
			fenetre.changerTaille(10, 10);
		}
		else if(source.equals(fenetre.getMenuItem15x15())) {
			fenetre.changerTaille(15, 15);
		}
		else if(source.equals(fenetre.getMenuItem20x20())) {
			fenetre.changerTaille(20, 20);
		}
		else if(source.equals(fenetre.getMenuItemFacile())) {
			fenetre.changerDifficulte(1);
		}
		else if(source.equals(fenetre.getMenuItemMoyen())) {
			fenetre.changerDifficulte(2);
		}
		else if(source.equals(fenetre.getMenuItemDifficile())) {
			fenetre.changerDifficulte(3);
		}

	}
}
