package Astroids;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
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
 * @version (0.5)
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
	private int framesPerShot = frames / 2; // how many frames between the
											// shots!
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
	private int healthStart = 100;
	private int astroStart = 5;
	// flags
	private boolean pause = false;
	private boolean windowActivated = true;
	private boolean cheat = false;
	private boolean testFlag;
	// working vars
	private int astroCount = astroStart;
	private int health = healthStart;
	private int spawnCooldown = 0;

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

	public synchronized void addSprites(Sprite sprite) {
		this.adds.add(sprite);
	}

	/**
	 * check how many objects @ drawboard and generating new objects
	 */
	private void checkObjects() {
		spawnCooldown--;
		if (health >= 120) {
			health -= 20;
			astroCount++;
		}
		if (Astroid.getCounter() < astroCount && spawnCooldown <= 0) {
			edgeCreationWarp(new Astroid(astroEdge, astroSize));
			spawnCooldown = frames / 2;
		}
	}

	/**
	 * check collision of for all sprites in ArrayList
	 * 
	 * @param sprites
	 *            ArrayList to check
	 * 
	 */
	private void collisionCheck(ArrayList<Sprite> sprites) {
		for (Sprite spriteA : sprites) {
			for (Sprite spriteB : sprites) {
				if (spriteA != spriteB)
					spriteA.collision(spriteB);
			}
		}
	}

	private void edgeCreationWarp(Astroid astroid) {

		double phi = astroid.getPhi();
		if (phi > 0) {
			if (Math.abs(phi) < 90) {
				if (new Random().nextBoolean()) {
					astroid.setCenterPoint(new Point(astroid.getCenterPoint()
							.getX(), -(gameScreenY-1)));
				} else {
					astroid.setCenterPoint(new Point(-(gameScreenX-1), astroid
							.getCenterPoint().getY()));
				}
			} else {
				if (new Random().nextBoolean()) {
					astroid.setCenterPoint(new Point(astroid.getCenterPoint()
							.getX(), -(gameScreenY-1)));
				} else {
					astroid.setCenterPoint(new Point((gameScreenX-1), astroid
							.getCenterPoint().getY()));
				}
			}
		} else {
			if (Math.abs(phi) < 90) {
				if (new Random().nextBoolean()) {
					astroid.setCenterPoint(new Point(astroid.getCenterPoint()
							.getX(), (gameScreenY-1)));
				} else {
					astroid.setCenterPoint(new Point(-(gameScreenX-1), astroid
							.getCenterPoint().getY()));
				}
			} else {
				if (new Random().nextBoolean()) {
					astroid.setCenterPoint(new Point(astroid.getCenterPoint()
							.getX(), (gameScreenY-1)));
				} else {
					astroid.setCenterPoint(new Point((gameScreenX-1), astroid
							.getCenterPoint().getY()));
				}
			}
		}
		if (astroid.collision(spaceShip)) {
			removeSprites(astroid);
			edgeCreationWarp(new Astroid(astroEdge, astroSize));
		}
	}

	public int getAstroSize() {
		return astroSize;
	}

	public CollisionDetector getCollisionDetector() {
		return collisionDetector;
	}

	public int getGameScreenX() {
		return gameScreenX;
	}

	public int getGameScreenY() {
		return gameScreenY;
	}

	public long getGlobalFrameTime() {
		return frameTime;
	}

	public int getHealth() {
		return health;
	}

	public GUIController getInputController() {
		return gUIController;
	}

	public double getKeyAcelleration() {
		return keyAcelleration;
	}

	public double getKeyRotationAngel() {
		return keyRotationAngel;
	}

	public int getLevel() {
		return astroCount - astroStart;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public int getWindowX() {
		return windowX;
	}

	public int getWindowY() {
		return windowY;
	}

	/**
	 * put out an string for weapon control over the GUI
	 * 
	 * @param string
	 *            to put out at the GUI
	 */
	public void guiOutPut(String string) {
		gUIController.outPutString(string, framesPerShot);
	}

	public void healthChange(int change) {
		if (health > 0) {
			health += change;
		}
	}

	public boolean isCheat() {
		return cheat;
	}

	public boolean isTestFlag() {
		return testFlag;
	}

	public void makeTest() {
		if (cheat && spawnCooldown <= 0) {
			System.out.println("RocketSpam!!!");
			for (int i = 0; i < 360; i += 2) {
				spawnCooldown = frames / 2;
				Rocket spamed = new Rocket(spaceShip);
				spamed.rotate(i);
				spamed.changeDirection(i);
			}
		}
	}

	public void pause() {
		if (this.pause == false) {
			this.pause = true;
		} else
			this.pause = false;
	}

	private void playerDieEvent() {
		gUIController.gameOverScreen();
	}

	public synchronized void removeSprites(Sprite sprite) {
		if (!(sprite instanceof SpaceShip)) {
			this.removals.add(sprite);
		}
	}

	/**
	 * deletes all sprites and reinitialise the gamefield
	 */
	public void restart() {
		for (Sprite sprite : sprites) {
			removeSprites(sprite);
		}
		spaceShip.setVector(new Vector(0, 90));
		spaceShip.setCenterPoint(new Point(0, 0));
		health = healthStart;
		astroCount = astroStart;
	}

	/*
	 * control the framerate with pause mode
	 */
	@Override
	public void run() {
		System.out.println("GameController started:\t" + this.getId());
		for (;;) {
			Long runTime = System.nanoTime();
			if (health <= 0) {
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

	public void setCheat(boolean change) {
		cheat = change;
	}

	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
	}

	public void setTestFlag(boolean testFlag) {
		this.testFlag = testFlag;
	}

	public void setWindowActivated(boolean windowActivated) {
		this.windowActivated = windowActivated;
	}

	public void spaceKey() {
		spaceShip.fire(framesPerShot);
	}

	/**
	 * updates all Sprites in this GameController
	 */
	private void update() {
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
			this.sprites.remove(toRemove);
		}
		removals.clear();
		// draw
		for (Sprite sprite : sprites) {
			sprite.draw();
		}
	}

}