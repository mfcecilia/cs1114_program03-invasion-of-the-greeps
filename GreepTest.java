import sofia.micro.*;
import static sofia.micro.jeroo.RelativeDirection.*;
import static sofia.micro.jeroo.CompassDirection.*;
import sofia.micro.greeps.*;

// -------------------------------------------------------------------------
/**
 *  Tests the various methods in the program including act
 *
 *  @author Mykayla Fernandes (mkaykay1)
 *  @version 2015.10.13
 */
public class GreepTest extends TestCase
{
    //~ Fields ................................................................
    /**
     * create a greep field
     */
    Greep greep;
    /**
     * create an earth field
     */
    Earth earth;
    /**
     * create tomato field
     */
    Tomato tomato;
    /**
     * create ship field
     */
    Ship ship;
    /**
     * creates water field
     */
    Water water;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new GreepTest test object.
     */
    public GreepTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        //intentionally left empty to allow for customized tests
    }


    // ----------------------------------------------------------
    /**
     * tests the act method
     */
    public void testAct()
    {
        earth = new Earth();
        greep = new Greep();
        tomato = new Tomato();
        ship = new Ship(1, 2);
        earth.add(greep, 1, 1);
        earth.add(greep, 1, 1);
        earth.add(tomato, 1, 1);
        earth.add(ship);
        assertEquals(EAST, greep.getDirection());
        assertFalse(greep.hasTomato());
    }
    /**
     * tests if greeps avoid water
     * this test passes
     */
    public void testCheckWater()
    {
        earth = new Earth();
        greep = new Greep();
        earth.add(greep, 1, 1);
        greep.turn(LEFT);
        greep.checkWater();
        assertEquals(EAST, greep.getDirection());
    }
    /**
     * tests tomatoRunner method
     */
    public void testTomatoRunner()
    {
        earth = new Earth();
        greep = new Greep();
        tomato = new Tomato();
        greep = new Greep();
        ship = new Ship(1, 2);
        earth.add(greep, 1, 1);
        earth.add(tomato, 1, 1);
        earth.add(greep, 1, 1);
        earth.add(ship);
        greep.scanTomato();
        greep.tomatoRunner();
        greep.scanShip();
        assertFalse(greep.hasTomato());
    }
    /**
     * test tomato guard
     * this test passes
     */
    public void testTomatoGuard()
    {
        earth = new Earth();
        greep = new Greep();
        tomato = new Tomato();
        earth.add(greep, 1, 1);
        earth.add(tomato, 1, 2);
        greep.scanTomato();
        greep.tomatoGuard();
        assertTrue(greep.sees(Tomato.class, HERE));
        assertEquals(SOUTH, greep.getDirection());
    }
    /**
     * tests navigation
     * this test passes
     */
    public void testNavigate()
    {
        earth = new Earth();
        greep = new Greep();
        earth.add(greep, 1, 1);
        greep.navigate();
        assertFalse(greep.sees(Water.class, HERE));
    }
}
