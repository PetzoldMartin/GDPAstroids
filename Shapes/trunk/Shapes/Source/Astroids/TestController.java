package Astroids;

import java.awt.Color;

import Shapes.*;

/**
 * class to test controller funktions
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */

public class TestController extends GameController{
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Drawable background = new Rectangle(new Point(0, 0), window.getX(),
				window.getY(), Color.BLACK, true);
		background.draw();
		Sprite test1 = new SpaceShip();
		Sprite test2 = new SpaceShip();
		Sprite astro = new Astroid();
		test1.vector.changeSpeed(8);
		test2.vector.changeSpeed(4);
		for (;;) {
			test1.vector.changeDirection(-90);
			test2.vector.changeDirection(-90);
			for (int j = 0; j < 4; j++) {
				for (int i = 0; i < 25; i++) {
					//astro.update();
					test1.update();
					test2.update();
				}
				astro.setMiddlePoint(new Point(0, 0));
				test1.vector.changeDirection(90);
				test2.vector.changeDirection(90);
			}

		}
	}
}
