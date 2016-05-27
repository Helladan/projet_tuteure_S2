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
	 *
	 */

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		System.out.println("Click sur menu");

		if(source.equals(fenetre.getmItemNouvellePartie())) {
			//jeu.nouvellePartie();
			fenetre.restart();
		}
		else if(source.equals(fenetre.getmItemScores())) {
			fenetre.afficherScores();
		}
		else if(source.equals(fenetre.getmItemQuitter())) {
			fenetre.quitter();
		}

	}
}
