package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Input.InputKeyController;
import Shapes.Drawable;
import Shapes.Point;
import Shapes.Rectangle;

/**
 * class that manage the program
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */
public class GameController extends Thread {
	public static void main(String[] args) {
		new GameController();
	}

	// TODO implement setup
	// [setup]
	public static double keyRotationAngel = 3;
	public static double keyAcelleration = 0.1;
	public static int windowX = 380 / 2;
	public static int windowY = 275 / 2;
	public static int frames = 50;
	// [setup/]
	public static long globalFrameTime = 1000 / frames;
	public static Point window = new Point(windowX * 2, windowY * 2);
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public static SpaceShip spaceShip;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public GameController() {
		Drawable background = new Rectangle(new Point(0, 0), window.getX(),
				window.getY(), Color.BLACK, true);
		background.draw();
		new SpaceShip();
		Thread frameController = new FrameController();
		Thread inputKeyController = new Thread(new InputKeyController());
		frameController.start();
		inputKeyController.start();
	}

	public static void update() throws InterruptedException {
		Long runTime = System.nanoTime();
		spaceShip.update();
		for (Sprite sprite : sprites) {
			sprite.update();
		}
		try {
			Thread.sleep(runTime = globalFrameTime
					- (System.nanoTime() - runTime) / 1000000);
		} catch (IllegalArgumentException e) {
			System.out.println("Time Overload: " + (runTime - globalFrameTime));
		}
		// System.out.println(runTime);
	}

}