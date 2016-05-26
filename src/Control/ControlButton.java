package Control;

import Model.Jeu;
import View.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		int source2 = e.getButton();

		fenetre.timer.start();

		// A partir des coordonnées du clic, dévoile la case et celles adjacentes
		//clic gauche
		if (source2 == MouseEvent.BUTTON1) {
			for (int i = 0; i < this.jeu.getGrille().getHauteur(); i++) {
				for (int j = 0; j < this.jeu.getGrille().getLargeur(); j++) {
					if (this.fenetre.getGrille()[i][j] == source && !this.jeu.getGrille().getGrille()[i][j].isDrapeau()) {
						jeu.devoileCase(i, j);
						fenetre.updateFen();
					}
				}
			}
			// clic droit
		}else if (source2 == MouseEvent.BUTTON3){
			for (int i = 0; i < this.jeu.getGrille().getHauteur(); i++) {
				for (int j = 0; j < this.jeu.getGrille().getLargeur(); j++) {
					if (this.fenetre.getGrille()[i][j] == source) {
						if (!this.jeu.getGrille().getGrille()[i][j].isDrapeau()){
							this.jeu.poserDrapeau(i,j);
						}else{
							this.jeu.retirerDrapeau(i,j);
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