package Control;

import Model.Chrono;
import Model.Grille;
import Model.Jeu;
import View.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButton extends Control implements ActionListener{

    public ControlButton(Jeu jeu , Fenetre fenetre, Grille grille) {
        super(jeu,fenetre, grille);
        fenetre.setControlBouton(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i =0; i< this.grille.getHauteur(); i++)
            for (int j =0; j < this.grille.getLargeur(); j++){
                if (this.fenetre.getGrid() == source){
                    jeu.devoileCase(i,j);
                }

            }
    }
}