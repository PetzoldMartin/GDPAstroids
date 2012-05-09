package Astroids;

import java.awt.Color;

import Shapes.Drawable;
import Shapes.Point;
import Shapes.Rectangle;


/**
 * class that manage the program
 * 
 * @author (Markus Krummnacker)
 * @version (0.3)
 */
public class Main {
	public static int windowX = 380/2;
	public static int windowY = 275/2;
	public static Point window = new Point(windowX*2, windowY*2);
	public static long globalFrameTime= 1000/50;

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
		long time =10;
		for (;;) {
			test1.vector.changeDirection(-90);
			test2.vector.changeDirection(-90);
			for (int i = 0; i < 25; i++) {
				//test1.rotate(5).draw();
//				Thread.sleep(time);
				astro.update();
				test1.update();
				test2.update();
//				Thread.sleep(time);
			}
			astro.setMiddlePoint(new Point(0,0));
			test1.vector.changeDirection(90);
			test2.vector.changeDirection(90);
			for (int i = 0; i < 25; i++) {
				//test1.rotate(5).draw();
//				Thread.sleep(time);
				astro.update();
				test1.update();
				test2.update();
//				Thread.sleep(time);
			}
			astro.setMiddlePoint(new Point(0,0));
			test1.vector.changeDirection(90);
			test2.vector.changeDirection(90);
			for (int i = 0; i < 25; i++) {
				//test1.rotate(5).draw();
//				Thread.sleep(time);
				astro.update();
				test1.update();	
				test2.update();
//				Thread.sleep(time);
			}
			astro.setMiddlePoint(new Point(0,0));
			test1.vector.changeDirection(90);
			test2.vector.changeDirection(90);
			for (int i = 0; i < 25; i++) {
				//test1.rotate(5).draw();
//				Thread.sleep(time);
				astro.update();
				test1.update();
				test2.update();
//				Thread.sleep(time);
			}
			astro.setMiddlePoint(new Point(0,0));
			test1.vector.changeDirection(90);
			test2.vector.changeDirection(90);
		}
	}
}
