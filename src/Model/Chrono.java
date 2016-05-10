package Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chrono {
    private float temps, meilleurTemps;
	private Timer timer;
	private Grille grille;

	/**
	 * Constructeur par défaut de chrono
	 *
	 * @author : Raphael-R-R
	 */

	public Chrono() {}

	/**
	 * Première surcharge du constructeur de Chrono
	 *
	 * Récupère le meilleur temps et initialite grille.
	 * Les autres variables d'instances sont
	 * initialisées par java.
	 *
	 * @param grille Une instance de Grille
	 * 
	 * @author : Raphael-R-R
	 */

	public Chrono(Grille grille) {
		this.grille = grille;

		meilleurTemps = getMeilleurTempsFromDB();
	}

	/**
	 * Démarre le timer qui permettra de calculer le temps écoulé
	 *
	 * @author : Raphael-R-R
	 */

	public void start() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				setTemps(getTemps() + 1);
			}
		});
	}

	/**
	 * Arrête le timer
	 *
	 * @author : Raphael-R-R
	 */

	public void stop() {
		timer.stop();
	}

	/**
	 * Retourne le temps actuel
	 *
	 * @author : Raphael-R-R
	 */

	public float getTemps() {
		return temps;
	}

	/**
	 * Retourne le meilleur temps
	 *
	 * @author : Raphael-R-R
	 */

	public float getMeilleurTemps() {
		return meilleurTemps;
	}

	/**
	 * Affecte le temps passé en paramètre au temps actuel
	 *
	 * @author : Raphael-R-R
	 */

	private void setTemps(float temps) {
		this.temps = temps;
	}

	/**
	 * Récupère le meilleur temps depuis la base de donnée
	 *
	 * @author : Raphael-R-R
	 */

	private float getMeilleurTempsFromDB() {

	}

	/**
	 * Ajoute le meilleur temps à la base de donnée
	 *
	 * @author : Raphael-R-R
	 */

	private float setMeilleurTempsInDB() {

	}

	/**
	 * Renvoie vrai si le temps actuel est le meilleur temps,
	 * sinon faux
	 *
	 * @author : Raphael-R-R
	 */

	private boolean isMeilleurTemps() {

	}
}
