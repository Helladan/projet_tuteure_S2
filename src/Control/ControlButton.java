package Control;

import Model.Jeu;
import View.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButton extends Control implements ActionListener {
	/**
	 * Constructeur de ControlButton
	 */
	public ControlButton(Jeu jeu, Fenetre fenetre) {
		super(jeu, fenetre);
		fenetre.setControlButton(this);

	}

	/**
	 * Traitement des boutons
	 *
	 * @param e
	 */

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		for(int i = 0; i < this.jeu.getGrille().getHauteur(); i++) {
			for(int j = 0; j < this.jeu.getGrille().getLargeur(); j++) {
				if(this.fenetre.getGrille()[i][j] == source) {
					jeu.devoileCase(i, j);
					System.out.print("Clic sur case");
				}
			}
		}
		for(int i = 0; i < this.jeu.getGrille().getHauteur(); i++) {
			for(int j = 0; j < this.jeu.getGrille().getLargeur(); j++) {
				if (this.jeu.getGrille().getGrille()[i][j].isMine()) {
					fenetre.editColor(i,j);
					fenetre.updateFen();
					System.out.print("-------------------------------------");
				}
			}
		}
	}



}