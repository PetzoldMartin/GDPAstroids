package Input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Astroids.GameController;
import Astroids.SpaceShip;
import Astroids.Vector;
import Input.WhiteboardInAndOutPut.GameoverScreen;
import Shapes.Point;
import Shapes.Shape;

/**
 * Das Whiteboard Controllpanel Vers.2.0
 * 
 * @author Aismael
 * 
 */
public class WhiteboardInAndOutPut extends Thread implements
		MouseMotionListener {

	protected int LabelX = 10;// XPosition f�r Schriftausgabe
	protected int LabelY = 25;// YPoasition f�r Schriftausgabe
	protected boolean JspinnerActivate = false;
	private JFrame whiteBoard;// das Interne Whiteboard
	private JScrollPane whiteBoardInlet;// das Interne ScrollPane des
										// Whiteboards
	private GameController gameController;// der Interne Gamecontroller
	private InputController inputController;// der Interne InputController
	private JPanel AWTandJframeMergecontainer;// Der Container der das Interface
												// auf das Whiteboard bringt und
												// die Jframe und AWT Container
												// Plaziert
	private JPanel JFrameButtonContainer;// der Container f�r die JButtons
	private JPanel AWTOutputContainer;// der Container f�r die AWT Outputs
	private JPanel JspinnerContainer;// der Container f�r die Jspinner
	private InputControllPanelWindow inputControllPanelWindow;// das Interne
																// InputControllPanelWindow
	private JSpinner velocity;
	private JSpinner angle;
	private JPanel gamoverContainer;
	private GameoverScreen gameoverScreen;

	/**
	 * Der Konstruktor f�r das {@link WhiteboardInAndOutPut} der alle
	 * Componenten Initialisiert
	 * 
	 * @param gameController
	 *            der dem {@link WhiteboardInAndOutPut} �bergebene
	 *            {@link GameController}
	 * @param inputController
	 *            der dem {@link WhiteboardInAndOutPut} �bergebene
	 *            {@link InputController}
	 */
	public WhiteboardInAndOutPut(GameController gameController,
			InputController inputController) {
		this.gameController = gameController;
		this.inputController = inputController;

		System.out.println("Whiteboardinput started:\t" + this.getId());

		velocity = new JSpinner();
		angle = new JSpinner();

		inputControllPanelWindow = new InputControllPanelWindow("TastenInput");
		inputControllPanelWindow.setSize(300, 300);
		inputControllPanelWindow.addMouseWheelListener(inputController);
		inputControllPanelWindow.addMouseListener(inputController);
		inputControllPanelWindow.addMouseMotionListener(inputController);
		inputControllPanelWindow.addKeyListener(inputController);
		inputControllPanelWindow.requestFocus();
		/**
		 * die Initialisierung des Whiteboards
		 */
		whiteBoard = Shape.getWhiteBoard().getFrame();
		whiteBoard.addKeyListener(inputController);
		whiteBoard.addWindowListener(inputController);
		whiteBoard.setSize(
				gameController.getWindowX() * 2 + gameController.getAstroSize()
						* 2 - 16 + 300, gameController.getWindowY() * 2
						+ gameController.getAstroSize() * 2 + 4);

		/**
		 * die Initialisierung des Whiteboardpanels
		 */
		whiteBoardInlet = Shape.getWhiteBoard().getScrollPane();
		whiteBoardInlet.setCursor(new Cursor(0));
		whiteBoardInlet.addMouseWheelListener(inputController);
		whiteBoardInlet.addMouseListener(inputController);
		whiteBoardInlet.addMouseMotionListener(this);

		/**
		 * die Initialisierung des AWTandJframeMergecontainers
		 */
		AWTandJframeMergecontainer = new JPanel(new BorderLayout());
		AWTandJframeMergecontainer.addKeyListener(inputController);
		whiteBoard.add(BorderLayout.EAST, AWTandJframeMergecontainer);
		AWTandJframeMergecontainer.setPreferredSize(new Dimension(300, 600));
		AWTandJframeMergecontainer.add(inputControllPanelWindow,
				BorderLayout.CENTER);
		inputControllPanelWindow.setPreferredSize(new Dimension(300, 300));
		inputControllPanelWindow.setCursor(new Cursor(12));

		/**
		 * die Initialisierung des AWTOutputContainers
		 */
		AWTOutputContainer = new JPanel(new GridLayout(6, 1));
		buildAWTOutputContainer();
		AWTandJframeMergecontainer.add(AWTOutputContainer, BorderLayout.NORTH);
		AWTOutputContainer.setPreferredSize(new Dimension(300, 240));
		/**
		 * die Initialisierung des JFrameButtonContainers
		 */
		JFrameButtonContainer = new JPanel(new GridLayout(4, 0));
		buildJFrameButtonContainer();
		JFrameButtonContainer.setBackground(Color.black);
		AWTandJframeMergecontainer.add(JFrameButtonContainer,
				BorderLayout.SOUTH);
		AWTandJframeMergecontainer.setBackground(Color.black);
		/**
		 * die Initialisierung des JspinnerContainers
		 */
		JspinnerContainer = new JPanel(new FlowLayout());
		buildJspinnerContainer();
		JFrameButtonContainer.add(JspinnerContainer);
		JspinnerContainer.setVisible(true);
		JspinnerContainer.setVisible(false);
	}

	/**
	 * Methode f�r das adden der JspinnerContainerKomponenten
	 */
	private void buildJspinnerContainer() {
		JspinnerContainer.add(new JLabel("Velocity"));
		JspinnerContainer.add(velocity);
		JspinnerContainer.add(new JLabel("Angle"));
		JspinnerContainer.add(angle);
		velocity.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if ((Integer) velocity.getValue() <= gameController
						.getMaxSpeed()
						&& (Integer) velocity.getValue() >= -gameController
								.getMaxSpeed() / 2) {
					gameController.getSpaceShip().setVector(
							new Vector((Integer) velocity.getValue(),
									gameController.getSpaceShip().getVector()
											.getPhi()));
				} else {
					gameController.getSpaceShip().setVector(
							new Vector(gameController.getSpaceShip()
									.getVector().getAmount(), gameController
									.getSpaceShip().getVector().getPhi()));

				}
			}
		});
		angle.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				gameController.getSpaceShip()
						.setVector(
								new Vector(gameController.getSpaceShip()
										.getVector().getAmount(),
										(Integer) angle.getValue() + 180));

			}
		});

	}

	/**
	 * Methode f�r das adden der JFrameButtonContainerKomponenten
	 */
	private void buildJFrameButtonContainer() {
		JButton ControllChange = new JButton("Alternative Controll deactivate");
		JButton JspinnerControll = new JButton("Jspinner Activate");
		JButton cheat = new JButton("cheat Activate");
		ControllChange.addActionListener(new EffectActionListener(
				ControllChange) {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputController.getAlternativeControll()) {
					inputController.setAlternativeControll(false);
					((AbstractButton) getComponent())
							.setText("Alternative Controll activate");

				} else {
					inputController.setAlternativeControll(true);
					((AbstractButton) getComponent())
							.setText("Alternative Controll deactivate");

				}
				whiteBoard.requestFocus();
			};
		});
		JspinnerControll.addActionListener(new EffectActionListener(JspinnerControll) {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JspinnerActivate) {
					JspinnerActivate = false;
					((AbstractButton) getComponent())
							.setText("Jspinner Activate");
					JspinnerContainer.setVisible(false);
					velocity.setFocusable(false);
					angle.setFocusable(false);

				} else {
					JspinnerActivate = true;
					((AbstractButton) getComponent())
							.setText("Jspinner deActivate");
					JspinnerContainer.setVisible(true);
					velocity.setFocusable(true);
					angle.setFocusable(true);

				}
				whiteBoard.requestFocus();
			};
		});
		cheat.addActionListener(new EffectActionListener(cheat) {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameController.cheat) {
					((AbstractButton) getComponent()).setText("cheat Activate");
					gameController.setCheat(false);
				} else {

					gameController.setCheat(true);
					((AbstractButton) getComponent())
							.setText("cheat deActivate");
				}
				whiteBoard.requestFocus();

			}
		});
		JFrameButtonContainer.add(ControllChange);
		JFrameButtonContainer.add(JspinnerControll);
		JFrameButtonContainer.add(cheat);
		cheat.setBackground(Color.black);

	}

	/**
	 * Methode f�r das adden der AWTOutputContainerKomponenten
	 */
	private void buildAWTOutputContainer() {
		AWTOutputContainer.add(new PointsOutput("Points"));
		AWTOutputContainer.add(new BufferedAWTWindow("Coords") {

			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(new Color(255, 255, 255));
				g.drawString(super.name, LabelX, LabelY);
			}

		});
		AWTOutputContainer.add(new XOutput("Xcoord"));
		AWTOutputContainer.add(new YOutput("Ycoord"));
		AWTOutputContainer.add(new speedOutPut("Speed"));
		AWTOutputContainer.add(new accelerationOutput("acceleration"));
	}

	/**
	 * die Methode zum Neuzeichnen der AWTOutputContainerKomponenten
	 */
	public void Outputrefresh() {
		for (Component AWTComponent : AWTOutputContainer.getComponents()) {
			AWTComponent.repaint();
		}
		JFormattedTextField textFieldAngle = ((JSpinner.DefaultEditor) angle
				.getEditor()).getTextField();
		JFormattedTextField textFieldVelocity = ((JSpinner.DefaultEditor) velocity
				.getEditor()).getTextField();
		textFieldAngle.setText(accelerationToString());
		textFieldVelocity.setText(speedToString());
		if(gameController.playerDieEvent()){
			gameoverSight();
		}
	
	}

	private void gameoverSight() {
		whiteBoardInlet.setVisible(false);
		AWTandJframeMergecontainer.setVisible(false);
		gameoverScreen = new GameoverScreen("Gameover");
		gameoverScreen.setSize(gameController.getWindowX()*3, gameController.getWindowY()*2-100);
		gamoverContainer=new JPanel(new BorderLayout());
		gamoverContainer.add(gameoverScreen);
		whiteBoard.add(BorderLayout.WEST,gamoverContainer);
		gamoverContainer.add(gameoverScreen,
				BorderLayout.CENTER);
		JButton restart = new JButton("Restart");
		restart.setFont(new Font("Serif", Font.PLAIN, 100));
		gamoverContainer.add(BorderLayout.PAGE_END,restart);
		restart.setPreferredSize(new Dimension(gameController.getWindowX()*3, 100));
		gamoverContainer.setBackground(Color.black);
		whiteBoardInlet.requestFocus();
	}

	/**
	 * Die Methode wenn die Maus gedr�ckt ist und Bewegt wird in der
	 * Alternativen Steuerung
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (this.inputController.isAlternativeControll()) {
			mouseControlWhiteboard(arg0);
		}

	}

	/**
	 * Die Methode wenn die Maus Bewegt wird in der Alternativen Steuerung
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (this.inputController.isAlternativeControll()) {
			mouseControlWhiteboard(arg0);
		}

	}

	/**
	 * die Methode zur Berechnung des Raumschiffbewegungsvectors bei der
	 * Alternativeb Steuerung
	 * 
	 * @param arg0
	 *            {@link MouseEvent}
	 * 
	 * @return der Bewegungsvector f�r das {@link SpaceShip}
	 */
	private void mouseControlWhiteboard(MouseEvent arg0) {

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
	 * Die Innere Klasse f�r einen ActionListener der die AWT oder Jframe
	 * Komponente ver�nden kann
	 * 
	 * @author Aismael
	 * 
	 */
	abstract class EffectActionListener implements ActionListener {
		private Component component;

		public Component getComponent() {
			return component;
		}

		public EffectActionListener(Component component) {
			this.component = component;

		}

		@Override
		public abstract void actionPerformed(ActionEvent e);

	}

	/**
	 * Die Innere Klasse f�r Das SpeedOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
	class speedOutPut extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public speedOutPut(String name) {
			super(name);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString("Speed: " + speedToString(), LabelX, LabelY);
		}

	}

	/**
	 * Die Innere Klasse f�r Das accelerationOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
	class accelerationOutput extends BufferedAWTWindow {

		// TODO from 270 to 360 degrees right output
		private static final long serialVersionUID = 1L;

		public accelerationOutput(String name) {
			super(name);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString("Angle: " + accelerationToString(), LabelX, LabelY);
		}

	}

	/**
	 * Die Innere Klasse f�r Das XcoordinatenOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
	class XOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public XOutput(String name) {
			super(name);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString("X:" + xToString(), LabelX, LabelY);
		}

	}

	/**
	 * Die Innere Klasse f�r Das YcoordinatenOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
	class YOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public YOutput(String name) {
			super(name);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString("Y:" + yToString(), LabelX, LabelY);
		}

	}

	/**
	 * Die Innere Klasse f�r Das SpielpunkteOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
	class PointsOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public PointsOutput(String name) {
			super(name);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString("Points:" + PointsToString(), LabelX, LabelY);
		}

	}

	class InputControllPanelWindow extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public InputControllPanelWindow(String name) {
			super(name);
		}

		@Override
		/**
		 * Die Paintmethode von InputControllPanelWindow  die einen Kreis und die Orientierungslinien zeichnet
		 */
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(0, 0, 255));
			g.fillOval(70, 70, 160, 160);
			g.setColor(new Color(255, 0, 0));
			g.drawLine(0, 150, 300, 150);
			g.drawLine(150, 0, 150, 300);

		}

	}

	class GameoverScreen extends BufferedAWTWindow {

		private static final long serialVersionUID = 12L;

		public GameoverScreen(String name) {
			super(name);
		}

		@Override
		/**
		 * Die Paintmethode von InputControllPanelWindow  die einen Kreis und die Orientierungslinien zeichnet
		 */
		public void paint(Graphics g) {
			super.paint(g);
			this.setFont(new Font("Serif", Font.PLAIN, 240));
			g.setColor(new Color(255, 0, 0));
			g.drawString("GAMOVER", 20, gameController.getWindowY());
			

		}

	}
	private String accelerationToString() {
		int ac2 = (int) gameController.getSpaceShip().getVector().getPhi();
		Integer acceleration = ac2;

		return acceleration.toString();
	}

	private String PointsToString() {
		Integer points = (int) gameController.getHealth();
		return points.toString();

	}

	private String yToString() {
		Integer ycoord = (int) gameController.getSpaceShip().getCenterPoint()
				.getY();
		return ycoord.toString();

	}

	private String xToString() {
		Integer xcoord = (int) gameController.getSpaceShip().getCenterPoint()
				.getX();
		return xcoord.toString();

	}

	private String speedToString() {
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
}