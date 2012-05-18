package Input;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Astroids.GameController;
import Astroids.Vector;
import Shapes.Point;
import Shapes.Shape;

public class Whiteboardinput extends Thread implements MouseMotionListener {
	private JFrame WhiteBoard;
	private JScrollPane mousy;
	private GameController gameController;

	public Whiteboardinput(GameController gameController,
			InputController inputController) {
		this.gameController = gameController;
		System.out.println("Whiteboardinput started:\t" + this.getId());
		mousy = Shape.getWhiteBoard().getScrollPane();
		WhiteBoard = Shape.getWhiteBoard().getFrame();
		mousy.setCursor(new Cursor(1));
		WhiteBoard.addKeyListener(inputController);
		WhiteBoard.addWindowListener(inputController);
		mousy.addMouseWheelListener(inputController);
		mousy.addMouseListener(inputController);
		mousy.addMouseMotionListener(this);

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		gameController.getSpaceShip().setVector(MouseControl(arg0));
//		MouseControl(arg0);

	}

	private Vector MouseControl(MouseEvent arg0) {

		Vector silence = new Vector(
				new Point(
						arg0.getX()
								- 389
								- gameController.getSpaceShip()
										.getCenterPoint().getX(), arg0.getY()
								+(- 278
								+ gameController.getSpaceShip()
										.getCenterPoint().getY())));
		Vector s2 = new Vector(new Point(0, 0));
		if (silence.getAmount() < 80) {
			if (silence.getX() > 0) {
				s2 = new Vector(silence.getAmount() / 8, -silence.getPhi());
			}
			if (silence.getX() < 0) {
				s2 = new Vector(silence.getAmount() / 8,
						(180 - silence.getPhi()));
			}
		} else {
			if (silence.getX() > 0) {
				s2 = new Vector(10, -silence.getPhi());
			}
			if (silence.getX() < 0) {
				s2 = new Vector(10,
						(180 - silence.getPhi()));
			}
			
		}
//		System.out.println(s2.getAmount() + "    " + s2.getPhi()+"  "+silence.getX()+"  "+silence.getY());
		return s2;
	}

}