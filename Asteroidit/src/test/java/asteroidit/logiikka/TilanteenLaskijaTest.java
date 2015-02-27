/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.logiikka;

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
        peli.getTilanteenLaskija().nappaimistonTila(1, 1, 1, 0, 1);
        peli.getTilanteenLaskija().aluksenHallinta();

        assertEquals(true, peli.getAlus().getNopeus() > 0);
    }

    @Test
    public void kaantoTest1() {
        peli.getTilanteenLaskija().nappaimistonTila(-1, 1, -1, -1, -1);
        peli.getTilanteenLaskija().aluksenHallinta();

        assertEquals(85, peli.getAlus().getSuunta(), 0.01);
    }
}
