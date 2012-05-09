package JUnitTest;
import junit.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MainTest extends TestSuite 
{
  public static Test suite()
  {
    TestSuite mySuite = new TestSuite( "Meine Test-Suite" );
    mySuite.addTestSuite(CircleTest.class);    // ... weitere Testklassen hinzufügen
    return mySuite;
  }
}
