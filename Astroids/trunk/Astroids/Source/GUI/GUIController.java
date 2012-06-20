package GUI;

import java.awt.event.MouseEvent;

import Astroids.GameController;
import Astroids.SpaceShip;
import Astroids.Vector;
import Shapes.Point;

/**
 * 
 * @author Aismael Die Listener Klasse die die Tastatureingabe und den Mausinput
 *         Entgegenimmt und Auswertet
 * 
 */
public class GUIController extends Thread {
	private GameController gameController;// der intern benutzte Gamecontroller
	private WhiteboardControllPanel whiteboardControllPanel;// der intern benutzte
														// WhiteboardInAndOutPut
	boolean alternativeControll = true;// boolean für einschalten und
										// ausschalten der Alternativen
										// Steuerung
	private BigListener bigListener;

	/**
	 * Der Konstruktor des InputControllers
	 * 
	 * @param gameController
	 */
	public GUIController(GameController gameController) {

		this.bigListener=new BigListener(gameController,this);
		this.gameController = gameController;
		System.out.println("InputController started:\t" + this.getId());
		
		whiteboardControllPanel = new WhiteboardControllPanel(this,bigListener);

		

	}

	/**
	 * Die Steurungsübergabe des ControllPanels
	 * 
	 * @param arg0
	 * @return Vector
	 */
	void mouseControl(MouseEvent arg0) {

		Vector tempVector = new Vector(new Point(arg0.getX() - 150,
				arg0.getY() - 150));
		Vector outPutVector = new Vector(0, 0);
		if (tempVector.getAmount() < 80) {
			outPutVector = new Vector(tempVector.getAmount() / (80/gameController.getMaxSpeed()), -tempVector.getPhi());
		} else {

			outPutVector = new Vector(gameController.getMaxSpeed(), -tempVector.getPhi());

		}
		gameController.getSpaceShip().setVector(outPutVector);
	}

	/**
	 * Die Methode die die AWT OUTputKomponenten neu Zeichnet
	 */
	public void interfacerefresh() {
		whiteboardControllPanel.Outputrefresh();
	}

	String accelerationToString() {
		int ac2 = (int) gameController.getSpaceShip().getVector().getPhi();
		Integer acceleration = ac2;

		return acceleration.toString();
	}

	String PointsToString() {
		Integer points = (int) gameController.getHealth();
		return points.toString();

	}

	String yToString() {
		Integer ycoord = (int) gameController.getSpaceShip().getCenterPoint()
				.getY();
		return ycoord.toString();

	}

	String xToString() {
		Integer xcoord = (int) gameController.getSpaceShip().getCenterPoint()
				.getX();
		return xcoord.toString();

	}

	String speedToString() {
		Double speed = gameController.getSpaceShip().getVector().getAmount();
		String speedString = "0";
		if (speed < 0) {
			String speedString0 = speed.toString();
			speedString = speedString0.substring(0, 3);
		}
		if (speed >= 0) {
			String speedString0 = speed.toString();
			speedString = speedString0.substring(0, 2);

		}
		return speedString;
	}
	/**
	 * die Methode zur Berechnung des Raumschiffbewegungsvectors bei der
	 * Alternativeb Steuerung
	 * 
	 * @param arg0
	 *            {@link MouseEvent}
	 * 
	 * @return der Bewegungsvector fï¿½r das {@link SpaceShip}
	 */
	void mouseControlWhiteboard(MouseEvent arg0) {

		Vector tempVector = new Vector(new Point(arg0.getX()
				- (gameController.getWindowX())
				- gameController.getSpaceShip().getCenterPoint().getX(),
				arg0.getY()
						+ (-(gameController.getWindowY()) + gameController
								.getSpaceShip().getCenterPoint().getY())));
		Vector outPutVector = new Vector(0, 0);
		if (tempVector.getAmount() < 160) {

			outPutVector = new Vector(tempVector.getAmount()
					/ (160 / gameController.getMaxSpeed()),
					-tempVector.getPhi());

		} else {

			outPutVector = new Vector(gameController.getMaxSpeed(),
					-tempVector.getPhi());

		}
		gameController.getSpaceShip().setVector(outPutVector);
		;
	}
	int playFieldSizeX(){
		return gameController.getWindowX() * 2 + gameController.getAstroSize()
		* 2 - 16 + 300;
	}
	int playFieldSizeY(){
		return gameController.getWindowY() * 2
				+ gameController.getAstroSize() * 2 + 4;
	}
	void velocityChangePerInt(int velocity){
		if (velocity <= gameController
				.getMaxSpeed()
				&&  velocity >= -gameController
						.getMaxSpeed() / 2) {
			gameController.getSpaceShip().setVector(
					new Vector(velocity,
							gameController.getSpaceShip().getVector()
									.getPhi()));
		} else {
			gameController.getSpaceShip().setVector(
					new Vector(gameController.getSpaceShip()
							.getVector().getAmount(), gameController
							.getSpaceShip().getVector().getPhi()));

		}
	
	}
	void angleChangePerInt(int angle){
		gameController.getSpaceShip()
		.setVector(
				new Vector(gameController.getSpaceShip()
						.getVector().getAmount(),
						angle + 180));
	}

	void setCheat(boolean cheat){
		gameController.setCheat(cheat);
	}
	boolean getCheat(){
		return gameController.cheat;
	}
	boolean playerDieEvent(){
		return gameController.playerDieEvent();
	}

	public int getWindowX() {
		return gameController.getWindowX();
	}

	public int getWindowY() {
		return gameController.getWindowY();
	}
}