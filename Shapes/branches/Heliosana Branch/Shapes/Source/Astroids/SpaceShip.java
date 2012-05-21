package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Shapes.*;

public class SpaceShip extends Sprite {
	private int fireCounter=0;

	// TODO make SpaceShip "Singel"
	public SpaceShip() {
		super();
		this.radius=15;
		gameController.setSpaceShip(this);
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

	public void fire(int frames) {
		if (fireCounter <=0) {
			new Rocket(this);
			fireCounter= frames;
		} else {
			System.out.println("not ready!!! "+fireCounter);
		}
			
	}
}
