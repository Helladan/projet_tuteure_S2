package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;


/**
 * Classe qui gère la base de données
 * <p>
 * TABLE chrono :
 * id INTEGER PRIMARY KEY AUTOINCREMENT // Clé primaire
 * taille TEXT NOT NULL                 // Taille de la grille (nombre de cases)
 * difficulte INT NOT NULL              // Difficulté (nombre de mines)
 * nom CHAR(10)                         // Nom du joueur
 * temps FLOAT)                         // Temps de la partie
 */
public class Database {
	private Connection c;
	private Statement stmt;
	private ResultSet resultatRequete;

	/**
	 * Constructeur de Database
	 * Invoque la méthode de création de fichier de sauvegarde
	 */
	public Database() {
		creationSauvegarde();
	}

	/**
	 * Getter de ResultatRequete
	 * <p>
	 * Utilisation :
	 * while ( resultatRequete.next() ) {
	 * int id = resultatRequete.getInt("id");
	 * int taille  = resultatRequete.getInt("taille");
	 * int difficulte = resultatRequete.getInt("taille");
	 * String  nom = resultatRequete.getString("nom");
	 * double temps = resultatRequete.getDouble("temps");
	 * }
	 *
	 * @return resultat de la requete
	 */
	public ResultSet getResultatRequete() {
		return resultatRequete;
	}

	/**
	 * Methode qui crée le fichier de sauvegarde au besoin
	 */
	private void creationSauvegarde() {
		System.out.println("Recherche de la sauvegarde...");

		// On test si la sauvegarde existe
		try {
			new BufferedReader(new FileReader("scores.db"));
			System.out.println("Fichier de sauvegarde trouvé");
		}
		// Si erreur, on la crée avec la table
		catch(Exception e) {
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:scores.db");

				stmt = c.createStatement();
				String sql = "CREATE TABLE chrono (" +
						"id INTEGER PRIMARY KEY AUTOINCREMENT , " + // Clé primaire
						"taille TEXT NOT NULL, " +                  // Taille de la grille (nombre de cases)
						"difficulte INT NOT NULL, " +               // Difficulté (nombre de mines)
						"nom CHAR(10), " +                          // Nom du joueur
						"temps FLOAT)";                             // Temps de la partie
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();

				System.out.println("Création du fichier de sauvegarde...");
			}
			catch(Exception exception) {
				System.out.println("Erreur sur la création du fichier de sauvegarde.");
			}
		}
	}


	/**
	 * Methode qui insere un score dans la BDD
	 *
	 * @param tailleGrille taille de la grille
	 * @param difficulte   difficulté de la partie
	 * @param nom          nom du joueur
	 * @param temps        temps du joueur pour finir la partie
	 */
	public void insertionScore(int tailleGrille, int difficulte, String nom, double temps) {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:scores.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String sql = "INSERT INTO chrono (" +
					"taille, " +
					"difficulte, " +
					"nom, " +
					"temps) " +
					"VALUES (" +
					tailleGrille + ", " +
					difficulte + ", " +
					"'" + nom + "', " +
					temps + ")";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Sauvegarde effectuée.");
	}


	/**
	 * Méthode qui récupère les sauvegardes des parties précédentes suivant :
	 *
	 * @param tailleGrille taille de la grille
	 * @param difficulte   difficulté de la partie
	 */
	public void recuperationSauvegarde(int tailleGrille, int difficulte) {
		try {
			String req;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:scores.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();

			req = "SELECT nom, temps " +
					"FROM chrono " +
					"WHERE taille = " + tailleGrille + " AND " +
					"difficulte = " + difficulte +
					" ORDER BY temps ASC " +
					"LIMIT 0, 5";

			resultatRequete = stmt.executeQuery(req);
		}
		catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}