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
import Astroids.Vector;
import GUI.WhiteboardControllPanel.EndScreenlevelOutput;
import GUI.WhiteboardControllPanel.GameoverScreen;
import Shapes.Shape;

/**
 * Das Whiteboard Controllpanel Vers.2.0
 * 
 * @author Aismael
 * 
 */
    public class WhiteboardControllPanel {

	protected int LabelX = 10;// XPosition f�r Schriftausgabe
	protected int LabelY = 25;// YPoasition f�r Schriftausgabe
	protected boolean JspinnerActivate = false;
	private JFrame whiteBoard;// das Interne Whiteboard
	private JScrollPane whiteBoardInlet;// das Interne ScrollPane des
										// Whiteboards
	private GUIController gUIController;// der Interne InputController
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
	private JPanel gameoverContainer;
	private BigListener bigListener;
	private boolean gameOver=false;

	/**
	 * Der Konstruktor f�r das {@link WhiteboardControllPanel} der alle
	 * Componenten Initialisiert
	 * 
	 * @param gameController
	 *            der dem {@link WhiteboardControllPanel} �bergebene
	 *            {@link GameController}
	 * @param gUIController
	 *            der dem {@link WhiteboardControllPanel} �bergebene
	 *            {@link GUIController}
	 * @param bigListener 
	 */
	protected  WhiteboardControllPanel(GUIController gUIController, BigListener bigListener) {
		this.gUIController = gUIController;
		this.bigListener=bigListener;

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
		whiteBoard.setSize(gUIController.playFieldSizeX(), gUIController.playFieldSizeY());
		/**
		 * die Initialisierung des Whiteboardpanels
		 */
		whiteBoardInlet = Shape.getWhiteBoard().getScrollPane();
		whiteBoardInlet.setCursor(new Cursor(0));
		whiteBoardInlet.addMouseWheelListener(bigListener);
		whiteBoardInlet.addMouseListener(bigListener);
		whiteBoardInlet.addMouseMotionListener(new MouseMotionListenerForAltenativeControll());

		/**
		 * die Initialisierung des AWTandJframeMergecontainers
		 */
		AWTandJframeMergecontainer = new JPanel(new BorderLayout());
		AWTandJframeMergecontainer.addKeyListener(bigListener);
		whiteBoard.add(BorderLayout.EAST, AWTandJframeMergecontainer);
		AWTandJframeMergecontainer.setPreferredSize(new Dimension(300, 600));
		AWTandJframeMergecontainer.add(inputControllPanelWindow,
				BorderLayout.CENTER);
		inputControllPanelWindow.setPreferredSize(new Dimension(300, 300));
		inputControllPanelWindow.setCursor(new Cursor(12));

		/**
		 * die Initialisierung des AWTOutputContainers
		 */
		AWTOutputContainer = new JPanel(new GridLayout(7, 0));
		buildAWTOutputContainer();
		AWTandJframeMergecontainer.add(AWTOutputContainer, BorderLayout.NORTH);
		AWTOutputContainer.setPreferredSize(new Dimension(300, 280));
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
		
		
		gameoverContainer=new JPanel(new BorderLayout());
		buildGameoverContainer();
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
				gUIController.velocityChangePerInt((Integer) velocity.getValue());
			}
		});
		angle.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				gUIController.angleChangePerInt((Integer) angle.getValue());

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
				if (bigListener.getAlternativeControll()) {
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
				if (gUIController.getCheat()) {
					((AbstractButton) getComponent()).setText("cheat Activate");
					((AbstractButton) getComponent()).setForeground(Color.black);
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
		JFrameButtonContainer.add(ControllChange);
		JFrameButtonContainer.add(JspinnerControll);
		JFrameButtonContainer.add(cheat);
		cheat.setBackground(Color.black);
		cheat.setForeground(Color.black);
		cheat.setBorderPainted(false);

	}

	/**
	 * Methode f�r das adden der AWTOutputContainerKomponenten
	 */
	private void buildAWTOutputContainer() {
		AWTOutputContainer.add(new LevelOutput("Level"));
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

	private void buildGameoverContainer(){
		JButton restart = new JButton("Restart");
		EndScreenlevelOutput endScreenlevelOutput = new EndScreenlevelOutput("EndScreenLevel");
		GameoverScreen gameoverScreen = new GameoverScreen("Gameover");
		gameoverScreen.setSize(gUIController.getWindowX()*2+300, gUIController.getWindowY()*2-200);
		gameoverContainer.add(gameoverScreen,
				BorderLayout.CENTER);
		restart.setFont(new Font("Serif", Font.PLAIN, 100));
		gameoverContainer.add(BorderLayout.PAGE_END,restart);
		gameoverContainer.add(BorderLayout.PAGE_START,endScreenlevelOutput);
		restart.setPreferredSize(new Dimension(gUIController.getWindowX()*2+300, 100));
		endScreenlevelOutput.setPreferredSize(new Dimension(gUIController.getWindowX()*2+300, 100));
		gameoverContainer.addKeyListener(bigListener);
		gameoverContainer.setBackground(Color.black);
		gameoverContainer.requestFocus();
		restart.addActionListener(new EffectActionListener(restart) {
			
			
	
			@Override
			public void actionPerformed(ActionEvent e) {
				gUIController.restart();
				whiteBoard.remove(gameoverContainer);
				whiteBoardInlet.setVisible(true);
				AWTandJframeMergecontainer.setVisible(true);
				whiteBoardInlet.requestFocus();
				gameOver=false;
	
			}
		});
	}

	/**
	 * die Methode zum Neuzeichnen der AWTOutputContainerKomponenten
	 */
	protected  void Outputrefresh() {
		
		if(gUIController.playerDieEvent()){
			if(!gameOver){
			gameoverSight();
			gameOver=true;
			}else{
				for (Component AWTComponent : gameoverContainer.getComponents()) {
					AWTComponent.repaint();
				}
			}
		}else{
			for (Component AWTComponent : AWTOutputContainer.getComponents()) {
				AWTComponent.repaint();
			}
			JFormattedTextField textFieldAngle = ((JSpinner.DefaultEditor) angle
					.getEditor()).getTextField();
			JFormattedTextField textFieldVelocity = ((JSpinner.DefaultEditor) velocity
					.getEditor()).getTextField();
			textFieldAngle.setText(gUIController.accelerationToString());
			textFieldVelocity.setText(gUIController.speedToString());
		}
	
	}

	private void gameoverSight() {
		whiteBoardInlet.setVisible(false);
		AWTandJframeMergecontainer.setVisible(false);
		whiteBoard.add(BorderLayout.WEST,gameoverContainer);
		
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
			g.drawString("Speed: " + gUIController.speedToString(), LabelX, LabelY);
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
			g.drawString("Angle: " + gUIController.accelerationToString(), LabelX, LabelY);
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
			g.drawString("X:" + gUIController.xToString(), LabelX, LabelY);
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
			g.drawString("Y:" + gUIController.yToString(), LabelX, LabelY);
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
			g.drawString("Points:" + gUIController.PointsToString(), LabelX, LabelY);
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
			this.setFont(new Font("Serif", Font.PLAIN, (int) (gUIController.getWindowX()/2.5)));
			g.setColor(new Color(255, 0, 0));
			g.drawString("GAMEOVER", gUIController.getWindowX()/7, gUIController.getWindowY());
			

		}

	}
	class LevelOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 12L;

		public LevelOutput(String name) {
			super(name);
		}

		@Override
		/**
		 * Die Paintmethode von InputControllPanelWindow  die einen Kreis und die Orientierungslinien zeichnet
		 */
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(0, 255, 0));
			g.drawString("Level "+ gUIController.levelToString(), LabelX, LabelY);
			

		}

	}
	
	class EndScreenlevelOutput extends BufferedAWTWindow {

		private static final long serialVersionUID = 12L;

		public EndScreenlevelOutput(String name) {
			super(name);
		}

		@Override
		/**
		 * Die Paintmethode von InputControllPanelWindow  die einen Kreis und die Orientierungslinien zeichnet
		 */
		public void paint(Graphics g) {
			super.paint(g);
			this.setFont(new Font("Serif", Font.PLAIN, gUIController.getWindowX()/6));
			g.setColor(new Color(0, 255, 0));
			g.drawString("Ihr erreichtes Level ist: "+ gUIController.levelToString(), LabelX, 90);
			

		}

	}
	class MouseMotionListenerForAltenativeControll implements MouseMotionListener {
		
		/**
		 * Die Methode wenn die Maus gedr�ckt ist und Bewegt wird in der
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