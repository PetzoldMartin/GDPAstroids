package Astroids;

import java.awt.Color;
import java.util.*;

import Input.InputController;
import Shapes.Drawable;
import Shapes.Point;
import Shapes.Rectangle;

/**
 * class that manage the program
 * 
 * @author (Martin Petzold)
 * @version (0.1)
 */
public class GameController extends Thread implements Runnable {

	public static void main(String[] args) {
		new GameController().start();
	}
	// [setup]
	private double keyRotationAngel = 6;
	private double keyAcelleration = 0.2;
	// Astro
	private int astroCount=0;
	private int astroSize=20;
	private int astroEdge=24;
	//Window
	private int windowX = 600; // max std 389
	private int windowY = 400; // max std 278
	private int gameScreenX = windowX -astroSize;
	private int gameScreenY = windowY -astroSize;
	private int frames = 30;
	//Ship
	private double maxSpeed = 10;
	private int framesPerShot = 10;
	// [setup/]
	private long globalFrameTime = 1000 / frames; // time of a Frame in millis
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> removals = new ArrayList<Sprite>();
	private ArrayList<Sprite> adds = new ArrayList<Sprite>();
	private SpaceShip spaceShip;
	private FrameController frameController;
	private InputController inputController;
	public Sprite test;
	

	// TODO Entwickeln Sie eine Klasse GameController, die alle Sprites kennt.
	// Diese Klasse verfügt über einen Thread zum Aktualisieren aller
	// Sprite-Grafiken. Dieser Thread fordert alle Sprites auf, sich bei Bedarf
	// zu aktualisieren.

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public void run() {
		System.out.println("GameController started:\t" + this.getId());
		new SpaceShip(this);
		Drawable backgroundFrame = new Rectangle(new Point(0, 0), windowX,
				windowY, Color.WHITE, true);
		Drawable background = new Rectangle(new Point(0, 0), gameScreenX, gameScreenY,
				Color.BLACK, true);
		backgroundFrame.draw();
		background.draw();
		for (int i = 0; i < astroCount; i++) {
			new Astroid(astroEdge,astroSize);
		}
		test = new Astroid(astroEdge,astroSize);
		frameController = new FrameController(this);
		inputController = new InputController(this);
		// inputController.start();
		frameController.start();

	}

	public void makeTest() {
		test.remove();
	}

	public void spaceKey() {
		spaceShip.fire();		
	}

	public void update() {
		for (Sprite remove : removals) {
//			System.out.println(sprite.centerPoint.getX() + "\t" + sprite.centerPoint.getY());
			remove.remove();
		}
		removals.clear();
		getSpaceShip().update();
//		 System.out.println(getSpaceShip().getMiddlePoint().getX() + "\t" + getSpaceShip().getMiddlePoint().getY());
		for (Sprite sprite : sprites) {
//			System.out.println(sprite.centerPoint.getX() + "\t" + sprite.centerPoint.getY());
			sprite.update();
		}
	}

	public void pause() {
		if (this.frameController.getPause()==false) {
			this.frameController.setPause(true);			
		}
		else this.frameController.setPause(false);
	}

	public void addSprites(Sprite sprite) {
		this.sprites.add(sprite);
	}

	public void removeSprites(Sprite sprite) {
		this.sprites.remove(sprite);
	}

	public void addRemovals(Sprite removals) {
		this.removals.add(removals);
	}

	public void addAdds(Sprite adds) {
		this.removals.add(adds);
	}

	public void setInputController(InputController inputController) {
		this.inputController = inputController;
	}

	public void setWindowActivated(boolean windowActivated) {
		frameController.setWindowActivated(windowActivated);
	}
	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
	}

	public void setTestflag(boolean testFlag) {
		frameController.testFlag = testFlag;
	}

	public int getWindowX() {
		return windowX;
	}

	public int getWindowY() {
		return windowY;
	}

	public double getKeyRotationAngel() {
		return keyRotationAngel;
	}

	public double getKeyAcelleration() {
		return keyAcelleration;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public long getGlobalFrameTime() {
		return globalFrameTime;
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public InputController getInputController() {
		return inputController;
	}

	public int getAstroSize() {
		return astroSize;
	}

	public int getGameScreenX() {
		return gameScreenX;
	}

	public int getGameScreenY() {
		return gameScreenY;
	}
}