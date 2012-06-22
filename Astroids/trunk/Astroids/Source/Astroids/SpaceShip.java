package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Point;
import Shapes.Polygon;

/**
 * generates a spaceship @ drawboard
 * 
 * @author Markus Krummnacker, Martin Petzold
 * @version (0.4)
 * 
 */
public class SpaceShip extends Sprite {
	private int fireCounter = 0;

	// TODO make SpaceShip "Singelton"
	/**
	 * create new SpaceShip
	 */
	public SpaceShip() {
		super();
		this.radius = Math.round(Math.sqrt(800));
		gameController.setSpaceShip(this);
		ArrayList<Point> shipList = new ArrayList<Point>();
		ArrayList<Point> cockPitList = new ArrayList<Point>();
		// ship
		shipList.add(new Point(14, 0)); // nose
		shipList.add(new Point(10, 4));
		shipList.add(new Point(6, 6));
		shipList.add(new Point(-4, 8));
		shipList.add(new Point(4, 14));
		shipList.add(new Point(6, 16));
		shipList.add(new Point(18, 18));
		shipList.add(new Point(20, 20));
		shipList.add(new Point(0, 20));
		shipList.add(new Point(-14, 14));
		shipList.add(new Point(-18, 10));
		shipList.add(new Point(-20, 6));
		shipList.add(new Point(-18, 2));
		shipList.add(new Point(-16, 0)); // ass
		shipList.add(new Point(-18, -2));
		shipList.add(new Point(-20, -6));
		shipList.add(new Point(-18, -10));
		shipList.add(new Point(-14, -14));
		shipList.add(new Point(0, -20));
		shipList.add(new Point(20, -20));
		shipList.add(new Point(18, -18));
		shipList.add(new Point(6, -16));
		shipList.add(new Point(4, -14));
		shipList.add(new Point(-4, -8));
		shipList.add(new Point(6, -6));
		shipList.add(new Point(10, -4));
		shipList.add(new Point(14, 0));
		// cockpit
		cockPitList.add(new Point(2, 4));
		cockPitList.add(new Point(8, 2));
		cockPitList.add(new Point(10, 0));
		cockPitList.add(new Point(8, -2));
		cockPitList.add(new Point(2, -4));
		this.addShape(new Polygon(shipList, Color.WHITE, false));
		this.addShape(new Polygon(cockPitList, Color.WHITE, true));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Astroids.Sprite#update()
	 */
	@Override
	public void update() {
		if (rotationPhi != getPhi()) {
			this.rotate(rotationPhi - getPhi());
		}
		rotationPhi = this.getPhi();
		super.update();
		if (fireCounter>0 && fireCounter--%3==0) {
			gameController.guiOutPut("Gun Reloading: " + fireCounter/3);
		}
	}

	/**
	 * change the vector
	 * 
	 * @param amount
	 *            value to change amount
	 * @param phi
	 *            value to change phi
	 * @param maxSpeed
	 *            value for max amount
	 */
	public void changeVector(double amount, double phi, double maxSpeed) {
		if (this.getAmount() < maxSpeed && amount > 0) {
			this.changeSpeed(amount);
		}
		if (this.getAmount() > -maxSpeed / 2 && amount < 0) {
			this.changeSpeed(amount);
		}
		this.changeDirection(phi);
	}

	/**
	 * create a rocket @ position & with the vector of this SpaceShip
	 * 
	 * @param framesPerShot
	 *            how many frames wait after a shot to fire again
	 */
	public void fire(int framesPerShot) {
		if ((fireCounter <= 0) || gameController.isCheat()) {
			new Rocket(this);
			fireCounter = framesPerShot;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Astroids.Sprite#destroy(Astroids.Sprite)
	 */
	@Override
	public void destroy(Sprite collider) {
		if (!gameController.isCheat() && !(collider instanceof Rocket)) {
			gameController.healthChange(-25);
		}
	}
}
