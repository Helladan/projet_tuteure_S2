import Model.Jeu;
import View.Fenetre;

/**
 * Classe comprenant le main du jeu
 *
 * @author Raphael-R-R
 */

public class Demineur {
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Fenetre fenetre = new Fenetre(jeu);
	}
}
