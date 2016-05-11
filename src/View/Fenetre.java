package View;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author Nassime
 */

public class Fenetre extends JFrame{

    /**
     * Déclaration des attributs
     */

    Model m;
    JMenuItem mItemNouvellePartie;
    JMenuItem mItemScores;
    JMenuItem mItemQuitter;
    JLabel chrono;
    JButton[][] grille;

    /**
     * Constructeur de Fenetre
     */

    public Fenetre(Model model){
        this.m=model;

        initAttribut();
        creerMenu();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initialisation des attributs
     */

    public void initAttribut(){

        mItemNouvellePartie = new JMenuItem("Nouvelle partie");
        mItemScores = new JMenuItem("Scores");
        mItemQuitter = new JMenuItem("Quitter");
        //chrono =

    }

    /**
     * Création de la barre de menu
     */

    public void creerMenu() {

        JMenuBar barMenu = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        menu.add(mItemNouvellePartie);
        menu.add(mItemScores);
        menu.add(mItemQuitter);

        barMenu.add(menu);

        setJMenuBar(barMenu);

    }

    /**
     * Création de la grille
     */

    public void creerGrille(){



    }

    /**
     * Ajout d'écouteur sur les JMenuItem
     * @param al
     */

    public void setControlMenu(ActionListener al){

        mItemNouvellePartie.addActionListener(al);
        mItemScores.addActionListener(al);
        mItemQuitter.addActionListener(al);

    }

    /**
     * Affichage d'une nouvelle grille lors du clique sur mItemNouvellePartie
     */

    public void restart(){

        getContentPane().removeAll();

        creerGrille();
        pack();

    }

    /**
     * Affichage des scores lors du clique sur mItemScores
     */

    public void afficherScores(){



    }

    /**
     * Quitte le jeu lors du clique sur mItemQuitter
     */

    public void Quitter(){

        System.exit(0);

    }


}
