package Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Chrono {
	public float temps, meilleurTemps;
	private Timer timer;
	private Grille grille;
	private Database database;

	/**
	 * Constructeur par défaut de chrono
	 */
	public Chrono() {}

	/**
	 * Première surcharge du constructeur de Chrono
	 * <p>
	 * Créer un objet Database, une référence vers un objet Grille,
	 * et résupère le meilleur temps.
	 * Les autres variables d'instances sont
	 * initialisées par java.
	 *
	 * @param grille Une instance de Grille
	 */
	public Chrono(Grille grille) {
		this.database = new Database();
		this.grille = grille;
	}

	/**
	 * Démarre le timer qui permettra de calculer le temps écoulé
	 */
	public void start() {
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				setTemps(getTemps() + 1);
			}
		});

		timer.start();
	}

	/**
	 * Arrête le timer
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * Retourne le temps actuel
	 */
	public float getTemps() {
		return temps;
	}

	/**
	 * Retourne le meilleur temps
	 */
	public float getMeilleurTemps() {
		return meilleurTemps;
	}

	/**
	 * Affecte le temps passé en paramètre au temps actuel
	 */
	private void setTemps(float temps) {
		this.temps = temps;
	}

	/**
	 * Récupère le meilleur temps depuis la base de donnée
	 * <p>
	 * TODO : Difficultée
	 * <p>
	 * Prendre en compte la difficultée lorsqu'elle
	 * sera gérée dans le model et la base de donnée.
	 */
	private void getMeilleurTempsFromDB() {
		int taille = grille.getHauteur()*grille.getLargeur();
		database.recuperationSauvegarde(taille, 0);

		ResultSet resultatRequete = database.getResultatRequete();

		if(resultatRequete != null)
		{
			try
			{
				/**
				 * D'abord on récupère la ligne actuelle.
				 * Si meilleurTemps == 0, c'est que meilleurTemps
				 * n'a pas encore été récupéré, on peut alors
				 * l'obtenir depuis la base de donnée.
				 */
				while (resultatRequete.next() && meilleurTemps == 0)
				{
					if (resultatRequete.getInt("taille") == taille)
					{
						meilleurTemps = resultatRequete.getFloat("temps");
					}
				}
			}
			catch (SQLException e)
			{
				System.out.println("Erreur sur le parcours de resultatRequete dans Chrono.getMeilleurTempsFromDB()");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Affecte @temps à la variable @meilleurTemps, si isMeilleurTemps()
	 * retourne vrai.
	 *
	 * @param pseudo : Le pseudo du joueur.
	 */
	public void setMeilleurTemps(String pseudo) {
		if(isMeilleurTemps()) {
			meilleurTemps = temps;

			setMeilleurTempsInDB(pseudo);
		}
	}

	/**
	 * Ajoute le meilleur temps à la base de donnée
	 * <p>
	 * TODO : Gestion de la difficultée
	 *
	 * @param pseudo : Le pseudo du joueur
	 */
	public void setMeilleurTempsInDB(String pseudo) {
		database.insertionScore(grille.getTaille(), 0, pseudo, meilleurTemps);
	}

	/* Ajoute le temps actuel à la base de donnée. */

	public void setTempsInDB(String pseudo) {
		database.insertionScore(grille.getTaille(), 0, pseudo, temps/10.0);
	}

	/**
	 * Renvoie vrai si le temps actuel est le meilleur temps,
	 * sinon faux
	 */
	private boolean isMeilleurTemps() {
		return (temps < meilleurTemps);
	}
}
