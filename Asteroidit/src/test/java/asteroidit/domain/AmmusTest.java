/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidit.domain;

import asteroidit.domain.Ammus;
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
public class AmmusTest {

    public AmmusTest() {
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
    public void vaakaliikeTest() {
        Ammus ammus = new Ammus(100, 100, 0);
        ammus.liiku();
        assertEquals(108, ammus.getX());
        assertEquals(100, ammus.getY());
    }

    @Test
    public void pystyliikeTest() {
        Ammus ammus = new Ammus(100, 100, 270);
        ammus.liiku();
        assertEquals(100, ammus.getX());
        assertEquals(108, ammus.getY());
    }

    @Test
    public void polygoniTest() {
        Ammus ammus = new Ammus(100, 100, 270);
        Polygon p = ammus.getAmmusPolygoni();
        assertEquals(99, p.xpoints[0]);
        assertEquals(99, p.ypoints[0]);

        assertEquals(101, p.xpoints[1]);
        assertEquals(99, p.ypoints[1]);

        assertEquals(101, p.xpoints[2]);
        assertEquals(101, p.ypoints[2]);

        assertEquals(99, p.xpoints[3]);
        assertEquals(101, p.ypoints[3]);
    }
}
