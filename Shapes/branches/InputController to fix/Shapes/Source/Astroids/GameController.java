package Astroids;

import java.awt.Color;
import java.util.ArrayList;

import Input.InputController;
import Shapes.*;

/**
 * class that manage the program
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */
public class GameController extends Thread{
	// TODO implement setup
	//[setup]
	public static double keyRotationAngel = 3;
	public static double keyAcelleration = 0.1;
	public static int windowX = 380 / 2;
	public static int windowY = 275 / 2;
	public static int frames = 25;
	//[setup/]
	public static long globalFrameTime = 1000 / frames;
	public static Point window = new Point(windowX * 2, windowY * 2);
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public static SpaceShip spaceShip;
	private InputController Input;

	// TODO Entwickeln Sie eine Klasse GameController, die alle Sprites kennt.
	// Diese Klasse verfügt über einen Thread zum Aktualisieren aller
	// Sprite-Grafiken. Dieser Thread fordert alle Sprites auf, sich bei Bedarf
	// zu aktualisieren.

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public GameController() {
		Drawable background = new Rectangle(new Point(0, 0), window.getX(),
				window.getY(), Color.BLACK, true);
		background.draw();
		new SpaceShip();
		
		//Input, ShipMovement and Whiteboard Refresh
		Input=new InputController();
		spaceShip.vector.changeVector(Input.doMovement());
		Thread inputKeyController= new Thread(Input);
		inputKeyController.start();
		Thread frameController =new Thread(new FrameController());
		frameController.start();
		
		//new Astroid();
		//new Astroid();
		}
	
	public static void update() throws InterruptedException {
		Long runTime = System.nanoTime();
		spaceShip.update();
		for (Sprite sprite : sprites) {
			sprite.update();
		}
		try {
			 Thread.sleep(runTime=globalFrameTime - (System.nanoTime() - runTime)/1000000);
			 } catch (IllegalArgumentException e) {
			 System.out.println("Time Overload: " + (runTime-globalFrameTime));
			}
		//System.out.println(runTime);
	}
}