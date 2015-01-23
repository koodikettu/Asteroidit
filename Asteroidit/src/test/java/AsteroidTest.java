/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import java.awt.Polygon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markku
 */
public class AsteroidTest {

    public AsteroidTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void arvotValittyvatOikein() {
        Asteroidi asteroidi = new Asteroidi(1000, -30, -2.5, 1.0);

        asteroidi.laskePolygoni();
        Polygon polygoni = asteroidi.getAsteroidiPolygoni();
//        Polygon vertailupg = new Polygon();
//        vertailupg.addPoint(1000, -10);
//        vertailupg.addPoint(1025, 40);
//        vertailupg.addPoint(990, 55);
//        vertailupg.addPoint(964, 33);

        assertEquals(polygoni.xpoints[3],964);
        assertEquals(polygoni.ypoints[0],-70);
    }

}
