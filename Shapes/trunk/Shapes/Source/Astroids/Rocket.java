package Astroids;

import java.awt.Color;

import Shapes.Line;

public class Rocket extends Sprite {
	// TODO commenting
	// TODO redo weapons
	
	private int lifeTimeFrame;

	public Rocket(SpaceShip spaceShip) {
		radius = 3;
		lifeTimeFrame = (int) (gameController.getGameScreenX() / gameController
				.getMaxSpeed());
		this.setVector(new Vector(spaceShip.getAmount()
				+ gameController.getMaxSpeed() / 2 + 1, spaceShip.getPhi()));
		this.addShape(new Line(getCenterPoint(), getCenterPoint().move(
				new Vector(gameController.getMaxSpeed(), this.getVector()
						.getPhi()).invert()), Color.CYAN));
		this.move(spaceShip.getCenterPoint());
	}

	@Override
	public void update() {
		super.update();
		if (this.getAmount() < gameController.getMaxSpeed() * 2) {
			this.changeSpeed(gameController.getKeyAcelleration());
		}
		if (lifeTimeFrame <= 0) {
			gameController.removeSprites(this);
		}
		lifeTimeFrame--;
	}

	@Override
	public void destroy(Sprite collider) {
		if (!(collider instanceof SpaceShip)) {
			gameController.removeSprites(this);
		}
	}
}