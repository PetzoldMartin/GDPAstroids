package keyinput;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Uebung9 extends JFrame {

    public Uebung9(String newTitel) {
        super.setTitle(newTitel);
    }

    public static void main(String str[]) {
        Uebung9 fenster = new Uebung9("Kreise zeichnen, hui, freu");
        fenster.setSize(500, 500);
        fenster.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(0, 0, 0));
        g.drawOval(50, 50, 100, 100);
    }

}