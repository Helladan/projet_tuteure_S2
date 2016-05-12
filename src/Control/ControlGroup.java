package Control;

import Model.Jeu;
import View.Fenetre;

public class ControlGroup {

    private Jeu jeu;
    private Fenetre fenetre;

    public ControlButton controlButton;
    public ControlMenu controlMenu;

    public ControlGroup(Jeu jeu){
        this.jeu = jeu;

        fenetre = new Fenetre(jeu);

        controlButton = new ControlButton(jeu, fenetre);
        controlMenu = new ControlMenu(fenetre);

    }
}
