package Model;

import java.awt.*;

public class Case
{
	private boolean devoilee, mine, drapeau;
	private int minesAdjacentes;
	final private Point coord;

	/**
	 * Seul et unique constructeur de Case.
	 * Crée une nouvelle instance de Case.
	 * Cette case est vide, non dévoilée et non
	 * marquée par un drapeau, son compteur de mines
	 * adjacente est à 0.
	 * Elle de plus caractérisée par ses coordonnées dans
	 * la grille.
	 * @param i La position en hauteur(y) de la case dans la grille.
	 * @param j La position en largeur(x) de la case dans la grille.
	 * @author Tarek
	 */
	public Case(int i, int j){
		devoilee=false;
		mine=false;
		drapeau=false;
		minesAdjacentes=0;
		coord = new Point(j,i);
	}

	/**
	 * Indique si la case est dévoilée
	 * @return true si la case est dévoilée
	 *         false si elle est cachée.
	 * @author Tarek
     */
	public boolean isDevoilee() {
		return devoilee;
	}
	/**
	 * Indique si la case est une mine.
	 * @return true si la case est une
	 * 			mine false si la case
	 * 		    n'est pas une mine.
	 * @author Tarek
     */
	public boolean isMine() {
		return mine;
	}
	/**
	 * Indique si la case est marquée comme un drapeau.
	 * @return true si la case est marqué comme drapeau,
	 * 		   false sinon.
	 * @author Tarek
     */
	public boolean isDrapeau() {
		return drapeau;
	}
	/**
	 * Indique le nombre de cases minées adjacentes à la case.
	 * @return Le nombre de cases minées adjacentes à la case (int).
	 * @author Tarek
     */
	public int getMinesAdjacentes() {
		return minesAdjacentes;
	}
	/**
	 * Indique les coordonnées de la case.
	 * @return Un objet Point contenant les coordonnées de la case.
	 * @author Tarek
     */
	public Point getCoord() {
		return coord;
	}
	/**
	 * Marque la case comme dévoilée.
	 * @author Tarek
     */
	public void setAsDevoilee() {
		devoilee = true;
	}
	/**
	 * Change l'état de l'attribut drapeau.
	 * @param drapeau true pour marqué la case comme drapeau, false pour
	 *                   marquée la case comme non drapeau.
	 * @author Tarek
     */
	public void setDrapeau(boolean drapeau) {
		this.drapeau = drapeau;
	}
	/**
	 * Marque la case comme une mine.
	 * @author Tarek
	 */
	public void setAsMine(){
		mine = true;
	}
	/**
	 * Incremente le nombre de mine adjacentes
	 * @author Tarek
	 */
	public void incrementeMinesAdjacentes(){
		minesAdjacentes++;
	}
}
