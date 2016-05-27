package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

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
	protected double compteur1,compteur2;
	protected Jeu jeu;

	public static final int LARGEUR = 600;
	public static final int HAUTEUR = 600;

	/**
	 * Constructeur de Fenetre
	 */

	public Fenetre(Jeu j) {
		this.jeu=j;
		this.grid = j.getGrille();

		initAttribut();
		creerMenu();
		creerChrono();
		creerGrille();
		setContentPane(panel);
		setSize(LARGEUR, HAUTEUR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Demineur");
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
		int i,j;
		for(i=0;i<grid.getHauteur();i++){
			for(j=0;j<grid.getLargeur();j++){
				if(grid.getGrille()[i][j].isDevoilee()){
					if(grid.getGrille()[i][j].isMine()){
						editColor(i,j);
						grille[i][j].setText("Mine");
					}
					else{
						afficheNbre(i,j);
					}
				}
				else if(grid.getGrille()[i][j].isDrapeau()){
					grille[i][j].setText("Drapeau");
				}
				else{
					grille[i][j].setText("");
				}
			}
		}
		if(jeu.getGrille().isPerdue())perdu();
		if(jeu.getGrille().isGagnee())gagne();
	}

	/**
	 * Ajoute le chrono à la fenetre.
	 */
	private void creerChrono(){
		JLabel labelTemps = new JLabel("Temps : ");
		JPanel panelChrono = new JPanel();
		panelChrono.setLayout(new BoxLayout(panelChrono, BoxLayout
				.X_AXIS));
		Timer t=new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chrono.setText(Float.toString(jeu.getTime().getTemps()));
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
				grille[i][j].setBackground(Color.GRAY);
				panelGrille.add(grille[i][j]);
			}
		}
		panel.add(panelGrille);
	}

	public JMenuItem getmItemQuitter() {
		return mItemQuitter;
	}

	public JMenuItem getmItemScores() {
		return mItemScores;
	}

	public JMenuItem getmItemNouvellePartie() {
		return mItemNouvellePartie;
	}

	public JButton[][] getGrille() {
		return grille;
	}


	/**
	 * Ajout d'écouteur sur les JMenuItem
	 *
	 * @param al
	 */

	public void setControlMenu(ActionListener al) {
		mItemNouvellePartie.addActionListener(al);
		mItemScores.addActionListener(al);
		mItemQuitter.addActionListener(al);
	}

	/**
	 * Ajout d'écouteur sur les JButton
	 *
	 * @param al
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
		getContentPane().removeAll();

		creerGrille();
		pack();
	}

	/**
	 * Affiche des scores lors du clique sur mItemScores
	 */

	public void afficherScores() {

	}

	/**
	 * Quitte le jeu lors du clique sur mItemQuitter
	 */

	public void quitter() {
		System.exit(0);
	}

	public void editColor(int i, int j){
		grille[i][j].setBackground(Color.LIGHT_GRAY);
	}

	public void afficheNbre(int i, int j){
		// Ecrit sur les cases le nombre de mines adjacentes
		editColor(i,j);
		if (jeu.getGrille().getGrille()[i][j].getMinesAdjacentes() !=0.0) {
			grille[i][j].setText(Integer.toString(grid.getGrille()[i][j].getMinesAdjacentes()));
			editColor(i,j);
			switch(jeu.getGrille().getGrille()[i][j].getMinesAdjacentes()){
				case 1:
					grille[i][j].setForeground(Color.BLUE);
					break;
				case 2:
					grille[i][j].setForeground(new Color(49, 141, 64));
					break;
				case 3:
					grille[i][j].setForeground(Color.RED);
					break;
				case 4:
					grille[i][j].setForeground(new Color(42, 78, 117));
					break;
				case 5:
					grille[i][j].setForeground(new Color(178, 124, 66));
					break;
				case 6:
					grille[i][j].setForeground(new Color(135, 178, 42));
					break;
				case 7:
					grille[i][j].setForeground(Color.GRAY);
					break;
				default:
					grille[i][j].setForeground(Color.BLACK);
					break;
			}
		}
	}

	/**
	 * Gère l'affichage lorsque la partie est gagnee.
	 */
	public void gagne(){
		JOptionPane optionPaneGagne = new JOptionPane();
		optionPaneGagne.showMessageDialog( this, "Bravo tu as gagné !", "Gagné !",
				JOptionPane.INFORMATION_MESSAGE );

		JDialog dialogGagne = optionPaneGagne.createDialog(this,"");
	}

	/**
	 * Gère l'affichage lorsque la partie est perdue.
	 */
	public void perdu(){
		JOptionPane optionPanePerdu = new JOptionPane();
		optionPanePerdu.showMessageDialog( this, "Tu as perdu !", "Perdu !",
				JOptionPane.ERROR_MESSAGE );

		JDialog dialogPerdu = optionPanePerdu.createDialog(this,"");
	}
}
