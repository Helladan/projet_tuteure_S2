package Control;


import Model.Chrono;
import Model.Case;
import Model.Jeu;
import View.Fenetre;

public abstract class Control {
	protected Jeu jeu;
	protected Fenetre fenetre;
	protected ControlMenu controlMenu;
	protected Case aCase;
	protected Chrono chrono;

	/**
	 * Constructeur par défaut de Control
	 *
	 * @author Raphael-R-R
	 */

	public Control() {}

	/**
	 * Constructeur de Control
	 *
	 * @param aCase       Une référence vers une instance de la classe Case
	 * @param chrono      Une référence vers une instance de la classe Chrono
	 * @param controlMenu Une référence vers une instance de la classe ControlMenu
	 * @author Raphael-R-R
	 */

	public Control(Case aCase, Chrono chrono, ControlMenu controlMenu) {
		this.aCase = aCase;
		this.chrono = chrono;
		this.controlMenu = controlMenu;
	}

	public Control(Jeu jeu, Fenetre fenetre) {
		this.jeu = jeu;
		this.fenetre = fenetre;
	}
}


