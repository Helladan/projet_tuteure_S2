package Model;

/**
 * Classe du jeu. Représente le modèle, fais le lien entre les classe Chrono,
 * Grille et Case. Chaque instance est une nouvelle partie.
 */
public class Jeu {
	public Chrono time;
	private Grille grille;

	/**
	 * Constructeur par défaut de jeu.
	 */
	public Jeu() {
		grille = new Grille();
		time = new Chrono();
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
	}

	/**
	 * Méthode permettant d'arreter le chrono si la partie est gagnée ou perdue.
	 */
	private void gestionChrono() {
		if(grille.isPerdue() || grille.isGagnee()) {
			time.stop();
		}
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

		if(grille.getGrille()[hauteur][larg].isMine()) {
			grille.setGrillePerdue();
			grille.toutDevoiler();
		}

		if(grille.getGrille()[hauteur][larg].getMinesAdjacentes() == 0) {
			devoileAuto(hauteur, larg);
		}
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
		grille.incrementeNbreDrapeau();
	}

	/**
	 * Enleve le drapeau de la case.
	 *
	 * @param hauteur La position en y de la case.
	 * @param larg    La position en x de la case.
	 */
	public void retirerDrapeau(int hauteur, int larg) {
		grille.getGrille()[hauteur][larg].setDrapeau(false);
		grille.decrementeNbreDrapeau();
	}

	/**
	 * Devoile récursivement toutes les cases non minées adjacente à la
	 * dernière case dévoilée.
	 *
	 * @param hauteur Pos y de la case a tester.
	 * @param larg    Pos x de la case a tester.
	 */
	private void devoileAuto(int hauteur, int larg) {
		int i, j, h, l;

		for(i = -1; i < 2; i++) {
			h = hauteur + i;

			// Si les coordonnées testées sont en dehors de la grille, on passe
			if(h < 0 || h >= grille.getHauteur()) {
				continue;
			}

			for(j = -1; j < 2; j++) {
				// Si les coordonnées testées sont la case dévoilée elle même, on passe
				if(i == 0 && j == 0) {
					continue;
				}

				l = larg + j;

				// Si les coordonnées testées sont en dehors de la grille, on passe
				if(l < 0 || l >= grille.getLargeur()) {
					continue;
				}

				if(!grille.getGrille()[h][l].isMine() &&
						!grille.getGrille()[h][l].isDevoilee()) {
					devoileCase(h, l);
				}
			}
		}
	}


	/**
	 * @return l'atribut time de la classe
	 */
	public Chrono getTime() {
		return time;
	}

	/**
	 * @return l'attribut grille de la classe
	 */
	public Grille getGrille() {
		return grille;
	}



	public void nouvellePartie(){
		grille = new Grille();
		time = new Chrono();
	}
}


