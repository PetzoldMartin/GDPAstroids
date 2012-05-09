package JUnitTest;
import junit.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MainTest extends TestSuite 
{
	//TODO EclEmma Installieren und Konfigurieren
//	The Eclipse preferences section General → Appearance → Editors → Text Editors → Annotations allows to modify the visual representation of coverage highlighting. The corresponding entries are:
//
//	    Full Coverage → Farbe anpassen
//	    Partial Coverage→Farbe anpassen
//	    No Coverage→Farbe anpassen
//	
//	Coverage Window einfügen
//	Window=>Show view=> others------ Java=>Coverage
//	wenn bunt stört dort remove all sessions auswählen

  public static Test suite()
  {
    TestSuite mySuite = new TestSuite( "Shapes Test-Suite" );
    mySuite.addTestSuite(CircleTest.class); 
    mySuite.addTestSuite(LineTest.class);
    mySuite.addTestSuite(PointTest.class);
    mySuite.addTestSuite(PolygonTest.class);
    mySuite.addTestSuite(RectangleTest.class);
    mySuite.addTestSuite(SpriteTest.class);
    
    
    // ... weitere Testklassen hinzufügen
    return mySuite;
  }
}
