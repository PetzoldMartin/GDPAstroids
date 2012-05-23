package Astroids;

import java.awt.Color;

import Collision.CollisionDetector;
import Shapes.Circle;
import Shapes.Drawable;
import Shapes.Figure;
import Shapes.Point;
import Shapes.Shape;

/**
 * abstract class that manage moveable objects with a Vector & a CenterPoint
 * (for self-rotation)
 * 
 * @author Markus Krummnacker, Martin Petzold
 * @version (0.3)
 * 
 */
public abstract class Sprite extends Figure {
	private CollisionDetector collisionDetector = new CollisionDetector();
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
	// TODO multiple Constructor!
	public Sprite() {
		gameController.addSprites(this);
		this.centerPoint = new Point(0, 0);
		this.vector = new Vector(0, 90);
		this.rotationPhi = 0;
		this.addShape(new Circle(radius, this.centerPoint, Color.RED, false));
	}

	/**
	 * Update the Sprite by one tact (frame) move the Sprite, checks corner
	 * collision and warp to the other corner then draw the changes
	 */
	public void update() {
		this.move(vector);
		Point cornerWarp = getEdgeWarp();
		if (!cornerWarp.equals(new Point(0, 0))) {
			move(cornerWarp);
		}
		this.draw();
	}

	public void radiusCollison(Sprite otherSprite) {
		double distance = new Vector(this.getCenterPoint().invert()
				.move(otherSprite.getCenterPoint())).getAmount();
		if (distance < (this.radius + otherSprite.radius)) {
			if(realCollision(otherSprite)){
				otherSprite.destroy(this);
			}
		}
	}
	// TODO make it Threaded
	public boolean realCollision(Sprite otherSprite) {
		for (Drawable s1 : this.getShapes()) {
			for (Drawable s2 : otherSprite.getShapes()) {
				if (collisionDetector.collide((Shape) s1, (Shape) s2)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * move the Sprite by the Vector around the drawboard
	 * 
	 * @return this Sprite
	 */
	public Sprite move() {
		return (Sprite) super.move(vector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Shapes.Figure#remove()
	 */
	@Override
	public void remove() {
		gameController.deleteSprite(this);
		super.remove();
	}

	/**
	 * destroy the Object
	 */
	public abstract void destroy(Sprite collider);

	// TODO implemets destroy astro and create 2 new smaller halt of radius and
	// edges

	/**
	 * rotate the Sprite around his CenterPoint
	 * 
	 * @param phi
	 *            Angel to rotate
	 * @return
	 */
	protected Drawable rotate(double phi) {
		return this.rotate(centerPoint, phi);
	}

	/**
	 * check the Sprite position and if it is out of GameScreen return the right
	 * warping point to move
	 * 
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

	/**
	 * move the Sprite to a new position on drawboard
	 * 
	 * @param centerPoint
	 *            new CenterPoint of the Sprite
	 */
	protected void setCenterPoint(Point centerPoint) {
		this.move(this.centerPoint.invert());
		this.move(centerPoint);
	}

	/**
	 * @return a copy of the CenterPoint of this Sprite
	 */
	public Point getCenterPoint() {
		return this.centerPoint.copy();
	}

	/**
	 * @param gameController
	 *            GameController to set
	 */
	public static void setGameController(GameController gameController) {
		Sprite.gameController = gameController;
	}

	public Vector getVector() {
		return vector;
	}

	protected double getAmount() {
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
