package Shapes;

import java.util.ArrayList;
import java.awt.Color;

/**
 * class that manage a static figure as an object
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public class Figure extends Drawable {
	private static ArrayList<Drawable> shapes = new ArrayList<Drawable>();
	private Point middlePoint = new Point(0, 0);

	/**
	 * Constructor for objects of class Figure
	 */
	public Figure() {
		this.addShape(new Circle(1, this.getMiddle(), Color.RED, false));
	}

	public Figure(Point middlePoint) {
		this.middlePoint = middlePoint;
		this.addShape(new Circle(1, this.getMiddle(), Color.RED, false));
	}

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
		//middlePoint.move(trans);
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

	public Drawable rotate(Point center, double phi) {
		//middlePoint.rotate(center, phi);
		for (Drawable aDrawable : shapes) {
			aDrawable.rotate(center, phi);
		}
		return this;
	}

	public Drawable rotate(double phi) {
		return this.rotate(middlePoint, phi);
	}

	public static Figure ship() {
		Figure ship = new Figure();
		ArrayList<Point> shipList = new ArrayList<Point>();
		shipList.add(new Point(-10, -10));
		shipList.add(new Point(10, -10));
		shipList.add(new Point(0, 15));
		ship.addShape(new Polygon(shipList, Color.GRAY, false));
		//ship.addShape(new Circle(1, ship.getMiddle(), Color.RED, false));
		return ship;

	}

	/**
	 * generate a figure snowMan return itself as object
	 */
	public static Figure snowMan() {
		int a = 3; // count usefull min 3
		int h = -1; // handedness -1 left | 1 right
		int x = 200; // x-start
		Figure snowMan = new Figure();
		for (int s = 3; s <= 3; s++) { // size loop
			int y = -250 + 40 * (5 - s); // y-position
			for (int i = 0; i < a; i++) {
				snowMan.addShape(new Circle(s * 10 * (a - i), new Point(x,
						y += s * 10 * (a + 1 - i) + s * 10 * (a - i)),
						Color.BLACK, false)); // balls
				for (int j = 0; j < a - i; j++) {
					snowMan.addShape(new Circle(2 * s, new Point(x, y - (a - i)
							* s * 6 + j * s * 20), Color.BLACK, true)); // knobs
				}
				if (i == (a - 2)) {
					for (int k = -1; k < 2; k += 2) {
						snowMan.addShape(new Circle(5 * s, new Point(k * s * 24
								+ x, y + 5 * s), Color.BLACK, false)); // hands
						if (k == h) {
							ArrayList<Point> broomStickList = new ArrayList<Point>();
							broomStickList.add(new Point(k * s * 20 + x - k * s
									- k * s * 40, y + 2 * s - s * 52)); // bottom
																		// edge
							broomStickList.add(new Point(k * s * 20 + x + k * s
									- k * s * 40, y - s * 52));
							broomStickList.add(new Point(k * s * 20 + x + k * s
									+ k * s * 40, y + s * 52)); // top edge
							broomStickList.add(new Point(k * s * 20 + x - k * s
									+ k * s * 40, y + 2 * s + s * 52));
							Polygon broomStick = new Polygon();
							broomStick.setPoints(broomStickList, Color.ORANGE
									.darker().darker(), true); // broomStick
							snowMan.addShape(broomStick);
							ArrayList<Point> broomList = new ArrayList<Point>();
							for (int l = 0; l < 10 * s; l++) {
								broomList.add(new Point(k * s * 20 + x + k * s
										+ k * s * 40, y + s * 52));
								broomList.add(new Point(k * s * 20 + x - k * s
										+ k * s * 40, y + 2 * s + s * 52));
								broomList.add(new Point(k * s * 10 + x + k * s
										* 40 + (l + 1 * a) * k * s, y + s * 75
										+ s * (s * 1 - l))); // peak
							}
							Polygon broom = new Polygon();
							broom.setPoints(broomList, Color.ORANGE.darker(),
									false); // broom end
							snowMan.addShape(broom);
						}
					}
				}
				if (i == (a - 1)) {
					for (int k = -1; k < 2; k += 2) {
						snowMan.addShape(new Circle(2 * s, new Point(k * s * 4
								+ x, y + 4 * s), Color.BLACK, true)); // eyes
						snowMan.addShape(new Circle(1 * s, new Point(k * s * 3
								+ x, y - 5 * s), Color.BLACK, true)); // mouth
					}
					snowMan.addShape(new Circle(2 * s, new Point(x, y),
							Color.ORANGE, true)); // nose

					snowMan.addShape(new Rectangle(new Point(x, y + s * 16),
							8 * s, 8 * s, Color.BLACK, true)); // hat
					snowMan.addShape(new Rectangle(new Point(x, y + s * 8),
							15 * s, s, Color.BLACK, true));
				}
			}
			x += 400;
		}
		return snowMan;
	}

	public Point getMiddle() {
		return middlePoint;
	}

	public void setMiddle(Point middle) {
		this.middlePoint = middle;
	}
}
