package Astroids;

import java.awt.Color;

import Shapes.Circle;
import Shapes.Drawable;
import Shapes.Figure;
import Shapes.Point;

public abstract class Sprite extends Figure {
	// TODO commenting
	protected int radius = 0;
	private Vector vector;
	private Point centerPoint;
	protected double rotationPhi;
	protected static GameController gameController;

	/**
	 * Constructor for objects of class Sprite - overloaded
	 * 
	 * @param middlePoint
	 *            The middlePoint of the Sprite
	 * 
	 * @param vector
	 *            The movement-speed and movement-direction of the Sprite
	 */
	public Sprite() {
		gameController.addSprites(this);
		this.centerPoint = new Point(0, 0);
		this.vector = new Vector(0, 90);
		this.rotationPhi = 0;
		this.addShape(new Circle(radius, this.centerPoint, Color.RED, false));
	}

	public void update() {
		this.move(vector);
		Point cornerWarp = getEdgeWarp();
		if (!cornerWarp.equals(new Point(0, 0))) {
			move(cornerWarp);
		}
		this.draw();
	}

	public Sprite move() {
		return (Sprite) super.move(vector);
	}

	@Override
	public void remove() {
		gameController.deleteSprite(this);
		super.remove();
	}

	public abstract void destroy();

	public Drawable rotate(double phi) {
		return this.rotate(centerPoint, phi);
	}

	/**
	 * @return Point to move to the other corner
	 */
	protected Point getEdgeWarp() {
		Point returnPoint = new Point(0, 0);
		if (Math.abs(this.centerPoint.getX()) >= gameController
				.getGameScreenX()) {
			if (this.centerPoint.getX() >= gameController.getGameScreenX()
					- gameController.getAstroSize()) {
				returnPoint.move(-gameController.getGameScreenX() * 2, 0); // right
			} else {
				returnPoint.move(gameController.getGameScreenX() * 2, 0); // left
			}
		}
		if (Math.abs(this.centerPoint.getY()) >= gameController
				.getGameScreenY()) {
			if (this.centerPoint.getY() >= gameController.getGameScreenY()) {
				returnPoint.move(0, -gameController.getGameScreenY() * 2); // upper
			} else {
				returnPoint.move(0, gameController.getGameScreenY() * 2); // bottom
			}
		}
		return returnPoint;
	}

	public void collide() {
		// TODO implemets destroy astro and create 2 new smaller halt of radius
		// and edges
	}

	protected void setCenterPoint(Point centerPoint) {
		this.move(this.centerPoint.invert());
		this.move(centerPoint);
	}

	public Point getCenterPoint() {
		return this.centerPoint.copy();
	}

	public static void setGameController(GameController gameController) {
		Sprite.gameController = gameController;
	}

	public Vector getVector() {
		return vector;
	}

	public double getAmount() {
		return vector.getAmount();
	}

	protected double getPhi() {
		return vector.getPhi();
	}

	protected void changeDirection(double phi) {
		vector.changeDirection(phi);
	}

	protected void changeSpeed(double amount) {
		vector.changeSpeed(amount);
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}
}
