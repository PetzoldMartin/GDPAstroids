package Shapes;

import java.util.ArrayList;

/**
 * class that manage a figure as an object
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public class Figure extends Drawable {
	private ArrayList<Drawable> shapes = new ArrayList<Drawable>();

	/**
	 * add aDrawable to the static ArrayList
	 * 
	 * @param aDrawable
	 *            shape to add return itself as object
	 */
	public Figure addShape(Drawable aDrawable) {
		shapes.add(aDrawable);
		return this;
	}

	/**
	 * draw all shapes in the static ArrayList
	 */
	@Override
	public void draw() {
		for (Drawable aDrawable : shapes) {
			aDrawable.draw();
		}
	}

	/**
	 * move all shapes in the static ArrayList and draw the moved figure
	 * 
	 * @param trans
	 *            point to move with dx & dy return itself as object
	 */
	@Override
	public Drawable move(Point trans) {
		for (Drawable aDrawable : shapes) {
			aDrawable.move(trans);
		}
		return this;
	}

	/**
	 * rotate all shapes in the static ArrayList and return the new Drawable
	 * 
	 * @param center
	 *            point to rotate around
	 * @param phi
	 *            angel to rotate
	 */

	@Override
	public Drawable rotate(Point center, double phi) {
		for (Drawable aDrawable : shapes) {
			aDrawable.rotate(center, phi);
		}
		return this;
	}
}
