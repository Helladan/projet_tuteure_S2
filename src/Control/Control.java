package Control;


import Model.Chrono;
import Model.Grille;
import Model.Jeu;
import View.Fenetre;

public abstract class Control
{
	protected Jeu jeu;
	protected Fenetre fenetre;
	protected ControlMenu controlMenu;


	/**
	 * Constructeur par défaut de Control
	 *
	 * @author Raphael-R-R
	 */

	public Control() {}

	/**
	 * Constructeur de Control
	 *
	 * @param case_ Une référence vers une instance de la classe Case
	 * @param chrono Une référence vers une instance de la 	classe Chrono
	 * @param controlMenu Une référence vers une instance de la classe ControlMenu
	 *
	 * @author Raphael-R-R
	 */

//	public Control(Case case_, Chrono chrono, ControlMenu controlMenu)
//	{
//		this.case_ = case_;
//		this.chrono = chrono;
//		this.controlMenu = controlMenu;
//	}

	public Control(Jeu jeu, Fenetre fenetre){
		this.jeu = jeu;
		this.fenetre = fenetre;
	}
}


