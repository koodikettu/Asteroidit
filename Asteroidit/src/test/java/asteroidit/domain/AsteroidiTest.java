/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import java.awt.Polygon;
import java.util.Random;
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
public class AsteroidiTest {

    Random random;

    public AsteroidiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        random = new Random();
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
    public void asteroidiAlustetaanOikeinKulmienMaara() {

//        Polygon vertailupg = new Polygon();
//        vertailupg.addPoint(1000, -10);
//        vertailupg.addPoint(1025, 40);
//        vertailupg.addPoint(990, 55);
//        vertailupg.addPoint(964, 33);
        for(int i=0;i<1000;i++) {
            Asteroidi asteroidi = new Asteroidi(1000, -30, -2.5, 1.0, random);
            asteroidi.laskePolygoni();
            Polygon polygoni = asteroidi.getAsteroidiPolygoni();
            assertEquals(true,polygoni.npoints>=5 && polygoni.npoints<=9);
        }

    }

    @Test
    public void paikkaAlustetaanOikeinX() {
        int x, y;
        Asteroidi asteroidi = new Asteroidi(100, 100, 10, 5, random);

        for (int index = 0; index < 100; index++) {
            asteroidi.alusta(random, 100, 100, 10, 5);
            x=asteroidi.getX();
            y=asteroidi.getY();
            if (y >= 0 && y <= 100) {
                assertEquals(true, ((x >= -10 && x <= 0) || (x >= 100 && x <= 110)));

            }


        }

    }
    
    
    @Test
    public void paikkaAlustetaanOikeinY() {
        int x, y;
        Asteroidi asteroidi = new Asteroidi(100, 100, 10, 5, random);

        for (int index = 0; index < 100; index++) {
            asteroidi.alusta(random, 100, 100, 10, 5);
            x=asteroidi.getX();
            y=asteroidi.getY();
            if (y >= 0 && y <= 100) {
                assertEquals(true, ((x >= -10 && x <= 0) || (x >= 100 && x <= 110)));
            }


        }

    }

}
