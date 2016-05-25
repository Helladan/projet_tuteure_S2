package Model;

/**
 * Classe du jeu. Représente le modèle, fais le lien entre les classe Chrono,
 * Grille et Case. Chaque instance est une nouvelle partie.
 *
 * @author Tarek
 */
public class Jeu {
	private Chrono time;
	private Grille grille;

	/**
	 * Constructeur par défaut de jeu.
	 */
	public Jeu() {
		grille = new Grille();
		time = new Chrono();
		time.start();
	}

	/**
	 * Constructeur de jeu avec des dimensions.
	 *
	 * @param hauteur   hauteur de la grille.
	 * @param larg      largeur de la grille.
	 * @param nbreMines nombre de mines présentes dans la grille.
	 */
	public Jeu(int hauteur, int larg, int nbreMines) {
		grille = new Grille(hauteur, larg, nbreMines);
		time = new Chrono();
		time.start();
	}

	/**
	 * Méthode permettant d'arreter le chrono si la partie est gagnée ou perdue.
	 */
	private void gestionChrono() {
		if(grille.isPerdue() || grille.isGagnee()) time.stop();
	}

	/**
	 * Devoile la case indiquée dans la grille. (Correspond a l'action de
	 * cliqué sur une case non dévoilée.)
	 *
	 * @param hauteur La position en y de la case a dévoiler.
	 * @param larg    La position en x de la case a dévoiler.
	 */
	public void devoileCase(int hauteur, int larg) {
		grille.getGrille()[hauteur][larg].setAsDevoilee();
		grille.incrementeCasesDevoilees();
		if(grille.getGrille()[hauteur][larg].isMine()) grille.setGrillePerdue();
		devoileAuto(hauteur, larg);
		gestionChrono();
	}

	/**
	 * Marque la case comme un drapeau.
	 *
	 * @param hauteur La position en y de la case à marquer.
	 * @param larg    La position en x de la case à marquer.
	 */
	public void poserDrapeau(int hauteur, int larg) {
		grille.getGrille()[hauteur][larg].setDrapeau(true);
	}

	/**
	 * Enleve le drapeau de la case.
	 *
	 * @param hauteur La position en y de la case.
	 * @param larg    La position en x de la case.
	 */
	public void retirerDrapeau(int hauteur, int larg) {
		grille.getGrille()[hauteur][larg].setDrapeau(false);
	}

	/**
	 * Devoile récursivement toutes les cases non minées adjacente à la
	 * dernière case dévoilée.
	 *
	 * @param hauteur Pos y de la case a tester.
	 * @param larg    Pos x de la case a tester.
	 */
	//TODO Corriger les conditions
	private void devoileAuto(int hauteur, int larg) {
		if(!(hauteur == 0 || larg == 0)
				&& !grille.getGrille()[hauteur - 1][larg - 1].isMine())
			devoileCase(hauteur - 1, larg - 1);
		if(!(hauteur == 0)
				&& !grille.getGrille()[hauteur - 1][larg].isMine())
			devoileCase(hauteur - 1, larg);
		if(!(hauteur == 0 || larg == grille.getLargeur() - 1)
		&& !grille.getGrille()[hauteur - 1][larg + 1].isMine())
			devoileCase(hauteur - 1, larg + 1);
		if(!(larg == grille.getLargeur() - 1)
				&& !grille.getGrille()[hauteur][larg + 1].isMine())
			devoileCase(hauteur, larg + 1);
		if(!(hauteur == grille.getHauteur() - 1 && larg == grille.getLargeur() - 1)
				&& !grille.getGrille()[hauteur + 1][larg + 1].isMine())
			devoileCase(hauteur + 1, larg + 1);
		if(!(hauteur == grille.getHauteur() - 1)
				&& !grille.getGrille()[hauteur + 1][larg].isMine())
			devoileCase(hauteur + 1, larg);
		if(!(hauteur == grille.getHauteur() - 1 && larg == 0)
				&& !grille.getGrille()[hauteur + 1][larg - 1].isMine())
			devoileCase(hauteur + 1, larg - 1);
		if(!(larg == 0)
				&& !grille.getGrille()[hauteur][larg - 1].isMine())
			devoileCase(hauteur, larg - 1);
	}

	public Grille getGrille() {
		return grille;
	}
}
