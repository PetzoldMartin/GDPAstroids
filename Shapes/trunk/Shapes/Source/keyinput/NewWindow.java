package keyinput;

import javax.swing.*;
import javax.swing.JTextField;
import java.awt.*;
 
public class NewWindow extends JFrame  
{
    protected static final long serialVersionUID = 1;
    protected ImageIcon play = new ImageIcon(NewWindow.class.getResource("play.png"));
    protected ImageIcon stop = new ImageIcon(NewWindow.class.getResource("stop.png"));
    protected ImageIcon pause = new ImageIcon(NewWindow.class.getResource("pause.png"));
    protected ImageIcon eject = new ImageIcon(NewWindow.class.getResource("eject.png"));
    private JButton buttonplay = new JButton (play);
    private JButton buttonstop = new JButton (stop);
    private JButton buttonpause = new JButton (pause);
    private JButton buttoneject = new JButton (eject);
    JLayeredPane ebene;
    
    // Schrift �ber/unter den Buttons
     private String ausgabeText="Play";
     private JTextField ausgabe= new JTextField(ausgabeText, 10);
    // Textfelder Font definieren
    Font textFont = new Font ("Square 721 BT", NORMAL, 20);
    
      public NewWindow()                                                                    
        {
                 super("MusicPlayer");                              // Text oben in der Leiste                                                     
                 setSize(700,200);                                  // Fenster Gr��e
                 setLocation(300,300);                              // Wo sich das Fenster beim starten befinden soll                     
                 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Schlie�e Fenster und Programm beim beenden 
                 setBackground(Color.LIGHT_GRAY);
                 setLayout(null);
                 
                 MyJPanel mp = new MyJPanel();                      // initialisiert das Panel f�r den Hintergrund
                 mp.setLayout(null);
                 
                 ebene = this.getLayeredPane();
                 ebene.add(mp, JLayeredPane.FRAME_CONTENT_LAYER, 0);
                 
                 int buttonx=100;                                   // x-Koordinate f�r die Buttons
                 int buttony=110;                                   // y-Koordinate f�r die Buttons
                 int buttonw=48;                                    // gr��e der Buttons
                 int buttonh=48;                                    // gr��e der Buttons
                 mp.setBounds(0,0,700,200);
                 buttonstop.setBounds(200, buttony, buttonw, buttonh);
                 buttonstop.setBorder(null);
                 buttoneject.setBounds(50, buttony, buttonw, buttonh);
                 buttoneject.setBorder(null);
                 buttonplay.setBounds(buttonx, buttony, buttonw, buttonh);
                 buttonplay.setBorder(null);
                 buttonpause.setBounds(150, buttony, buttonw, buttonh);
                 buttonpause.setBorder(null);
                 mp.add(buttoneject);
                 mp.add(buttonstop);
                 mp.add(buttonplay);
                 mp.add(buttonpause);    
                 buttonstop.setActionCommand("Stop");                               
                 buttoneject.setActionCommand("�ffnen");                                
                 buttonplay.setActionCommand("Start");
                 buttonpause.setActionCommand("Pause");
 
                //Groesse, Positionen und Fonts der randlosen Textfelder
               int textx=100;
               int texty=250;
               int textw=100;
               int texth=100;
              
              // textx=butx+butw;
              // textw=200;
               ausgabe.setBounds(textx,texty,textw,texth);
               ausgabe.setFont(textFont);
               ausgabe.setBackground(Color.black);
              //  ausgabe.setHorizontalAlignment(JTextField.LEFT); 
              //  ausgabe.setHorizontalAlignment(JTextField.RIGHT); 
               ausgabe.setBorder(null);
               ausgabe.setOpaque(false);
               
               add(ausgabe);  // Textausgeben "play"
                                
                 // Men�-Leiste
                 MenuBar mb = new MenuBar();    
                        
                 // Ein Men�
                 Menu m1 = new Menu("Datei");
                 mb.add(m1);
 
                 // Men�eintr�ge
                 MenuItem oeff = new MenuItem("�ffnen");
                 MenuItem schl = new MenuItem("Schlie�en");
                 
                 // Menue Action
                 MenueSchliessenService menueSchliessen = new MenueSchliessenService(this);
                 schl.addActionListener(menueSchliessen);
                 // Datai ausw�hlen
                 OeffnenDialogClass odc = new OeffnenDialogClass();
                 oeff.addActionListener(odc);
                 // Datei abspielen
                 setMenuBar(mb);
                 m1.add(schl);  
                 m1.add(oeff); 
                 // Actionlistener f�r die Buttons   
                 buttoneject.addActionListener(odc);
                 buttonplay.addActionListener(odc);
                 buttonstop.addActionListener(odc);
                 buttonpause.addActionListener(odc); 
        }
 
}