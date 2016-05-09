package Model;

public class Grille
{
	private final static int LARGEUR_PAR_DEFAUT = 8;
	private final static int HAUTEUR_PAR_DEFAUT = 8;
	private final static int NBRE_DE_MINES_PAR_DEFAUT = 4;

	private Case grille[][];
	private final int hauteur;
	private final int largeur;
	private final int nombreDeMines;
	private final int casesVides;
	private int casesDevoilees;

	/**
	 * Constructeur par défaut de Grille,
	 * crée une nouvelle grille avec les dimensions par
	 * défaut.
	 *
	 * @author Tarek
	 */
	public Grille()
	{
		this(HAUTEUR_PAR_DEFAUT, LARGEUR_PAR_DEFAUT, NBRE_DE_MINES_PAR_DEFAUT);
	}

	/**
	 * Crée une nouvelle grille aux dimensions
	 * transmises en parametre.
	 *
	 * @param haut      Hauteur de la grille.
	 * @param larg      Largeur de la grille.
	 * @param nbreMines Nombre de mines à placer dans la grille.
	 * @author Tarek
	 */
	public Grille(int haut, int larg, int nbreMines)
	{
		hauteur = haut;
		largeur = larg;
		nombreDeMines = nbreMines;
		this.newGrille();
		this.placeMines();
		casesVides = haut*larg - nbreMines;
		casesDevoilees = 0;
	}

	/**
	 * Renvoie la hauteur de la grille.
	 *
	 * @return Hauteur de la grille (int) en nombre de cases.
	 * @author Tarek
	 */
	public int getHauteur()
	{
		return hauteur;
	}

	/**
	 * Renvoie la largeur de la grille.
	 *
	 * @return Largeur de la grille (int) en nombre de cases.
	 * @author Tarek
	 */
	public int getLargeur()
	{
		return largeur;
	}

	/**
	 * Renvoie le nombre de mines dans la grille.
	 *
	 * @return Le nombre de mines présents dans la grille (int).
	 * @author Tarek
	 */
	public int getNombreDeMines()
	{
		return nombreDeMines;
	}

	/**
	 * Génère une nouvelle grille.
	 *
	 * @author Tarek
	 */
	private void newGrille()
	{
		int i, j;
		grille = new Case[hauteur][largeur];
		for(i = 0; i < hauteur; i++)
		{
			for(j = 0; j < largeur; j++)
			{
				grille[i][j] = new Case(i, j);
			}
		}
	}

	/**
	 * Place les mines dans la grille.
	 *
	 * @author Tarek
	 */
	private void placeMines()
	{
		int i, j;
		double probMine = (double) nombreDeMines/(hauteur*largeur);
		int minesAPlace = nombreDeMines;
		for(i = 0; i < hauteur; i++)
		{
			for(j = 0; j < largeur; j++)
			{
				if(minesAPlace != 0)
				{
					if(Math.random() <= probMine)
					{
						grille[i][j].setAsMine();
						minesAPlace--;
						if(i != 0 && j != 0) grille[i - 1][j - 1].incrementeMinesAdjacentes();
						if(i != 0) grille[i - 1][j].incrementeMinesAdjacentes();
						if(i != 0 && j != largeur - 1) grille[i - 1][j + 1].incrementeMinesAdjacentes();
						if(j != largeur - 1) grille[i][j + 1].incrementeMinesAdjacentes();
						if(i != hauteur - 1 && j != largeur - 1) grille[i + 1][j + 1].incrementeMinesAdjacentes();
						if(i != hauteur - 1) grille[i + 1][j].incrementeMinesAdjacentes();
						if(i != hauteur - 1 && j != 0) grille[i + 1][j - 1].incrementeMinesAdjacentes();
						if(j != 0) grille[i][j - 1].incrementeMinesAdjacentes();
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
	 * @author Tarek
	 */
	public boolean isGagnee()
	{
		return casesDevoilees == casesVides;
	}

	/**
	 * Incremente le compteur de cases dévoilées.
	 * @author Tarek
	 */
	public void incrementeCasesDevoilees()
	{
		casesDevoilees++;
	}
}
