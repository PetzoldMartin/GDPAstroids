package GUI;

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
/**
 * Ein Listener der viele Listener zusammenfasst
 * um eine besser Uebersicht zu gewährleisten und Renundanten code bei mehrfacher verwendung von geleichem Listeneraufgaben zu verhindern
 * implementiert den {@link KeyListener},den {@link WindowListener},den {@link MouseWheelEvent}, den {@link MouseListener} und den {@link MouseMotionListener}
 * @author Aismael
 *
 */
public class BigListener implements KeyListener, WindowListener,
		MouseWheelListener, MouseListener, MouseMotionListener {

	private double keyAmount = 0;// der Interne Speicher für KeyAmount
	private double keyPhi = 0;// der Interne Speicher für KeyPhi
	private GameController gameController;// der intern benutzte Gamecontroller
	boolean alternativeControll = false;// boolean für einschalten und
	// ausschalten der Alternativen
	// Steuerung
	private GUIController gUIController;//der interne GUIController

	/**
	 * Der Konstruktor des InputControllers
	 * 
	 * @param gameController {@link GameController}
	 * @param gUIController {@link GUIController}
	 */
	public BigListener(GameController gameController,
			GUIController gUIController) {
		this.gameController = gameController;
		this.gUIController = gUIController;

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/** events wenn eine taste Gedrueckt wird
	 * @param e {@link KeyEvent}
	 */
	@Override
	public void keyPressed(KeyEvent e) {
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

	/** events beim Tastenloslassen
	 * @param e {@link KeyEvent}
	 */
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
	 * @param e {@link WindowEvent}
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
	 * @param e {@link WindowEvent}
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);

	}

	/**
	 * Die Methode die Astroids anhält wenn das Fenster deaktiviert ist
	 * @param e {@link WindowEvent}
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
	 * @param arg0 {@link MouseEvent}
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {

		gUIController.mouseControl(arg0);

	}

	/**
	 * Die Methode die die Spaceshipbewegungsberechnung bei Mausbewegung und
	 * gedrückter Maustaste ausführt
	 * @param arg0 {@link MouseEvent}
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		gUIController.mouseControl(arg0);

	}

	/**
	 * Die Methode die Bei Mausclick das spaceKeyevent auslöst
	 * @param arg0 {@link MouseEvent}
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
	 * @param arg0 {@link MouseEvent}
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
	 * gibt den Zusatnd der Alternativen Kontrolle zurück
	 * @return AlternativeControll
	 */
	protected boolean isAlternativeControll() {
		return alternativeControll;
	}

	/**
	 * setzt die Alternative Kontrolle
	 * @param AlternativeControll
	 */
	protected void setAlternativeControll(boolean AlternativeControll) {
		this.alternativeControll = AlternativeControll;
	}

	

}
