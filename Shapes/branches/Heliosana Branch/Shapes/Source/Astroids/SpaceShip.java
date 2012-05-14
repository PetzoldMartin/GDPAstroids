package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.Point;
import Shapes.Polygon;

public class SpaceShip extends Sprite {

	public SpaceShip(GameController gameController) {
		super();
		// TODO make SpaceShip "Singel"
		gameController.spaceShip = this;
		ArrayList<Point> shipList = new ArrayList<Point>();
		shipList.add(new Point(-10, -10));
		shipList.add(new Point(-10, 10));
		shipList.add(new Point(15, 0));
		this.addShape(new Polygon(shipList, Color.WHITE, false));
	}

	@Override
	public void update() {
		super.update();
		if (rotationPhi != vector.getPhi()) {
			this.rotate(rotationPhi - vector.getPhi());
		}
		rotationPhi = this.vector.getPhi();
	}
	public void changeVector(double amount,double phi,double maxSpeed) {
		if (this.vector.getAmount() < maxSpeed && amount > 0) {
			vector.changeSpeed(amount);
		}
		if (this.vector.getAmount() > -maxSpeed/2 && amount < 0) {
			vector.changeSpeed(amount);
		}
		this.vector.changeDirection(phi);
	}
}
