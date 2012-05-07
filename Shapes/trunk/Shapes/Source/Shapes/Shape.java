package Shapes;

import teaching.*;
import java.awt.Color;

/**
 * class that manage shapes color's and solid's and allocate whiteboard subclass
 * of Drawable
 * 
 * default settings:
 * 
 * @var color grey
 * @var solid false
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public abstract class Shape extends Drawable {
	private Color color = Color.GRAY;
	private boolean solid = false;
	private static WhiteBoard whiteBoard = new WhiteBoard();
	protected Object representation;

	public WhiteBoard getWhiteBoard() {
		return whiteBoard;
	}

	public Color getColor() {
		return color; // returns black if not setted
	}

	public boolean isSolid() {
		return solid; // return false if not setted
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
}
