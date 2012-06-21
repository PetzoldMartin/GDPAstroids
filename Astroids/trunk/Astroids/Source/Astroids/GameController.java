package Astroids;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Collision.CollisionDetector;
import GUI.GUIController;
import Shapes.Drawable;
import Shapes.Point;
import Shapes.Rectangle;

/**
 * class that manage the program
 * 
 * @author (Martin Petzold , Markus Krummnacker)
 * @version (0.4)
 */
public class GameController extends Thread implements Runnable {
	public static void main(String[] args) {
		new GameController();
	}

	// [setup]
	// Window
	private int windowX = 1366 / 2 - 200; // std 600
	private int windowY = 768 / 2 - 25; // std 400
	private int frames = 30;
	// Ship
	private double keyAcelleration = 0.2;
	private double keyRotationAngel = 6;
	private double maxSpeed = 10;
	private int framesPerShot = 10; // how many frames between the shots!
	// Astro
	private int astroSize = 30;
	private int astroEdge = 24;
	// [setup/]
	private int gameScreenX = windowX - astroSize;
	private int gameScreenY = windowY - astroSize;
	private long frameTime = 1000 / frames; // time of a Frame in millis
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private Set<Sprite> removals = new HashSet<Sprite>();
	private Set<Sprite> adds = new HashSet<Sprite>();
	private SpaceShip spaceShip;
	private GUIController gUIController;
	private CollisionDetector collisionDetector;
	private int startHealth = 100;
	private int health = startHealth;
	private int astroStart = 5;
	private int astroCount = astroStart;
	// flags
	private boolean pause = false;
	private boolean windowActivated = true;
	public boolean cheat = false;
	public boolean testFlag;

	// testing
	// private Sprite test;

	public boolean isTestFlag() {
		return testFlag;
	}

	public void setTestFlag(boolean testFlag) {
		this.testFlag = testFlag;
	}

	/**
	 * generates a window with a drawboard starts itself as Thread
	 */
	public GameController() {
		System.out.println("GameController initialisiert:\t" + this.getId());
		Sprite.setGameController(this);
		Drawable backgroundFrame = new Rectangle(new Point(0, 0), windowX,
				windowY, Color.WHITE, true);
		Drawable background = new Rectangle(new Point(0, 0), gameScreenX,
				gameScreenY, Color.BLACK, true);
		backgroundFrame.draw();
		background.draw();
		collisionDetector = new CollisionDetector();
		new SpaceShip();
		gUIController = new GUIController(this);
		this.start();
	}

	/*
	 * control the framerate with pause mode
	 */
	@Override
	public void run() {
		System.out.println("GameController started:\t" + this.getId());
		for (;;) {
			Long runTime = System.nanoTime();
			if (health == 0) {
				playerDieEvent();
			} else if (!pause && windowActivated) {
				update();
				spaceShip.changeVector(gUIController.getKeyAmount(),
						gUIController.getKeyPhi(), maxSpeed);
				// System.out.println((double)(System.nanoTime() - runTime)
				// /1000000);
			}
			// interface frame refresh
			gUIController.interfacerefresh();
			try {
				Thread.sleep(runTime = frameTime
						- (System.nanoTime() - runTime) / 1000000);
			} catch (IllegalArgumentException e) {
				System.out.println("FrameTime overrun:\t " + (-runTime));
			} catch (InterruptedException e) {
				System.out.println("Frame interuppted");
				e.printStackTrace();
			}
		}

	}

	public void playerDieEvent() {
		gUIController.gameOverScreen();
	}

	/**
	 * updates all Sprites in sprites
	 */
	public void update() {
		// check
		checkObjects();
		// testing
		if (testFlag == true) {
			makeTest();
		}
		// update
		for (Sprite sprite : sprites) {
			sprite.update();
		}
		// add
		for (Sprite toAdd : adds) {
			this.sprites.add(toAdd);
		}
		adds.clear();
		// collision
		collisionCheck(sprites);
		// remove
		for (Sprite toRemove : removals) {
			toRemove.remove();
		}
		removals.clear();
		// draw
		for (Sprite sprite : sprites) {
			sprite.draw();
		}
	}

	/**
	 * check how many objects @ drawboard and generating new objects
	 */
	public void checkObjects() {
		if (health >= 150) {
			health-=50;
			astroCount++;
		}
		if (Astroid.getCounter() < astroCount) {
			new Astroid(astroEdge, astroSize);
		}
	}

	/**
	 * check collision of for all sprites in ArrayList
	 * 
	 * @param sprites
	 *            ArrayList to check
	 * 
	 */
	public void collisionCheck(ArrayList<Sprite> sprites) {
		for (Sprite spriteA : sprites) {
			for (Sprite spriteB : sprites) {
				if (spriteA != spriteB)
					spriteA.collision(spriteB);
				// TODO redo collision
			}
		}
	}

	public void makeTest() {
		new Astroid(astroEdge, astroSize);
	}

	public void spaceKey() {
		spaceShip.fire(framesPerShot);
	}

	public void pause() {
		if (this.pause == false) {
			this.pause = true;
		} else
			this.pause = false;
	}

	public synchronized void addSprites(Sprite sprite) {
		this.adds.add(sprite);
	}

	public synchronized void removeSprites(Sprite sprite) {
		if (!(sprite instanceof SpaceShip)) {
			this.removals.add(sprite);
		}
	}

	public synchronized void deleteSprite(Sprite sprite) {
		this.sprites.remove(sprite);
	}

	public void setInputController(GUIController gUIController) {
		this.gUIController = gUIController;
	}

	public void setWindowActivated(boolean windowActivated) {
		this.windowActivated = windowActivated;
	}

	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
	}

	public void setTestflag(boolean testFlag) {
		this.testFlag = testFlag;
	}

	public void setCheat(boolean change) {
		cheat = change;
	}

	public void healthChange(int change) {
		health += change;
		if (health < 0) {
			health = 0;
		}
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
		return frameTime;
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public GUIController getInputController() {
		return gUIController;
	}

	public CollisionDetector getCollisionDetector() {
		return collisionDetector;
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

	public int getHealth() {
		return health;
	}

	public void restart() {
		for (Sprite sprite : sprites) {
			removeSprites(sprite);
		}
		spaceShip.setVector(new Vector(0, 90));
		spaceShip.setCenterPoint(new Point(0, 0));
		health = startHealth;
		astroCount = astroStart;
	}

	public int getLevel() {
		return astroCount - astroStart;
	}

	public void guiOutPut(String string) {
		//TODO implemet the Gui output
	}
	
}