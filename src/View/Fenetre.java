package View;

import Control.ControlGroup;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nassime
 */

public class Fenetre extends JFrame {

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
	protected Jeu jeu;

	public static final int LARGEUR = 0;
	public static final int HAUTEUR = 0;

	/**
	 * Constructeur de Fenetre
	 */
	public Fenetre(Jeu j) {
		this.jeu = j;
		this.grid = j.getGrille();

		initAttribut();
		creerMenu();
		creerChrono();
		creerGrille();
		setContentPane(panel);
		setMinimumSize(new Dimension(LARGEUR, HAUTEUR));
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Déminheur");
		setResizable(false);
		setFocusable(true);

		updateFen();
	}

	/**
	 * Initialisation des attributs
	 */
	public void initAttribut() {
		mItemNouvellePartie = new JMenuItem("Nouvelle partie");
		mItemScores = new JMenuItem("Scores");
		mItemQuitter = new JMenuItem("Quitter");
		chrono = new JLabel("");
		panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}

	/**
	 * Rafraichissement de la fenêtre après clic sur case
	 */
	public void updateFen() {
		int i, j;
		for(i = 0; i < grid.getHauteur(); i++) {
			for(j = 0; j < grid.getLargeur(); j++) {
				if(grid.getGrille()[i][j].isDevoilee()) {
					if(grid.getGrille()[i][j].isMine()) {
						editColor(i, j);
						grille[i][j].setIcon(new ImageIcon("src/bomb.png"));
					}
					else {
						afficheNbre(i, j);
					}
				}
				else if(grid.getGrille()[i][j].isDrapeau()) {
					grille[i][j].setIcon(new ImageIcon("src/flag.png"));
				}
				else {
					grille[i][j].setIcon(null);
				}
			}
		}
		if(jeu.getGrille().isPerdue()) {
			perdu();
		}
		else if(jeu.getGrille().isGagnee()) {
			gagne();
		}
	}

	/**
	 * Ajoute le chrono à la fenetre.
	 */
	private void creerChrono() {
		JLabel labelTemps = new JLabel("Temps : ");
		JPanel panelChrono = new JPanel();

		panelChrono.setLayout(new BoxLayout(panelChrono, BoxLayout.X_AXIS));

		Timer t = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chrono.setText(Double.toString((int)jeu.getTime().getTemps()/10.));
			}
		});
		t.start();
		panelChrono.add(labelTemps);
		panelChrono.add(chrono);
		panel.add(panelChrono);
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
	public void creerGrille() {
		int h, l, i, j;

		h = grid.getHauteur();
		l = grid.getLargeur();
		JPanel panelGrille = new JPanel(new GridLayout(h, l));

		this.grille = new JButton[h][l];

		for(i = 0; i < h; i++) {
			for(j = 0; j < l; j++) {
				grille[i][j] = new JButton();
				grille[i][j].setPreferredSize(new Dimension(20,20));
				grille[i][j].setBackground(Color.GRAY);
				//System.out.println(grille[i][j].getFont());
				grille[i][j].setFont(new Font("Dialog", Font.BOLD, 15));
				grille[i][j].setMargin(new Insets(0,0,0,0));
				grille[i][j].setPreferredSize(new Dimension(30, 30));

				panelGrille.add(grille[i][j]);
			}
		}
		panel.add(panelGrille);
	}

	/**
	 * @return l'attribut de l'option Quitter du menu
	 */
	public JMenuItem getmItemQuitter() {
		return mItemQuitter;
	}

	/**
	 * @return l'attribut de l'option Scores du menu
	 */
	public JMenuItem getmItemScores() {
		return mItemScores;
	}

	/**
	 * @return l'attribut de l'option Nouvelle Partie du menu
	 */
	public JMenuItem getmItemNouvellePartie() {
		return mItemNouvellePartie;
	}

	/**
	 * @return l'attribut grille (tableau de boutons)
	 */
	public JButton[][] getGrille() {
		return grille;
	}


	/**
	 * Ajout d'écouteur sur les JMenuItem
	 *
	 * @param al Listener du menu
	 */
	public void setControlMenu(ActionListener al) {
		mItemNouvellePartie.addActionListener(al);
		mItemScores.addActionListener(al);
		mItemQuitter.addActionListener(al);
	}

	/**
	 * Ajout d'écouteur sur les JButton
	 *
	 * @param al MouseLister des boutons
	 */
	public void setControlButton(MouseListener al) {

		int h, l, i, j;
		
		h = grid.getHauteur();
		l = grid.getLargeur();

		for(i = 0; i < h; i++) {
			for(j = 0; j < l; j++) {
				grille[i][j].addMouseListener(al);
			}
		}

	}

	/**
	 * Affichage d'une nouvelle grille lors du clique sur mItemNouvellePartie
	 */
	public void restart() {
		this.dispose();
		Jeu j = new Jeu();
		ControlGroup gp = new ControlGroup(j);
	//	this.setVisible(true);
	}

	/**
	 * Affiche des scores lors du clique sur mItemScores
	 */
	public void afficherScores() {
		JOptionPane scorePan = new JOptionPane();
		Database database = new Database();
		ResultSet scores;
		boolean haveData = false;

		database.recuperationSauvegarde(jeu.getGrille().getTaille(), 0);
		scores = database.getResultatRequete();

		try {
			if(scores.next())
				haveData = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		if(scores != null && haveData) {
			try {
				int i = 1;
				String scoreList = "\n";

				do {
					scoreList += i + "." + scores.getString("nom") + " " + scores.getDouble("temps") + "\n";
					i++;
				} while(scores.next());

				scorePan.showMessageDialog(this, "Meilleurs scores : " + scoreList, "Scores", JOptionPane.NO_OPTION);
			}
			catch (SQLException e) {
				e.printStackTrace();

			}
		}
		else
			scorePan.showMessageDialog(this, "Aucune donnée de sauvegarde.", "Scores", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Quitte le jeu lors du clique sur mItemQuitter
	 */
	public void quitter() {
		System.exit(0);
	}

	/**
	 * Change la couleur de fond des cases dévoilées
	 * @param x coordonnées en ordonnée de la case
	 * @param y coordonnées en abssyse de la case
	 */
	public void editColor(int x, int y) {
		grille[x][y].setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * Affiche le nombre de mines adjacentes sur chaque bouton,
	 * et change la couleur d'affichage suivant le cas
	 * @param x
	 * @param y
	 */
	public void afficheNbre(int x, int y) {
		// Ecrit sur les cases le nombre de mines adjacentes
		editColor(x, y);
		if(jeu.getGrille().getGrille()[x][y].getMinesAdjacentes() != 0.0) {
			grille[x][y].setText(Integer.toString(grid.getGrille()[x][y].getMinesAdjacentes()));
			editColor(x, y);
			switch(jeu.getGrille().getGrille()[x][y].getMinesAdjacentes()) {
				case 1:
					grille[x][y].setForeground(Color.BLUE);
					break;
				case 2:
					grille[x][y].setForeground(new Color(49, 141, 64));
					break;
				case 3:
					grille[x][y].setForeground(Color.RED);
					break;
				case 4:
					grille[x][y].setForeground(new Color(42, 78, 117));
					break;
				case 5:
					grille[x][y].setForeground(new Color(178, 124, 66));
					break;
				case 6:
					grille[x][y].setForeground(new Color(135, 178, 42));
					break;
				case 7:
					grille[x][y].setForeground(Color.GRAY);
					break;
				default:
					grille[x][y].setForeground(Color.BLACK);
					break;
			}
		}
	}

	/**
	 * Gère l'affichage lorsque la partie est gagnee.
	 */
	public void gagne() {
		JOptionPane optionPaneGagne = new JOptionPane();
		String nom = optionPaneGagne.showInputDialog(this, "Bravo tu as gagné ! Entre ton pseudo : ");

		if(nom != null)
			jeu.getTime().setTempsInDB(nom);
	}

	/**
	 * Gère l'affichage lorsque la partie est perdue.
	 */
	public void perdu() {
		JOptionPane optionPanePerdu = new JOptionPane();
		optionPanePerdu.showMessageDialog(this, "Tu as perdu !", "Perdu !",
				JOptionPane.ERROR_MESSAGE);

		JDialog dialogPerdu = optionPanePerdu.createDialog(this, "");
	}
}
