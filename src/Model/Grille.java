package Model;

/**
 * Grille de jeu. Tableau 2D de Case
 *
 * @author Tarek
 */
public class Grille {
	private final static int LARGEUR_PAR_DEFAUT = 8;
	private final static int HAUTEUR_PAR_DEFAUT = 8;
	private final static int NBRE_DE_MINES_PAR_DEFAUT = 10;
	private final int hauteur;
	private final int largeur;
	private final int nombreDeMines;
	private final int casesVides;
	private Case grille[][];
	private int casesDevoilees;
	private boolean grillePerdue;

	/**
	 * Constructeur par défaut de Grille,
	 * crée une nouvelle grille avec les dimensions par
	 * défaut.
	 */
	public Grille() {
		this(HAUTEUR_PAR_DEFAUT, LARGEUR_PAR_DEFAUT, NBRE_DE_MINES_PAR_DEFAUT);
	}

	/**
	 * Crée une nouvelle grille aux dimensions
	 * transmises en parametre.
	 *
	 * @param haut      Hauteur de la grille.
	 * @param larg      Largeur de la grille.
	 * @param nbreMines Nombre de mines à placer dans la grille.
	 */
	public Grille(int haut, int larg, int nbreMines) {
		hauteur = haut;
		largeur = larg;
		nombreDeMines = nbreMines;
		this.newGrille();
		this.placeMines();
		casesVides = haut*larg - nbreMines;
		casesDevoilees = 0;
		grillePerdue = false;
	}

	/**
	 * Renvoie la hauteur de la grille.
	 *
	 * @return Hauteur de la grille (int) en nombre de cases.
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * Renvoie la largeur de la grille.
	 *
	 * @return Largeur de la grille (int) en nombre de cases.
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Renvoie le nombre de mines dans la grille.
	 *
	 * @return Le nombre de mines présents dans la grille (int).
	 */
	public int getNombreDeMines() {
		return nombreDeMines;
	}

	/**
	 * Retourne la taille de la grille.
	 *
	 * @return retourne le nombre de cases de la grille
	 */
	public int getTaille() {
		return hauteur*largeur;
	}

	/**
	 * Génère une nouvelle grille.
	 */
	private void newGrille() {
		int i, j;

		grille = new Case[hauteur][largeur];

		for(i = 0; i < hauteur; i++) {
			for(j = 0; j < largeur; j++) {
				grille[i][j] = new Case(i, j);
			}
		}
	}

	/**
	 * Place les mines dans la grille.
	 */
	private void placeMines() {
		int i, j;
		double probMine = (double) nombreDeMines/(hauteur*largeur);
		int minesAPlace = nombreDeMines;

		// Tant qu'il reste des mines à placer
		while(minesAPlace > 0) {
			for(i = 0; i < hauteur; i++) {
				for(j = 0; j < largeur; j++) {
					if(minesAPlace != 0) {
						// On fait un rand en rapport avec la probabilité de placement d'une mine
						if(Math.random() <= probMine) {
							// Si ça passe, on met une mine dans la case
							grille[i][j].setAsMine();
							// Et on réduit le nombre de mines à placer
							minesAPlace--;

							// on incrémente le nombre de mines adjacente des huit cases autour
							if(i != 0 && j != 0) {
								grille[i - 1][j - 1].incrementeMinesAdjacentes();
							}
							if(i != 0) {
								grille[i - 1][j].incrementeMinesAdjacentes();
							}
							if(i != 0 && j != largeur - 1) {
								grille[i - 1][j + 1].incrementeMinesAdjacentes();
							}
							if(j != largeur - 1) {
								grille[i][j + 1].incrementeMinesAdjacentes();
							}
							if(i != hauteur - 1 && j != largeur - 1) {
								grille[i + 1][j + 1].incrementeMinesAdjacentes();
							}
							if(i != hauteur - 1) {
								grille[i + 1][j].incrementeMinesAdjacentes();
							}
							if(i != hauteur - 1 && j != 0) {
								grille[i + 1][j - 1].incrementeMinesAdjacentes();
							}
							if(j != 0) {
								grille[i][j - 1].incrementeMinesAdjacentes();
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Indique si la grille est gagnée (toutes les cases sont
	 * dévoilées sauf les mines).
	 *
	 * @return True si la grille est gagnée, False sinon.
	 */
	public boolean isGagnee() {
		return casesDevoilees == casesVides;
	}

	/**
	 * Marquer la grille comme perdue.
	 */
	public void setGrillePerdue() {
		grillePerdue = true;
	}

	/**
	 * Indique si la grille est perdue (une mine à été dévoilée).
	 *
	 * @return True si la grille est perdue, false sinon.
	 */
	public boolean isPerdue() {
		return grillePerdue;
	}

	/**
	 * Incremente le compteur de cases dévoilées.
	 */
	public void incrementeCasesDevoilees() {
		casesDevoilees++;
	}

	/**
	 * Renvoie la grille de jeu.
	 *
	 * @return Un tableau 2D de Case.
	 */
	public Case[][] getGrille() {
		return grille;
	}
}
