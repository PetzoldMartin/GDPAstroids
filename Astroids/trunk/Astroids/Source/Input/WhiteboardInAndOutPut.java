package Input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
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
	private double xsize;
	private double ysize;

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

		inputControllPanelWindow = new InputControllPanelWindow("TastenInput",
				gameController);
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
		xsize = Shape.getWhiteBoard().getScrollPane().getSize().getWidth() / 2;
		ysize = Shape.getWhiteBoard().getScrollPane().getSize().getHeight() / 2;
	}

	/**
	 * Methode f�r das adden der JspinnerContainerKomponenten
	 */
	private void buildJspinnerContainer() {
		velocity = new JSpinner();
		angle = new JSpinner();
		JspinnerContainer.add(new JLabel("Velocity"));
		JspinnerContainer.add(velocity);
		JspinnerContainer.add(new JLabel("Angle"));
		JspinnerContainer.add(angle);
		velocity.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if ((Integer) velocity.getValue() <= 10) {
					gameController.getSpaceShip().setVector(
							new Vector((Integer) velocity.getValue(),
									gameController.getSpaceShip().getVector()
											.getPhi()));
				} else {
					gameController.getSpaceShip().setVector(
							new Vector(10, gameController.getSpaceShip()
									.getVector().getPhi()));

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
		JButton test = new JButton("Jspinner Activate");
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
							.setText("Alternative Controll deActivate");
					
				}
				whiteBoard.requestFocus();
			};
		});
		test.addActionListener(new EffectActionListener(test) {

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
				}else{

					gameController.setCheat(true);
					((AbstractButton) getComponent())
							.setText("cheat deActivate");
				}
				whiteBoard.requestFocus();

			}
		});
		JFrameButtonContainer.add(ControllChange);
		JFrameButtonContainer.add(test);
		JFrameButtonContainer.add(cheat);
		cheat.setBackground(Color.black);

	}

	/**
	 * Methode f�r das adden der AWTOutputContainerKomponenten
	 */
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

	/**
	 * die Methode zum Neuzeichnen der AWTOutputContainerKomponenten
	 */
	public void Outputrefresh() {
		for (Component AWTComponent : AWTOutputContainer.getComponents()) {
			AWTComponent.repaint();
		}

	}

	/**
	 * Die Methode wenn die Maus gedr�ckt ist und Bewegt wird in der
	 * Alternativen Steuerung
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (this.inputController.isAlternativeControll()) {
			gameController.getSpaceShip().setVector(
					MouseControlWhiteboard(arg0));
		}

	}

	/**
	 * Die Methode wenn die Maus Bewegt wird in der Alternativen Steuerung
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (this.inputController.isAlternativeControll()) {
			gameController.getSpaceShip().setVector(
					MouseControlWhiteboard(arg0));
		}

	}

	/**
	 * die Methode zur Berechnung des Raumschiffbewegungsvectors bei der
	 * Alternativeb Steuerung
	 * 
	 * @param arg0 {@link MouseEvent}
	 *            
	 * @return der Bewegungsvector f�r das {@link SpaceShip}
	 */
	private Vector MouseControlWhiteboard(MouseEvent arg0) {

		Vector silence = new Vector(new Point(arg0.getX() - (xsize)
				- gameController.getSpaceShip().getCenterPoint().getX(),
				arg0.getY()
						+ (-(ysize) + gameController.getSpaceShip()
								.getCenterPoint().getY())));
		Vector s2 = new Vector(0, 0);
		if (silence.getAmount() < 160) {

			s2 = new Vector(silence.getAmount() / 16, -silence.getPhi());

		} else {

			s2 = new Vector(gameController.getMaxSpeed(), -silence.getPhi());

		}
		return s2;
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

	/**
	 * Die Innere Klasse f�r Das accelerationOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
	class accelerationOutput extends BufferedAWTWindow {

		// TODO from 270 to 360 degrees right output
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
			int ac2 = (int) gameController.getSpaceShip().getVector().getPhi() - 180;
			Integer acceleration = Math.abs(ac2 % 360);
			String accelerationString = "Angle: " + acceleration.toString();
			return accelerationString;
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

	/**
	 * Die Innere Klasse f�r Das YcoordinatenOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
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

	/**
	 * Die Innere Klasse f�r Das SpielpunkteOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
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
			Integer points = (int) gameController.getHealth();
			String pointsString = "Points:" + points.toString();
			return pointsString;

		}
	}
	
	class InputControllPanelWindow extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public InputControllPanelWindow(String name, GameController gameController) {
			super(name, gameController);
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

}