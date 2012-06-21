package GUI;

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
import Shapes.Shape;

/**
 * Das Whiteboard Controllpanel Vers.2.0
 * 
 * @author Aismael
 * 
 */
public class WhiteboardControllPanel {

	protected int LabelX = 10;// XPosition fuer Schriftausgabe
	protected int LabelY = 25;// YPoasition fuer Schriftausgabe
	protected boolean jSpinnerActivate = false;
	private JFrame whiteBoard;// das Interne Whiteboard
	private JScrollPane whiteBoardInlet;// das Interne ScrollPane des
										// Whiteboards
	private GUIController gUIController;// der Interne InputController
	private JPanel aWTandJframeMergecontainer;// Der Container der das Interface
												// auf das Whiteboard bringt und
												// die Jframe und AWT Container
												// Plaziert
	private JPanel jFrameButtonContainer;// der Container fuer die JButtons
	private JPanel aWTOutputContainer;// der Container fuer die AWT Outputs
	private JPanel jSpinnerContainer;// der Container fuer die Jspinner
	private InputControllPanelWindow inputControllPanelWindow;// das Interne
																// InputControllPanelWindow
	private JSpinner velocity;//der JSpinner für die Geschwindigkeit
	private JSpinner angle;//der JSpinner für den Winkel
	private JPanel gameOverContainer;// der Container fuer den Gameoverbildschirm
	private BigListener bigListener;// der interne bigListener
	private boolean gameOver = false;//das Boolean das ausgelöst wird wenn der Gamoverbildschirm angezeigt wird
	private JButton restart;//der Button für den Spielrestart
	/**
	 * Der Konstruktor fuer das {@link WhiteboardControllPanel} der alle
	 * Componenten Initialisiert
	 * 
	 * @param gameController
	 *            der dem {@link WhiteboardControllPanel} uebergebene
	 *            {@link GameController}
	 * @param gUIController
	 *            der dem {@link WhiteboardControllPanel} uebergebene
	 *            {@link GUIController}
	 * @param bigListener 
	 */
	protected WhiteboardControllPanel(GUIController gUIController, BigListener bigListener) {
		this.gUIController = gUIController;
		this.bigListener = bigListener;

		velocity = new JSpinner();
		angle = new JSpinner();

		inputControllPanelWindow = new InputControllPanelWindow("TastenInput");
		inputControllPanelWindow.setSize(300, 300);
		inputControllPanelWindow.addMouseWheelListener(bigListener);
		inputControllPanelWindow.addMouseListener(bigListener);
		inputControllPanelWindow.addMouseMotionListener(bigListener);
		inputControllPanelWindow.addKeyListener(bigListener);
		inputControllPanelWindow.requestFocus();
		/**
		 * die Initialisierung des Whiteboards
		 */
		whiteBoard = Shape.getWhiteBoard().getFrame();
		whiteBoard.addKeyListener(bigListener);
		whiteBoard.addWindowListener(bigListener);
		whiteBoard.setSize(gUIController.playFieldSizeX(),
				gUIController.playFieldSizeY());
		/**
		 * die Initialisierung des Whiteboardpanels
		 */
		whiteBoardInlet = Shape.getWhiteBoard().getScrollPane();
		whiteBoardInlet.setCursor(new Cursor(0));
		whiteBoardInlet.addMouseWheelListener(bigListener);
		whiteBoardInlet.addMouseListener(bigListener);
		whiteBoardInlet
				.addMouseMotionListener(new MouseMotionListenerForAltenativeControll());

		/**
		 * die Initialisierung des AWTandJframeMergecontainers
		 */
		aWTandJframeMergecontainer = new JPanel(new BorderLayout());
		aWTandJframeMergecontainer.addKeyListener(bigListener);
		whiteBoard.add(BorderLayout.EAST, aWTandJframeMergecontainer);
		aWTandJframeMergecontainer.setPreferredSize(new Dimension(300, 600));
		aWTandJframeMergecontainer.add(inputControllPanelWindow,
				BorderLayout.CENTER);
		inputControllPanelWindow.setPreferredSize(new Dimension(300, 300));
		inputControllPanelWindow.setCursor(new Cursor(12));

		/**
		 * die Initialisierung des AWTOutputContainers
		 */
		aWTOutputContainer = new JPanel(new GridLayout(7, 0));
		buildAWTOutputContainer();
		aWTandJframeMergecontainer.add(aWTOutputContainer, BorderLayout.NORTH);
		aWTOutputContainer.setPreferredSize(new Dimension(300, 280));
		/**
		 * die Initialisierung des JFrameButtonContainers
		 */
		jFrameButtonContainer = new JPanel(new GridLayout(4, 0));
		buildJFrameButtonContainer();
		jFrameButtonContainer.setBackground(Color.black);
		aWTandJframeMergecontainer.add(jFrameButtonContainer,
				BorderLayout.SOUTH);
		aWTandJframeMergecontainer.setBackground(Color.black);
		/**
		 * die Initialisierung des JspinnerContainers
		 */
		jSpinnerContainer = new JPanel(new FlowLayout());
		buildJspinnerContainer();
		jFrameButtonContainer.add(jSpinnerContainer);
		jSpinnerContainer.setVisible(true);
		jSpinnerContainer.setVisible(false);

		/**
		 * die Initialisierung des GameoverContainers
		 */
		gameOverContainer = new JPanel(new BorderLayout());
		buildGameoverContainer();
		whiteBoard.requestFocus();
	}

	/**
	 * die Methode zum Neuzeichnen der der GUIAnzeigen
	 */
	protected void Outputrefresh() {
	
		if (gUIController.playerDieEvent()) {
			if (!gameOver) {
				gameoverSight();
				gameOver = true;
			} 
			else {
				for (Component AWTComponent : gameOverContainer.getComponents()) {
					AWTComponent.repaint();
				}
			}
		} else {
			for (Component AWTComponent : aWTOutputContainer.getComponents()) {
				AWTComponent.repaint();
			}
			JFormattedTextField textFieldAngle = ((JSpinner.DefaultEditor) angle
					.getEditor()).getTextField();
			JFormattedTextField textFieldVelocity = ((JSpinner.DefaultEditor) velocity
					.getEditor()).getTextField();
			textFieldAngle.setText(gUIController.accelerationToStringForJSpinner());
			textFieldVelocity.setText(gUIController.speedToString());
		}
	
	}

	/**
	 * die Methode die das Spielfeld Verbirgt und den Gamoverbildschirm anzeigt
	 */
	private void gameoverSight() {
		whiteBoardInlet.setVisible(false);
		aWTandJframeMergecontainer.setVisible(false);
		whiteBoard.add(BorderLayout.WEST, gameOverContainer);
		whiteBoardInlet.requestFocus();
	
	}

	/**
	 * Methode fuer das adden der JspinnerContainerKomponenten
	 */
	private void buildJspinnerContainer() {
		jSpinnerContainer.add(new JLabel("Velocity"));
		jSpinnerContainer.add(velocity);
		jSpinnerContainer.add(new JLabel("Angle"));
		jSpinnerContainer.add(angle);
		velocity.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				gUIController.velocityChangePerInt((Integer) velocity
						.getValue());
				inputControllPanelWindow.requestFocus();
			}
		});
		angle.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				gUIController.angleChangePerInt((Integer) angle.getValue());
				inputControllPanelWindow.requestFocus();
			}
		});

	}

	/**
	 * Methode fuer das adden der JFrameButtonContainerKomponenten
	 */
	private void buildJFrameButtonContainer() {
		JButton ControllChange = new JButton("Alternative Controll deactivate");
		JButton JspinnerControll = new JButton("Jspinner Activate");
		JButton cheat = new JButton("cheat Activate");
		ControllChange.addActionListener(new EffectActionListener(
				ControllChange) {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (bigListener.isAlternativeControll()) {
					bigListener.setAlternativeControll(false);
					((AbstractButton) getComponent())
							.setText("Alternative Controll activate");

				} else {
					bigListener.setAlternativeControll(true);
					((AbstractButton) getComponent())
							.setText("Alternative Controll deactivate");

				}
				whiteBoard.requestFocus();
			};
		});
		JspinnerControll.addActionListener(new EffectActionListener(
				JspinnerControll) {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jSpinnerActivate) {
					jSpinnerActivate = false;
					((AbstractButton) getComponent())
							.setText("Jspinner Activate");
					jSpinnerContainer.setVisible(false);
					velocity.setFocusable(false);
					angle.setFocusable(false);

				} else {
					jSpinnerActivate = true;
					((AbstractButton) getComponent())
							.setText("Jspinner deActivate");
					jSpinnerContainer.setVisible(true);
					velocity.setFocusable(true);
					angle.setFocusable(true);

				}
				whiteBoard.requestFocus();
			};
		});
		cheat.addActionListener(new EffectActionListener(cheat) {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gUIController.getCheat()) {
					((AbstractButton) getComponent()).setText("cheat Activate");
					((AbstractButton) getComponent())
							.setForeground(Color.black);
					gUIController.setCheat(false);
				} else {

					gUIController.setCheat(true);
					((AbstractButton) getComponent())
							.setText("cheat deActivate");
					((AbstractButton) getComponent()).setForeground(Color.RED);
				}
				whiteBoard.requestFocus();

			}
		});
		jFrameButtonContainer.add(ControllChange);
		jFrameButtonContainer.add(JspinnerControll);
		jFrameButtonContainer.add(cheat);
		cheat.setBackground(Color.black);
		cheat.setForeground(Color.black);
		cheat.setBorderPainted(false);

	}

	/**
	 * Methode fuer das adden der AWTOutputContainerKomponenten
	 */
	private void buildAWTOutputContainer() {
		aWTOutputContainer.add(new LevelOutput("Level"));
		aWTOutputContainer.add(new PointsOutput("Points"));
		aWTOutputContainer.add(new BufferedAWTWindow("Coords") {

			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(new Color(255, 255, 255));
				g.drawString(super.name, LabelX, LabelY);
			}

		});
		aWTOutputContainer.add(new XOutput("Xcoord"));
		aWTOutputContainer.add(new YOutput("Ycoord"));
		aWTOutputContainer.add(new speedOutPut("Speed"));
		aWTOutputContainer.add(new accelerationOutput("acceleration"));
	}

	/**
	 * Methode fuer das adden der GameoverContainerKomponenten
	 */
	private void buildGameoverContainer() {
		restart = new JButton("Restart");
		EndScreenlevelOutput endScreenlevelOutput = new EndScreenlevelOutput("EndScreenLevel");
		gameOverContainer.add(new GameoverScreen("Gameover"),
				BorderLayout.CENTER);
		restart.setFont(new Font("Serif", Font.PLAIN, 100));
		gameOverContainer.add(BorderLayout.SOUTH, restart);
		gameOverContainer.add(BorderLayout.NORTH, endScreenlevelOutput);
		restart.setPreferredSize(new Dimension(
				gUIController.getWindowX() * 2 + 350, 100));
		gameOverContainer.setBackground(Color.black);
		endScreenlevelOutput.setPreferredSize(new Dimension(gUIController
				.getWindowX() * 2 + 350, 100));
		restart.addActionListener(new EffectActionListener(restart) {

			@Override
			public void actionPerformed(ActionEvent e) {
				gUIController.restart();
				whiteBoard.remove(gameOverContainer);
				whiteBoardInlet.setVisible(true);
				aWTandJframeMergecontainer.setVisible(true);
				gameOver = false;
				inputControllPanelWindow.requestFocus();

			}
		});
	}

	/**
	 * Die Innere Klasse fuer Das LevelOutput Fenster
	 * @author Aismael
	 * 
	 */
	class LevelOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 12L;

		public LevelOutput(String name) {
			super(name);
		}

		@Override
		
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(0, 255, 0));
			g.drawString("Level " + gUIController.levelToString(), LabelX,
					LabelY);

		}

	}

	/**
	 * Die Innere Klasse fuer Das SpielpunkteOutput Fenster
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
			g.drawString("Points:" + gUIController.pointsToString(), LabelX,
					LabelY);
		}
	
	}

	/**
	 * Die Innere Klasse fuer Das XcoordinatenOutput Fenster
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
			g.drawString("X:" + gUIController.xToString(), LabelX, LabelY);
		}
	
	}

	/**
	 * Die Innere Klasse fuer Das YcoordinatenOutput Fenster
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
			g.drawString("Y:" + gUIController.yToString(), LabelX, LabelY);
		}
	
	}

	/**
	 * Die Innere Klasse fuer Das SpeedOutput Fenster
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
			g.drawString("Speed: " + gUIController.speedToString(), LabelX,
					LabelY);
		}
	
	}

	/**
	 * Die Innere Klasse fuer Das accelerationOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
	class accelerationOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 1L;

		public accelerationOutput(String name) {
			super(name);
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 255, 255));
			g.drawString("Angle: " + gUIController.accelerationToString(),
					LabelX, LabelY);
		}

	}

	/**
	 * Die Innere Klasse fuer Das InputControllPanelWindow Fenster
	 * 
	 * @author Aismael
	 * 
	 */
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

	/**
	 * Die Innere Klasse fuer Das EndScreenlevelOutput Fenster
	 * 
	 * @author Aismael
	 * 
	 */
	class EndScreenlevelOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 124L;

		public EndScreenlevelOutput(String name) {
			super(name);
			this.setFont(new Font("Serif", Font.PLAIN, gUIController
					.getWindowX() / 6));
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(0, 255, 0));
			g.drawString(
					"Ihr erreichtes Level ist: "
							+ gUIController.levelToString(), LabelX, 90);

		}

	}

	/**
	 * Die Innere Klasse fuer Das GameoverScreen Fenster
	 * 
	 * @author Aismael
	 * 
	 */
	class GameoverScreen extends BufferedAWTWindow {

		private static final long serialVersionUID = 122L;

		public GameoverScreen(String name) {
			super(name);
			this.setSize(gUIController.getWindowX() * 2 + 300,
					gUIController.getWindowY() * 2 - 200);
			this.setFont(new Font("Serif", Font.PLAIN, (int) (gUIController
					.getWindowX() / 2.5)));
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 0, 0));
			g.drawString("GAMEOVER", gUIController.getWindowX() / 7,
					gUIController.getWindowY());

		}

	}

	/**
	 * Die Innere Klasse fuer einen ActionListener der die AWT oder Jframe
	 * Komponente verwenden kann
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
	 * Die Innere Klasse eines Mouselistener der die Mausparameter nur weitergibt wenn die 
	 * Alternative eingabe Aktiviert ist
	 * 
	 * @author Aismael
	 * 
	 */
	class MouseMotionListenerForAltenativeControll implements
			MouseMotionListener {

		/**
		 * Die Methode wenn die Maus gedrueckt ist und Bewegt wird in der
		 * Alternativen Steuerung
		 */
		@Override
		public void mouseDragged(MouseEvent arg0) {
			if (bigListener.isAlternativeControll()) {
				gUIController.mouseControlWhiteboard(arg0);
				
			}

		}

		/**
		 * Die Methode wenn die Maus Bewegt wird in der Alternativen Steuerung
		 */
		@Override
		public void mouseMoved(MouseEvent arg0) {
			if (bigListener.isAlternativeControll()) {
				gUIController.mouseControlWhiteboard(arg0);
				
			}

		}
	}
}