package GUI;

import java.awt.event.MouseEvent;

import Astroids.Astroid;
import Astroids.GameController;
import Astroids.SpaceShip;
import Astroids.Vector;
import Shapes.Point;
import Teaching.WhiteBoard;

/**
 * Der {@link GUIController} ist der Conntroller der zwischen Spiel und GUI sowie den Listenern vermittelt und 
 * die Berechnungen ausfuehrt die Notwendig sind damit Spiel und GUI und Listener miteinander Kommunizieren koennen
 * 
 * @author Aismael
 * 
 */
public class GUIController extends Thread {
	private GameController gameController;// der intern benutzte Gamecontroller
	private WhiteboardControllPanel whiteboardControllPanel;//das WhiteboardControllPanel
	boolean alternativeControll = true;// boolean fuer einschalten und
										// ausschalten der Alternativen
										// Steuerung
	private BigListener bigListener;//der Interne Biglistener der mit dem Gamecontroller bekannt gemacht wird
										private String outputString="";//der OutputString
										private int counterInt;//der Counter für die zeit der outputzeile

	/**
	 * Der Konstruktor des InputControllers
	 * 
	 * @param gameController der vom Spiel übergebene {@link GameController}
	 */
	public GUIController(GameController gameController) {
		this.bigListener = new BigListener(gameController, this);
		this.gameController = gameController;
		System.out.println("GUIController started:\t" + this.getId());

		whiteboardControllPanel = new WhiteboardControllPanel(this, bigListener);

	}

	/**
	 * Die Methode  die den Ausgaberefresh des {@link WhiteboardControllPanel} ausloest
	 */
	public void interfacerefresh() {
		whiteboardControllPanel.Outputrefresh();
		counterInt--;
		if (counterInt<=0){
			outputString="";
		}
	}

	public double getKeyAmount() {
		return bigListener.getKeyAmount();
	}

	public double getKeyPhi() {
		return bigListener.getKeyPhi();
	}

	/**
	 * Die Berechnungsmethode die die Mausposition auf einem AWT Objekt entgegenimmt
	 * und diese in einen BewegungsveKtor {@link Vector} umrechnet mit dem das {@link SpaceShip} gesteuert wird
	 * 
	 * @param arg0 {@link MouseEvent}
	 */
	protected void mouseControl(MouseEvent arg0) {

		Vector tempVector = new Vector(new Point(arg0.getX() - 150,
				arg0.getY() - 150));
		Vector outPutVector = new Vector(0, 0);
		if (tempVector.getAmount() < 80) {
			outPutVector = new Vector(tempVector.getAmount()
					/ (80 / gameController.getMaxSpeed()), -tempVector.getPhi());
		} else {

			outPutVector = new Vector(gameController.getMaxSpeed(),
					-tempVector.getPhi());

		}
		gameController.getSpaceShip().setVector(outPutVector);
	}

	/**
	 * Die Berechnungsmethode die die Mausposition auf dem {@link WhiteBoard} Objekt entgegenimmt
	 * und diese in einen BewegungsveKtor {@link Vector} umrechnet mit dem das {@link SpaceShip} gesteuert wird
	 * 
	 * @param arg0 {@link MouseEvent}
	 */
	protected void mouseControlWhiteboard(MouseEvent arg0) {

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

	/**
	 * Die Methode die den Winkel des  {@link SpaceShip} entgenimmt und als String zurueckgibt
	 * @return der Winkel des {@link SpaceShip} als String
	 */
	protected String accelerationToString() {
		int ac2 = (int) gameController.getSpaceShip().getVector().getPhi();
		Integer acceleration = ac2;

		return acceleration.toString();
	}

	/**
	 * Die Methode die den Winkel des  {@link SpaceShip} entgenimmt und als String zurueckgibt
	 * @return der Winkel des {@link SpaceShip} als String
	 */
	protected String accelerationToStringForJSpinner() {
		int ac2 = (int) gameController.getSpaceShip().getVector().getPhi();
		Integer acceleration = ac2;

		return acceleration.toString();
	}
	/**
	 * Die Methode die die Punkte des  Spiels entgenimmt und als String zurueckgibt
	 * @return die Points als String
	 */
	protected String pointsToString() {
		Integer points = (int) gameController.getHealth();
		return points.toString();

	}

	/**
	 * Die Methode die die Y-Position des  {@link SpaceShip} entgenimmt und als String zurueckgibt
	 * @return die Y-Position des {@link SpaceShip} als String
	 */
	protected String yToString() {
		Integer ycoord = (int) gameController.getSpaceShip().getCenterPoint()
				.getY();
		return ycoord.toString();

	}

	/**
	 * Die Methode die die X-Position des  {@link SpaceShip} entgenimmt und als String zurueckgibt
	 * @return die X-Position des {@link SpaceShip} als String
	 */
	protected String xToString() {
		Integer xcoord = (int) gameController.getSpaceShip().getCenterPoint()
				.getX();
		return xcoord.toString();

	}

	/**
	 * Die Methode die die Geschwindigkeit des  {@link SpaceShip} entgenimmt und als String zurueckgibt
	 * @return die Geschwindigkeit des {@link SpaceShip} als String
	 */
	protected String speedToString() {
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
	 * greift die im Spiel gegebene X-Groesse des Spielfeldes entgegenimmt und die {@link WhiteBoard} Groesse für Spiel + Gui Anpasst
	 * @return die Angepasste X-Groesse
	 */
	protected int playFieldSizeX() {
		return gameController.getWindowX() * 2 + gameController.getAstroSize()
				* 2 - 16 + 300;
	}
	/**
	 * greift die im Spiel gegebene Y-Groesse des Spielfeldes entgegenimmt und die {@link WhiteBoard} Groesse für Spiel + Gui Anpasst
	 * @return die Angepasste Y-Groesse
	 */
	protected int playFieldSizeY() {
		return gameController.getWindowY() * 2 + gameController.getAstroSize()
				* 2 + 4;
	}

	/**
	 * Methode die einen Integer Wert entgegenimmt und in einen Vector mit dem {@link SpaceShip} Winkel und der neuen Geschwindigkeit
	 * zurückgibt
	 * @param velocity der {@link Vector} der das Raumschiff bewegt
	 */
	protected void velocityChangePerInt(int velocity) {
		if (velocity <= gameController.getMaxSpeed()
				&& velocity >= -gameController.getMaxSpeed() / 2) {
			gameController.getSpaceShip().setVector(
					new Vector(velocity, gameController.getSpaceShip()
							.getVector().getPhi()));
		} else {
			gameController.getSpaceShip().setVector(
					new Vector(gameController.getSpaceShip().getVector()
							.getAmount(), gameController.getSpaceShip()
							.getVector().getPhi()));

		}

	}
	/**
	 * Methode die einen Integer Wert entgegenimmt und in einen Vector mit der {@link SpaceShip} Geschwindigkeit und der neuen Winkel
	 * zurückgibt
	 * @param velocity der {@link Vector} der das Raumschiff bewegt
	 */
	protected void angleChangePerInt(int angle) {
		gameController.getSpaceShip().setVector(
				new Vector(gameController.getSpaceShip().getVector()
						.getAmount(), angle));
	}

	/**
	 * nimmt ein boolean entgegen und setzt den Cheatmodus des Spiels
	 * @param cheat das Boolean das den cheat Modus verändert
	 */
	protected void setCheat(boolean cheat) {
		gameController.setCheat(cheat);
	}

	/**
	 * reicht den Zustand des Cheatmoduses weiter
	 * @return der Zustand des Cheatmodus
	 */
	protected boolean getCheat() {
		return gameController.cheat;
	}

	/**
	 * reicht den Zustand des Playerstatuses weiter
	 * @return der Zustand des Playerstatuses
	 */
	public void gameOverScreen() {
		if(!whiteboardControllPanel.isGameOver()){
		whiteboardControllPanel.gameoverSight();}
	}

	/**
	 * reicht die X-Groesse des Spielfeldes Weiter
	 * @return die X-Groesse des Spielfeldes
	 */
	protected int getWindowX() {
		return gameController.getWindowX();
	}

	/**
	 * reicht die Y-Groesse des Spielfeldes Weiter
	 * @return die Y-Groesse des Spielfeldes
	 */
	protected int getWindowY() {
		return gameController.getWindowY();
	}
	/**
	 * löst das Restart event des Spiels aus
	 */
	protected void restart() {
		gameController.restart();
	}

	/**
	 * Die Methode die das aktuelle Spielelevel entgenimmt und als String zurueckgibt
	 * @return das aktuelle Spielelevel
	 */
	protected String levelToString() {
		Integer level = (int) gameController.getLevel();
		return level.toString();
	}
	
	/**
	 * die Methode die den Outputstring zurückgibt
	 * @return
	 */
	protected String astrocountToString() {
		Integer astroid = Astroid.getCounter();
		return astroid.toString();
	}
	/**
	 * 
	 * @param output der OutputString
	 * @param counterTime die Maximale Zeit der Outputanzeige in Frames
	 */
	public void outPutString(String output,int counterTime) {
		outputString=output;
		counterInt=counterTime;
	}
	protected String getOutPutString(){
		return outputString;
		
	}
}