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
	private JScrollPane WhiteboardInlet;
	private GameController gameController;
	private InputController inputController;

	public Whiteboardinput(GameController gameController,
			InputController inputController) {
		this.gameController = gameController;
		this.inputController = inputController;
		System.out.println("Whiteboardinput started:\t" + this.getId());
		WhiteboardInlet = Shape.getWhiteBoard().getScrollPane();
		WhiteBoard = Shape.getWhiteBoard().getFrame();
		WhiteboardInlet.setCursor(new Cursor(0));
		WhiteBoard.addKeyListener(inputController);
		WhiteBoard.addWindowListener(inputController);
		WhiteboardInlet.addMouseWheelListener(inputController);
		WhiteboardInlet.addMouseListener(inputController);
		WhiteboardInlet.addMouseMotionListener(this);
		WhiteBoard.setSize(
				gameController.getWindowX() * 2 + gameController.getAstroSize()
						* 2 - 16, gameController.getWindowY() * 2
						+ gameController.getAstroSize() * 2 + 4);

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (this.inputController.isOutput()) {
			gameController.getSpaceShip().setVector(
					MouseControlWhiteboard(arg0));
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (this.inputController.isOutput()) {
			gameController.getSpaceShip().setVector(
					MouseControlWhiteboard(arg0));
		}

	}

	private Vector MouseControlWhiteboard(MouseEvent arg0) {

		Vector silence = new Vector(new Point(arg0.getX()
				- gameController.getWindowX()
				- gameController.getSpaceShip().getCenterPoint().getX(),
				arg0.getY()
						+ (-gameController.getWindowY() + gameController
								.getSpaceShip().getCenterPoint().getY())));
		Vector s2 = new Vector(new Point(0, 0));
		if (silence.getAmount() < 160) {
			if (silence.getX() > 0) {
				s2 = new Vector(silence.getAmount() / 16, -silence.getPhi());
			}
			if (silence.getX() < 0) {
				s2 = new Vector(silence.getAmount() / 16,
						(180 - silence.getPhi()));
			}
		} else {
			if (silence.getX() > 0) {
				s2 = new Vector(10, -silence.getPhi());
			}
			if (silence.getX() < 0) {
				s2 = new Vector(10, (180 - silence.getPhi()));
			}

		}
		// System.out.println(s2.getAmount() + "    " +
		// s2.getPhi()+"  "+silence.getX()+"  "+silence.getY());
		return s2;
	}

}