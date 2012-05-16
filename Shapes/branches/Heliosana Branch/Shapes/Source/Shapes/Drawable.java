package Shapes;

/**
 * declare methods draw() and move() for its subclasses
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public abstract class Drawable extends Object {
	/**
	 * abstract declaration of draw()
	 */
	public abstract void draw();

	/**
	 * abstract declaration of drawable()
	 * 
	 * @param dx
	 *            x value to move
	 * @param dy
	 *            y value to move
	 */
	public abstract Drawable move(Point trans);

	/**
	 * rotate the Drawable around a center point
	 * 
	 * @param center
	 *            point to rotate around
	 * @param phi
	 *            angel to rotate
	 */
	public abstract Drawable rotate(Point center, double phi);

	/**
	 * remove the Drawable from Whiteboard
	 */
	public abstract void remove();
	//TODO implement the remove() in all subclasses
}
