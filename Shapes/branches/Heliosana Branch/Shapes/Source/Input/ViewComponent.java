package Input;

import java.awt.Graphics;
import java.io.File;

import javax.swing.JComponent;

class ViewComponent extends JComponent
	{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void setImage( File file )
	  {
	      repaint();
	  }
	  @Override
	  protected void paintComponent( Graphics g )
	  {
		  g.drawLine(0, 150, 300, 150);
	  }
	}


