package Shapes;

import java.awt.Color;

import Teaching.WhiteBoard;

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
	protected Color color = Color.GRAY;
	protected boolean solid = false;
	private static WhiteBoard whiteBoard = new WhiteBoard();

	@Override
	public void remove() {
		getWhiteBoard().removeShape(representation);
	}

	protected Object representation;

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public static WhiteBoard getWhiteBoard() {
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
}
