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

		fenetre.timer.start();

		// A partir des coordonnées du clic, dévoile la case et celles adjacentes

		for(int i = 0; i < this.jeu.getGrille().getHauteur(); i++) {
			for(int j = 0; j < this.jeu.getGrille().getLargeur(); j++) {
				if(this.fenetre.getGrille()[i][j] == source) {
					if (!this.jeu.getGrille().getGrille()[i][j].isMine() ) {
						jeu.devoileCase(i, j);
					}else {

					}
				}
			}
		}
		// Ecrit sur les cases le nombre de mines adjacentes
		for(int i = 0; i < this.jeu.getGrille().getHauteur(); i++) {
			for(int j = 0; j < this.jeu.getGrille().getLargeur(); j++) {
				if (this.jeu.getGrille().getGrille()[i][j].isDevoilee()) {
					fenetre.editColor(i,j);
					//if (jeu.getGrille().getGrille()[i][j].getMinesAdjacentes() !=0.0) {
						fenetre.getGrille()[i][j].setText(Integer.toString(jeu.getGrille().getGrille()[i][j].getMinesAdjacentes()));
						fenetre.updateFen();
					//}
				}
			}
		}
	}



}