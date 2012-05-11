package Astroids;

import java.awt.Color;

import Shapes.*;

/**
 * class that manage the program
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */
@SuppressWarnings("serial")
public class GameController extends javax.swing.JFrame {
	// TODO implement setup
	//[setup]
	public static double keyRotationAngel = 3;
	public static double keyAcelleration = 0.1;
	public static int windowX = 380 / 2;
	public static int windowY = 275 / 2;
	public static int frames = 30;
	//[setup/]
	public static long globalFrameTime = 1000 / frames;
	public static Point window = new Point(windowX * 2, windowY * 2);

	// TODO Entwickeln Sie eine Klasse GameController, die alle Sprites kennt.
	// Diese Klasse verfügt über einen Thread zum Aktualisieren aller
	// Sprite-Grafiken. Dieser Thread fordert alle Sprites auf, sich bei Bedarf
	// zu aktualisieren.

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
					// astro.update();
					test1.update();
					test2.update();
					Thread.sleep(globalFrameTime);
				}
				astro.setMiddlePoint(new Point(0, 0));
				test1.vector.changeDirection(90);
				test2.vector.changeDirection(90);
			}
		}
	}
}
