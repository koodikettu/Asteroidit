/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.peli;

import asteroidit.domain.Ammus;
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
    public void aloitaAlustaTest1() {
        peli.aloitaAlusta();
        assertEquals(4, peli.getAsteroidilista().size());
    }

    @Test
    public void aloitaAlustaTest2() {

        peli.uusiAmmus(100, 200, 270);
        peli.aloitaAlusta();
        assertEquals(0, peli.getAmmuslista().size());
    }

    @Test
    public void aloitaAlustaTest3() {
        peli.getAlus().setX(333);

        peli.getAlus().setY(333);

        peli.getAlus().setSuunta(333);

        peli.uusiAmmus(100, 200, 270);
        peli.aloitaAlusta();
        assertEquals(peli.getRuudunLeveys() / 2, peli.getAlus().getX());
        assertEquals(peli.getRuudunKorkeus() / 2, peli.getAlus().getY());
    }
    
        @Test
    public void aloitaAlustaTest4() {
        peli.getAlus().setX(333);

        peli.getAlus().setY(333);

        peli.getAlus().setSuunta(333);

        peli.uusiAmmus(100, 200, 270);
        peli.aloitaAlusta();
        assertEquals(90, peli.getAlus().getSuunta(), 0.01);
    }
    
            @Test
    public void aloitaAlustaTest5() {
        peli.uusiAsteroidi();
        assertEquals(5, peli.getAsteroidilista().size());
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
    public void kiihdytysTest1() {
        peli.nappaimistonTila(1, 1, 1, 0, 1);
        peli.aluksenHallinta();

        assertEquals(true, peli.getAlus().getNopeus() > 0);
    }

    @Test
    public void kaantoTest1() {
        peli.nappaimistonTila(-1, 1, -1, -1, -1);
        peli.aluksenHallinta();

        assertEquals(85, peli.getAlus().getSuunta(), 0.01);
    }

    @Test
    public void uusiAsteroidiToimii1() {
        int a = peli.getAsteroidilista().size();
        peli.uusiAsteroidi();
        assertEquals(1, peli.getAsteroidilista().size() - a);
    }

    @Test
    public void uusiAsteroidiToimii2() {
        peli.getAsteroidilista().clear();
        peli.uusiAsteroidi();
        peli.getAsteroidilista().get(0).laskePolygoni();
        int x = peli.getAsteroidilista().get(0).getX();
        int y = peli.getAsteroidilista().get(0).getY();
        assertEquals(false, peli.getTormaystenKasittelija().onRuudulla(x, y));
    }

    @Test
    public void laskeTilanneTest() {
        peli.getAsteroidilista().get(0).setX(100);
        peli.getAsteroidilista().get(0).setY(100);
        peli.getAsteroidilista().get(0).setDx(5);
        peli.getAsteroidilista().get(0).setDy(5);
        peli.laskeTilanne();
        assertEquals(105, peli.getAsteroidilista().get(0).getX());
        assertEquals(105, peli.getAsteroidilista().get(0).getY());

    }

    @Test
    public void laskeTilanneTest2() {
        peli.getAmmuslista().clear();
        Ammus a = new Ammus(50, 50, 0);
        peli.getAmmuslista().add(a);
        peli.laskeTilanne();
        assertEquals(58, peli.getAmmuslista().get(0).getX());
        assertEquals(50, peli.getAmmuslista().get(0).getY());

    }

    @Test
    public void laskeTilanneTest3() {
        peli.getAmmuslista().clear();
        Ammus a = new Ammus(5, 5, 180);
        peli.getAmmuslista().add(a);
        peli.laskeTilanne();
        assertEquals(0, peli.getAmmuslista().size());
        assertEquals(0, peli.getPoistettavatAmmukset().size());

    }

    @Test
    public void laskeTilanneTest4() {
        peli.getAmmuslista().clear();
        peli.getAsteroidilista().clear();
        Ammus a = new Ammus(800, 100, 180);
        peli.getAmmuslista().add(a);

        peli.getAsteroidilista().clear();
        peli.uusiAsteroidi();
        peli.getAsteroidilista().get(0).laskePolygoni();
        peli.getAsteroidilista().get(0).setX(800);
        peli.getAsteroidilista().get(0).setY(100);
        peli.laskeTilanne();
        peli.laskeTilanne();
        assertEquals(0, peli.getAmmuslista().size());
        assertEquals(0, peli.getPoistettavatAmmukset().size());

    }

}
