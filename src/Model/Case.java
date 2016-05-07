package Model;

import java.awt.*;

public class Case
{
	private boolean devoilee, mine, drapeau;
	private int minesAdjacentes;
	private Point coord;

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
