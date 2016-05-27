package Control;

import Model.Grille;
import Model.Jeu;
import View.Fenetre;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlButton extends Control implements MouseListener {
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
	 * @contributor : Raphael-R-R
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		int source2 = e.getButton();

		if(jeu.time.getTemps()==0){
			jeu.time.start();
		}

		// A partir des coordonnées du clic, dévoile la case et celles adjacentes
		//clic gauche
		if (source2 == MouseEvent.BUTTON1 && !(jeu.getGrille().isPerdue() ||
				jeu.getGrille().isGagnee())) {

			int hauteur = jeu.getGrille().getHauteur(), largeur = jeu.getGrille().getLargeur();

			for (int i = 0; i < hauteur; i++) {
				for (int j = 0; j < largeur; j++) {
					if (fenetre.getGrille()[i][j].equals(source) && !jeu.getGrille().getGrille()[i][j].isDrapeau()) {
						jeu.devoileCase(i, j);
						fenetre.updateFen();
					}
				}
			}
			// clic droit
		}else if (source2 == MouseEvent.BUTTON3 && !(jeu.getGrille().isPerdue() ||
				jeu.getGrille().isGagnee())){

			int hauteur = jeu.getGrille().getHauteur(), largeur = jeu.getGrille().getLargeur();

			for (int i = 0; i < hauteur; i++) {
				for (int j = 0; j < largeur; j++) {
					if (fenetre.getGrille()[i][j].equals(source)) {
						if (!jeu.getGrille().getGrille()[i][j].isDrapeau()){
							jeu.poserDrapeau(i,j);
						}else{
							jeu.retirerDrapeau(i,j);
						}
						fenetre.updateFen();
					}


				}
			}
		}else{

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}