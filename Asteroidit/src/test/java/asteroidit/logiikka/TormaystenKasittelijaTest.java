/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.logiikka;

import asteroidit.domain.Alus;
import asteroidit.domain.Ammus;
import asteroidit.domain.Ammus;
import asteroidit.domain.Asteroidi;
import asteroidit.domain.Asteroidi;
import asteroidit.logiikka.TormaystenKasittelija;
import asteroidit.peli.Asteroidipeli;
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
public class TormaystenKasittelijaTest {

    Asteroidipeli peli = new Asteroidipeli();
    TormaystenKasittelija kasittelija = new TormaystenKasittelija(peli);
    Kirjanpitaja kirjanpitaja = peli.getKirjanpitaja();

    public TormaystenKasittelijaTest() {
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
    public void onRuudulla1ToimiiPos() {
        Ammus ammus = new Ammus(100, 100, 0);
        assertEquals(true, kasittelija.onRuudulla(ammus));
    }

    @Test
    public void onRuudulla1ToimiiNeg() {
        Ammus ammus = new Ammus(-4, 100, 0);
        assertEquals(false, kasittelija.onRuudulla(ammus));
    }
    
        @Test
    public void onRuudulla2ToimiiPos() {

        assertEquals(true, kasittelija.onRuudulla(5,600));
    }

    @Test
    public void onRuudulla2ToimiiNeg() {

        assertEquals(false, kasittelija.onRuudulla(1100,50));
    }

    @Test
    public void reunanYlitysKasittelijaVasemmaltaYli() {
        Asteroidi asteroidi = new Asteroidi(0, 0, -30, 0, peli.getRandom());
        asteroidi.liiku();
        kasittelija.hoidaReunanYlitykset(asteroidi, 25);
        assertEquals(1025, asteroidi.getX());
    }

    @Test
    public void reunanYlitysKasittelijaAlhaalta() {
        Asteroidi asteroidi = new Asteroidi(500, 600, 0, 30, peli.getRandom());
        asteroidi.liiku();
        kasittelija.hoidaReunanYlitykset(asteroidi, 25);
        assertEquals(-25, asteroidi.getY());
    }

    @Test
    public void tutkiTormaysTesti1() {
        int pisteet = peli.getKirjanpitaja().getPisteet();
        Asteroidi asteroidi = new Asteroidi(500, 600, 0, 30, peli.getRandom());
        asteroidi.laskePolygoni();
        Ammus ammus = new Ammus(500, 600, 5);
        assertEquals(true, asteroidi.getAsteroidiPolygoni().contains(ammus.getX(), ammus.getY()));


    }

    @Test
    public void tutkiTormaysKirjanpito() {
        int pisteet = peli.getKirjanpitaja().getPisteet();
        Asteroidi asteroidi = new Asteroidi(500, 600, 0, 30, peli.getRandom());
        asteroidi.laskePolygoni();
        Ammus ammus = new Ammus(500, 600, 5);
        peli.getAsteroidilista().add(asteroidi);
        peli.getAmmuslista().add(ammus);
        kasittelija.tutkiTormaykset(peli.getAmmuslista(), peli.getAsteroidilista());
        assertEquals(10, peli.getKirjanpitaja().getPisteet() - pisteet);
//       assertEquals(true, peli.getAmmuslista().isEmpty());
//       assertEquals(true, peli.getAsteroidilista().isEmpty());
    }

    @Test
    public void tutkiTormaysListojenYllapito() {
        int pisteet = peli.getKirjanpitaja().getPisteet();
        Asteroidi asteroidi = new Asteroidi(500, 600, 0, 30, peli.getRandom());
        asteroidi.laskePolygoni();
        Ammus ammus = new Ammus(500, 600, 5);
        peli.getAsteroidilista().clear();
        peli.getAmmuslista().clear();
        peli.getAsteroidilista().add(asteroidi);
        peli.getAmmuslista().add(ammus);
        kasittelija.tutkiTormaykset(peli.getAmmuslista(), peli.getAsteroidilista());

        assertEquals(true, peli.getAmmuslista().isEmpty());
        assertEquals(true, peli.getTormaystenKasittelija().getPoistettavat().isEmpty());
        assertEquals(false, peli.getAsteroidilista().contains(asteroidi));
    }

    @Test
    public void tutkiTormaysTestEiTormaysta() {

        int a = kasittelija.tutkiTormays(peli.getAlus(), new Asteroidi(15, 15, 30, 30, peli.getRandom()));
        assertEquals(0, a);

    }

    @Test
    public void tutkiTormaysTestTormays1() {
        int x = peli.getRuudunLeveys() / 2;
        int y = peli.getRuudunKorkeus() / 2;
        Asteroidi asteroidi = new Asteroidi(0, 0, 0, 0, peli.getRandom());
        asteroidi.alusta(peli.getRandom(), 1000, 600, 25, 0);
        asteroidi.setX(x);
        asteroidi.setY(y);
        asteroidi.laskePolygoni();
        int a = kasittelija.tutkiTormays(peli.getAlus(), asteroidi);
        System.out.println(a);
        assertEquals(true, a > 0);

    }
    
        @Test
    public void tutkiAluksenTormayksetsTest() {
        int x = peli.getRuudunLeveys() / 2;
        int y = peli.getRuudunKorkeus() / 2;
        Asteroidi asteroidi = new Asteroidi(0, 0, 0, 0, peli.getRandom());
        asteroidi.alusta(peli.getRandom(), 1000, 600, 25, 0);
        asteroidi.setX(x);
        asteroidi.setY(y);
        asteroidi.laskePolygoni();
        peli.getAsteroidilista().clear();
        peli.getAsteroidilista().add(asteroidi);
        boolean a = kasittelija.tutkiAluksenTormaykset(peli.getAsteroidilista());
        assertEquals(true, a);
        assertEquals(-1, peli.getKirjanpitaja().getTila());

    }

    @Test
    public void asteroidinPoistoToimiiOikein() {

        Asteroidi a = peli.getAsteroidilista().get(0);
        kasittelija.getPoistettavat().add(a);
        kasittelija.poistaPoistettavat(peli.getAmmuslista(), peli.getAsteroidilista());
        assertEquals(false, kasittelija.getPoistettavat().contains(a));
    }

    @Test
    public void asteroidienLisaysTasonNoustessaToimii() {
        kirjanpitaja.reset();
        kirjanpitaja.kasvataPisteita(190);

        peli.getAsteroidilista().get(0).setX(800);
        peli.getAsteroidilista().get(0).setY(100);
        peli.getAsteroidilista().get(0).laskePolygoni();
        Ammus ammus = new Ammus(800, 100, 90);
        peli.getAmmuslista().add(ammus);
        System.out.println(kirjanpitaja.getPisteet());
        int a = kasittelija.tutkiTormaykset(peli.getAmmuslista(), peli.getAsteroidilista());
        System.out.println(kirjanpitaja.getPisteet());
        assertEquals(1, a);

    }
}
