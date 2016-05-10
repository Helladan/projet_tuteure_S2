package Model;

/**
 * Classe du jeu.
 * @author Tarek
 */
public class Partie {
    private Chrono time;
    private Grille grille;

    /**
     * Constructeur par défaut de jeu.
     */
    public Partie() {
        grille = new Grille();
        time = new Chrono();
    }

    /**
     * Constructeur de jeu avec des dimensions
     * @param hauteur
     * @param larg
     * @param nbreMines
     */
    public Partie(int hauteur, int larg, int nbreMines) {
        grille = new Grille(hauteur, larg, nbreMines);
        time = new Chrono();
    }


}
