package Input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Shapes.Point;

import Astroids.GameController;
import Astroids.Vector;

public class InputController extends Thread implements KeyListener,
		WindowListener, MouseWheelListener, MouseListener, MouseMotionListener {
	private Frame inputWindow;
	private double keyAmount = 0;
	private double keyPhi = 0;
	private double pendel = 0.21;
	static GameController gameController;

	public InputController(GameController gameController) {
		
		System.out.println("InputController started:\t" + this.getId());
		this.gameController = gameController;
		
		inputWindow = new InputWindow("TastenInput", this.gameController);
		Font l = new Font("Arial", Font.BOLD, 25);
		inputWindow.setSize(300, 300);
		inputWindow.setLocation(900, 100);
		inputWindow.setVisible(true);
		inputWindow.requestFocus();
		inputWindow.addKeyListener(this);
		inputWindow.addWindowListener(this);
		inputWindow.addMouseWheelListener(this);
		inputWindow.addMouseListener(this);
		inputWindow.addMouseMotionListener(this);
		inputWindow.setBackground(Color.BLACK);
		inputWindow.setFont(l);

	}

	void paint(Graphics g) {
		g.drawOval(1, 1, 100, 100);
	}

	public void keyTyped(KeyEvent e) {
	}

	// events wenn eine taste Gedr�ckt wird
	public void keyPressed(KeyEvent e) {
		// TODO Stop with Windowclose
		// TODO Doubblekeysfail
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				| e.getKeyCode() == KeyEvent.VK_A)
			keyPhi = -gameController.getKeyRotationAngel();
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				| e.getKeyCode() == KeyEvent.VK_D)
			keyPhi = +gameController.getKeyRotationAngel();
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			keyAmount = gameController.getKeyAcelleration();
		if (e.getKeyCode() == KeyEvent.VK_DOWN
				| e.getKeyCode() == KeyEvent.VK_S)
			keyAmount = -gameController.getKeyAcelleration();
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			gameController.makeTest();
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			gameController.spaceKey();
		if (e.getKeyCode() == KeyEvent.VK_PAUSE)
			gameController.pause();

	}

	// events beim Tastenloslassen
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				| e.getKeyCode() == KeyEvent.VK_A)
			keyPhi = 0;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				| e.getKeyCode() == KeyEvent.VK_D)
			keyPhi = 0;
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			keyAmount = 0;
		if (e.getKeyCode() == KeyEvent.VK_DOWN
				| e.getKeyCode() == KeyEvent.VK_S)
			keyAmount = 0;

	}

	public double getKeyAmount() {
		return keyAmount;
	}

	public double getKeyPhi() {
		return keyPhi;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		gameController.pause();

	}

	@Override
	public void windowClosed(WindowEvent e) {
		

	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		gameController.pause(true);

	}

	@Override
	public void windowDeiconified(WindowEvent e) {


	}

	@Override
	public void windowIconified(WindowEvent e) {


	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		Vector silence = new Vector(new Point(arg0.getX() - 150,
				arg0.getY() - 150));
		if (silence.getAmount() < 20) {
			keyPhi = 0;
			keyAmount = 0;
		} else {
			if(arg0.getY() < 170&&arg0.getY() > 130)
			{
				keyAmount = 0;
			}
			if (arg0.getY() > 170) {
				keyAmount = -gameController.getKeyAcelleration();
			}
			if (arg0.getY() < 130) {
				keyAmount = +gameController.getKeyAcelleration();
			}
			if (arg0.getX() > 150) {
				keyPhi = +gameController.getKeyRotationAngel();
			}
			if (arg0.getX() < 150) {
				keyPhi = -gameController.getKeyRotationAngel();
			}

		}

	}

	public void Interfacerefresh() {
		inputWindow.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		System.out.println(arg0);
		
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
		
	}

}