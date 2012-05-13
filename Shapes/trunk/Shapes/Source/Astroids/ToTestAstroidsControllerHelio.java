package Astroids;

import java.awt.Color;

import Shapes.Drawable;
import Shapes.Point;
import Shapes.Rectangle;

/**
 * class that manage the program
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */

public class ToTestAstroidsControllerHelio extends GameController {
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args)  {
		Drawable background = new Rectangle(new Point(0, 0), window.getX(),
				window.getY(), Color.BLACK, true);
		background.draw();
		new SpaceShip();
//		new Astroid();
//		new Astroid();
		Sprite test= new Astroid();
		spaceShip.vector.changeSpeed(8);
		for (;;) {
			spaceShip.vector.changeDirection(-90);
			for (int j = 0; j < 4; j++) {
				for (int i = 0; i < 25; i++) {
					try {
						update();
						Thread.sleep(globalFrameTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				spaceShip.vector.changeDirection(90);
				test.setMiddlePoint(new Point(0,0));
			}
		}
	}
}
