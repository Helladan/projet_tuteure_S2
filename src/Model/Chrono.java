package Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Chrono {
    private float temps, meilleurTemps;
	private Timer timer;
	private Grille grille;
	private Database database;

	/**
	 * Constructeur par défaut de chrono
	 *
	 * @author : Raphael-R-R
	 */

	public Chrono() {}

	/**
	 * Première surcharge du constructeur de Chrono
	 *
	 * Créer un objet Database, une référence vers un objet Grille,
	 * et résupère le meilleur temps.
	 * Les autres variables d'instances sont
	 * initialisées par java.
	 *
	 * @param grille Une instance de Grille
	 *
	 * @author : Raphael-R-R
	 */

	public Chrono(Grille grille) {
		this.database = new Database();
		this.grille = grille;

		getMeilleurTempsFromDB();
	}

	/**
	 * Démarre le timer qui permettra de calculer le temps écoulé
	 *
	 * @author : Raphael-R-R
	 */

	public void start() {
		timer = new Timer(1000, new ActionListener() {
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
	 * TODO : Difficultée
	 *
	 * Prendre en compte la difficultée lorsqu'elle
	 * sera gérée dans le model et la base de donnée.
	 *
	 * @author : Raphael-R-R
	 */

	private void getMeilleurTempsFromDB() {
		int taille = grille.getHauteur() * grille.getLargeur();
		database.recuperationSauvegarde(taille, 0);

		ResultSet resultatRequete = database.getResultatRequete();

		try
		{
			/**
			 * D'abord on récupère la ligne actuelle.
			 * Si meilleurTemps == 0, c'est que meilleurTemps
			 * n'a pas encore été récupéré, on peut alors
			 * l'obtenir depuis la base de donnée.
			 */

			while(resultatRequete.next() && meilleurTemps == 0) {
				if(resultatRequete.getInt("taille") == taille) {
					meilleurTemps = resultatRequete.getFloat("temps");
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erreur sur le parcours de resultatRequete dans Chrono.getMeilleurTempsFromDB()");
			e.printStackTrace();
		}
	}

	/**
	 * Affecte @temps à la variable @meilleurTemps, si isMeilleurTemps()
	 * retourne vrai.
	 *
	 * @param pseudo : Le pseudo du joueur.
	 *
	 * @author : Raphael-R-R
	 */

	public void setMeilleurTemps(String pseudo) {
		if(isMeilleurTemps()) {
			meilleurTemps = temps;

			setMeilleurTempsInDB(pseudo);
		}
	}

	/**
	 * Ajoute le meilleur temps à la base de donnée
	 *
	 * TODO : Gestion de la difficultée
	 *
	 * @param pseudo : Le pseudo du joueur
	 *
	 * @author : Raphael-R-R
	 */

	public void setMeilleurTempsInDB(String pseudo) {
		database.insertionScore(grille.getTaille(), 0, pseudo, meilleurTemps);
	}

	/**
	 * Renvoie vrai si le temps actuel est le meilleur temps,
	 * sinon faux
	 *
	 * @author : Raphael-R-R
	 */

	private boolean isMeilleurTemps() {
		return (temps < meilleurTemps);
	}
}
