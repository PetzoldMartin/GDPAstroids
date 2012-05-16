package Astroids;

import Shapes.Line;

public class Rocket extends Sprite {
	public Rocket(SpaceShip spaceShip) {
		this.setVector(new Vector(spaceShip.getAmount()+5,spaceShip.getPhi()));
		this.addShape(new Line(getCenterPoint(),getCenterPoint().move(this.getVector().invert())));
		this.move(spaceShip.getCenterPoint());
	}
}
