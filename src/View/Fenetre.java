package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Nassime
 */

public class Fenetre extends JFrame
{

	/**
	 * Déclaration des attributs
	 */

	protected Grille grid;
	protected JMenuItem mItemNouvellePartie;
	protected JMenuItem mItemScores;
	protected JMenuItem mItemQuitter;
	protected JLabel chrono;
	protected JButton[][] grille;
	protected JPanel panel;

	public static final int LARGEUR = 600;
	public static final int HAUTEUR = 600;

	/**
	 * Constructeur de Fenetre
	 */

	public Fenetre(Jeu j)
	{
		this.grid = j.getGrille();

		this.panel = new JPanel(new GridLayout(grid.getHauteur(), grid.getLargeur()));

		initAttribut();
		creerMenu();
		creerGrille();
		setSize(LARGEUR, HAUTEUR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Demineur");
		setResizable(false);
		setVisible(true);
		setFocusable(true);

		updateFen();
	}

	/**
	 * Initialisation des attributs
	 */

	public void initAttribut()
	{

		mItemNouvellePartie = new JMenuItem("Nouvelle partie");
		mItemScores = new JMenuItem("Scores");
		mItemQuitter = new JMenuItem("Quitter");
		//chrono =

	}

	/**
	 * Rafraichissement de la fenêtre après clic sur case
	 */

	public void updateFen()
	{
		int i, j;

		for(j = 0; j < grid.getHauteur(); j++)
		{
			for(i = 0; i < grid.getLargeur(); i++)
				panel.add(grille[j][i]);
		}

		setContentPane(panel);
		setVisible(true);
	}

	/**
	 * Création de la barre de menu
	 */

	public void creerMenu()
	{

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

	public void creerGrille()
	{
		int h, l, i, j;

		h = grid.getHauteur();
		l = grid.getLargeur();

		this.grille = new JButton[h][l];

		for(i = 0; i < h; i++) {
			for(j = 0; j < l; j++) {
				grille[i][j] = new JButton();
			}
		}

	}

	public JMenuItem getmItemQuitter()
	{
		return mItemQuitter;
	}

	public JMenuItem getmItemScores()
	{
		return mItemScores;
	}

	public JMenuItem getmItemNouvellePartie()
	{
		return mItemNouvellePartie;
	}

	public JButton[][] getGrille()
	{
		return grille;
	}


	/**
	 * Ajout d'écouteur sur les JMenuItem
	 *
	 * @param al
	 */

	public void setControlMenu(ActionListener al)
	{

		mItemNouvellePartie.addActionListener(al);
		mItemScores.addActionListener(al);
		mItemQuitter.addActionListener(al);

	}

	/**
	 * Ajout d'écouteur sur les JButton
	 *
	 * @param al
	 */

	public void setControlButton(ActionListener al)
	{

		int h, l, i, j;

		h = grid.getHauteur();
		l = grid.getLargeur();

		for(i = 0; i > h; i++)
		{
			for(j = 0; j > l; j++)
			{
				grille[i][j].addActionListener(al);
			}
		}

	}

	/**
	 * Affichage d'une nouvelle grille lors du clique sur mItemNouvellePartie
	 */

	public void restart()
	{

		getContentPane().removeAll();

		creerGrille();
		pack();

	}

	/**
	 * Affiche des scores lors du clique sur mItemScores
	 */

	public void afficherScores()
	{


	}

	/**
	 * Quitte le jeu lors du clique sur mItemQuitter
	 */

	public void quitter()
	{

		System.exit(0);

	}

}
