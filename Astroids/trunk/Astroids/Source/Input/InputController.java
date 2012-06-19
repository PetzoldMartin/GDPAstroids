package Input;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Astroids.GameController;
import Astroids.Vector;
import Shapes.Point;

/**
 * 
 * @author Aismael Die Listener Klasse die die Tastatureingabe und den Mausinput
 *         Entgegenimmt und Auswertet
 * 
 */
public class InputController extends Thread implements KeyListener,
		WindowListener, MouseWheelListener, MouseListener, MouseMotionListener {
	InputControllPanelWindow inputControllPanelWindow;
	private double keyAmount = 0;// der Interne Speicher für KeyAmount
	private double keyPhi = 0;// der Interne Speicher für KeyPhi
	private GameController gameController;// der intern benutzte Gamecontroller
	private WhiteboardInAndOutPut whiteboardInAndOutPut;// der intern benutzte
														// WhiteboardInAndOutPut
	boolean alternativeControll = true;// boolean für einschalten und
										// ausschalten der Alternativen
										// Steuerung

	/**
	 * Der Konstruktor des InputControllers
	 * 
	 * @param gameController
	 */
	public InputController(GameController gameController) {

		this.gameController = gameController;
		System.out.println("InputController started:\t" + this.getId());
		inputControllPanelWindow = new InputControllPanelWindow("TastenInput",
				gameController);
		whiteboardInAndOutPut = new WhiteboardInAndOutPut(gameController, this,
				inputControllPanelWindow);

		inputControllPanelWindow.setSize(300, 300);
		inputControllPanelWindow.addMouseWheelListener(this);
		inputControllPanelWindow.addMouseListener(this);
		inputControllPanelWindow.addMouseMotionListener(this);
		inputControllPanelWindow.addKeyListener(this);
		inputControllPanelWindow.requestFocus();

	}

	/**
	 * Die Steurungsübergabe des ControllPanels
	 * 
	 * @param arg0
	 * @return Vector
	 */
	private Vector MouseControl(MouseEvent arg0) {

		Vector silence = new Vector(new Point(arg0.getX() - 150,
				arg0.getY() - 150));
		Vector s2 = new Vector(0, 0);
		if (silence.getAmount() < 80) {
			s2 = new Vector(silence.getAmount() / 8, -silence.getPhi());
		} else {

			s2 = new Vector(10, -silence.getPhi());

		}
		return s2;
	}

	/**
	 * Die Methode die die AWT OUTputKomponenten neu Zeichnet
	 */
	public void Interfacerefresh() {
		inputControllPanelWindow.repaint();
		whiteboardInAndOutPut.Outputrefresh();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// events wenn eine taste Gedrueckt wird
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Doubblekeysfail
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_LEFT:
			keyPhi = -gameController.getKeyRotationAngel();
			break;
		case KeyEvent.VK_A:
			keyPhi = -gameController.getKeyRotationAngel();
			break;
		case KeyEvent.VK_RIGHT:
			keyPhi = +gameController.getKeyRotationAngel();
			break;
		case KeyEvent.VK_D:
			keyPhi = +gameController.getKeyRotationAngel();
			break;
		case KeyEvent.VK_UP:
			keyAmount = gameController.getKeyAcelleration();
			break;
		case KeyEvent.VK_W:
			keyAmount = gameController.getKeyAcelleration();
			break;
		case KeyEvent.VK_DOWN:
			keyAmount = -gameController.getKeyAcelleration();
			break;
		case KeyEvent.VK_S:
			keyAmount = -gameController.getKeyAcelleration();
			break;
		case KeyEvent.VK_ENTER:
			gameController.setTestflag(true);
			break;
		case KeyEvent.VK_PAUSE:
			gameController.pause();
			break;
		case KeyEvent.VK_SPACE:
			gameController.spaceKey();
		default:
			break;
		}

	}

	// events beim Tastenloslassen
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_LEFT:
			keyPhi = 0;
			break;
		case KeyEvent.VK_A:
			keyPhi = 0;
			break;
		case KeyEvent.VK_RIGHT:
			keyPhi = 0;
			break;
		case KeyEvent.VK_D:
			keyPhi = 0;
			break;
		case KeyEvent.VK_UP:
			keyAmount = 0;
			break;
		case KeyEvent.VK_W:
			keyAmount = 0;
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_S:
			keyAmount = 0;
			break;
		case KeyEvent.VK_ENTER:
			gameController.setTestflag(false);
			break;
		default:
			break;
		}

	}

	/**
	 * 
	 * @return den KeyAmount
	 */
	public double getKeyAmount() {
		return keyAmount;
	}

	/**
	 * 
	 * @return den KeyPhi
	 */
	public double getKeyPhi() {
		return keyPhi;
	}

	/**
	 * Die Methode die Astroids ausführt wenn das Fenster Aktiviert ist
	 */
	@Override
	public synchronized void windowActivated(WindowEvent e) {
		gameController.setWindowActivated(true);

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	/**
	 * Die Methode die Astroids beendet wenn das Fenster geschlossen wird
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);

	}

	/**
	 * Die Methode die Astroids anhält wenn das Fenster deaktiviert ist
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		gameController.setWindowActivated(false);

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	/**
	 * Die Methode die die Spaceshipbewegungsberechnung bei Mausbewegung
	 * ausführt
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {

		gameController.getSpaceShip().setVector(MouseControl(arg0));

	}

	/**
	 * Die Methode die die Spaceshipbewegungsberechnung bei Mausbewegung und
	 * gedrückter Maustaste ausführt
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		gameController.getSpaceShip().setVector(MouseControl(arg0));

	}

	/**
	 * Die Methode die Bei Mausclick das spaceKeyevent auslöst
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		gameController.spaceKey();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	/**
	 * Die Methode die bei gepresster Maus das spaceKeyevent auslöst
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		gameController.spaceKey();

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}

	/**
	 * 
	 * @return AlternativeControll
	 */
	public boolean isAlternativeControll() {
		return alternativeControll;
	}

	/**
	 * 
	 * @param AlternativeControll
	 */
	public void setAlternativeControll(boolean AlternativeControll) {
		this.alternativeControll = AlternativeControll;
	}

	/**
	 * 
	 * @return AlternativeControll
	 */
	public boolean getAlternativeControll() {
		return alternativeControll;
	}

}