package Astroids;

import java.awt.Color;
import Shapes.Line;

/**
 * class that manage a rocket
 * 
 * @author (Martin Petzold , Markus Krummnacker)
 * @version (0.4)
 */
public class Rocket extends Sprite {

	private int lifeTimeFrame;

	/**
	 * create a spaceship
	 * 
	 * @param spaceShip
	 *            that generates the rocket
	 */
	public Rocket(SpaceShip spaceShip) {
		radius = gameController.getMaxSpeed()*2;
		lifeTimeFrame = (int) (gameController.getGameScreenX()* 1.2 / gameController
				.getMaxSpeed());
		this.setVector(new Vector(spaceShip.getAmount()
				+ gameController.getMaxSpeed() / 2 + 1, spaceShip.getPhi()));
		this.addShape(new Line(getCenterPoint(), getCenterPoint().move(
				new Vector(gameController.getMaxSpeed()*2, this.getVector()
						.getPhi())), Color.ORANGE));
		this.move(spaceShip.getCenterPoint().move(new Vector(radius, getPhi())));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Astroids.Sprite#update()
	 */
	@Override
	public void update() {
		super.update();
		if (this.getAmount() < gameController.getMaxSpeed() * 2) {
			this.changeSpeed(gameController.getKeyAcelleration());
		}
		if ((lifeTimeFrame <= 0) && !gameController.cheat) {
			destroy(null);
		}
		lifeTimeFrame--;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Astroids.Sprite#destroy(Astroids.Sprite)
	 */
	@Override
	public void destroy(Sprite collider) {
		if (!(collider instanceof SpaceShip) && !(collider instanceof Rocket)) {
			gameController.removeSprites(this);
			if ((collider!=(null))) {
				gameController.healthChange(1);
			}
		}
	}
}