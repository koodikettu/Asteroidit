/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import asteroidit.domain.Alus;
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
public class AlusTest {

    public AlusTest() {
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
    public void kaantotestiOikea() {
        Alus alus = new Alus(100, 100, 359);
        alus.kaanna(1);

        assertEquals(4, alus.getSuunta(), 0.01);
    }

    @Test
    public void kaantotestiVasen() {
        Alus alus = new Alus(100, 100, 3);
        alus.kaanna(-1);

        assertEquals(358, alus.getSuunta(), 0.01);
    }

    @Test
    public void testaaPolygonia() {
        Alus alus = new Alus(100, 100, 0);
        alus.laskeAlusPolygoni();
        Polygon polygon = alus.getAlusPolygoni();
        assertEquals(130, polygon.xpoints[0]);

    }

    @Test
    public void testaaPolygoninKaanto() {
        Alus alus = new Alus(100, 100, 0);
        alus.setSuunta(180);
        alus.laskeAlusPolygoni();
        Polygon polygon = alus.getAlusPolygoni();
        assertEquals(100, polygon.ypoints[0]);

    }

    @Test
    public void laskeUusiNopeusTest() {
        Alus alus = new Alus(100, 100, 0);
        assertEquals(1, alus.laskeUusiNopeus(0, 0, 1, 45), 0.06);
    }

    @Test
    public void laskeUusiSuuntaTest() {
        Alus alus = new Alus(100, 100, 0);
        alus.laskeUusiNopeus(0, 0, 1, 45);
        assertEquals(45, alus.laskeUusiSuunta(), 0.6);
    }

    @Test
    public void liikuTest1() {
        Alus alus = new Alus(100, 100, 90);
 
            alus.kiihdyta(true);

            alus.liiku();
                        alus.kiihdyta(true);

            alus.liiku();


        assertEquals(true, alus.getY() < 100);
    }
    
        @Test
    public void liikuTest2() {
        Alus alus = new Alus(100, 100, 0);
 
            alus.kiihdyta(true);

            alus.liiku();
                        alus.kiihdyta(true);

            alus.liiku();
                        alus.kiihdyta(true);

            alus.liiku();


        assertEquals(true, alus.getX() > 100);
    }

}
