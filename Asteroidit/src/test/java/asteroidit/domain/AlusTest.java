/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import asteroidit.domain.Alus;
import asteroidit.peli.Asteroidipeli;
import java.awt.Polygon;
import java.util.Arrays;
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

    private Asteroidipeli peli;

    public AlusTest() {
        peli = new Asteroidipeli();
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
        alus.laskeUusiSuunta();
        assertEquals(45, alus.getKulkusuunta(), 0.6);
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

    @Test
    public void kiihdytysTest() {
        Alus alus = new Alus(100, 100, 90);

        alus.kiihdyta(true);
        alus.kiihdyta(true);
        assertEquals(1, alus.getNopeus(), 0.01);

    }

    @Test
    public void hidastumisTest() {
        Alus alus = new Alus(100, 100, 90);

        alus.kiihdyta(true);
        alus.kiihdyta(true);
        double a = alus.getNopeus();
        System.out.println(a);
        alus.liiku();
        alus.liiku();
        System.out.println(alus.getNopeus());
        assertEquals(0.4, a - alus.getNopeus(), 0.01);

    }

    @Test
    public void hidastumis2Test() {
        Alus alus = new Alus(100, 100, 90);

        alus.kiihdyta(true);
        alus.liiku();
        alus.liiku();
        System.out.println(alus.getNopeus());
        assertEquals(0, alus.getNopeus(), 0.01);

    }

    @Test
    public void kiihdytysTest2() {
        Alus alus = new Alus(100, 100, 90);

        for (int i = 0; i < 10; i++) {
            alus.kiihdyta(false);
        }

        alus.liiku();

        assertEquals(100, alus.getX());
        assertEquals(105, alus.getY());

    }

    @Test
    public void kiihdytysTest3() {
        Alus alus = new Alus(100, 100, 45);

        for (int i = 0; i < 10; i++) {
            alus.kiihdyta(true);
        }

        alus.liiku();

        assertEquals(100 + 5 * Math.cos(Math.toRadians(45)), alus.getX(), 1);
        assertEquals(100 - 5 * Math.sin(Math.toRadians(45)), alus.getY(), 1);

    }

    @Test
    public void polygoniTest() {
        Alus alus = new Alus(100, 100, 90);
        alus.laskeAlusPolygoni();
        Polygon p = alus.getAlusPolygoni();
        System.out.println(Arrays.toString(p.xpoints));
        System.out.println(Arrays.toString(p.ypoints));
        assertEquals(100, p.xpoints[0]);
        assertEquals(70, p.ypoints[0]);

        assertEquals(85, p.xpoints[1]);
        assertEquals(126, p.ypoints[1]);

        assertEquals(115, p.xpoints[2]);
        assertEquals(126, p.ypoints[2]);

    }

    @Test
    public void suunta1Test() {
        Alus alus = new Alus(100, 100, 359);
        alus.kaanna(1);
        assertEquals(4, alus.getSuunta(), 0.01);
    }

    @Test
    public void suunta1Test2() {
        Alus alus = new Alus(100, 100, 2);
        alus.kaanna(-1);
        assertEquals(357, alus.getSuunta(), 0.01);
    }

    @Test
    public void ammuntaTest1() {
        Alus alus = new Alus(100, 100, 90);
        
        assertEquals(true, alus.ammu()!=null);

    }

    @Test
    public void ammuntaTest2() {
        Alus alus = new Alus(100, 100, 90);
        assertEquals(true, alus.ammu()!=null);
        assertEquals(false, alus.ammu()!=null);

    }

}
