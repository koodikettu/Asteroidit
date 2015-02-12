/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import asteroidit.domain.Alus;
import asteroidit.domain.Ammus;
import asteroidit.domain.Asteroidi;
import asteroidit.logiikka.TormaystenKasittelija;
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
public class TormaystenKasittelijaTest {

    Asteroidipeli peli;
    TormaystenKasittelija kasittelija;

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
        peli = new Asteroidipeli();
        kasittelija = new TormaystenKasittelija(peli);
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
    public void onRuudullaToimiiPos() {
        Ammus ammus = new Ammus(100, 100, 0);
        assertEquals(true, kasittelija.onRuudulla(ammus));
    }

    @Test
    public void onRuudullaToimiiNeg() {
        Ammus ammus = new Ammus(-4, 100, 0);
        assertEquals(false, kasittelija.onRuudulla(ammus));
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
    public void tutkiTormaysTesti() {
        int pisteet = peli.getKirjanpitaja().getPisteet();
        Asteroidi asteroidi = new Asteroidi(500, 600, 0, 30, peli.getRandom());
        asteroidi.laskePolygoni();
        Ammus ammus = new Ammus(500, 600, 5);
        assertEquals(true, asteroidi.getAsteroidiPolygoni().contains(ammus.getX(), ammus.getY()));
        peli.getAsteroidilista().add(asteroidi);
        peli.getAmmuslista().add(ammus);
        kasittelija.tutkiTormaykset(peli.getAmmuslista(), peli.getAsteroidilista());
//       assertEquals(10,peli.getKirjanpitaja().getPisteet()-pisteet);
//       assertEquals(true, peli.getAmmuslista().isEmpty());
//       assertEquals(true, peli.getAsteroidilista().isEmpty());
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
        assertEquals(false, peli.getAsteroidilista().contains(asteroidi));
    }

    @Test
    public void tutkiAluksenTormayksetTest() {
        peli.getAsteroidilista().clear();
        peli.uusiAsteroidi();
        peli.getAlus().setX(100);
        peli.getAlus().setY(100);
        peli.getAlus().laskeAlusPolygoni();
        peli.getAsteroidilista().get(0).setX(100);
        peli.getAsteroidilista().get(0).setY(90);
        System.out.println(peli.getAlus().getAlusPolygoni().xpoints[0]);
        System.out.println(peli.getAsteroidilista().get(0).getX());
        System.out.println(peli.getAsteroidilista().size());

        kasittelija.tutkiAluksenTormaykset(peli.getAsteroidilista());
        assertEquals(1, peli.getKirjanpitaja().getTila());

    }
}
