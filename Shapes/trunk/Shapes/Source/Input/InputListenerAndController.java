package Input;

import java.awt.event.*;

import Astroids.OldGameController;
import Astroids.Vector;


public class InputListenerAndController extends javax.swing.JFrame implements
		java.awt.event.KeyListener,Runnable {
	private static final long serialVersionUID = 1L;
	//	private InputListener InputListener;
	public long timeSlice = OldGameController.globalFrameTime; // Millisekunden, wie oft soll die Bewegung
	// abgefragt werden
	private static boolean left = false;
	private static  boolean right = false;
	private static  boolean up = false;
	private static  boolean down = false;
	public static Vector Movement = new Vector(0,0);
//	private boolean stopFlag = false;
	public InputListenerAndController() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(200, 200);
		this.setVisible(true);
		addKeyListener(this);
	}

	public void keyTyped(java.awt.event.KeyEvent e) {
		// do nothing
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT | e.getKeyCode() == KeyEvent.VK_A)
			left = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT | e.getKeyCode() == KeyEvent.VK_D)
			right = true;
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			up=true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN | e.getKeyCode() == KeyEvent.VK_S)
			down = true;
	}

	public void keyReleased(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT | e.getKeyCode() == KeyEvent.VK_A)
			left = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT | e.getKeyCode() == KeyEvent.VK_D)
			right = false;
		if (e.getKeyCode() == KeyEvent.VK_UP | e.getKeyCode() == KeyEvent.VK_W)
			up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN | e.getKeyCode() == KeyEvent.VK_S)
			down = false;
	}

	/*
	 * Ruft alle timeSlice Millisekunden die abstrakte Methode doMovement auf
	 */
	@Override
	public void run() {
		InputListenerAndController proggi = new InputListenerAndController() {
		};
        WindowListener wl = new WindowAdapter () {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            };
        proggi.addWindowListener(wl);

			}


	public static   Vector doMovement()
	{

		if (left == true) {
			Movement.changeDirection(6);
			System.out.println("left");

		}
		if (right == true) {
			Movement.changeDirection(-6);
			System.out.println("right");

		}
		if (up == true) {
			Movement.changeSpeed(0.1);
			System.out.println("up");

		}
		if (down == true) {
			Movement.changeSpeed(-0.1);
			System.out.println("down");

		}
		return Movement;
	}

}

