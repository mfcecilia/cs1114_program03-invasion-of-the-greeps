import sofia.micro.greeps.*;
import sofia.util.Random;

//-------------------------------------------------------------------------
/**
 *  greeps collect tomatoes 
 *  brings them back to the ship
 *  avoids water along the way
 *  communicates using paint trails
 *
 *  @author mykayla (mkaykay1)
 *  @version 2015.10.05
 */
public class Greep extends Alien
{
    //~ Fields ................................................................

    // Remember--only boolean fields are allowed in the Greeps class


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Greep object.
     */
    public Greep()
    {
        super();
    }


    //~ Methods ...............................................................
    
    /**
     * greeps collect tomotoes
     * then bring them back to the ship
     * avoiding water along the way
     * and communicating with paint trails
     */
    public void act()
    {
        this.tomatoGuard();
        this.tomatoRunner();
    }
    /**
     * 15% chance of turning right
     * 15% chance of turning left
     * 70% chance of moving ahead
     * allows greeps to traverse the world
     */
    public void navigate()
    {
        int value = Random.generator().nextInt(100);
        if (value < 15)
        {
            this.turn(LEFT);
        }
        else if (value < 30)
        {
            this.turn(RIGHT);
        }
        else   
        {
            this.hop();
        }
        this.checkWater();
    }
    /**
     * keeps greep from drowning
     */
    public void checkWater()
    {
        while (this.sees(Water.class, AHEAD))
        {
            this.turn(RIGHT);
        }
    }
    /**
     * keeps a greep at the tomato to be a loader
     */
    public void tomatoGuard()
    {
        if (!this.sees(Tomato.class, HERE) && this.sees(Ship.class, HERE))
        {
            this.navigate();
        }
        else if (!this.sees(Tomato.class, HERE) && !this.sees(Ship.class, HERE))
        {
            this.navigate();
            this.scanTomato();
        }
        else if (this.sees(Tomato.class, HERE) && this.sees(Greep.class, HERE))
        {
            this.loadTomato(HERE);
        }
    }
    /**
     * these greeps take tomatoes to the ship
     * if there are already guards at the tomato patches.
     */
    public void tomatoRunner()
    {
        if (this.sees(Tomato.class, HERE) && this.sees(Greep.class, HERE) 
            && !this.sees(Ship.class, HERE) && !this.hasTomato())
        {
            this.loadTomato(HERE);
        }
        else if (this.hasTomato() && !this.sees(Ship.class, HERE))
        {
            this.navigate();
            this.scanShip();
        }
        else if (this.sees(Ship.class, HERE) && this.hasTomato())
        {
            this.unloadTomato();
        }
    }
    /**
     * scans their surroundings for a tomato
     */
    public void scanTomato()
    {
        if (this.sees(Tomato.class, AHEAD))
        {
            this.hop();
        }
        else if (this.sees(Tomato.class, RIGHT))
        {
            this.turn(RIGHT);
        }
        else if (this.sees(Tomato.class, LEFT))
        {
            this.turn(LEFT);
        }
    }
    /**
     * scans their surroundings for a ship
     */
    public void scanShip()
    {
        if (this.sees(Ship.class, AHEAD))
        {
            this.hop();
        }
        else if (this.sees(Ship.class, RIGHT))
        {
            this.turn(RIGHT);
        }
        else if (this.sees(Ship.class, LEFT))
        {
            this.turn(LEFT);
        }
    }
}
