package Control;
import View.*;
import java.awt.event.*;

/**
 * @author Corentin
 */

public class ControlMenu extends Control implements ActionListener {
    Fenetre fenetre;


    public ControlMenu(Fenetre fenetre){
        this.fenetre = fenetre;
        fenetre.setControlMenu(this);
    }

    /**
     * Gère le menu en accèdant aux fontions crées dans la fenêtre
     * @param e
     */

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == fenetre.getmItemNouvellePartie()){
            fenetre.restart();
        }
        else if(e.getSource() == fenetre.getmItemScores()){
            fenetre.afficherScores();
        }
        else if(e.getSource() == fenetre.getmItemQuitter()){
            fenetre.quitter();
        }

    }
}
