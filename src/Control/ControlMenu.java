package Control;
import View.*;
import java.awt.event.*;

/**
 * @author Corentin
 */

public class ControlMenu extends Control implements ActionListener {
    Fenetre fenetre;
    fenetre.setControlMenu(this);


    public ControlMenu(){

    }

    public ControlMenu(Fenetre fenetre){
        this.fenetre = fenetre;
    }

    /**
     * Gère le menu en accèdant aux fontions crées dans la fenêtre
     * @param e
     */

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == fenetre.mItemNouvellePartie){
            fenetre.restart();
        }
        else if(e.getSource() == fenetre.mItemScores){
            fenetre.afficherScores();
        }
        else if(e.getSource() == fenetre.mItemQuitter){
            fenetre.quitter();
        }

    }
}
