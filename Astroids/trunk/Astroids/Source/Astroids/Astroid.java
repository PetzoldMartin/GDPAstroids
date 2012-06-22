package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Circle;
import Shapes.Point;
import Shapes.Polygon;

/**
 * class that manage a Astroid
 * 
 * @author (Martin Petzold , Markus Krummnacker)
 * @version (0.4)
 */
public class Astroid extends Sprite {
	// FIXME astroid generation
	private int edge;
	private static int count = 0;

	/**
	 * create new Astroid
	 * 
	 * @param edge
	 *            of the new one
	 * @param radius
	 *            size of the new one
	 * @param vector
	 *            vector of the new one
	 * @param centerPoint
	 *            centerpoint of the new one
	 */
	public Astroid(int edge, int radius, Vector vector, Point centerPoint) {
		super();
		count++;
		this.edge = edge;
		this.radius = radius;
		ArrayList<Point> astroList = new ArrayList<Point>();
		for (int phi = 0; phi < 360; phi += 360 / edge) {
			astroList.add(new Vector(radius - radius / (radius / 5)
					* Math.random(), phi));
		}
		this.addShape(new Polygon(astroList, Color.WHITE, false));
		this.setVector(vector);
		this.rotationPhi = Math.random() * 12 - 6;
		this.move(centerPoint);
	}
	/**
	 * create new Astroid
	 * 
	 * @param edge
	 *            of the new one
	 * @param radius
	 *            size of the new one
	 */
	public Astroid(int edge, int radius) {
		this(edge, radius, new Vector(Math.random() * 3, Math.random() * 360),
				new Point(Math.random() * gameController.getGameScreenX() * 2
						- gameController.getGameScreenX(), Math.random()
						* gameController.getGameScreenY() * 2
						- gameController.getGameScreenY()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Astroids.Sprite#update()
	 */
	@Override
	public void update() {
		this.rotate(this.rotationPhi);
		super.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Astroids.Sprite#destroy(Astroids.Sprite)
	 */
	@Override
	public void destroy(Sprite collider) {
		split(collider.getVector());
		delete();
	}

	public void delete() {
		gameController.removeSprites(this);
	}

	/**
	 * create 2 new astroids @ position of this
	 * 
	 * @param vector
	 *            orthogonal split vector
	 */
	public void split(Vector vector) {
		// TODO redo vector of new Astroids!!!
		if (this.edge > 12) {
			for (int i = -1; i <= 1; i += 2) {
				new Astroid(
						//FIXME radius / edge scaling
						(int) (edge + edge / 2 - Math.random() * edge) * 2 / 4,
						(int) radius * 2 / 3, new Vector(this.getVector().move(
								new Vector(this.getVector().getAmount() / -2,
										90 * i + vector.getPhi()))), this
								.getCenterPoint().move(
										new Vector(radius * 2 / 3, vector
												.getPhi() - 90 * i)));
			}
		}
	}

	public static int getCounter() {
		return count;
	}

	public void remove() {
		super.remove();
		count--;
	}
}
