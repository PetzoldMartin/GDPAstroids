package Input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Astroids.GameController;


public class InputController extends Thread implements KeyListener, WindowListener,MouseWheelListener,MouseListener {
	private Frame inputWindow;
	private double keyAmount = 0;
	private double keyPhi = 0;
	static GameController gameController;

	public InputController(GameController gameController) {

		this.gameController = gameController;
		inputWindow = new InputWindow("Tasteninput",this.gameController);
		Font l = new Font("Arial", Font.BOLD, 40);
		inputWindow.setSize(300, 300);
		inputWindow.setLocation(900, 100);
		inputWindow.setVisible(true);
		inputWindow.requestFocus();
		inputWindow.addKeyListener(this);
		inputWindow.addWindowListener(this);
		inputWindow.addMouseWheelListener(this);
		inputWindow.addMouseListener(this);
		inputWindow.setBackground(Color.BLACK);
		inputWindow.setFont(l);
		
	}
	
	void paint(Graphics g)
	{
		g.drawOval(1,1,100,100);
	}
	
	public void keyTyped(KeyEvent e) {
	}

	// events wenn eine taste Gedrückt wird
	public void keyPressed(KeyEvent e) {
		//TODO Stop with Windowclose
		//TODO Doubblekeysfail
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
		inputWindow.repaint();
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
		gameController.pause(false);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		if(arg0.getWheelRotation()==-1)
		{
			keyAmount = gameController.getKeyAcelleration();
		}
		if(arg0.getWheelRotation()==1)
		{
			keyAmount = -gameController.getKeyAcelleration();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
//		if(arg0.getX()>150){
//			keyPhi = -gameController.getKeyRotationAngel();
//		}
//		if(arg0.getX()<150){
//			keyPhi = +gameController.getKeyRotationAngel();
//		}
//		System.out.println(arg0);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}