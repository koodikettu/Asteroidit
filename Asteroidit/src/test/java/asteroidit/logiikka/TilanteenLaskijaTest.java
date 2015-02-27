/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.logiikka;

import asteroidit.domain.Ammus;
import asteroidit.domain.Asteroidi;
import asteroidit.peli.Asteroidipeli;
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
public class TilanteenLaskijaTest {

    Asteroidipeli peli;

    public TilanteenLaskijaTest() {
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
    public void kiihdytysTest1() {
        peli.getTilanteenLaskija().ylaNuoliTila(true);
        peli.getTilanteenLaskija().aluksenHallinta();

        assertEquals(true, peli.getAlus().getNopeus() > 0);
    }

    @Test
    public void kaantoTest1() {
        peli.getTilanteenLaskija().oikeaNuoliTila(true);
        peli.getTilanteenLaskija().aluksenHallinta();

        assertEquals(85, peli.getAlus().getSuunta(), 0.01);
    }

    @Test
    public void vasenNuoliTest() {
        peli.getTilanteenLaskija().vasenNuoliTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[0]);
        peli.getTilanteenLaskija().vasenNuoliTila(true);
        assertEquals(true, peli.getTilanteenLaskija().nappaintenTila()[0]);
        peli.getTilanteenLaskija().aluksenHallinta();
        assertEquals(95, peli.getAlus().getSuunta(), 0.01);
        peli.getTilanteenLaskija().vasenNuoliTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[0]);
    }

    @Test
    public void oikeaNuoliTest() {
        peli.getTilanteenLaskija().oikeaNuoliTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[1]);
        peli.getTilanteenLaskija().oikeaNuoliTila(true);
        assertEquals(true, peli.getTilanteenLaskija().nappaintenTila()[1]);
        peli.getTilanteenLaskija().aluksenHallinta();
        assertEquals(85, peli.getAlus().getSuunta(), 0.01);
        peli.getTilanteenLaskija().oikeaNuoliTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[1]);
    }

    @Test
    public void ylaNuoliTest() {
        peli.getTilanteenLaskija().ylaNuoliTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[2]);
        peli.getTilanteenLaskija().ylaNuoliTila(true);
        assertEquals(true, peli.getTilanteenLaskija().nappaintenTila()[2]);
        peli.getTilanteenLaskija().aluksenHallinta();
        assertEquals(true, peli.getAlus().getNopeus() > 0  && peli.getAlus().getKulkusuunta()==90);
        peli.getTilanteenLaskija().ylaNuoliTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[2]);
    }
    
    @Test
    public void alaNuoliTest() {
        peli.getTilanteenLaskija().alaNuoliTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[3]);
        peli.getTilanteenLaskija().alaNuoliTila(true);
        assertEquals(true, peli.getTilanteenLaskija().nappaintenTila()[3]);
        peli.getTilanteenLaskija().aluksenHallinta();
        assertEquals(true, peli.getAlus().getNopeus() > 0);
        assertEquals(270, peli.getAlus().getKulkusuunta(),0.01);
        peli.getTilanteenLaskija().alaNuoliTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[3]);
    }

    @Test
    public void valilyontiTest() {
        peli.getTilanteenLaskija().valilyontiTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[4]);
        peli.getTilanteenLaskija().valilyontiTila(true);
        assertEquals(true, peli.getTilanteenLaskija().nappaintenTila()[4]);
        peli.getTilanteenLaskija().valilyontiTila(false);
        assertEquals(false, peli.getTilanteenLaskija().nappaintenTila()[4]);
    }
    
    @Test
    public void laskeTilanneTest1() {
        peli.getAsteroidilista().clear();
        Asteroidi a = new Asteroidi(0,0,0,0, peli.getRandom());
        a.setX(100);
        a.setY(100);
        a.laskePolygoni();
        peli.getAsteroidilista().add(a);
        peli.getAmmuslista().clear();
        Ammus b = new Ammus(100,100,90);
        peli.getAmmuslista().add(b);
        peli.getTilanteenLaskija().laskeTilanne();
        assertEquals(1, peli.getAsteroidilista().size());
        assertEquals(0, peli.getAmmuslista().size());
        
        
    }
}
