/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.logiikka;

import asteroidit.domain.Asteroidi;
import asteroidit.logiikka.Kirjanpitaja;
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
public class KirjanpitajaTest {

    public KirjanpitajaTest() {
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
    public void kirjanpPalauttaaTilanOikein() {
        Asteroidipeli peli = new Asteroidipeli();
        Kirjanpitaja kp = new Kirjanpitaja(1, peli);
        assertEquals(1, kp.getTila());

    }

    @Test
    public void kirjanpPalauttaaPisteetOikein() {
        Asteroidipeli peli = new Asteroidipeli();
        Kirjanpitaja kp = new Kirjanpitaja(1, peli);
        assertEquals(0, kp.getPisteet());
        kp.kasvataPisteita(10);
        assertEquals(10, kp.getPisteet());

    }
    
        @Test
    public void nopeudenKasvatusToimii() {
        Asteroidipeli peli = new Asteroidipeli();
        Kirjanpitaja kp = new Kirjanpitaja(1, peli);
        assertEquals(0, kp.getPisteet());
        int a = peli.getAsteroidienNopeus();
        kp.kasvataPisteita(200);
        assertEquals(true, peli.getAsteroidienNopeus()-a>0);

    }
    
    
}
