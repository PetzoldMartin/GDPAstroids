package Input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class WhiteboardInAndOutPut extends Thread implements
		MouseMotionListener {

	private JFrame WhiteBoard;
	private JScrollPane WhiteboardInlet;
	private GameController gameController;
	private InputController inputController;
	private JPanel AWTandJframeMergecontainer;
	private JPanel JFrameButtonContainer;
	private JPanel AWTOutputContainer;
	private JPanel JspinnerContainer;
	private int LabelX = 10;
	private int LabelY = 25;

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

		JspinnerContainer = new JPanel(new GridLayout(4, 1));
		buildJspinnerContainer();
		WhiteBoard.add(BorderLayout.SOUTH, JspinnerContainer);

		AWTOutputContainer = new JPanel(new GridLayout(6, 1));
		buildAWTOutputContainer();
		AWTandJframeMergecontainer.add(AWTOutputContainer, BorderLayout.NORTH);
		AWTOutputContainer.setPreferredSize(new Dimension(300, 240));

		JFrameButtonContainer = new JPanel(new GridLayout(2, 0));
		buildJFrameButtonContainer();
		AWTandJframeMergecontainer.add(JFrameButtonContainer,
				BorderLayout.SOUTH);

	}

	private void buildJspinnerContainer() {
		// TODO Auto-generated method stub
		
	}

	private void buildJFrameButtonContainer() {
		JButton ControllChange= new JButton("Alternative Controll activate");
		JButton test = new JButton("test");
		ControllChange.addActionListener(new EffectActionListener(ControllChange) {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputController.getOutput()) {
					inputController.setOutput(false);
					 getButton().setText("Alternative Controll activate");
				} else {
					inputController.setOutput(true);
					getButton().setText("Alternative Controll deactivate");
				}

			};});
		JFrameButtonContainer.add(ControllChange);
		JFrameButtonContainer.add(test);
		
	}

	private void buildAWTOutputContainer() {
		AWTOutputContainer.add(new PointsOutput("Points", this.gameController));
		AWTOutputContainer.add(new BufferedAWTWindow("Coord",
				this.gameController) {

			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(new Color(255, 255, 255));
				g.drawString(super.name, LabelX, LabelY);
			}

		});
		AWTOutputContainer.add(new XOutput("Xcoord", this.gameController));
		AWTOutputContainer.add(new YOutput("Ycoord", this.gameController));
		AWTOutputContainer.add(new speedOutPut("Speed", this.gameController));
		AWTOutputContainer.add(new accelerationOutput("acceleration",
				this.gameController));
	}

	public void Outputrefresh() {
		for (Component AWTComponent : AWTOutputContainer.getComponents()) {
			AWTComponent.repaint();
		}

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

	public void Interfacerefresh() {
		inputController.Interfacerefresh();

	}

	    abstract class EffectActionListener implements ActionListener{
		private JButton button;

		public JButton getButton() {
			return button;
		}

		public EffectActionListener(JButton button){
			this.button=button;
			
		}

		@Override
		public abstract void actionPerformed(ActionEvent e) ;

		
	}
	
	class speedOutPut extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public speedOutPut(String name, GameController gameController) {
			super(name, gameController);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString(speedToString(), LabelX, LabelY);
		}

		private String speedToString() {
			Double speed = gameController.getSpaceShip().getVector()
					.getAmount();
			String speedString = "0";
			if (speed < 0) {
				String speedString0 = "Speed: " + speed.toString();
				speedString = speedString0.substring(0, 11);
			}
			if (speed >= 0) {
				String speedString0 = "Speed: " + speed.toString();
				speedString = speedString0.substring(0, 10);

			}
			return speedString;
		}

	}

	class accelerationOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public accelerationOutput(String name, GameController gameController) {
			super(name, gameController);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString(accelerationToString(), LabelX, LabelY);
		}

		private String accelerationToString() {
			int ac2 = (int) gameController.getSpaceShip().getVector().getPhi() - 90;
			Integer acceleration = Math.abs(ac2 % 360);
			String accelerationString = "Angle: " + acceleration.toString();
			return accelerationString;
		}

	}

	class XOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public XOutput(String name, GameController gameController) {
			super(name, gameController);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString(xToString(), LabelX, LabelY);
		}

		private String xToString() {
			Integer xcoord = (int) gameController.getSpaceShip()
					.getCenterPoint().getX();
			String xString = "X:" + xcoord.toString();
			return xString;

		}
	}

	class YOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public YOutput(String name, GameController gameController) {
			super(name, gameController);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString(yToString(), LabelX, LabelY);
		}

		private String yToString() {
			Integer ycoord = (int) gameController.getSpaceShip()
					.getCenterPoint().getY();
			String yString = "Y:" + ycoord.toString();
			return yString;

		}
	}

	class PointsOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public PointsOutput(String name, GameController gameController) {
			super(name, gameController);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString(PointsToString(), LabelX, LabelY);
		}

		private String PointsToString() {
			// TODO Gamepoints in Gamecotroller or whereever
			Integer points = (int) 0;
			String pointsString = "Points:" + points.toString();
			return pointsString;

		}
	}

}