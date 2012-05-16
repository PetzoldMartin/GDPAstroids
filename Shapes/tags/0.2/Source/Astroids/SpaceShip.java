package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.*;

public class SpaceShip extends Sprite {

	// TODO make SpaceShip "Singel"
	public SpaceShip(GameController gameController) {
		super();
		this.radius=15;
		gameController.setSpaceShip(this);
		this.setGameController(gameController);
		ArrayList<Point> shipList = new ArrayList<Point>();
		shipList.add(new Point(-10, -10));
		shipList.add(new Point(-10, 10));
		shipList.add(new Point(15, 0));
		this.addShape(new Polygon(shipList, Color.WHITE, false));
//		this.addShape(new Circle(radius, this.getCenterPoint().copy(), Color.RED, false));	
	}

	@Override
	public void update() {
		super.update();
		if (rotationPhi != getPhi()) {
			this.rotate(rotationPhi - getPhi());
		}
		rotationPhi = this.getPhi();
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

	public void fire() {
		new Rocket(this);
	}
}
