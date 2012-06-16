package Input;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Astroids.GameController;
import Astroids.Vector;
import Shapes.Point;
import Shapes.Shape;

public class WhiteboardInAndOutPut extends Thread implements MouseMotionListener {

	private JFrame WhiteBoard;
	private JScrollPane WhiteboardInlet;
	private GameController gameController;
	private InputController inputController;
	private JPanel AWTandJframeMergecontainer;
	private JPanel JFrameButtonContainer;
	private JPanel AWTOutputContainer;
	private JPanel JspinnerContainer;

	public WhiteboardInAndOutPut(GameController gameController,
			InputController inputController, InputWindow inputWindow) {
		this.gameController = gameController;
		this.inputController = inputController;
		
		System.out.println("Whiteboardinput started:\t" + this.getId());
		
		WhiteBoard = Shape.getWhiteBoard().getFrame();
		WhiteBoard.addKeyListener(inputController);
		WhiteBoard.addWindowListener(inputController);
		WhiteBoard.setSize(
				gameController.getWindowX() * 2 + gameController.getAstroSize()
				* 2 - 16 + 300, gameController.getWindowY() * 2
				+ gameController.getAstroSize() * 2 + 4);
		
		WhiteboardInlet = Shape.getWhiteBoard().getScrollPane();
		WhiteboardInlet.setCursor(new Cursor(1));
		WhiteboardInlet.addMouseWheelListener(inputController);
		WhiteboardInlet.addMouseListener(inputController);
		WhiteboardInlet.addMouseMotionListener(this);
		
		AWTandJframeMergecontainer = new JPanel(new BorderLayout());
		WhiteBoard.add(BorderLayout.EAST, AWTandJframeMergecontainer);
		AWTandJframeMergecontainer.setPreferredSize(new Dimension(300, 800));
		AWTandJframeMergecontainer.add(inputWindow, BorderLayout.CENTER);
		inputWindow.setPreferredSize(new Dimension(300, 300));
		inputWindow.setCursor(new Cursor(12));
		
		JspinnerContainer=new JPanel(new GridLayout(4, 1));
		WhiteBoard.add(BorderLayout.SOUTH,JspinnerContainer);
		
		AWTOutputContainer = new JPanel(new GridLayout(1, 6));
		AWTandJframeMergecontainer.add(AWTOutputContainer,
				BorderLayout.NORTH);
		
		JFrameButtonContainer = new JPanel(new GridLayout(1, 3));
		AWTandJframeMergecontainer.add(JFrameButtonContainer,
				BorderLayout.SOUTH);

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
		Vector s2 = new Vector(0, 0);
		if (silence.getAmount() < 160) {

			s2 = new Vector(silence.getAmount() / 16, -silence.getPhi());

		} else {

			s2 = new Vector(10, -silence.getPhi());

		}
		return s2;
	}

}