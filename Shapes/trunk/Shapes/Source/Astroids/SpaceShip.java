package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Point;
import Shapes.Polygon;

public class SpaceShip extends Sprite {
	private int fireCounter = 0;

	// TODO commenting
	// TODO make SpaceShip "Singelton"
	public SpaceShip() {
		super();
		this.radius = 15;
		gameController.setSpaceShip(this);
		ArrayList<Point> shipList = new ArrayList<Point>();
		shipList.add(new Point(-10, -10));
		shipList.add(new Point(-10, 10));
		shipList.add(new Point(15, 0));
		this.addShape(new Polygon(shipList, Color.WHITE, false));
		// this.addShape(new Circle(radius, this.getCenterPoint().copy(),
		// Color.RED, false));
	}

	@Override
	public void update() {
		if (rotationPhi != getPhi()) {
			this.rotate(rotationPhi - getPhi());
		}
		rotationPhi = this.getPhi();
		super.update();
		fireCounter--;
	}

	public void changeVector(double amount, double phi, double maxSpeed) {
		if (this.getAmount() < maxSpeed && amount > 0) {
			this.changeSpeed(amount);
		}
		if (this.getAmount() > -maxSpeed / 2 && amount < 0) {
			this.changeSpeed(amount);
		}
		this.changeDirection(phi);
	}

	public void fire(int framesPerShot) {
		if ((fireCounter <= 0) || gameController.cheat) {
			new Rocket(this);
			fireCounter = framesPerShot;
		} else {
			System.out.println("Gun not ready!!! " + fireCounter);
		}

	}

	@Override
	public void destroy(Sprite collider) {
		if (!gameController.cheat) {
		// TODO Player die event
		}
	}
}
