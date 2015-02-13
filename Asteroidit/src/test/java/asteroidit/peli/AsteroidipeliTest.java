/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.peli;

import asteroidit.gui.Kayttoliittyma;
import asteroidit.peli.Asteroidipeli;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
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
public class AsteroidipeliTest {

    Asteroidipeli peli;

    public AsteroidipeliTest() {
        peli = new Asteroidipeli();
        
//        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(peli);

//        SwingUtilities.invokeLater(kayttoliittyma);
//        System.out.println("Käyttöliittymän luominen.");
//        while (kayttoliittyma.getPiirtoalusta() == null) {
//            
//        }
//
//        peli.setPiirtoalusta(kayttoliittyma.getPiirtoalusta());
//        peli.setYlapaneeli(kayttoliittyma.getYlapaneeli());
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
    public void aloitaAlustaToimii() {
        peli.aloitaAlusta();
        assertEquals(4, peli.getAsteroidilista().size());
    }

    @Test
    public void uusiAmmusToimii() {
        int a = peli.getAmmuslista().size();
        peli.uusiAmmus(100, 200, 90);
        assertEquals(1, peli.getAmmuslista().size() - a);
    }

    @Test
    public void nappaimistoTest() {
        peli.nappaimistonTila(1, 1, 1, 0, 1);
        peli.aluksenHallinta();

        assertEquals(true, peli.getAlus().getNopeus() > 0);
    }

    @Test
    public void uusiAsteroidiToimii() {
        int a = peli.getAsteroidilista().size();
        peli.uusiAsteroidi();
        assertEquals(1, peli.getAsteroidilista().size() - a);
    }

//    @Test
//    public void luuppiTest() {
//        int a = peli.getAsteroidilista().get(0).getX();
//        peli.luuppi();
//        peli.luuppi();
//        assertEquals(true, peli.getAsteroidilista().get(0).getX()!=a);
//
//    }

}
