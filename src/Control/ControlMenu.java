package Control;

import View.*;

import java.awt.event.*;

/**
 * @author Corentin
 */

public class ControlMenu extends Control implements ActionListener {
	Fenetre fenetre;


	/* @contributor : Raphael-R-R */

	public ControlMenu(Fenetre fenetre) {
		this.fenetre = fenetre;
		this.fenetre.setControlMenu(this);
	}

	/**
	 * Gère le menu en accèdant aux fontions crées dans la fenêtre
	 *
	 * @param e
	 *
	 * @contributor : Raphael-R-R
	 */

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		System.out.println("Click sur menu");

		if(source.equals(fenetre.getmItemNouvellePartie())) {
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
